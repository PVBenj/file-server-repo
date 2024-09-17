package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Group implements Serializable {
    private String groupId;
    private String groupName;
    private List<User> users;
    private final String groupOwnerId;

    public Group(String groupId, String groupName, String groupOwnerId) {
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

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }
    
    
    
    
}
