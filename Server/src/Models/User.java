package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable {
    private final String userId;
    private String username;
    private String password;
    private String firstName;
    private String mobile;
    private String role;
    private List<Group> groups;

    public User(String userId, String username, String password, String firstName, String mobile, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.mobile = mobile;
        this.role = role;
        this.groups = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
    
    
    
    
}
