package FileHandler;

import DatabaseControll.DBQueryExcecutor;
import DatabaseControll.FileDBHandler;
import Interfaces.FileInterface;
import Models.FileModel;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileHandleImpl extends UnicastRemoteObject implements FileInterface {
    private final String basePath =  "/home/kali/Desktop/Project/file-server-repo/Server/src/FileHandler/FileStorage/";
    public FileHandleImpl() throws RemoteException {
        super();
    }

    @Override
    public void sayHello() {
        System.out.println("Hey I am File Handler");
    }

    @Override
    public boolean uploadFile(FileModel file) throws RemoteException {

        try{
//            fetching username from ownerID to create file path
            String fileOwnerName = FileDBHandler.getUsernamefromID(file.getOwnerId());


//            Creating the file path to store files according to user
            Path savePath = Paths.get(this.basePath +file.getOwnerId()+"_"+fileOwnerName);
            System.out.println(savePath.toString());

//            create Directory if the specified path not exist
            if(!Files.exists(savePath)){
                Files.createDirectories(savePath);
            }

//            Saving the file to the Directory
            File saveFile = new File(savePath.toString()+"/"+file.getFileName());
            FileOutputStream save = new FileOutputStream(saveFile);
            save.write(file.getFiledata());
            save.close();

//            Adding file Details to Database
            FileDBHandler.addFile(file.getOwnerId(),file.getFilePath(),file.getFileName(),file.getCreateDateTime());
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean uploadFile(List<FileModel> files) throws RemoteException {
        for(FileModel file : files){
            this.uploadFile(file);
        }
        return true;
    }

    @Override
    public FileModel downloadFile(String ownerID, String fileName) throws RemoteException {
        try {
            byte[] fileData;
            String fileOwnername = FileDBHandler.getUsernamefromID(ownerID);
            Path savedPath = Paths.get(this.basePath + ownerID+"_"+fileOwnername + "/" + fileName);
            File dfile = new File(savedPath.toString());
            FileInputStream In = new FileInputStream(dfile);
            fileData = new byte[(int) dfile.length()];
            In.read(fileData);
            In.close();

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);

            return new FileModel(fileName,ownerID,formattedDateTime,fileData);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<FileModel> downloadFile(String OwnerID, List<String> filenames) throws RemoteException {
        List<FileModel> files= new ArrayList<>();
        for(String filename : filenames){
            FileModel file = this.downloadFile(OwnerID,filename);
            files.add(file);
        }
        return files;
    }
}
