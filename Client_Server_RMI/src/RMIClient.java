import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args){
        try {
            // mencari objek remote dari registry
            MyRemoteInterface stub = (MyRemoteInterface)Naming.lookup("//localhost/HelloService");

            // memanggil metode remote
            String response = stub.sayHello("World");
            System.out.println("Response from server: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
