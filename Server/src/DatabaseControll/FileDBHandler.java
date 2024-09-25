package DatabaseControll;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileDBHandler {

    public static void addFile(String ownerID, String filepath, String filename, String dateandTime){
        String qu = String.format("Insert into Files (OwnerID,filepath,filename,DateTime) values ('%s','%s','%s','%s')",ownerID,filepath,filename,dateandTime);
        DBQueryExcecutor.executeQuery(qu,"update");;
    }

    public static String getUsernamefromID(String userID) throws SQLException {
        String fileOwnerName=null;
        String qu = String.format("SELECT username FROM Login where userID = '%s'",userID);
        ResultSet rs = DBQueryExcecutor.executeQuery(qu,"get");

        while (rs.next()) {
            fileOwnerName = rs.getString(1);
        }
        return fileOwnerName;
    }
}
