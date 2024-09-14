import ClientHandler.Login;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) {
        try{
            LocateRegistry.createRegistry(1099);
            Login obj = new Login();
            Naming.bind("FileServer", obj);

            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}