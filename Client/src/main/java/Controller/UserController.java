package Controller;

import Models.UserModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;


public class UserController {
    
     
    
    
    public static List<UserModel> getAllUsers() {
        try {
            return RemoteHandler.getRemoteUser().fetchAllUsers();
        } catch(RemoteException | SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        
//        return List.of(
//            new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"),
//            new UserModel("0002", "bobdylan", "Bob123", "Bob", "0718274567", "Admin"),
//            new UserModel("0003", "slystallone", "Sly123", "Silvester", "0718274567", "Admin"),
//            new UserModel("0004", "deamon", "Dami123", "Deamon", "0718274567", "Admin")
//        ); 

    }
     
    //Method to create a user
    public static boolean createUser(UserModel user) {
        try {
            return RemoteHandler.getRemoteUser().createUser(user);
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
            return false;
        }

//        return true;
    }
    
    
    //Method to update an user
    public static boolean updateUser(UserModel updatedUser) {
        try {
            return RemoteHandler.getRemoteUser().updateUser(updatedUser);
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
            return false;
        }
//        return true;
    }
    
    //Method to remove user
    public static boolean removeUser(String userId) {
        try {
            return RemoteHandler.getRemoteUser().deleteUser(userId);
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
            return false;
        }
//        return true;
    }
    
    public static boolean addUserToGroup(String userId, String groupId) {
        try {
            return RemoteHandler.getRemoteUser().addUserToGroup(userId, groupId);
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
            return false;
        }

//        return true;
    }

}
    

