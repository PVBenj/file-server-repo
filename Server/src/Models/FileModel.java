package Models;

import java.io.Serializable;

public class FileModel implements Serializable {
    private String fileId;
    private String fileName;
    byte [] fileData;
    private final String ownerId;
    private final String filePath = null;
    private final String createDateTime;



    public FileModel(String fileName, String ownerId, String createDateTime,byte[] fileData) {
        this.fileData = fileData;
        this.fileName = fileName;
        this.ownerId = ownerId;
        this.createDateTime = createDateTime;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getFilePath() {
        return filePath;
    }
    public byte[] getFiledata(){return this.fileData;}

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public void setFilename (String fileName) {
        this.fileName = fileName;
    }
    public void setFileData(byte[] data) {
        this.fileData = data;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }
      
}
