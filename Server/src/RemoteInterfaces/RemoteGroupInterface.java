package RemoteInterfaces;

import Models.GroupModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGroupInterface extends Remote {

    boolean addUser(GroupModel group) throws RemoteException;
}
