import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImplementation extends UnicastRemoteObject implements MyRemoteInterface {
  private final String VALID_USER = "admin";
  private final String VALID_PASS = "qwerty";

  protected MyRemoteImplementation() throws RemoteException {
    super();
  }

  @Override
  public String sayHello(String name) throws RemoteException {
    return "Hello, " + name + "!";
  }

  @Override
  public String authUser(String username, String password) throws RemoteException {
    if (!authenticate(username, password)) {
      throw new SecurityException("Unathorized access");
    }
    return "Authentication : " + username;
  }

  @Override
  public String info(int height, int weight) throws RemoteException {
    return "\nTinggi : " + height + "cm" + "\nBerat : " + weight + "kg";
  }

  private boolean authenticate(String username, String password) {
    return VALID_USER.equals(username) && VALID_PASS.equals(password);
  }
}
