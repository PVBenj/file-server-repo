package Controller;

import Model.UserModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
        DefaultTableModel userTableModel = new DefaultTableModel(columnNames, 0) {
           @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are uneditable
                return false;
            }
        };
        
        // Add rows from List<User>
        for (UserModel user : users) {
            Object[] row = { user.getUserId(), user.getUsername(), user.getFirstName(), user.getEmail(), user.getMobile(), user.getRole() };
            userTableModel.addRow(row);
        }
        
        return userTableModel;
            
    }
     
    //Method to create a user
    public static void createUser(UserModel user, String groupId) {
         try {
             //Checks if the backend user creation is success
             if(RemoteHandler.getRemoteUser().createUser(user))
                 //Check if groupId passed is not empty
                 if(!groupId.isEmpty()) {
                     //update newly created user in usergroups_tb
                 }
            JOptionPane.showMessageDialog(null, "User created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
         } catch (RemoteException ex) {
             Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "User creation unsuccessful!", "Error", JOptionPane.ERROR_MESSAGE);
         }
    }
    
    
    //Method to update an user
    public static void updateUser(UserModel updatedUser) {
        //
        
        
        JOptionPane.showMessageDialog(null, "Account updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //Method to remove user
    public static boolean removeUser(String userId) {
        
        JOptionPane.showMessageDialog(null, "User removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    //Method to get usernames
    public static List<String> getUsernames() {
        
        //Test data
        List<String> userNames = List.of(
                "benjamin",
                "john",
                "denver"
        );
        
        return userNames;
        
    }
    
    //Test
    public static List<UserModel> getAllUsers() {
        return List.of(
            new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"),
            new UserModel("0002", "bobdylan", "Bob123", "Bob", "0718274567", "Admin"),
            new UserModel("0003", "slystallone", "Sly123", "Silvester", "0718274567", "Admin")
        );
    }

}
    

