import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class TaskManagerClient {

  public static void main(String[] args) {
    TaskManagerInterface stub = null;
    Scanner scanner = new Scanner(System.in);

    try {
      stub = (TaskManagerInterface) Naming.lookup("//localhost/TaskManager");
      System.out.println("Terhubung ke Task Manager Server.");
    } catch (Exception e) {
      System.err.println("Gagal terhubung ke server: " + e.getMessage());
      System.err.println("Pastikan server sudah berjalan (jalankan TaskManagerServer).");
      scanner.close();
      return;
    }

    while (true) {
      try {
        System.out.println("\n=== Task Manager ===");
        System.out.println("1. Tambah Task");
        System.out.println("2. Lihat Task");
        System.out.println("3. Centang Task Selesai");
        System.out.println("4. Keluar");
        System.out.print("Pilihan (1-4): ");

        String choiceInput = scanner.nextLine().trim();
        int choice;
        try {
          choice = Integer.parseInt(choiceInput);
        } catch (NumberFormatException e) {
          System.err.println("Error: Pilihan harus berupa angka 1-4.");
          continue;
        }

        switch (choice) {
          case 1:
            System.out.print("Masukkan task: ");
            String task = scanner.nextLine();
            try {
              stub.addTask(task);
              System.out.println("Task ditambahkan!");
            } catch (RemoteException e) {
              System.err.println("Error: " + e.getMessage());
            }
            break;

          case 2:
            try {
              List<String> tasks = stub.getTask();
              if (tasks.isEmpty()) {
                System.out.println("Belum ada task.");
              } else {
                System.out.println("\nDaftar Task:");
                for (int i = 0; i < tasks.size(); i++) {
                  System.out.println((i + 1) + ". " + tasks.get(i));
                }
              }
            } catch (RemoteException e) {
              System.err.println("Error mengambil task: " + e.getMessage());
            }
            break;

          case 3:
            try {
              List<String> tasksToComplete = stub.getTask();
              if (tasksToComplete.isEmpty()) {
                System.out.println("Tidak ada task untuk dicentang.");
                break;
              }
              System.out.println("\nDaftar Task (centang nomor berapa?):");
              for (int i = 0; i < tasksToComplete.size(); i++) {
                System.out.println((i + 1) + ". " + tasksToComplete.get(i));
              }
              System.out.print("Nomor task yang selesai: ");
              String indexInput = scanner.nextLine().trim();
              int index;
              try {
                index = Integer.parseInt(indexInput) - 1;
              } catch (NumberFormatException e) {
                System.err.println("Error: Masukkan nomor yang valid.");
                break;
              }
              stub.removeTaskByIndex(index);
              System.out.println("Task selesai dan dihapus!");
            } catch (RemoteException e) {
              System.err.println("Error: " + e.getMessage());
            }
            break;

          case 4:
            System.out.println("Keluar...");
            scanner.close();
            return;

          default:
            System.err.println("Error: Pilihan tidak valid. Gunakan angka 1-4.");
        }
      } catch (Exception e) {
        System.err.println("Error tak terduga: " + e.getMessage());
      }
    }
  }
}
