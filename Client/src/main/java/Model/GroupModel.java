package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GroupModel implements Serializable {
    private String groupId;
    private String groupName;
    private final UserModel groupOwner;
    private List<UserModel> groupMembers;

    public GroupModel(String groupId, String groupName, UserModel groupOwner) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupOwner = groupOwner;
        groupMembers = new ArrayList<>();
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
    
    public void addUser(UserModel username) {
        groupMembers.add(username);
    }
    
    public void setUers(List<UserModel> users) {
        this.groupMembers = users;
    }
    
    public List<UserModel> getGroupMembers() {
        return groupMembers;
    }
    
    public String groupMembersToString() {
        String groupMembersStr = null;
        
        for(UserModel user : this.groupMembers) {
            if(groupMembersStr != null) {
                groupMembersStr += user.getUsername() + ", ";
            } else {
                groupMembersStr = user.getUsername() + ", ";
            }
        }
        
        return groupMembersStr;
    }
    
}
