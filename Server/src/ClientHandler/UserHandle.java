package ClientHandler;
import DatabaseController.DBQueryExcecutor;
import DatabaseController.UserDBHandler;
import RemoteInterfaces.RemoteUserInterface;
import Models.GroupModel;
import Models.UserModel;

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
    public UserModel login(String username, String password) {
        try {
            ResultSet rs = UserDBHandler.authenticateUser(username,password);
            UserModel logonUser = null;
            while(rs.next()) {
                logonUser = new UserModel(rs.getString(1),rs.getString(2),
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
    public boolean updateUser(UserModel updateUser) throws RemoteException {
        return false;
    }

    


    @Override
    public boolean createUser(UserModel newUser) throws RemoteException {
        try {
            UserDBHandler.addUser(newUser);

            if(newUser.getGroups().size() > 0){
                for (GroupModel g : newUser.getGroups()){
                    this.addUserToGroup(newUser.getUserId(),g.getGroupId());

                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserModel> fetchAllUsers() throws RemoteException, SQLException {
//        this methode fetch all users data from database and return the data set as a User Object List
        List<UserModel> userList= new ArrayList<>();
        ResultSet allUsersData = UserDBHandler.getAllUsers();

        while (allUsersData.next()){

            UserModel u = new UserModel(allUsersData.getString(1),allUsersData.getString(2),
                    allUsersData.getString(3),allUsersData.getString(4),allUsersData.getString(5),
                    allUsersData.getString(6));
            userList.add(u);
        }
        return userList;
    }

    @Override
    public boolean deleteUser(String UserID) throws RemoteException {
        UserDBHandler.deletUser(UserID);
        return true;
    }

    @Override
    public boolean addUserToGroup(String userId, String groupId) throws RemoteException {
        return UserDBHandler.addUserToGroup(userId,groupId);


    }

    //Helper method for login method
    private List<GroupModel> getUserGroups(String userId) {
        List<GroupModel> userGroups = new ArrayList<>();

        try(ResultSet rs = UserDBHandler.getUserGroups(userId)) {
            //Check if the result set is empty
            if(rs != null) {
                while(rs.next()) {
                    userGroups.add(new GroupModel(rs.getString(1), rs.getString(2), rs.getString(3)));
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
