package DatabaseController;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBQueryExcecutor {
    static Connection connection= DatabaseConnection.getConnection();

    public static  ResultSet executeQuery(String query, List<Object> sqlParameters, String type){

        ResultSet rs;


        if(connection != null){
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                if(sqlParameters != null){
                    setParameters(statement,sqlParameters);
                }


                if(type.equals("get")){
                    rs = statement.executeQuery();
                    return rs;
                }else if(type.equals("update")){
                    int res = statement.executeUpdate();
                    return null;
                }
//
//
//
//
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("Execution Failed");
//
            }
        }

        return null;
    }

//    Helper methode for executeQuery Methode
    public static void setParameters(PreparedStatement statement,List<Object> parameters) throws SQLException {
        for(int i = 0; i <parameters.size();i++){

            if(parameters.get(i) instanceof String){

                statement.setString(i+1,parameters.get(i).toString());


            } else if (parameters.get(i) instanceof Integer) {
                statement.setInt(i,(Integer) parameters.get(i));

            }else if(parameters.get(i) instanceof byte[]){

                statement.setBytes(i,(byte[]) parameters.get(i));

            }
        }
    }
}
