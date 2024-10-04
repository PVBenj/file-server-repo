package DatabaseController;

import Models.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static DatabaseController.DBQueryExcecutor.executeQuery;

public class UserDBHandler {

    public static ResultSet authenticateUser(String username, String password) {
        String qu = "SELECT * FROM users_tb where username = ? and password_hash = ?";
        List<Object> parameters = Arrays.asList(username, password);

        return executeQuery(qu, parameters, "get");
    }

    public static void addUser(UserModel user) {
        String qu = "Insert into users_tb  (user_id,username,password_hash,first_name,mobile,email,role) values (?,?,?,?,?,?,?)";
        List<Object> parameters = Arrays.asList(user.getUserId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getMobile(), user.getEmail(), user.getRole());
        executeQuery(qu, parameters, "update");
    }

    public static ResultSet getAllUsers() {
        return executeQuery("SELECT * FROM users_tb",null,"get");

    }

    public static boolean addUserToGroup(String userID , String groupID){

        try {
            String qu = "Insert usergroups_tb Login (user_id, group_id) values (?,?)";
            DBQueryExcecutor.executeQuery(qu,Arrays.asList(userID,groupID),"update");

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public static boolean deletUser(String userID){
        String qu = "Delete from users_tb where user_id = ?";
        DBQueryExcecutor.executeQuery(qu,Arrays.asList(userID),"update");
        return true;
    }

    //Fetch the groups of the a particular user
    public static ResultSet getUserGroups(String userId) {
        String query =
                "SELECT g.* FROM groups_tb g JOIN usergroups_tb ug ON g.group_id = ug.group_id WHERE ug.user_id = ?";
        try (ResultSet rs = executeQuery(query, Arrays.asList(userId), "get")) {
            return rs;
        } catch (Exception e) {
            System.err.printf("%s: %s%n", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    public static String getUsernamefromID(String userID) throws SQLException {
        String fileOwnerName=null;
        String qu = "SELECT username FROM users_tb where user_id = ?";
        ResultSet rs = DBQueryExcecutor.executeQuery(qu,Arrays.asList(userID),"get");

        while (rs.next()) {
            fileOwnerName = rs.getString(1);
        }
        return fileOwnerName;

    }



}
