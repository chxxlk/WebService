import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
        String serverAddress = "localhost";
        int port = 12345;

        try(Socket socket = new Socket(serverAddress, port)){
            System.out.println("Connected to server");

            // mengirim data ke server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String userInputLine;
            while((userInputLine = userInput.readLine()) != null){
                out.println(userInputLine); //mengirim data ke server
                String response = in.readLine(); // membaca response dari server
                System.out.println("Server response: " + response);
            }
        }catch(IOException e){
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
