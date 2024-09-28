package DatabaseController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQueryExcecutor {
    static Connection connection= DatabaseConnection.getConnection();
    public static  ResultSet executeQuery(String query, String type){

        ResultSet rs;

        if(connection != null){
            try {
                Statement statement = connection.createStatement();

                String q = query;

                if(type.equals("get")){
                    rs = statement.executeQuery(q);
                    return rs;
                }else if(type.equals("update")){
                    int res = statement.executeUpdate(q);
                    return null;
                }




            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("Execution Failed");

            }
        }

        return null;
    }
}
