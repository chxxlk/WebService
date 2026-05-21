import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

  public static void main(String[] args) {
    try {
      Registry registryMahasiswa = LocateRegistry.getRegistry(
          "localhost",
          1000);
      Registry registryMatakuliah = LocateRegistry.getRegistry(
          "localhost",
          1001);

      MahasiswaInterface mahasiswaInterface = (MahasiswaInterface) registryMahasiswa.lookup(
          "MahasiswaInterface");
      MatakuliahInterface matakuliahInterface = (MatakuliahInterface) registryMatakuliah.lookup(
          "MatakuliahInterface");

      Scanner scanner = new Scanner(System.in);

      while (true) {
        System.out.println("\nSISTEM AKADEMIK RMI");
        System.out.println("1. Mahasiswa");
        System.out.println("2. Matakuliah");
        System.out.println("0. Keluar");
        System.out.print("Pilih: ");

        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
          case 1:
            menuMahasiswa(scanner, mahasiswaInterface);
            break;
          case 2:
            menuMatakuliah(scanner, matakuliahInterface);
            break;
          case 0:
            System.out.println("Terima kasih!");
            scanner.close();
            return;
          default:
            System.out.println("Pilihan tidak valid!");
        }
      }
    } catch (Exception e) {
      System.err.println("Client error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private static void menuMahasiswa(
      Scanner scanner,
      MahasiswaInterface service) {
    try {
      while (true) {
        System.out.println("\n--- MAHASISWA ---");
        System.out.println("1. Tambah");
        System.out.println("2. Lihat");
        System.out.println("3. Cari");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Kembali");
        System.out.print("Pilih: ");

        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
          case 1:
            System.out.print("NIM: ");
            String nim = scanner.nextLine();
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("Alamat: ");
            String alamat = scanner.nextLine();
            System.out.println(service.tambah(nim, nama, alamat));
            break;
          case 2:
            System.out.println(service.lihat());
            break;
          case 3:
            System.out.print("NIM: ");
            String cariNim = scanner.nextLine();
            System.out.println(service.cari(cariNim));
            break;
          case 4:
            System.out.print("NIM: ");
            String updateNim = scanner.nextLine();
            System.out.print("Nama Baru: ");
            String updateNama = scanner.nextLine();
            System.out.print("Alamat Baru: ");
            String updateAlamat = scanner.nextLine();
            System.out.println(
                service.update(updateNim, updateNama, updateAlamat));
            break;
          case 5:
            System.out.print("NIM: ");
            String deleteNim = scanner.nextLine();
            System.out.println(service.delete(deleteNim));
            break;
          case 0:
            return;
          default:
            System.out.println("Pilihan tidak valid!");
        }
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static void menuMatakuliah(
      Scanner scanner,
      MatakuliahInterface service) {
    try {
      while (true) {
        System.out.println("\n--- MATA KULIAH ---");
        System.out.println("1. Tambah");
        System.out.println("2. Lihat");
        System.out.println("3. Cari");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Kembali");
        System.out.print("Pilih: ");

        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
          case 1:
            System.out.print("Kode: ");
            String kode = scanner.nextLine();
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("SKS: ");
            String sks = scanner.nextLine();
            System.out.println(service.tambah(kode, nama, sks));
            break;
          case 2:
            System.out.println(service.lihat());
            break;
          case 3:
            System.out.print("Kode: ");
            String cariKode = scanner.nextLine();
            System.out.println(service.cari(cariKode));
            break;
          case 4:
            System.out.print("Kode: ");
            String updateKode = scanner.nextLine();
            System.out.print("Nama Baru: ");
            String updateNama = scanner.nextLine();
            System.out.print("SKS Baru: ");
            String updateSks = scanner.nextLine();
            System.out.println(
                service.update(updateKode, updateNama, updateSks));
            break;
          case 5:
            System.out.print("Kode: ");
            String deleteKode = scanner.nextLine();
            System.out.println(service.delete(deleteKode));
            break;
          case 0:
            return;
          default:
            System.out.println("Pilihan tidak valid!");
        }
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
