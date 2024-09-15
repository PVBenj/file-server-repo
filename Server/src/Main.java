import ClientHandler.Login;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) {
        try{
            System.setProperty("java.rmi.server.hostname", "192.168.8.123");
            // Create registry on port 1099 (default RMI port)
            LocateRegistry.createRegistry(1500);
            Login obj = new Login();
            obj.sayHello();
//             Bind the remote object to the registry
            Naming.rebind("rmi://192.168.8.123:1500/LoginService", obj);



            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}