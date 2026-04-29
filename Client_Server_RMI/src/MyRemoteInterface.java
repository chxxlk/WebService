import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemoteInterface extends Remote {

  String sayHello(String name, String password) throws RemoteException;

  // String authUser(String username, String password) throws RemoteException;

  String info(int height, int weight) throws RemoteException;
}
