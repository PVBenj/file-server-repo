package ClientHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Login extends UnicastRemoteObject implements RemoteInterface {

    public Login() throws RemoteException {
        super();
    }

    @Override
    public boolean login(String username, String password) {
        //Login logic
        return false;
    }

    @Override
    public void sayHello() {
        System.out.println("Hi I'm the file server!");
    }

    //implement login methods defined in the ClientHandler.LoginInterface
}
