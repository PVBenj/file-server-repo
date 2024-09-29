package DatabaseController;

import Models.Group;
import Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static DatabaseController.DBQueryExcecutor.executeQuery;

public class UserDBHandler {

    public static ResultSet authenticateUser(String username, String password){
        String qu = "SELECT * FROM Login where username = ? and password = ?";
        List<Object> parameters = Arrays.asList(username,password);

        return executeQuery(qu,parameters,"get");
    }
    public static void addUser(User user){
        String qu = "Insert into Login (userID,groupID,username,password,mobile,firstname,role) values (?,?,?,?,?,?,?)";
        List<Object> parameters = Arrays.asList(user.getUserId(),user.getUserId(),user.getUsername(),user.getPassword(),user.getMobile(),user.getFirstName(),user.getRole());
        executeQuery(qu,parameters,"update");
    }

    public static ResultSet getAllUsers(){
//        return executeQuery("SELECT * FROM Login",null,"get");
        return null;
    }

    //Fetch the groups of the a particular user
    public static ResultSet getUserGroups(String userId) {
//        String query = String.format
//                ("SELECT g.* FROM groups_tb g JOIN usergroups_tb ug ON g.group_id = ug.group_id WHERE ug.user_id = '%s'", userId);
//        try(ResultSet rs = executeQuery(query, "get")) {
//            return rs;
//        } catch (Exception e) {
//            System.err.printf("%s: %s%n", e.getMessage(), Arrays.toString(e.getStackTrace()));
//            return null;
//        }
//    }
        return null;
    }
}
