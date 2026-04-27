import java.rmi.Naming;
import java.util.Scanner;

public class RMIClient {
  public static void main(String[] args) {
    try {
      MyRemoteInterface stub = (MyRemoteInterface) Naming.lookup("//localhost/HelloService");
      // String response = stub.sayHello("Chris");
      // String responseInfo = stub.info(175, 45);
      Scanner input = new Scanner(System.in);
      System.out.print("Masukan Nama : ");
      String response = stub.sayHello(input.nextLine());

      System.out.print("Masukan Tinggi dan Berat (Pisahkan dengan spasi) : ");
      String data = input.nextLine();

      String[] parts = data.split(" ");
      int height = Integer.parseInt(parts[0]);
      int weight = Integer.parseInt(parts[1]);

      String responseInfo = stub.info(height, weight);

      System.out.println("Response from Server : \n" + response + responseInfo);

      input.close();
    } catch (Exception e) {
      System.err.println("client Exception" + e.toString());
      e.printStackTrace();
    }
  }
}
