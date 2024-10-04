package RemoteInterfaces;

import Models.UserModel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface RemoteUserInterface extends Remote {
    UserModel login(String username, String password) throws RemoteException;
    boolean updateUser(UserModel updateUser) throws RemoteException;
    boolean createUser(UserModel newUser) throws RemoteException;
    List<UserModel> fetchAllUsers() throws RemoteException, SQLException;
    boolean deleteUser(String UserID) throws RemoteException;
    boolean addUserToGroup(String userId, String groupId) throws RemoteException;
}
