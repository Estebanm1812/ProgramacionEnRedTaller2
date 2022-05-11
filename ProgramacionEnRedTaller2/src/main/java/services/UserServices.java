package services;

import entity.Order;
import entity.User;
import model.Message;
import provider.UserProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.ArrayList;

@Path("user")
public class UserServices {

    @POST
    @Path("addUser")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addUser(User user){

        UserProvider provider = new UserProvider();
        try {
            provider.addUser(user);
            return Response.status(200).entity(user).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }


    }

    @POST
    @Path("getRecords/{nationalId}")
    @Produces("application/json")
    public Response getRecords(@PathParam("nationalId") String nationalId){

        UserProvider provider = new UserProvider();
        try {
            ArrayList<Order> records = provider.getRecords(nationalId);
            return Response.status(200).entity(records).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }


    }

    @POST
    @Path("getAllRecords/{nationalId}")
    @Produces("application/json")
    public Response getAllRecords(@PathParam("nationalId") String nationalId){

        UserProvider provider = new UserProvider();
        try {
            ArrayList<Order> records = provider.getAllRecords(nationalId);
            return Response.status(200).entity(records).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }


    }

}
