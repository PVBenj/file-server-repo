package Controller;

import Model.UserModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.util.Arrays;


public class LoginController {
    
    
    public static UserModel login(String username, String password) {
        
        try {
            return RemoteHandler.getRemoteUser().login(username, password);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        } 
       
    }
    
    
    
}
