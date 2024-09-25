package ClientHandler;
import DatabaseControll.UserDBHandler;
import Interfaces.UserInterface;
import Models.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserHandle extends UnicastRemoteObject implements UserInterface {

    public UserHandle() throws RemoteException {
        super();
    }

    @Override
    public User login(String username, String password) {


        User logonUser = null;

        ResultSet rs = UserDBHandler.authenticateUser(username,password);


        try {
            while (rs.next()) {
                logonUser = new User(rs.getString(1),rs.getString(3),
                        rs.getString(4),rs.getString(6),rs.getString(5),
                        rs.getString(7));

            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


        return logonUser;



    }

    @Override
    public void sayHello() {
        System.out.println("Hi I'm the file server!");
    }


    @Override
    public String register(User newUser) throws RemoteException {
        UserDBHandler.addUser(newUser.getUserId(),newUser.getUserId(),newUser.getUsername(),newUser.getPassword(),newUser.getMobile(),newUser.getFirstName(),newUser.getRole());
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

    //implement login methods defined in the ClientHandler.LoginInterface
}
