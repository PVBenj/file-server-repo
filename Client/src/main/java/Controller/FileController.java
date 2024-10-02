package Controller;

import Model.FileModel;
import Model.UserModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author benjamin
 */
public class FileController {
    
    private static List<FileModel> files;
    
    public static List<FileModel> getMyFiles(String userId) {
        
        try {
            return RemoteHandler.getRemoteFileObj().fetchAllFiles(userId);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        } 
        
        //Test data
        /* return List.of(
            new FileModel("file1", "test1.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file2", "test2.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file3", "test3.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB")
        ); */
       
    }
    
    public static boolean createNewFile(List<FileModel> fileObjs) {
        try {
           return RemoteHandler.getRemoteFileObj().uploadFile(fileObjs); 
        }catch (RemoteException e) {
            System.err.println("Error Details: " + e.getMessage());
            return false;
        }
    }
    
    public static List<FileModel> getRecentFiles(String userId) {
        List<FileModel> recentFiles = new ArrayList<>();
        files = FileController.getMyFiles(userId);
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            
            for(FileModel file : files) {
                LocalDateTime createdDateTime = LocalDateTime.parse(file.getCreateDateTime());
                LocalDateTime currentDateTime = LocalDateTime.now();
                
                if(Duration.between(currentDateTime, createdDateTime).toHours() <= 24) {
                    recentFiles.add(file);
                }
            } 
            return recentFiles;
            
        } catch (Exception ex) {
            System.err.println("Error Details: " + ex.getMessage());
            return null;
        }
        
        //Test data
        /* return List.of(
            new FileModel("file1", "test1.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file2", "test2.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file3", "test3.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB")
        ); */
    }
    
    public static boolean updateFileName(String fileId, String newName) {
        try {
            return RemoteHandler.getRemoteFileObj().updateFileName(fileId, newName);
        }catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        } 
    }
    
    public static boolean deleteFile(String fileId) {
        try {
            return RemoteHandler.getRemoteFileObj().deleteFile(fileId);
        } catch (RemoteException ex) {  
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }
    
    public static List<FileModel> getSharedByMeFiles(String userId) {
        List<FileModel> sharedFiles = new ArrayList<>();
        
        files = getMyFiles(userId);
        
        for(FileModel file : files) {
            if(!file.getSharedWithUsers().isEmpty()) {
                sharedFiles.add(file);
            }
        }
        return sharedFiles;



        //Test data
        /* return List.of(
            new FileModel("file1", "test1.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file2", "test2.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file3", "test3.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB")
        ); */
         
    }
    
    public static List<FileModel> getSharedWithYouFiles(String userId) {
        List<FileModel> sharedWithMeFiles = new ArrayList<>();
        
        files = getMyFiles(userId);
        
        for(FileModel file : files) {
            for(UserModel user : file.getSharedWithUsers()) {
                if(user.getUserId().equals(userId)) {
                    sharedWithMeFiles.add(file);
                }
            }
        }
        return sharedWithMeFiles;





        //Test data
        /* return List.of(
            new FileModel("file1", "test1.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file2", "test2.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file3", "test3.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB")
        ); */
         
    }
    
    public static boolean shareFileWithUser(String fileId, List<String> users) {
        
        try {
            return RemoteHandler.getRemoteFileObj().shareFileWithUser(fileId, users);
        } catch (RemoteException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        } 
    }
    
}