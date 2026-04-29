import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImplementation extends UnicastRemoteObject implements MyRemoteInterface {
  // TODO: using array for multiple user
  private final String VALID_USER = "chris";
  private final String VALID_PASS = "123";

  protected MyRemoteImplementation() throws RemoteException {
    super();
  }

  @Override
  public String sayHello(String name, String password) throws RemoteException {
    if (!authenticate(name, password)) {
      throw new SecurityException("Unathorized access");
    }
    return "Hello, " + name + "!";
  }

  // @Override
  // public String auth(String username, String password) throws RemoteException {
  // if (!authenticate(username, password)) {
  // throw new SecurityException("Unathorized access");
  // }
  // }

  @Override
  public String info(int height, int weight) throws RemoteException {
    return "\nTinggi : " + height + "cm" + "\nBerat : " + weight + "kg";
  }

  private boolean authenticate(String name, String password) {
    return VALID_USER.equals(name) && VALID_PASS.equals(password);
  }
}
