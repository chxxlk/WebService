import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // membuat dan memulai registry RMI pada port default (1099)
            LocateRegistry.createRegistry(1099);
            // membuat instance dari implementasi remote
            MyRemoteImplementation obj = new MyRemoteImplementation();
            // mendaftarkan objek remote dengan nama "HalloService"
            Naming.rebind("//localhost/HelloService", obj);

            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
