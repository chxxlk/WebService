import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;

public class RMIServer {
  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);

      MyRemoteImplementation obj = new MyRemoteImplementation();

      Naming.rebind("//localhost/HelloService", obj);

      System.out.println("Server is Ready");
    } catch (ExportException e) {
      System.err.println("Port 1099 already in use. Registry mungkin sudah jalan.");
    } catch (RemoteException e) {
      System.err.println("Masalah komunikasi RMI: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Unexpected error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
