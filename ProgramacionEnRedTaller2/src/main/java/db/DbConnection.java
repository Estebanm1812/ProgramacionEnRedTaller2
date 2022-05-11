package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {

    private Connection connection;

    public DbConnection( ) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_11","P09728_1_11","ZCSaQGZU");

    }

    public ResultSet getData(String sql) throws SQLException {

        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        return resultSet;
    }

    public void runQuerry(String sql ) throws SQLException {

        connection.createStatement().execute(sql);

    }

    public void close() throws SQLException {
        connection.close();
    }

}
