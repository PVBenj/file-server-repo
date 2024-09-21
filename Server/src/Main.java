import ClientHandler.UserHandle;
import FileHandler.FileHandleImpl;


import java.net.Inet4Address;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.net.InetAddress;

public class Main {

    public static void main(String[] args) {

        try{
            Inet4Address ip = (Inet4Address) InetAddress.getLocalHost();
            System.out.println(ip.getHostAddress());

            System.setProperty("java.rmi.server.hostname", "192.168.8.123");
            // Create registry on port 1099 (default RMI port)
            LocateRegistry.createRegistry(1500);
            UserHandle obj = new UserHandle();
            FileHandleImpl obj2 = new FileHandleImpl();
            obj.sayHello();
//             Bind the remote object to the registry
            Naming.rebind("rmi://192.168.8.123:1500/UserHandle", obj);
            Naming.rebind("rmi://192.168.8.123:1500/FileHandle", obj2);



            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}