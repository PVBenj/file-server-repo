package ClientHandler;
import DatabaseControll.DBQueryExcecutor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;




public class Login extends UnicastRemoteObject implements RemoteInterface {

    public Login() throws RemoteException {
        super();
    }

    @Override
    public User login(String username, String password) {

        User logonUser = null;

        String qu = String.format("SELECT * FROM Login where username = '%s' and password = '%s'",username,password);


        ResultSet rs = DBQueryExcecutor.executeQuery(qu);
        try {
            while (rs.next()) {
                logonUser = new User(rs.getInt(1),rs.getInt(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getString(6),rs.getString(7));

            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return logonUser;



    }

    @Override
    public void sayHello() {
        System.out.println("Hi I'm the file server!");
    }

    //implement login methods defined in the ClientHandler.LoginInterface
}
