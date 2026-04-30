import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class CalculatorClient {

  public static void main(String[] args) {
    CalculatorInterface stub = null;
    Scanner scanner = new Scanner(System.in);

    try {
      stub = (CalculatorInterface) Naming.lookup("//localhost/calculatorservice");
      System.out.println("Terhubung ke Calculator Server.");
    } catch (Exception e) {
      System.err.println("Gagal terhubung ke server: " + e.getMessage());
      System.err.println("Pastikan server sudah berjalan (jalankan CalculatorServer).");
      scanner.close();
      return;
    }

    try {
      System.out.print("Masukkan dua angka (pisahkan spasi): ");
      String input = scanner.nextLine().trim();

      if (input.isEmpty()) {
        System.err.println("Error: Input tidak boleh kosong.");
        scanner.close();
        return;
      }

      String[] parts = input.split("\\s+");
      if (parts.length < 2) {
        System.err.println("Error: Harap masukkan dua angka dipisahkan spasi.");
        scanner.close();
        return;
      }

      double a, b;
      try {
        a = Double.parseDouble(parts[0]);
        b = Double.parseDouble(parts[1]);
      } catch (NumberFormatException e) {
        System.err.println("Error: Input harus berupa angka valid.");
        scanner.close();
        return;
      }

      System.out.println("\nPilih operasi:");
      System.out.println("1. Tambah");
      System.out.println("2. Kurang");
      System.out.println("3. Kali");
      System.out.println("4. Bagi");
      System.out.print("Pilihan (1-4): ");

      int choice;
      try {
        choice = Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        System.err.println("Error: Pilihan harus berupa angka 1-4.");
        scanner.close();
        return;
      }

      double result = 0;
      String op = "";

      switch (choice) {
        case 1:
          result = stub.add(a, b);
          op = "Tambah";
          break;
        case 2:
          result = stub.subtract(a, b);
          op = "Kurang";
          break;
        case 3:
          result = stub.multiply(a, b);
          op = "Kali";
          break;
        case 4:
          if (b == 0) {
            System.err.println("Error: Tidak bisa membagi dengan nol.");
            scanner.close();
            return;
          }
          result = stub.divide(a, b);
          op = "Bagi";
          break;
        default:
          System.err.println("Error: Pilihan tidak valid. Gunakan angka 1-4.");
          scanner.close();
          return;
      }

      System.out.println("\nHasil " + op + " dari " + a + " dan " + b + " = " + result);

    } catch (RemoteException e) {
      System.err.println("Error komunikasi RMI: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error tak terduga: " + e.getMessage());
    } finally {
      scanner.close();
    }
  }
}
