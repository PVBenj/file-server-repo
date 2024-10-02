package RemoteInterfaces;

import Models.Group;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGroupInterface extends Remote {

    boolean addUser(Group group) throws RemoteException;
}
