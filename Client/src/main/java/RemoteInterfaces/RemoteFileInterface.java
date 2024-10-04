package RemoteInterfaces;

import Models.FileModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author benjamin
 */
public interface RemoteFileInterface extends Remote {
    void sayHello() throws RemoteException;
    boolean uploadFile(FileModel file) throws RemoteException; 
    boolean deleteFile(String fileId) throws RemoteException;
    //boolean uploadFile(FileModel file, byte[] fileData, int length) throws RemoteException;
    boolean updateFileName(String fileId, String newName) throws RemoteException; //Method to be added to the server side.
    boolean shareFileWithUser(String fileId, List<String> usernames) throws RemoteException;
    byte[] downloadFile(String fileId) throws RemoteException;
    List<FileModel> fetchAllFiles(String userId) throws RemoteException; //Method to be added to the server side.
}
