package Controller;

import Model.GroupModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author benjamin
 */
public class GroupController {
    
    private List<GroupModel> groups;
    
    //Get all usergroups for admin view
    public static List<GroupModel> getAllUserGroups() {
        try {
            return RemoteHandler.getRemoteGroup().fetchAllGroups();
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        } 
        
        //Test data
        /* List<GroupModel> groups = List.of(
            new GroupModel("group1", "Admin Group", new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin")),
            new GroupModel("group2", "Admin Group", new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin")),
            new GroupModel("group3", "Admin Group", new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"))
        ); 
        
        for(GroupModel group : groups) {
            group.setUsers(
                    List.of(
                        new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"),
                        new UserModel("0002", "bobdylan", "Bob123", "Bob", "0718274567", "Admin"),
                        new UserModel("0003", "slystallone", "Sly123", "Silvester", "0718274567", "Admin")
                    )
            );
        } */
        
        
    }
    
    //Get all usergroups by userId
    public static List<GroupModel> getUserGroups(String userId) {
        try {
            return RemoteHandler.getRemoteGroup().fetchUserGroups(userId);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        } 
        
    }
    
    
    //Method to create a group
    public static boolean createGroup(GroupModel group, List<String> userIds) {
        try {
            return RemoteHandler.getRemoteGroup().createGroup(group, userIds);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        } 
    }
    
    //Method to remove user
    public static boolean removeGroup(String groupId) {
        try {
            return RemoteHandler.getRemoteGroup().deleteGroup(groupId);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        } 
        
    }
    
    public static boolean removeUserFromGroup(String groupId, List<String> selectedUserIds) {
        try {
            return RemoteHandler.getRemoteGroup().removeUsers(groupId, selectedUserIds);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    
    public static boolean addUsertoGroup(String userId, List<String> selectedUserIds) {
        try {
            return RemoteHandler.getRemoteGroup().addUsers(userId, selectedUserIds);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
}
