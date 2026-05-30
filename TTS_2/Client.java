import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import mahasiswa.*;
public class Client {
    public static void main(String[] args) {
        try {
            Registry registryMahasiswa = LocateRegistry.getRegistry("localhost", 1000);
            MahasiswaInterface mhsInterface = (MahasiswaInterface) registryMahasiswa.lookup("MahasiswaInterface");

            Scanner scanner = new Scanner(System.in);

            while (true) {
            System.out.println("SISTEM AKADEMIK RMI");
            System.out.println("1. Mahasiswa");
            System.out.println("2. Matakuliah");
            System.out.println("3. KST");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();
                switch (pilihan) {
                    case 1:
                        menuMahasiswa(scanner, mhsInterface);
                        break;
                    case 2:
                        menuMahasiswa(scanner, mhsInterface);
                        break;
                    case 3:
                        menuMahasiswa(scanner, mhsInterface);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Inputan tidak valid");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Client error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void menuMahasiswa (Scanner scanner, MahasiswaInterface service) {
        try {
          while (true) {
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Lihat Mahasiswa");
            System.out.println("3. Cari Mahasiswa");
            System.out.println("4. Update Mahasiswa");
            System.out.println("5. Hapus Mahasiswa");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

              int pilihan = scanner.nextInt();
              scanner.nextLine();
              switch (pilihan) {
                case 1:
                    System.out.print("NIM : ");
                    String nim = scanner.nextLine();
                    System.out.print("Nama : ");
                    String nama = scanner.nextLine();
                    System.out.print("Alamat : ");
                    String alamat = scanner.nextLine();
                    System.out.println(service.tambahMahasiswa(nim, nama, alamat));
                    break;
                case 2:
                    System.out.println(service.lihat());
                    break;
                case 3:
                    System.out.print("NIM : ");
                    String cariNim = scanner.nextLine();
                    System.out.println(service.cari(cariNim));
                    break;
                case 4:
                    System.out.print("NIM : ");
                    String updateNim = scanner.nextLine();
                    System.out.print("Nama Baru : ");
                    String updateNama = scanner.nextLine();
                    System.out.print("Alamat : ");
                    String updateAlamat = scanner.nextLine();
                    System.out.println(service.update(updateNim, updateNama, updateAlamat));
                    break;
                case 5:
                    System.out.println("NIM : ");
                    String deleteNim = scanner.nextLine();
                    System.out.println(service.delete(deleteNim));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
              }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
