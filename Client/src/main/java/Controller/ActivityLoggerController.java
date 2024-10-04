package Controller;

import Models.ActivityLogger;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author benjamin
 */
public class ActivityLoggerController {
    private static DefaultTableModel activityTableModel;
    
    public static List<ActivityLogger> getAdminActivities() {
        //Test data
        //Test data
        return List.of(
                new ActivityLogger("Benjamin", null, "Login attemp failed", new Date().toString()),
                new ActivityLogger("John", null, "Updated shared file 'test.txt'", new Date().toString()),
                new ActivityLogger("John", null, "Deleted shared file 'test2.txt'", new Date().toString())
        );
        
    }
    
    //Get admin view of activity logs
    /* public static DefaultTableModel getAdminActivities() {
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
    } */
    
    //Get user view of activity logs
    public static List<ActivityLogger> getUserActivities(String userId) {
        //Back end implementation
        
        
        //Test data
        return List.of(
                new ActivityLogger("Benjamin", null, "Login attemp failed", new Date().toString()),
                new ActivityLogger("John", null, "Updated shared file 'test.txt'", new Date().toString()),
                new ActivityLogger("John", null, "Deleted shared file 'test2.txt'", new Date().toString())
        );
    }
    
    public static void logActivity(ActivityLogger activity) {
        
    }
   
}
