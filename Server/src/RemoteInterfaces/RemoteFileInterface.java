package RemoteInterfaces;
import Models.FileModel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteFileInterface extends Remote{
    void sayHello() throws RemoteException;
    boolean uploadFile(FileModel file) throws RemoteException;
    boolean uploadFile(List<FileModel> files) throws RemoteException;
    byte[] downloadFile(String fileID) throws RemoteException;
//    List<FileModel> downloadFile(String fileID) throws RemoteException;
    boolean deleteFile(String fileId) throws RemoteException;
    boolean shareFileWithUser(String fileId, List<String> usernames) throws RemoteException;
    List<FileModel> fetchAllFiles(String userId) throws RemoteException;
}