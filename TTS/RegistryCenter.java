import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryCenter {

    public static void main(String[] args) {
        try {
            Registry registry1000 = LocateRegistry.createRegistry(1000);
            Registry registry1001 = LocateRegistry.createRegistry(1001);
            Registry registry1002 = LocateRegistry.createRegistry(1002);

            MahasiswaImplementation serverMahasiswa =
                new MahasiswaImplementation();
            MatakuliahImplementation serverMatakuliah =
                new MatakuliahImplementation();
            KstImplementation serverKst = new KstImplementation();

            registry1000.rebind("MahasiswaInterface", serverMahasiswa);
            registry1001.rebind("MatakuliahInterface", serverMatakuliah);
            registry1002.rebind("KstInterface", serverKst);
            System.out.println("Registry Center berjalan...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
