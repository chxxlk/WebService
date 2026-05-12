import java.io.*;
import java.net.*;

public class client {

  public static void main(String[] args) {
    String serverAddress = "localhost";
    int serverPort = 12345;

    try (Socket clientSocket = new Socket(serverAddress, serverPort)) {
      System.out.println("Connected to server calculator");
      System.out.println("Perintah yang tersedia : ");
      System.out.println("TAMBAH|A|B| -> Penjumlahan");
      System.out.println("KURANG|A|B| -> Pengurangan");
      System.out.println("KALI|A|B|   -> Perkalian");
      System.out.println("BAGI|A|B|   -> Pembagian");
      System.out.println("SELESAI     -> Keluar");
      System.out.println("===========================================");

      PrintWriter out = new PrintWriter(
          clientSocket.getOutputStream(),
          true);
      BufferedReader in = new BufferedReader(
          new InputStreamReader(clientSocket.getInputStream()));
      BufferedReader userInput = new BufferedReader(
          new InputStreamReader(System.in));
      String userInputLine;

      while (true) {
        System.out.print("> ");
        userInputLine = userInput.readLine();
        out.println(userInputLine);
        String response = in.readLine();
        System.out.println("server: " + response);

        if (userInputLine.equalsIgnoreCase("SELESAI")) {
          System.out.println("Koneksi ditutup");
          break;
        }
      }
    } catch (IOException e) {
      System.err.println("Error connecting to server: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
