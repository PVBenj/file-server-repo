package FileHandler;

import DatabaseController.FileDBHandler;
import DatabaseController.UserDBHandler;
import RemoteInterfaces.RemoteFileInterface;
import Models.FileModel;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileHandleImpl extends UnicastRemoteObject implements RemoteFileInterface {
    private final String basePath =  "/home/kali/Desktop/Project/file-server-repo/Server/src/FileHandler/FileStorage/";
    private final ExecutorService executor;
    public FileHandleImpl() throws RemoteException {
        super();
        this.executor = Executors.newFixedThreadPool(10);
    }


    @Override
    public void sayHello() {
        System.out.println("Hey I am File Handler");
    }

    @Override
    public boolean uploadFile(FileModel file) throws RemoteException {

        executor.execute(()->{
            try{
//            fetching username from ownerID to create file path
                String fileOwnerName = UserDBHandler.getUsernamefromID(file.getOwnerId());


//            Creating the file path to store files according to user
                String foldername = file.getOwnerId()+"_"+fileOwnerName;
                Path savePath = Paths.get(this.basePath +foldername);


//            create Directory if the specified path not exist
                if(!Files.exists(savePath)){
                    Files.createDirectories(savePath);
                }

                Path filePath = Paths.get(savePath +"/" +  file.getFileName());

                System.out.println(filePath);
//            Saving the file to the Directory

                FileChannel fileChannel = FileChannel.open(filePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                ByteBuffer buffer = ByteBuffer.wrap(file.getFiledata());
                fileChannel.write(buffer);



//            Adding file Details to Database
                System.out.println("FileID" + file.getFileId());
                System.out.println("Filename" + file.getFileName());
                System.out.println("filepath" + foldername+ "/"+ file.getFileName());
                System.out.println("created" + file.getCreateDateTime());
                System.out.println("ownerID" + file.getOwnerId());

                FileDBHandler.addFile(file.getFileId(),file.getFileName(),foldername + "/" + file.getFileName(),file.getCreateDateTime(),file.getOwnerId());


            }catch (Exception e){
                e.printStackTrace();

            }
        });

        return true;


    }

    @Override
    public boolean uploadFile(List<FileModel> files) throws RemoteException {
        for(FileModel file : files){
            this.uploadFile(file);
        }
        return true;
    }

    @Override
    public byte[] downloadFile(String fileID) throws RemoteException {

        CompletableFuture<byte[]> future = new CompletableFuture<>();

        executor.execute(()->{
            try {
                List<String> fileDetails = FileDBHandler.getFileDetailsByFileID(fileID);

                Path filePath = Paths.get(this.basePath+fileDetails.get(1));

                byte[] file = Files.readAllBytes(filePath);
                future.complete(file);



                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);


            }
            catch (Exception e){
                e.printStackTrace();

            }

        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public List<FileModel> downloadFile(String fileID) throws RemoteException {
//        List<FileModel> files= new ArrayList<>();
//        for(String filename : filenames){
//            FileModel file = this.downloadFile(OwnerID,filename);
//            files.add(file);
//        }
//        return files;
//    }

    @Override
    public boolean deleteFile(String fileId) throws RemoteException {
        return false;
    }

    @Override
    public boolean shareFileWithUser(String fileId, List<String> usernames) throws RemoteException {
        return false;
    }

    @Override
    public List<FileModel> fetchAllFiles(String userId) throws RemoteException {
        return List.of();
    }
}
