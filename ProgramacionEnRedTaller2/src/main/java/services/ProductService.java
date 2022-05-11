package services;

import entity.Product;
import model.Message;
import provider.ProductProvider;
import provider.UserProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("product")
public class ProductService {

    @POST
    @Path("addProduct")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addProduct(Product product){

        System.out.println(product.getName() + " Aqui tendria que mostrar algo");
        ProductProvider provider = new ProductProvider();
        try {
            provider.addProduct(product);
            return Response.status(200).entity(product).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }
}
