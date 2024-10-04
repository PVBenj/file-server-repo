package GroupHandler;

import DatabaseController.GroupDBHandler;
import Models.GroupModel;
import RemoteInterfaces.RemoteGroupInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GroupHandleImpl extends UnicastRemoteObject implements RemoteGroupInterface {

    protected GroupHandleImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean addUser(GroupModel group) throws RemoteException {
        try {
            GroupDBHandler.CreateGroup(group);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
