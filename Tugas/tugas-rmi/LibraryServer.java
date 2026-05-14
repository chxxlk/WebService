import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class LibraryServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            LibraryImplementation obj = new LibraryImplementation();
            Naming.rebind("//localhost/libraryService", obj);

            System.out.println("=== SISTEM PEMINJAMAN BUKU (RMI) ===");
            System.out.println("Server siap di port 1099");
            System.out.println("Service: //localhost/libraryService");
            System.out.println("Menunggu koneksi client...");
        } catch (Exception e) {
            System.out.println("Error Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
