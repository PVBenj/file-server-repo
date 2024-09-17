package ServerHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class RemoteHandler {
    static final String serverName = "FileServer";
    
    
    //Method to get the stub for RMI
    public static RemoteInterface getRemoteObj() {
        try{
            return (RemoteInterface) Naming.lookup(serverName);
        }catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
}
    
    

