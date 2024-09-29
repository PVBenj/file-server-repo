package RemoteInterfaces;

import Model.FileModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author benjamin
 */
public interface RemoteFileInterface extends Remote {
    void sayHello() throws RemoteException;
    //boolean uploadFile(FileModel file) throws RemoteException;  **Redundant method should be removed from the server side.
    boolean deleteFile(String fileId) throws RemoteException;
    boolean uploadFile(List<FileModel> files) throws RemoteException;
    boolean updateFileName(String fileId, String newName) throws RemoteException; //Method to be added to the server side.
    boolean shareFileWithUser(String fileId, List<String> usernames) throws RemoteException;
    FileModel downloadFile(String username, String fileName) throws RemoteException;
    List<FileModel> fetchAllFiles(String userId) throws RemoteException; //Method to be added to the server side.
}
