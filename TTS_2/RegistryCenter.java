import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import mahasiswa.*;
import matakuliah.*;
public class RegistryCenter {

    public static void main(String[] args) {
        try {
            Registry mahasiswa = LocateRegistry.createRegistry(1000);
            Registry matakuliah = LocateRegistry.createRegistry(1001);
            // Registry kst = LocateRegistry.createRegistry(1002);

            MahasiswaImplementation serverMahasiswa = new MahasiswaImplementation();
            MatakuliahImplementation serverMatakuliah = new MatakuliahImplementation();
            // KstImplementation serverKst = new KstImplementation();

            mahasiswa.rebind("MahasiswaInterface", serverMahasiswa);
            matakuliah.rebind("MatakuliahInterface", serverMatakuliah);
            // kst.rebind("KstInterface", serverKst);

            System.out.println("Registry center berjalan...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
