package ClientHandler;
import DatabaseController.UserDBHandler;
import RemoteInterfaces.RemoteUserInterface;
import Models.Group;
import Models.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class UserHandle extends UnicastRemoteObject implements RemoteUserInterface {

    public UserHandle() throws RemoteException {
        super();
    }

    @Override
    public User login(String username, String password) {
        try {
            ResultSet rs = UserDBHandler.authenticateUser(username,password);
            System.out.println(rs);
            User logonUser = null;
            while(rs.next()) {
                logonUser = new User(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getString(7));
                logonUser.setEmail(rs.getString(6));
                logonUser.setGroups(getUserGroups(logonUser.getUserId()));
                return logonUser;
            }

        } catch (Exception e) {
            System.err.printf("%s: %s%n", e.getMessage(), Arrays.toString(e.getStackTrace()));
        }

        return null;
    }

    @Override
    public void sayHello() {
        System.out.println("Hi I'm the file server!");
    }


    @Override
    public String register(User newUser) throws RemoteException {
        UserDBHandler.addUser(newUser);
        return "Data Success fully added";
    }

    @Override
    public List<User> fetchAllUsers() throws RemoteException, SQLException {
//        this methode fetch all users data from database and return the data set as a User Object List
        List<User> userList= new ArrayList<>();
        ResultSet allUsersData = UserDBHandler.getAllUsers();

        while (allUsersData.next()){

            User u = new User(allUsersData.getString(1),allUsersData.getString(2),
                    allUsersData.getString(3),allUsersData.getString(4),allUsersData.getString(5),
                    allUsersData.getString(6));
            userList.add(u);
        }
        return userList;
    }

    @Override
    public String deleteUser(int UserID) throws RemoteException {
        return "";
    }

    //Helper method for login method
    private List<Group> getUserGroups(String userId) {
        List<Group> userGroups = new ArrayList<>();

        try(ResultSet rs = UserDBHandler.getUserGroups(userId)) {
            //Check if the result set is empty
            if(rs != null) {
                while(rs.next()) {
                    userGroups.add(new Group(rs.getString(1), rs.getString(2), rs.getString(3)));
                }
                return userGroups;
            }else {
                System.err.println("User has no groups!");
                return null;
            }
        } catch (Exception e) {
            System.err.printf("%s: %s%n", e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
        return userGroups;
    }

    //implement login methods defined in the ClientHandler.LoginInterface
}
