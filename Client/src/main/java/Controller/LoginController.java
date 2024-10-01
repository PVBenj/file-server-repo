package Controller;

import Model.ActivityLogger;
import Model.PasswordHash;
import Model.UserModel;
import ServerHandler.RemoteHandler;
import View.Home.Home;
import java.util.Date;
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
                ActivityLoggerController.logActivity(
                        new ActivityLogger(username, null, "Login attempt with incorrect credentials", new Date().toString())
                );
            }
            
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
       
    }
    
    
    
}
