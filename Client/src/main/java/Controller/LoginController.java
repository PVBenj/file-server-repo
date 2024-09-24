package Controller;

import Model.PasswordHash;
import Model.UserModel;
import ServerHandler.RemoteHandler;
import View.Home.Home;
import javax.swing.JOptionPane;


public class LoginController {
    
    
    public static void login(String username, String password) {
        
        try{
            UserModel user = RemoteHandler.getRemoteUser()
                    .login(username, PasswordHash.hash(password));
            
            if(user != null) {
                new Home(user);
            }else {
                JOptionPane.showMessageDialog(null, "Incorrect credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
       
    }
    
    
    
}
