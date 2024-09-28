package RemoteInterfaces;

import Models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface RemoteUserInterface extends Remote {
    User login(String username, String password) throws RemoteException;
    void sayHello() throws RemoteException;
    String register(User newUser) throws RemoteException;
    List<User> fetchAllUsers() throws RemoteException, SQLException;
    String deleteUser(int UserID) throws RemoteException;
}
