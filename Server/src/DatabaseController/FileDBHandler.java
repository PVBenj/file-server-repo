package DatabaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileDBHandler {

    public static void addFile(String fileID, String filename, String filepath, String dateandTime,String ownerID){
        String qu = "Insert into files_tb (file_id,file_name,file_path,created,owner_id) values (?,?,?,?,?)";
        DBQueryExcecutor.executeQuery(qu, Arrays.asList(fileID,filename,filepath,dateandTime,ownerID),"update");;
    }

    public static List<String> getFileDetailsByFileID(String fileID) throws SQLException {
        String qu = "Select * from files_tb where file_id = ?";
        ResultSet rs = DBQueryExcecutor.executeQuery(qu,Arrays.asList(fileID),"get");

        List<String> details = new ArrayList<>();

        while (rs.next()){
            details.add(rs.getString(2));
            details.add(rs.getString(3));
            details.add(rs.getString(5));

        }


        return details;

    }


}
