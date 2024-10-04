package GroupsHandler;

import DatabaseController.GroupDBHandler;
import DatabaseController.UserDBHandler;
import Models.GroupModel;
import RemoteInterfaces.RemoteGroupInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.List;

public class GroupHandleImpl extends UnicastRemoteObject implements RemoteGroupInterface {
    public GroupHandleImpl() throws RemoteException{
        super();
    }
    @Override
    public boolean createGroup(GroupModel group) throws RemoteException {
        try {
            GroupDBHandler.CreateGroup(group);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteGroup(String groupId) throws RemoteException {
        return false;
    }

    @Override
    public List<GroupModel> fetchUserGroups(String userId) throws RemoteException {
        List<GroupModel> grpsList = null;
        try {

            ResultSet grps = GroupDBHandler.getAllGroupsByUserID(userId);
            while (grps.next()){
                grpsList.add(
                        new GroupModel(grps.getString(1),grps.getString(2), UserDBHandler.getUserfromID(grps.getString(3)))
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<GroupModel> fetchAllGroups() throws RemoteException {
        List<GroupModel> grpsList = null;
        try {

            ResultSet grps = GroupDBHandler.getAllGroups();
            while (grps.next()){
                grpsList.add(
                        new GroupModel(grps.getString(1),grps.getString(2), UserDBHandler.getUserfromID(grps.getString(3)))
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return grpsList;
    }

    @Override
    public boolean addUsers(String groupId, List<String> userIds) throws RemoteException {
        return false;
    }

    @Override
    public boolean removeUsers(String groupId, List<String> userIds) throws RemoteException {
        return false;
    }
}
