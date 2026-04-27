import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemoteInterface extends Remote {
  String sayHello(String name) throws RemoteException;

  // String info(String height, String weight) throws RemoteException;
  String info(int height, int weight) throws RemoteException;
}
