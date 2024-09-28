package RemoteInterfaces;

import Model.GroupModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author benjamin
 */
public interface RemoteGroupInterface extends Remote {
    boolean createGroup(GroupModel group) throws RemoteException;
    boolean deleteGroup(GroupModel group) throws RemoteException;
    boolean updateGroup(GroupModel group) throws RemoteException;
    List<GroupModel> fetchAllGroups() throws RemoteException; //Method to be added to the server side.
    //All the three methods should be updated in the server side.
}
