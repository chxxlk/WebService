import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AbsenServer {

  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);

      // Server
      AbsenImplementation absen = new AbsenImplementation();
      Naming.rebind("//localhost/absenService", absen);

      System.out.println("=== System Absen Server ===");
      System.out.println("Server Ready, Waiting for connection");
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
