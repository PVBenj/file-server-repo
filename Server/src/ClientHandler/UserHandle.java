package ClientHandler;
import DatabaseControll.DBQueryExcecutor;
import Interfaces.RemoteInterface;
import Models.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserHandle extends UnicastRemoteObject implements RemoteInterface {

    public UserHandle() throws RemoteException {
        super();
    }

    @Override
    public User login(String username, String password) {


        User logonUser = null;

        String qu = String.format("SELECT * FROM Login where username = '%s' and password = '%s'",username,password);


        ResultSet rs = DBQueryExcecutor.executeQuery(qu,"get");

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
        String qu = String.format("Insert into Login (userID,groupID,username,password,mobile,firstname,role) values (%s,%s,'%s','%s','%s','%s','%s')",
                newUser.getUserId(),newUser.getUserId(),newUser.getUsername(),newUser.getPassword(),newUser.getMobile(),newUser.getFirstName(),newUser.getRole());
        DBQueryExcecutor.executeQuery(qu,"update");
        return "Data Success fully added";
    }

    @Override
    public List<User> fetchAllUsers() throws RemoteException, SQLException {
//        this methode fetch all users data from database and return the data set as a User Object List
        List<User> userList= new ArrayList<>();
        ResultSet allUsersData = DBQueryExcecutor.executeQuery("SELECT * FROM Login","get");

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
