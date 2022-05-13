package services;

import entity.Order;
import entity.User;
import model.Message;
import model.OrderChange;
import model.OrderInformationAnswer;
import provider.OrderProvider;
import provider.UserProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("order")
public class OrderServices {


    @POST
    @Path("addOrder")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addOrder(Order order){

        System.out.println(order.getUserID());
        OrderProvider provider = new OrderProvider();
        try {
            provider.addOrder(order);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }


    }
    //La info se tiene que pasar en el fortamo de "Identificador de la orden"//"Nombre Del producto"//"Cantidad del producto"
    @PUT
    @Path("addProductToOrder")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addProductToOrder(OrderChange order){

        OrderProvider provider = new OrderProvider();
        try {
            provider.addProductToOrder(order);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }



    }
    @DELETE
    @Path("deleteProductFromOrder/{info}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response deleteProductFromOrder(@PathParam("info") String info){

        String parts[] = info.split("-");
        OrderProvider provider = new OrderProvider();
        try {
            provider.deleteProductFromOrder(parts);
            return Response.status(200).entity(parts).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }



    }

    @PUT
    @Path("removeProductFromOrder")
    @Produces("application/json")
    @Consumes("application/json")
    public Response removeProductFromOrder(OrderChange order){


        OrderProvider provider = new OrderProvider();
        try {
            provider.removeProductFromOrder(order);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }

    @PUT
    @Path("updatePayStatus/{info}")
    @Produces("application/json")
    public Response updateStatus(@PathParam("info") String info){

        OrderProvider provider = new OrderProvider();
        try {
            Order order = provider.updateStatus(info);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }


    @GET
    @Path("getInfoOrder/{info}")
    @Produces("application/json")
    public Response getInfoOrder(@PathParam("info") String info){

        OrderProvider provider = new OrderProvider();
        try {
            OrderInformationAnswer data = provider.getInfoFromOrder(info);
            return Response.status(200).entity(data).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }


    }






}
