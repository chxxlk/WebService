import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryCenter {

  public static void main(String[] args) {
    try {
      Registry registry1000 = LocateRegistry.createRegistry(1000);
      Registry registry1001 = LocateRegistry.createRegistry(1001);

      MahasiswaImplementation serverMahasiswa = new MahasiswaImplementation();
      MatakuliahImplementation serverMatakuliah = new MatakuliahImplementation();

      registry1000.rebind("MahasiswaInterface", serverMahasiswa);
      registry1001.rebind("MatakuliahInterface", serverMatakuliah);

      System.out.println("Registry Center berjalan...");
      System.out.println("MahasiswaInterface terdaftar di port 1000");
      System.out.println("MatakuliahInterface terdaftar di port 1001");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
