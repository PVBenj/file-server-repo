package Controller;

import Model.GroupModel;
import Model.UserModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author benjamin
 */
public class GroupController {
    
    //Get all usergroups for admin view
    public static List<GroupModel> getUserGroupsAdmin() {
        try {
            return RemoteHandler.getRemoteGroup().fetchAllGroups();
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }
    
    //Get all usergroups by userId
    public static List<GroupModel> getUserGroups(String userId) {
        /* try {
            return RemoteHandler.getRemoteGroup().fetchUserGroups(userId);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
        } */
        
        
        return new ArrayList<>();
    }
    
    
    //Get all group table for the admin
    public static DefaultTableModel getGroupTable() {
        
        //Test data
        List<GroupModel> groups = List.of(
            new GroupModel("group1", "Admin Group", new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin")),
            new GroupModel("group2", "Admin Group", new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin")),
            new GroupModel("group3", "Admin Group", new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"))
        ); 
        
        /* List<GroupModel> groups = new ArrayList<>();
        try {
            groups = RemoteHandler.getRemoteGroup().fetchAllGroups();
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
        } */
        
        //Table model creation
        String[] columnNames = {"Group ID", "Group Name", "Members", "Created By"};
        DefaultTableModel groupsTableModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows from List<Groups> groups
        for (GroupModel group : groups) {
            Object[] row = { group.getGroupId(), group.getGroupName(), group.groupMembersToString(), group.getGroupOwner().getFirstName() };
            groupsTableModel.addRow(row);
        }
        
        return groupsTableModel;
    }
    
    //Get group table for the generic users
    public static DefaultTableModel getGroupTableByOwner(String ownerId) {
        
        //Test data
        List<GroupModel> groups = List.of(
            new GroupModel("UserGroup1", "Admin Group", new UserModel("0045", "johnwick", "John123", "John", "0718274567", "Admin")),
            new GroupModel("UserGroup2", "Admin Group", new UserModel("0032", "johnwick", "John123", "John", "0718274567", "Admin")),
            new GroupModel("UserGroup3", "Admin Group", new UserModel("0222", "johnwick", "John123", "John", "0718274567", "Admin"))
        ); 
        
        /* List<GroupModel> groups = new ArrayList<>();
        try {
            groups = RemoteHandler.getRemoteGroup().fetchUserGroups(ownerId);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
        } */
        
        String[] columnNames = {"Group ID", "Group Name", "Members", "Created By"};
        DefaultTableModel groupsTableModel = new DefaultTableModel(columnNames, 0) {
           @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are uneditable
                return false;
            }
        };
        
        // Add rows from List<User>
        for (GroupModel group : groups) {
            Object[] row = { group.getGroupId(), group.getGroupName(), "Benjamin, Lasindu, Ashan", group.getGroupOwner().getUsername() };
            groupsTableModel.addRow(row);
        }
        
        return groupsTableModel;
    }
    
    
    //Method to create a group
    public static boolean createGroup(GroupModel group, List<String> users) {
        /* try {
            return RemoteHandler.getRemoteGroup().createGroup(group);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        } */
        
        return true;
    }
    
    //Method to remove user
    public static boolean removeGroup(String groupId) {
        /* try {
            return RemoteHandler.getRemoteGroup().deleteGroup(groupId);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        } */
        
        
        return true;
    }
    
    public static boolean removeUserFromGroup(String userId, List<String> selectedUsers) {
        //Yet to configure
        
        return true;
    }
    
    public static boolean addUsertoGroup(String userId, List<String> selectedUsers) {
        /* try {
            return RemoteHandler.getRemoteGroup().addUsers(userId, selectedUsers);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        } */
        
        return true;
    }
}
