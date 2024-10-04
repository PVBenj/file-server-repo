package DatabaseController;

import Models.GroupModel;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class GroupDBHandler {

    public static boolean CreateGroup(GroupModel group) {
        String Query = "Insert into groups_tb(group_id, group_name, owner_id) values(?, ?, ?)";
        List<Object> parameters = Arrays.asList(group.getGroupId(), group.getGroupName(), group.getGroupOwner());
        DBQueryExcecutor.executeQuery(Query, parameters, "update");

        return true;
    }

    public static ResultSet getAllGroups(){
        String Query = "Select * from groups_tb";

        return DBQueryExcecutor.executeQuery(Query,null, "get");
    }

    public static ResultSet getAllGroupsByUserID(String userID){
        String Query = "Select * from groups_tb where owner_id = ?";

        return DBQueryExcecutor.executeQuery(Query,Arrays.asList(userID), "get");
    }

}
