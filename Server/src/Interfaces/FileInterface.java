package Interfaces;
import Models.FileModel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FileInterface extends Remote{
    void sayHello() throws RemoteException;
    boolean uploadFile(FileModel file) throws RemoteException;
    boolean uploadFile(List<FileModel> files) throws RemoteException;
    FileModel downloadFile(String username, String fileName) throws RemoteException;
}