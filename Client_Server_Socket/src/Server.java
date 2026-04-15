import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // nunggu client
                System.out.println("Client Connected");

                // karena ada blocking perlu pakai thread
                // dengan thread bisa handle banyak client sekaligus tanpa perlu nunggu 1 beres dulu
                new Thread(() -> {
                    try {
                        // membaca dari client
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                        String inputLine;

                        // in.readLine() itu blocking artinya dia nunggu clien 1 sampai selesai kirim data baru client 2 bisa kirim data
                        while ((inputLine = in.readLine()) != null) {
                            System.out.println("Received : " + inputLine);
                            out.println("Echo : " + inputLine); // kirim response ke client
                        }
                    } catch (IOException e) {
                        System.err.println("Client error : " + e.getMessage());
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
