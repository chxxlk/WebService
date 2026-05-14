import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class LibraryClient {

  public static void main(String[] args) {
        try {
            LibraryInterface lib = (LibraryInterface) Naming.lookup(
                "//localhost/libraryService"
            );
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== SISTEM PEMINJAMAN BUKU (RMI) ===");

            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                try {
                    loggedIn = lib.login(username, password);
                } catch (RemoteException e) {
                    System.out.println(
                        "Error: Gagal terhubung ke server - " + e.getMessage()
                    );
                    System.out.println("Pastikan server sudah berjalan.");
                    scanner.close();
                    return;
                }

                if (!loggedIn) {
                    System.out.println(
                        "Login gagal! Username atau password salah.\n"
                    );
                }
            }
            System.out.println("\033[2J\033[H");
            System.out.println(
                "=== Login berhasil! Selamat datang, admin. ==="
            );

            while (true) {
                System.out.println("=== CLIENT MENU ===");
                System.out.println("1. Tambah Buku");
                System.out.println("2. Tampilkan Buku");
                System.out.println("3. Cari Buku");
                System.out.println("4. Hapus Buku");
                System.out.println("5. Pinjam Buku");
                System.out.println("6. Kembalikan Buku");
                System.out.println("7. History");
                System.out.println("8. Keluar");
                System.out.print("Pilih Menu: ");

                String input = scanner.nextLine();
                int pilihan;
                try {
                    pilihan = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Masukkan angka yang valid\n");
                    continue;
                }

                try {
                    switch (pilihan) {
                        case 1:
                            System.out.print("Masukkan Judul Buku: ");
                            String judul = scanner.nextLine();
                            System.out.print("Masukkan Nama Penulis: ");
                            String penulis = scanner.nextLine();
                            System.out.println("\nResponse Server:");
                            System.out.println(lib.tambahBuku(judul, penulis));
                            break;
                        case 2:
                            System.out.println("\nResponse Server:");
                            System.out.println(lib.tampilBuku());
                            break;
                        case 3:
                            System.out.print("Masukkan Kata Kunci: ");
                            String keyword = scanner.nextLine();
                            System.out.println("\nResponse Server:");
                            System.out.println(lib.cariBuku(keyword));
                            break;
                        case 4:
                            System.out.print("Masukkan ID Buku: ");
                            String idHapusStr = scanner.nextLine();
                            try {
                                int idHapus = Integer.parseInt(idHapusStr);
                                System.out.println("\nResponse Server:");
                                System.out.println(lib.hapusBuku(idHapus));
                            } catch (NumberFormatException e) {
                                System.out.println(
                                    "Error: ID harus berupa angka"
                                );
                            }
                            break;
                        case 5:
                            System.out.print("Masukkan ID Buku: ");
                            String idPinjamStr = scanner.nextLine();
                            System.out.print("Masukkan Nama Peminjam: ");
                            String peminjam = scanner.nextLine();
                            try {
                                int idPinjam = Integer.parseInt(idPinjamStr);
                                System.out.println("\nResponse Server:");
                                System.out.println(
                O
                                    lib.pinjamBuku(idPinjam, peminjam)
                                );
                            } catch (NumberFormatException e) {
                                System.out.println(
                                    "Error: ID harus berupa angka"
                                );
                            }
                            break;
                        case 6:
                            System.out.print("Masukkan ID Buku: ");
                            String idKembaliStr = scanner.nextLine();
                            try {
                                int idKembali = Integer.parseInt(idKembaliStr);
                                System.out.println("\nResponse Server:");
                                System.out.println(
                                    lib.kembalikanBuku(idKembali)
                                );
                            } catch (NumberFormatException e) {
                                System.out.println(
                                    "Error: ID harus berupa angka"
                                );
                            }
                            break;
                        case 7:
                            System.out.println("\nResponse Server:");
                            System.out.println(lib.getHistory());
                            break;
                        case 8:
                            System.out.println(
                                "Program selesai. Terima kasih!"
                            );
                            scanner.close();
                            System.exit(0);
                            break;
                        default:
                            System.out.println(
                                "Error: Pilihan tidak valid. Masukkan angka 1-8."
                            );
                    }
                } catch (RemoteException e) {
                    System.out.println(
                        "Error: Gagal berkomunikasi dengan server - " +
                            e.getMessage()
                    );
                }
                System.out.println();
            }
        } catch (MalformedURLException e) {
            System.out.println("Error: URL service tidak valid");
        } catch (NotBoundException e) {
            System.out.println(
                "Error: Service tidak ditemukan. Pastikan server sudah berjalan."
            );
        } catch (RemoteException e) {
            System.out.println(
                "Error: Gagal terhubung ke server. Pastikan server sudah berjalan."
            );
        }
    }
}
