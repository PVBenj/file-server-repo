package ServerHandler;

import Model.FileModel;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author benjamin
 */
public class FileController {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
    
    public static boolean createNewFile(List<File> rawFiles, String fileOwnerId) {
        //After the upload of the file returns a true
        return true;
    }
    
    private List<FileModel> createFileObj(List<File> rawFiles, String fileOwnerId) {
        List<FileModel> fileObjs = new ArrayList<>();
        String createdDateTime = formatter.format(new Date());
        for(int i = 0; i < rawFiles.size(); i++) {
            fileObjs.add(
                    new FileModel(rawFiles.get(i).getName(), fileOwnerId, createdDateTime));
        }
        return fileObjs;
    }
}
