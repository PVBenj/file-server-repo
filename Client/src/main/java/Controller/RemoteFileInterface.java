package Controller;

import Model.FileModel;
import Model.UserModel;
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
    boolean uploadFile(List<FileModel> files) throws RemoteException;
    FileModel downloadFile(String username, String fileName) throws RemoteException;
    boolean updateFile(FileModel file) throws RemoteException; //Method to be added to the server side.
    List<FileModel> fetchAllFiles(UserModel user) throws RemoteException; //Method to be added to the server side.
}
