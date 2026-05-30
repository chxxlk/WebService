import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class KstImplementation
    extends UnicastRemoteObject
    implements KstInterface
{

    private static final String FILE_NAME = "kst.txt";

    protected KstImplementation() throws RemoteException {
        super();
    }

    private ArrayList<String[]> bacaSemua() {
        ArrayList<String[]> data = new ArrayList<>();
        try (
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    data.add(parts);
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
        return data;
    }

    private void tulisSemua(ArrayList<String[]> data) {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (String[] row : data) {
                fw.write(row[0] + ";" + row[1] + "\n");
            }
        } catch (IOException e) {
            System.out.println("Gagal menulis file: " + e.getMessage());
        }
    }

    @Override
    public String tambah(String nim, String kodeMK) throws RemoteException {
        if (
            nim == null || nim.trim().isEmpty()
        ) return "Gagal: NIM tidak boleh kosong!";
        if (
            kodeMK == null || kodeMK.trim().isEmpty()
        ) return "Gagal: Kode MK tidak boleh kosong!";

        ArrayList<String[]> data = bacaSemua();
        for (String[] kst : data) {
            if (kst[0].equals(nim) && kst[1].equals(kodeMK)) return (
                "Gagal: NIM " + nim + " sudah terdaftar di MK " + kodeMK + "!"
            );
        }

        data.add(new String[] { nim, kodeMK });
        tulisSemua(data);
        return "Berhasil tambah KST: " + nim + " - " + kodeMK;
    }

    @Override
    public String lihat() throws RemoteException {
        ArrayList<String[]> data = bacaSemua();
        if (data.isEmpty()) return "DAFTAR KST:\nBelum ada data";

        StringBuilder sb = new StringBuilder("DAFTAR KST:\n");
        for (String[] kst : data) {
            sb.append(kst[0]).append(" - ").append(kst[1]).append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String cari(String nim) throws RemoteException {
        if (
            nim == null || nim.trim().isEmpty()
        ) return "Gagal: NIM tidak boleh kosong!";

        ArrayList<String[]> data = bacaSemua();
        StringBuilder sb = new StringBuilder("KST MAHASISWA " + nim + ":\n");
        boolean ditemukan = false;
        for (String[] kst : data) {
            if (kst[0].equals(nim)) {
                sb.append(kst[1]).append("\n");
                ditemukan = true;
            }
        }
        if (!ditemukan) return "Tidak ditemukan";
        return sb.toString().trim();
    }

    @Override
    public String delete(String nim, String kodeMK) throws RemoteException {
        ArrayList<String[]> data = bacaSemua();
        for (int i = 0; i < data.size(); i++) {
            String[] kst = data.get(i);
            if (kst[0].equals(nim) && kst[1].equals(kodeMK)) {
                data.remove(i);
                tulisSemua(data);
                return "Berhasil hapus KST: " + nim + " - " + kodeMK;
            }
        }
        return "Gagal: Data KST tidak ditemukan";
    }
}
