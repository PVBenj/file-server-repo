package Controller;

import Model.FileModel;
import Model.UserModel;
import ServerHandler.RemoteHandler;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    
    public static DefaultTableModel getUserFileTable(UserModel user) {
        
        
       //Test data
       List<FileModel> files = List.of(
            new FileModel("file1", "test1.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file2", "test2.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB"),
            new FileModel("file3", "test3.txt", new Date().toString(), new UserModel("0001", "johnwick", "John123", "John", "0718274567", "Admin"), "file size in MB")
        );
         
        String[] columnNames = {"File Name", "Owner", "Created", "Size"};
        DefaultTableModel fileTableModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows from List<User>
        for (FileModel file : files) {
            Object[] row = { file.getFileName(), file.getOwner().getFirstName(), file.getCreateDateTime(), "file size in MB" };
            fileTableModel.addRow(row);
        }
        
        return fileTableModel;
    }
    
    public static boolean createNewFile(List<FileModel> rawFiles) {
        try {
           return RemoteHandler.getRemoteFileObj().uploadFile(rawFiles); 
        }catch (RemoteException e) {
            System.err.println("Error Details: " + e.getMessage());
        }
        return false;
    }
    
    public static List<FileModel> fetchRecentFiles(UserModel user) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            List<FileModel> recentFiles = new ArrayList<>();
            List<FileModel> allFiles = RemoteHandler.getRemoteFileObj().fetchAllFiles(user);
            
            for(FileModel file : allFiles) {
                LocalDateTime createdDateTime = LocalDateTime.parse(file.getCreateDateTime());
                LocalDateTime currentDateTime = LocalDateTime.now();
                
                if(Duration.between(currentDateTime, createdDateTime).toHours() <= 24) {
                    recentFiles.add(file);
                }
            }
            return recentFiles;
            
        } catch (RemoteException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}