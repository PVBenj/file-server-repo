package Model;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class FileModel implements Serializable {
    private String fileId;
    private String fileName;
    private final String createDateTime; 
    private UserModel owner; // The user who created this file
    private String fileSize;
    private File rawFile;
    private List<UserModel> sharedWithUsers; // Users with whom this file is shared
    private List<GroupModel> sharedWithGroups; // Groups with whom this file is shared

    public FileModel(String fileId, String fileName, String createDateTime, UserModel owner, String fileSize) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.createDateTime = createDateTime;
        this.owner = owner;
        this.fileSize = fileSize;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }
    
    public List<UserModel> getSharedWithUsers() {
        return sharedWithUsers;
    }

    public void setSharedWithUsers(List<UserModel> sharedWithUsers) {
        this.sharedWithUsers = sharedWithUsers;
    }

    public List<GroupModel> getSharedWithGroups() {
        return sharedWithGroups;
    }

    public void setSharedWithGroups(List<GroupModel> sharedWithGroups) {
        this.sharedWithGroups = sharedWithGroups;
    }

    public UserModel getOwner() {
        return owner;
    }

    public String getFileSize() {
        return fileSize;
    }

    public File getRawFile() {
        return rawFile;
    }

    public void setRawFile(File rawFile) {
        this.rawFile = rawFile;
    }
  
}
