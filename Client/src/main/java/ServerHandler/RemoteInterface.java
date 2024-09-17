package ServerHandler;

import Model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteInterface extends Remote {
    User login(String username, String password) throws RemoteException;
}
