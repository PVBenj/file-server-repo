package DatabaseController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/FileServer";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL,USER,PASSWORD);

            System.out.println("Connection to the data base success");

        }catch (ClassNotFoundException e){
            System.out.println("JDCB Driver not found");
            e.printStackTrace();

        }catch (SQLException e){
            System.out.println("Failed to connect to the DB");
        }

        return connection;


    }

    public static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
                System.out.println("Connection Successfully closed");
            }catch (SQLException e){
                System.out.println("Error Closing the connection");
            }
        }
    }



}
