package ClientHandler;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userID;
    private int groupId;
    private String username;
    private String password;
    private String mobile;
    private String firstname;
    private String role;

    public User(int userID, int groupID, String username,String password,String mobile, String firstname, String role){
        this.userID = userID;
        this.groupId = groupID;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.firstname = firstname;
        this.role = role;
    }

    protected void setUsername(String username){
        this.username = username;
    }
    protected void setRole(String role){
        this.role = role;
    }


    public int userID(){
        return this.userID;
    }
    public int groupID(){
        return this.groupId;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getMobile(){
        return this.mobile;
    }
    public String getFirstname(){
        return this.firstname;
    }
    public String getRole(){
        return this.role;
    }

}
