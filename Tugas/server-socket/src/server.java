import java.io.*;
import java.net.*;

public class server {
  public static void main(String[] args) {
    int port = 12345;

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Server started on port " + port);

      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("New client connected: " + clientSocket.getInetAddress());

        Thread clientThread = new Thread(new ClientHandler(clientSocket));
        clientThread.start();
      }
    } catch (IOException e) {
      System.err.println("Error starting server: " + e.getMessage());
    }
  }
}

class ClientHandler implements Runnable {
  private Socket clientSocket;

  public ClientHandler(Socket socket) {
    this.clientSocket = socket;
  }

  @Override
  public void run() {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

      String inputLine;

      while ((inputLine = in.readLine()) != null) {
        System.out.println("Client: " + inputLine);
        String[] userInput = inputLine.split("\\|");
        String order = userInput[0].toUpperCase();

        if (order.equals("SELESAI")) {
          out.println("terimakasih");
          break;
        }

        if (userInput.length < 3) {
          out.println("Error: Format harus PERINTAH|A|B");
          continue;
        }

        try {
          int first_value = Integer.parseInt(userInput[1]);
          int second_value = Integer.parseInt(userInput[2]);
          int result = 0;

          switch (order) {
            case "TAMBAH":
              result = first_value + second_value;
              break;
            case "KURANG":
              result = first_value - second_value;
              break;
            case "KALI":
              result = first_value * second_value;
              break;
            case "BAGI":
              if (second_value == 0) {
                out.println("Error: Bagi nol!");
                continue;
              }
              result = first_value / second_value;
              break;
            default:
              out.println("Error: Perintah tidak dikenal");
              continue;
          }
          out.println("Hasil: " + result);
        } catch (NumberFormatException e) {
          out.println("Error: Input harus angka!");
        }
      }
    } catch (IOException e) {
      System.out.println("Client Error: " + e.getMessage());
    } finally {
      try {
        clientSocket.close();
        System.out.println("Client disconnected.");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
