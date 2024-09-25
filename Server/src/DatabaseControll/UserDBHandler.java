package DatabaseControll;

import java.sql.ResultSet;

public class UserDBHandler {

    public static ResultSet authenticateUser(String username, String password){
        String qu = String.format("SELECT * FROM Login where username = '%s' and password = '%s'",username,password);
        ResultSet rs = DBQueryExcecutor.executeQuery(qu,"get");

        return rs;
    }
    public static void addUser(String userID,String groupID,String username,String password,String mobile,String firstname, String role){
        String qu = String.format("Insert into Login (userID,groupID,username,password,mobile,firstname,role) values (%s,%s,'%s','%s','%s','%s','%s')",
                userID,groupID,username,password,mobile,firstname,role);

        DBQueryExcecutor.executeQuery(qu,"update");
    }

    public static ResultSet getAllUsers(){
        ResultSet rs = DBQueryExcecutor.executeQuery("SELECT * FROM Login","get");
        return rs;
    }
}
