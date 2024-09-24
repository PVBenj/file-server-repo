package Controller;

import Model.GroupModel;
import Model.UserModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author benjamin
 */
public class GroupController {
    
    public static DefaultTableModel getGroupTable() {
        
        //Test data
        List<GroupModel> groups = List.of(
            new GroupModel("group1", "Admin Group", new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin")),
            new GroupModel("group2", "Admin Group", new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin")),
            new GroupModel("group3", "Admin Group", new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"))
        );
        
        String[] columnNames = {"Group ID", "Group Name", "Members", "Created By"};
        DefaultTableModel groupsTableModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows from List<User>
        for (GroupModel group : groups) {
            Object[] row = { group.getGroupId(), group.getGroupName(), "Benjamin, Lasindu, Ashan", group.getGroupOwner().getFirstName() };
            groupsTableModel.addRow(row);
        }
        
        return groupsTableModel;
    }
    
    //Method to create a group
    public static void createGroup(GroupModel group, List<String> users) {
        
    }
    
    //Method to fetch all the groups
    public static List<GroupModel> fetchAllGroups() {
        try {
            return RemoteHandler.getRemoteGroup().fetchAllGroups();
        } catch (RemoteException ex) {
            Logger.getLogger(GroupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
