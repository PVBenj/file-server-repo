package RemoteInterfaces;

import Models.UserModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;


public interface RemoteUserInterface extends Remote {
    UserModel login(String username, String password) throws RemoteException;
    boolean createUser(UserModel newUser) throws RemoteException; //Method name and return type has to be change from the server side.
    boolean updateUser(UserModel updatedUser) throws RemoteException; //This method should be added to the server side.
    List<UserModel> fetchAllUsers() throws RemoteException, SQLException;
    boolean deleteUser(String UserID) throws RemoteException; //Return type has to be change from the server side.
    boolean addUserToGroup(String userId, String groupId) throws RemoteException;
}
