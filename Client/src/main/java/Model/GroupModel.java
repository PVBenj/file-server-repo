package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GroupModel implements Serializable {
    private String groupId;
    private String groupName;
    private List<UserModel> userIds;
    private final UserModel groupOwner;

    public GroupModel(String groupId, String groupName, UserModel groupOwner) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.userIds = new ArrayList<>();
        this.groupOwner = groupOwner;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<UserModel> getUsers() {
        return userIds;
    }

    public void addUsers(UserModel user) {
        this.userIds.add(user);
    }

    public UserModel getGroupOwner() {
        return groupOwner;
    }
    
}
