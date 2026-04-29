import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RMIClient {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    try {
      MyRemoteInterface stub = (MyRemoteInterface) Naming.lookup("//localhost/HelloService");

      System.out.print("Username: ");
      String user = input.nextLine();

      System.out.print("Password: ");
      String pass = input.nextLine();

      // String authUser = stub.authUser(user, pass);

      // System.out.print("Masukan Nama : ");
      // String name = input.nextLine();

      String response = stub.sayHello(user, pass);

      System.out.print("Masukan Tinggi dan Berat (Pisahkan dengan spasi) : ");
      String data = input.nextLine();

      String[] parts = data.split(" ");
      if (parts.length < 2) {
        throw new IllegalArgumentException("Input harus 2 angka: tinggi <spasi> berat");
      }
      int height = Integer.parseInt(parts[0]);
      int weight = Integer.parseInt(parts[1]);

      String responseInfo = stub.info(height, weight);

      // System.out.println(authUser);
      System.out.println("Response from Server : \n" + response + responseInfo);

    } catch (RemoteException e) {
      System.err.println("Gagal koneksi ke server RMI : " + e.getMessage());
    } catch (NumberFormatException e) {
      System.err.println("Input tinggi/berat harus angka");
    } catch (NotBoundException e) {
      System.err.println("Service 'HelloService' tidak ditemukan di registry");
    } catch (Exception e) {
      System.err.println("client Exception" + e.toString());
      e.printStackTrace();
    } finally {
      input.close();
    }
  }
}
