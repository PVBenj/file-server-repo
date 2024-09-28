package Controller;

import Model.FileModel;
import Model.UserModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author benjamin
 */
public class FileController {
    
    private static DefaultTableModel fileTableModel;
    
    public static DefaultTableModel getUserFileTable(UserModel user) {
       //Test data
       List<FileModel> files = List.of(
            new FileModel("file1", "test1.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file2", "test2.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file3", "test3.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB")
        );
         
        //Construct file table model
        constructFileTableModel();
        
        // Add rows from List<User>
        for (FileModel file : files) {
            Object[] row = { file.getFileId(), file.getFileName(), file.getOwner().getFirstName(), file.getCreateDateTime(), "file size in MB" };
            fileTableModel.addRow(row);
        }
        
        return fileTableModel;
    }
    
    public static boolean createNewFile(List<FileModel> fileObjs) {
        try {
           return RemoteHandler.getRemoteFileObj().uploadFile(fileObjs); 
        }catch (RemoteException e) {
            System.err.println("Error Details: " + e.getMessage());
        }
        return false;
    }
    
    public static DefaultTableModel fetchRecentFiles(UserModel user) {
        //Construct file table model
        constructFileTableModel();
        /* try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            List<FileModel> allFiles = RemoteHandler.getRemoteFileObj().fetchAllFiles(user);
            
            
            for(FileModel file : allFiles) {
                LocalDateTime createdDateTime = LocalDateTime.parse(file.getCreateDateTime());
                LocalDateTime currentDateTime = LocalDateTime.now();
                
                if(Duration.between(currentDateTime, createdDateTime).toHours() <= 24) {
                    Object[] row = { file.getFileId(), file.getFileName(), file.getOwner().getFirstName(), file.getCreateDateTime(), "file size in MB" };
                    fileTableModel.addRow(row);
                }
            } 
            return fileTableModel;
            
        } catch (Exception ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; */
        
        //Test data
       List<FileModel> files = List.of(
            new FileModel("file1", "test1.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file2", "test2.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file3", "test3.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB")
        );
         
        //Construct file table model
        constructFileTableModel();
        
        // Add rows from List<User>
        for (FileModel file : files) {
            Object[] row = { file.getFileId(), file.getFileName(), file.getOwner().getFirstName(), file.getCreateDateTime(), "file size in MB" };
            fileTableModel.addRow(row);
        }
        
        return fileTableModel;
    }
    
    public static void updateFileName(String fileId, String newName) {
        
        System.out.format("New filename update success: %s - %s", fileId, newName);
    }
    
    public static boolean deleteFile(String fileId) {
        
        return true;
    }
    
    public static DefaultTableModel getSharedByMeFiles(String userId) {
        
        
        return fileTableModel;
    }
    
    public static DefaultTableModel getSharedWithYouFiles(String userId) {
        
        
        return fileTableModel;
    }
    
    //Contruct file table model
    private static void constructFileTableModel() {
        String[] columnNames = {"File Id", "File Name", "Owner", "Created", "Size"};
        fileTableModel = new DefaultTableModel(columnNames, 0) {
           @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are uneditable
                return column == 1;
            }
        };
    }
}