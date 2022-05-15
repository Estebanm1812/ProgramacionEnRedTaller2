package provider;

import db.DbConnection;
import entity.Order;
import entity.Product;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserProvider {

    public void addUser(User user) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String sql="INSERT INTO usersA00369267 (nombre, nationalId) VALUES ('$NAME','$USERID')";
        System.out.println(user.getNombre());
        sql= sql.replace("$NAME",user.getNombre());
        sql= sql.replace("$USERID",user.getNationalId());
        conn.runQuerry(sql);
        conn.close();

    }

    public int findUserId(int natId) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();
        String sql="SELECT id FROM usersA00369267 WHERE nationalId = '$NATIONALID'";
        sql = sql.replace("$NATIONALID", Integer.toString(natId));
        ResultSet results =  conn.getData(sql);
        int foundId = 0;
        //conn.runQuerry(sql);
        while(results.next()) {

            //int id = results.getInt("id");
            foundId = results.getInt("id");
        }
        conn.close();
        return foundId;

    }


    public ArrayList<Order> getRecords(String nationalId) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        int id = findUserId(Integer.parseInt(nationalId));

        System.out.println(id);
        String sql = "SELECT ordersA00369267.id, ordersA00369267.creationDate, ordersA00369267.payed, ordersA00369267.payDate FROM ordersA00369267 WHERE ordersA00369267.userID = $ORDERID AND ordersA00369267.payed = 1" ;

        sql = sql.replace("$ORDERID",Integer.toString(id));

        ArrayList<Order> orders = new ArrayList<>();

        System.out.println(sql);
        ResultSet results =  conn.getData(sql);

        while(results.next()){

            int id2 = results.getInt("id");

            String creationDate  = results.getString("creationDate");

            int payed = results.getInt("payed");


            String payDate = results.getString("payDate");

            if(payDate==null){
                payDate = "0";
            }

            Order order = new Order(id2,Long.valueOf(creationDate),payed,Long.valueOf(payDate),id);
            orders.add(order);
        }

        /*
        String data = " ";

        for(int i=0; i < orders.size();i++){

            data +=  "\n " + (i+1) + " id " + orders.get(i).getId() + ", Creation Date: " + orders.get(i).getCreationDate();
            if(orders.get(i).getPayed()==0){

                data+= " State: No Pay Yet";
            }else{
                data+= "State Already Payed , Pay Date: " + orders.get(i).getPayDate();

            }


        }
        */
        return orders;
    }

    public ArrayList<Order> getAllRecords(String nationalId) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        int id = findUserId(Integer.parseInt(nationalId));

        System.out.println(id);
        String sql = "SELECT ordersA00369267.id, ordersA00369267.creationDate, ordersA00369267.payed, ordersA00369267.payDate FROM ordersA00369267 WHERE ordersA00369267.userID = $ORDERID" ;

        sql = sql.replace("$ORDERID",Integer.toString(id));

        ArrayList<Order> orders = new ArrayList<>();

        System.out.println(sql);
        ResultSet results =  conn.getData(sql);

        while(results.next()){

            int id2 = results.getInt("id");

            String creationDate  = results.getString("creationDate");

            int payed = results.getInt("payed");


            String payDate = results.getString("payDate");

            if(payDate==null){
                payDate = "0";
            }

            Order order = new Order(id2,Long.valueOf(creationDate),payed,Long.valueOf(payDate),id);
            orders.add(order);
        }

        return orders;

    }
}




