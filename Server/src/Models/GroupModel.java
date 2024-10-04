package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GroupModel implements Serializable {
    private String groupId;
    private String groupName;
    private List<UserModel> users;
    private final String groupOwnerId;

    public GroupModel(String groupId, String groupName, String groupOwnerId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.users = new ArrayList<>();
        this.groupOwnerId = groupOwnerId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupOwnerId(){return groupOwnerId;}

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void addUsers(UserModel user) {
        this.users.add(user);
    }
    
    
    
    
}
