package Model;

import java.io.Serializable;


public class GroupModel implements Serializable {
    private String groupId;
    private String groupName;
    private final UserModel groupOwner;

    public GroupModel(String groupId, String groupName, UserModel groupOwner) {
        this.groupId = groupId;
        this.groupName = groupName;
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
    
    public UserModel getGroupOwner() {
        return groupOwner;
    }
    
}
