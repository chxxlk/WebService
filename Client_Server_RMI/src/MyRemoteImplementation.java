import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class MyRemoteImplementation extends UnicastRemoteObject implements MyRemoteInterface {
  // private final String VALID_USER = "chris";
  // private final String VALID_PASS = "123";

  private Map<String, String> users = new HashMap<>();

  protected MyRemoteImplementation() throws RemoteException {
    super();
    // dummy
    users.put("chris", "123");
    users.put("juan", "123");
    users.put("stev", "123");
  }

  @Override
  public String sayHello(String name, String password) throws RemoteException {
    if (!authenticate(name, password)) {
      throw new SecurityException("Unathorized access");
    }
    return "Hello, " + name + "!";
  }

  @Override
  public String info(int height, int weight) throws RemoteException {
    return "\nTinggi : " + height + "cm" + "\nBerat : " + weight + "kg";
  }

  private boolean authenticate(String name, String password) {
    // return VALID_USER.equals(name) && VALID_PASS.equals(password);
    return users.containsKey(name) && users.get(name).equals(password);
  }
}
