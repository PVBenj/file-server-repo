package ServerHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import RemoteInterfaces.RemoteUserInterface;
import RemoteInterfaces.RemoteFileInterface;
import RemoteInterfaces.RemoteGroupInterface;


public class RemoteHandler {
    
    private static String serverIp = "192.168.211.128";
        
    //Methods to get the stub for RMI
    public static RemoteUserInterface getRemoteUser() {
        try{
            return (RemoteUserInterface) Naming.lookup("rmi:" + serverIp + ":1500/UserHandle");
        }catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public static RemoteFileInterface getRemoteFileObj() {
        try{
            return (RemoteFileInterface) Naming.lookup("rmi:" + serverIp + ":1500/Filehandle");
        }catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public static RemoteGroupInterface getRemoteGroup() {
        try{
            return (RemoteGroupInterface) Naming.lookup("rmi:" + serverIp + ":1500/GroupHandl");
        }catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
}
    
    

