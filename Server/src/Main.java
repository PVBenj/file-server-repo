import ClientHandler.UserHandle;
import DatabaseController.DatabaseConnection;
import FileHandler.FileHandleImpl;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class Main {

    public static void main(String[] args) {

        try{


            String ip = CurrentIPHandler.getCurrentIP();


            System.setProperty("java.rmi.server.hostname", ip);
            // Create registry on port 1099 (default RMI port)
            LocateRegistry.createRegistry(1500);
            UserHandle obj = new UserHandle();
            FileHandleImpl obj2 = new FileHandleImpl();

//             Bind the remote object to the registry
            Naming.rebind("rmi://"+ip+":1500/UserHandle", obj);
            Naming.rebind("rmi://"+ip+":1500/FileHandle", obj2);





            System.out.println("RMI Server is running on IP:" + ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}