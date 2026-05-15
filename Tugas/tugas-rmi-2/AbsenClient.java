import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class AbsenClient {

  public static void main(String[] args) {
    try {
      AbsenInterface absen = (AbsenInterface) Naming.lookup(
          "//localhost/absenService");
      Scanner scanner = new Scanner(System.in);

      System.out.println("=== System Absen Client ===");

      boolean loggedIn = false;
      while (!loggedIn) {
        System.out.println("\033[2J\033[H");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
          loggedIn = absen.login(username, password);
        } catch (RemoteException e) {
          System.out.println("Error: " + e.getMessage());
          System.out.println("Pastikan server sudah berjalan");
          scanner.close();
          return;
        }

        if (loggedIn) {
          System.out.println("\033[2J\033[H");
          System.out.println("Login Berhasil");
          System.out.println("Hallo " + username);
        } else {
          System.out.println(
              "Login gagal: Username atau Password salah \n");
        }
      }

      while (true) {
        System.out.println("=== Menu ===");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tampilkan Mahasiswa");
        System.out.println("3. Cari Mahasiswa");
        System.out.println("4. Hapus Mahasiswa");
        System.out.println("5. Absen Mahasiswa");
        System.out.println("6. Keluar");
        System.out.print("Pilih Menu: ");

        String input = scanner.nextLine();
        int command;

        try {
          command = Integer.parseInt(input);
        } catch (NumberFormatException e) {
          System.out.println("Error: Masukang angka yang valid!\n");
          continue;
        }
        try {
          switch (command) {
            case 1:
              System.out.println("\033[2J\033[H");
              System.out.println("=== Tambah Mahasiswa ===");
              System.out.print("Masukan Nama : ");
              String studentName = scanner.nextLine();
              System.out.print("Masukan NIM : ");
              String studentId = scanner.nextLine();
              System.out.println(
                  absen.addStudents(studentName, studentId));
              break;
            case 2:
              System.out.println("\033[2J\033[H");
              System.out.println("=== Tampilkan Mahasiswa ===");
              System.out.println(absen.showStudents());
              break;
            case 3:
              System.out.println("\033[2J\033[H");
              System.out.println("=== Cari Mahasiswa ===");
              System.out.print("Masukan NIM atau Nama : ");
              String keyword = scanner.nextLine();
              System.out.println(absen.searchStudent(keyword));
              break;
            case 4:
              System.out.println("\033[2J\033[H");
              System.out.println("=== Hapus Mahasiswa ===");
              break;
            case 5:
              System.out.println("\033[2J\033[H");
              System.out.println("=== Absen Mahasiswa ===");
              break;
            case 6:
              System.out.println("\033[2J\033[H");
              System.out.println("Terima Kasih");
              scanner.close();
              System.exit(0);
              break;
            default:
              System.out.println("\033[2J\033[H");
              System.out.println("Input Salah (Pilih angka 1-6)");
              break;
          }
        } catch (RemoteException e) {
          System.out.println(
              "Error: Gagal berkomunikasi dengan server - " +
                  e.getMessage());
        }
      }
    } catch (Exception e) {
      System.out.println("Pastikan Server Berjalan");
      System.out.println("Error: " + e.getMessage());
    }
  }
}
