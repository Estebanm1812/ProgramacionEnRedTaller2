package provider;

import db.DbConnection;
import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductProvider {

    public void addProduct(Product product) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String sql="INSERT INTO productsA00369267 (name,price) VALUES ('$NAME','$PRICE')";
        sql= sql.replace("$NAME",product.getName());
        //sql= sql.replace("$QUANTITY",Integer.toString(product.getQuantity()));
        sql = sql.replace("$PRICE", Double.toString(product.getPrice()));
        conn.runQuerry(sql);
        conn.close();
    }

    public int findProductId(String name) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();
        String sql="SELECT id FROM productsA00369267 WHERE name = '$NAME'";
        sql = sql.replace("$NAME", name);
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

}
