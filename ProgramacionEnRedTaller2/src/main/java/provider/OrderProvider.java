package provider;

import db.DbConnection;
import entity.Order;
import entity.Product;
import entity.User;
import model.OrderChange;
import model.OrderInformationAnswer;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;


public class OrderProvider {

    public void addOrder(Order order) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        //Instant instant = Instant.ofEpochSecond(order.getCreationDate());

        String sql="INSERT INTO ordersA00369267 (creationDate, userID,payed,payDate) VALUES ($CREATIONDATE,'$USERID',$PAYED,$PAYDATE)";
        sql= sql.replace("$CREATIONDATE", Long.toString(new Date().getTime())); //Hacer cambio a time unix

        UserProvider provider = new UserProvider();
        int id = provider.findUserId(order.getUserID());

        if(id==0){
            conn.close();
            return;
        }else {
            sql = sql.replace("$USERID", Integer.toString(id));
            sql = sql.replace("$PAYED", "-1");
            sql = sql.replace("$PAYDATE", "0");
            conn.runQuerry(sql);
            conn.close();
        }
    }


    public void addProductToOrder(OrderChange order) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        //int numberOrder = Integer.parseInt(parts[0]);
        //String productName = ;
        //int quantity = Integer.parseInt(parts[2]);

        ProductProvider provider = new ProductProvider();

        String sql="INSERT INTO orders_productsA00369267 (orderId, productId,quantity) VALUES ('$ORDERID','$PRODUCTID','$QUANTITY')";
        sql= sql.replace("$ORDERID", Integer.toString(order.getOrderId())); //Hacer cambio a time unix
        sql = sql.replace("$QUANTITY", Integer.toString(order.getQuantity()));
        int id = provider.findProductId(order.getProductName());

        if(id==0){
            conn.close();
            return;
        }else {
            sql = sql.replace("$PRODUCTID", Integer.toString(id));
            conn.runQuerry(sql);
            conn.close();
        }




    }

    public void deleteProductFromOrder(OrderChange parts) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        //int numberOrder = Integer.parseInt(parts[0]);
        //String productName = parts[1];
        //int quantity = Integer.parseInt(parts[2]);

        ProductProvider provider = new ProductProvider();

        String sql="DELETE FROM orders_productsA00369267 WHERE orderId = $ORRDERID AND productId = $PRODUCTID";
        sql= sql.replace("$ORRDERID",  Integer.toString(parts.getOrderId())); //Hacer cambio a time unix
        int id = provider.findProductId(parts.getProductName());

        if(id==0){
            conn.close();
            return;
        }else{
            sql = sql.replace("$PRODUCTID", Integer.toString(id));
            conn.runQuerry(sql);
            conn.close();
        }




    }

    public void removeProductFromOrder(OrderChange order) throws SQLException, ClassNotFoundException {


        DbConnection conn = new DbConnection();

        //int numberOrder = Integer.parseInt(parts[0]);
        //String productName = parts[1];
        //int quantity = Integer.parseInt(parts[2]);

        ProductProvider provider = new ProductProvider();

        String sql="UPDATE orders_productsA00369267 SET quantity = $QUANTITY WHERE orderId = $ORDERID AND productId = $PRODUCTID";
        sql= sql.replace("$ORDERID",  Integer.toString(order.getOrderId())); //Hacer cambio a time unix
        sql = sql.replace("$QUANTITY",    Integer.toString(order.getQuantity()));
        int id = provider.findProductId(order.getProductName());

        if(id==0){
            conn.close();
            return;
        }else {

            sql = sql.replace("$PRODUCTID", Integer.toString(id));
            System.out.println(sql);
            conn.runQuerry(sql);
            conn.close();
        }


    }

    public Order updateStatus(String info) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String sql="UPDATE ordersA00369267 SET payed = 1 WHERE id = $ID";

        long time = System.currentTimeMillis();
        //Instant instant = Instant.ofEpochSecond(time);
        //

        sql = sql.replace("$ID", info);

        conn.runQuerry(sql);


        sql = "UPDATE ordersA00369267 SET payDate = '$PAY' WHERE id = $ID";

        sql = sql.replace("$ID", info);
        //sql = sql.replace("$PAY", instant.toString());
        sql = sql.replace("$PAY", Long.toString(time));
        conn.runQuerry(sql);

        sql = "SELECT ordersA00369267.id,ordersA00369267.creationDate, ordersA00369267.payed, ordersA00369267.payDate, ordersA00369267.userID FROM ordersA00369267 WHERE id = '$ID' AND payDate = $PAYDATE ";
        sql = sql.replace("$ID", info);
        sql = sql.replace("$PAYDATE", Long.toString(time));

        ArrayList<Order> orders = new ArrayList<>();

        ResultSet results =  conn.getData(sql);

        while(results.next()){

            int id = results.getInt("id");

            long creationDate = results.getLong("creationDate");

            int payed = results.getInt("payed");

            long payDate = results.getLong("payDate");

            int userId = results.getInt("userID");

            Order order = new Order(id,creationDate,payed,payDate,userId);
            orders.add(order);
        }


        conn.close();
        return orders.get(0);

    }

    public OrderInformationAnswer getInfoFromOrder(String info) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String sql = "SELECT productsA00369267.id, productsA00369267.name, productsA00369267.price, ordersA00369267.id, orders_productsA00369267.quantity FROM (productsA00369267 INNER JOIN orders_productsA00369267 " +
                "ON productsA00369267.id = orders_productsA00369267.productId)INNER JOIN ordersA00369267 ON orders_productsA00369267.orderId = ordersA00369267.id WHERE ordersA00369267.id = $ORDERID";
        sql = sql.replace("$ORDERID", info);
        ArrayList<Product> products = new ArrayList<>();

        ResultSet results =  conn.getData(sql);

        while(results.next()){

            int id = results.getInt("id");

            String name = results.getString("name");

            int price = results.getInt("price");

            int quantity = results.getInt("quantity");

            Product product = new Product(id,name,quantity,price);
            products.add(product);
        }

        String data = "";
        int productAmount = 0;
        int totalPrice = 0;
        for(int i=0; i < products.size();i++){

            data +=  "\n " + (i+1) + "Name: " + products.get(i).getName() + ", Price: " + products.get(i).getPrice() + ", Quantity: " + products.get(i).getQuantity();
            productAmount+= products.get(i).getQuantity();
            totalPrice+= (products.get(i).getQuantity()*products.get(i).getPrice());
        }
        OrderInformationAnswer answer = new OrderInformationAnswer(data,productAmount,totalPrice);


        conn.close();
        return answer;




    }

    public void removeProductQuantityFromOrder(OrderChange order) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        ProductProvider provider = new ProductProvider();

        String sql = "SELECT * FROM orders_productsA00369267 WHERE orderId = $ORDERID AND productId = $PRODUCTID";
        sql = sql.replace("$ORDERID",  Integer.toString(order.getOrderId()));
        int productId = provider.findProductId(order.getProductName());
        sql = sql.replace("$PRODUCTID",  Integer.toString(productId));

        Product product = new Product();
        ResultSet results =  conn.getData(sql);
        while(results.next()){


            int quantity = results.getInt("quantity");

            product = new Product(214124,"1fasdfas",quantity,31231);
            //products.add(product);
        }
        if((product.getQuantity()-order.getQuantity())>0) {

            sql="UPDATE orders_productsA00369267 SET quantity = $QUANTITY WHERE orderId = $ORDERID AND productId = $PRODUCTID";
            sql= sql.replace("$ORDERID",  Integer.toString(order.getOrderId())); //Hacer cambio a time unix
            sql = sql.replace("$QUANTITY",    Integer.toString(product.getQuantity()-order.getQuantity()));
            sql = sql.replace("$PRODUCTID", Integer.toString(productId));

            System.out.println(sql);
            conn.runQuerry(sql);
            conn.close();

        }else{


            conn.close();

        }


        }


}
