package ClientHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegInterface extends Remote{
    public void register(User regUser) throws RemoteException;
}
