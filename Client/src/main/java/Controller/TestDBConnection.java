package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TestDBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/fileserver_db";
    private static final String username = "benjamin";
    private static final String password = "Ben@123";

     public static Connection getConnection() {
         try {
             return DriverManager.getConnection(url, username, password);
         }catch(SQLException e) {
             System.out.println(e.getMessage());
         }
         return null;
     }
}
