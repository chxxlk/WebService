import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImplementation extends UnicastRemoteObject implements MyRemoteInterface {
  protected MyRemoteImplementation() throws RemoteException {
    super();
  }

  @Override
  public String sayHello(String name) throws RemoteException {
    return "Hello, " + name + "!";
  }

  // public String info(String height, String weight) throws RemoteException {
  // return "\nTinggi : " + height + "\nBerat : " + weight;
  // }
  @Override
  public String info(int height, int weight) throws RemoteException {
    return "\nTinggi : " + height + "cm" + "\nBerat : " + weight + "kg";
  }
}
