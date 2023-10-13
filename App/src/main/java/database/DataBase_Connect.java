package database;

import java.sql.*;

public class DataBase_Connect {
    static Connection connection;
    public void Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String user_name="root";
        String user_password="root";
        String url="jdbc:mysql://localhost:3308/users";

        try {
            connection = DriverManager.getConnection(url, user_name, user_password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
