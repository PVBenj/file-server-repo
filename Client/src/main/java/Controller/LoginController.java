package Controller;

import Model.User;
import ServerHandler.RemoteHandler;
import View.Home.AdminHome;
import View.Home.UserHome;
import java.security.MessageDigest;
import javax.swing.JOptionPane;


public class LoginController {
    
    
    public static void login(String username, String password) {
        
        try{
            User user = RemoteHandler.getRemoteObj()
                    .login(username, PasswordHash.hash(password));
            
            if(user != null) {
                if(user.getRole().equals("Admin")) {
                    new AdminHome(user).setVisible(true);
                } else {
                    new UserHome(user).setVisible(true);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Incorrect credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
       
    }
    
}

class PasswordHash {
    
    public static String hash(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(data.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
}
