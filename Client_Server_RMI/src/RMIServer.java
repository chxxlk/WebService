import java.rmi.Naming;
// import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      MyRemoteImplementation obj = new MyRemoteImplementation();
      Naming.rebind("//localhost/HelloService", obj);
      System.out.println("Server is Ready");
    } catch (Exception e) {
      System.err.println("server Exception " + e.toString());
      e.printStackTrace();
    }
  }
}
