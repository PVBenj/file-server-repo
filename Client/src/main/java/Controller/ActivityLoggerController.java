package Controller;

import Model.ActivityLogger;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author benjamin
 */
public class ActivityLoggerController {
    private static DefaultTableModel activityTableModel;
    
    //Get admin view of activity logs
    public static DefaultTableModel getAdminActivities() {
        //Back end implementation
        
        
        //Test data
        List<ActivityLogger> activities = List.of(
                new ActivityLogger("user1", "Benjamin", "Login attemp failed", new Date().toString()),
                new ActivityLogger("user2", "John", "EXE file upload attempt", new Date().toString()),
                new ActivityLogger("user2", "Stallone", "large file upload", new Date().toString())
        );
        
        constructActivityTableModel();
        // Add rows from List<ActivityLogger>
        for (ActivityLogger activity : activities) {
            Object[] row = { activity.getUserName(), activity.getDetails(), activity.getDateAndTime() };
            activityTableModel.addRow(row);
        }
        
        return activityTableModel;
    }
    
    //Get user view of activity logs
    public static DefaultTableModel getUserActivities() {
        //Back end implementation
        
        
        //Test data
        List<ActivityLogger> activities = List.of(
                new ActivityLogger("user1", "Benjamin", "Login attemp failed", new Date().toString()),
                new ActivityLogger("user2", "John", "Updated shared file 'test.txt'", new Date().toString()),
                new ActivityLogger("user2", "John", "Deleted shared file 'test2.txt'", new Date().toString())
        );
        
        constructActivityTableModel();
        // Add rows from List<ActivityLogger>
        for (ActivityLogger activity : activities) {
            Object[] row = { activity.getUserName(), activity.getDetails() + " on" + activity.getDateAndTime() };
            activityTableModel.addRow(row);
        }
        
        return activityTableModel;
    }
    
    public static void createActivity(String userId, String username, String details, String dateAndTime) {
        
    }
    
    //Contruct activity table model
    private static void constructActivityTableModel() {
        String[] columnNames = {"Username", "Details", "Time & Date"};
        activityTableModel = new DefaultTableModel(columnNames, 0) {
           @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are uneditable
                return false;
            }
        };
    }
   
}
