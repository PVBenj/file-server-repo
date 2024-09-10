package Login;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Login extends UnicastRemoteObject implements LoginInterface {

    protected Login() throws RemoteException {
        super();
    }

    //implement login methods defined in the Login.LoginInterface
}
