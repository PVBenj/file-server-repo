package Controller;

import Model.UserModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class UserController {
    
     public static DefaultTableModel getUserTable() {
         
         //Logic for getting the list of user objects from the server.
         
         
        //Test data
        List<UserModel> users = List.of(
            new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"),
            new UserModel("0002", "bobdylan", "Bob123", "Bob", "0718274567", "Admin"),
            new UserModel("0003", "slystallone", "Sly123", "Silvester", "0718274567", "Admin")
        );
         
        String[] columnNames = {"User ID", "Username", "First Name", "Email", "Mobile", "Role"};
        DefaultTableModel userTableModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows from List<User>
        for (UserModel user : users) {
            Object[] row = { user.getUserId(), user.getUsername(), user.getFirstName(), user.getEmail(), user.getMobile(), user.getRole() };
            userTableModel.addRow(row);
        }
        
        return userTableModel;
            
    }
     
    //Method to create a user
    public static void createUser(UserModel user, String groupName) {
        
    }
    
    
    //Method to update an user
    public static void updateUser(UserModel updatedUser) {
        //
        
        
        JOptionPane.showMessageDialog(null, "Account updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //Method to get all users
    public static List<UserModel> getAllUsers() {
         try {
             return RemoteHandler.getRemoteUser().fetchAllUsers();
         } catch (RemoteException | SQLException ex) {
             Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
    }

}
    

