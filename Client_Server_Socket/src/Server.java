import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private enum State {
        NORMAL, WAITING_FOR_OP, WAITING_FOR_NUMS
    }

    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // nunggu client
                System.out.println("Client Connected");

                // karena ada blocking perlu pakai thread
                // dengan thread bisa handle banyak client sekaligus ga perlu nunggu 1 beres
                // dulu
                new Thread(() -> {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                        // membaca dari client
                        String inputLine;
                        // memberikan state normal
                        State curentState = State.NORMAL;
                        String selectedOp = "";

                        // in.readLine() itu blocking artinya dia nunggu clien 1 sampai selesai kirim
                        // data baru client 2 bisa kirim data
                        while ((inputLine = in.readLine()) != null) {
                            String input = inputLine.trim();
                            System.out.println("Received from client: " + input);

                            if (curentState == State.NORMAL) {
                                String[] parts = input.split(" ", 2);
                                String command = parts[0].toUpperCase();

                                switch (command) {
                                    case "HALO":
                                        if (parts.length > 1) {
                                            out.println("Halo " + parts[1] + ", selamat datang di server!");
                                        } else {
                                            out.println("Halo!, (Tips : Kamu bisa Ketik 'HALO <nama>')");
                                        }
                                        break;

                                    case "KALKULATOR":
                                        out.println(
                                                "Menu Kalkulator: 1. Tambah 2. Kurang 3. Kali 4. Bagi. Pilih angka (1-4): ");
                                        curentState = State.WAITING_FOR_OP;
                                        break;

                                    case "KELUAR":
                                        out.println("Sampai Jumpa!");
                                        System.out.println("Client meminta putus koneksi.");
                                        clientSocket.close();
                                        return;

                                    default:
                                        out.print("ERROR: Perintah '" + command + "' tidak dikenali.");
                                        break;
                                }
                            } else if (curentState == State.WAITING_FOR_OP) {
                                selectedOp = input;
                                out.println("Masukan 2 angka dipisahkan spasi (contoh: 5 10): ");
                                curentState = State.WAITING_FOR_NUMS;
                            } else if (curentState == State.WAITING_FOR_NUMS) {
                                try {
                                    String[] numbers = input.split(" ");
                                    int a = Integer.parseInt(numbers[0]);
                                    int b = Integer.parseInt(numbers[1]);
                                    int result = 0;
                                    boolean valid = true;

                                    switch (selectedOp) {
                                        case "1":
                                            result = a + b;
                                            break;
                                        case "2":
                                            result = a - b;
                                            break;
                                        case "3":
                                            result = a * b;
                                            break;
                                        case "4":
                                            if (b != 0)
                                                result = a / b;
                                            else {
                                                out.println("Error: Tidak bisa bagi 0!");
                                                valid = false;
                                            }
                                            break;
                                        default:
                                            out.println("Operasi tidak valid");
                                    }
                                    if (valid)
                                        out.println("Hasil: " + result + " (Kembali ke mode normal)");
                                } catch (Exception e) {
                                    out.println("Format salah, Kirim 2 angka (contoh: 10 5)");
                                }
                                curentState = State.NORMAL;
                            }

                            // kirim response ke client
                            // out.println("Echo : " + inputLine);
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
