package Model;

/**
 *
 * @author benjamin
 */
public class UserGroup {
    
    private UserModel user;
    private GroupModel group;

    // Constructor
    public UserGroup(UserModel user, GroupModel group) {
        this.user = user;
        this.group = group;
    }

    // Getters and Setters
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }
}

