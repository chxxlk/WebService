import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    try {
      CounterInterface stb = (CounterInterface) Naming.lookup("//localhost/counterService");
      Scanner scanner = new Scanner(System.in);
      int response = 0;

      System.out.println("Perintah yang tersedia :");
      System.out.println("inc -> increment (+1)");
      System.out.println("dec -> decrement (-1");
      System.out.println("get -> lihat nilai counter");
      System.out.println("reset -> reset ke 0");
      System.out.println("exit -> keluar");

      while (true) {
        System.out.print("> ");
        String cmd = scanner.nextLine().toUpperCase();
        switch (cmd) {
          case "INC":
            response = stb.incCounterValue();
            System.out.println("Counter sekarang : " + response);
            break;
          case "DEC":
            response = stb.decCounterValue();
            System.out.println("Counter sekarang : " + response);
            break;
          case "GET":
            response = stb.getCounterValue();
            System.out.println("Nilai Counter : " + response);
            break;
          case "RESET":
            response = stb.resetCounterValue();
            System.out.println("counter telah di reset ke - " + response);
            break;
          case "EXIT":
            System.out.println("Terima kasih");
            scanner.close();
            System.exit(0);
            break;
          default:
            System.out.println("Masukan perintah yang valid");
            break;
        }
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (RemoteException e) {
      System.out.println("Error Client: " + e.getMessage());
      e.printStackTrace();
    } catch (NotBoundException e) {
      e.printStackTrace();
    }
  }
}
