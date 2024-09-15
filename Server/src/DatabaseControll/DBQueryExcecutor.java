package DatabaseControll;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQueryExcecutor {

    public static  ResultSet executeQuery(String query){
        Connection connection= DatabaseConnection.getConnection();
        ResultSet rs = null;
        if(connection != null){
            try {
                Statement statement = connection.createStatement();

                String q = query;

                rs = statement.executeQuery(q);



            }catch (SQLException e){
                System.out.println("Execution Failed");
            }
        }
        return rs;
    }
}
