package RemoteInterfaces;

import Models.ActivityLogger;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author benjamin
 */
public interface RemoteActivityLogger extends Remote {
    boolean logActivity(ActivityLogger activity) throws RemoteException;
    List<ActivityLogger> getUserActivities(String userId) throws RemoteException;
    List<ActivityLogger> getAdminActivities() throws RemoteException;
}
