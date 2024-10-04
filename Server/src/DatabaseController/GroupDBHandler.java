package DatabaseController;

import Models.GroupModel;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class GroupDBHandler {

    public static boolean CreateGroup(GroupModel group) {
        String Query = "Insert into groups_tb(group_id, group_name, owner_id) values(?, ?, ?)";
        List<Object> parameters = Arrays.asList(group.getGroupId(), group.getGroupName(), group.getGroupOwnerId());
        DBQueryExcecutor.executeQuery(Query, parameters, "update");

        return true;
    }

}
