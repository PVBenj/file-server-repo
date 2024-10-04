package Controller;

import Models.UserModel;
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

//        return new UserModel(
//               "0001", "johnwick", "John123", "John", "0718274567", "Generic"
//            );
       
    }
    
    
    
}
