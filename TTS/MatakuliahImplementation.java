import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MatakuliahImplementation
    extends UnicastRemoteObject
    implements MatakuliahInterface
{

    private static final String FILE_NAME = "matakuliah.txt";

    protected MatakuliahImplementation() throws RemoteException {
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
                String[] parts = line.split(";", 3);
                if (parts.length == 3) {
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
                fw.write(row[0] + ";" + row[1] + ";" + row[2] + "\n");
            }
        } catch (IOException e) {
            System.out.println("Gagal menulis file: " + e.getMessage());
        }
    }

    @Override
    public String tambah(String kode, String nama, String sks)
        throws RemoteException {
        if (
            kode == null || kode.trim().isEmpty()
        ) return "Error: Kode tidak boleh kosong";
        if (
            nama == null || nama.trim().isEmpty()
        ) return "Error: Nama tidak boleh kosong";

        ArrayList<String[]> data = bacaSemua();
        for (String[] mk : data) {
            if (mk[0].equals(kode)) return (
                "Gagal: Kode " + kode + " sudah terdaftar!"
            );
        }
        data.add(new String[] { kode, nama, sks });
        tulisSemua(data);
        return (
            "Berhasil tambah: " + kode + " - " + nama + " - " + sks + " SKS "
        );
    }

    @Override
    public String lihat() throws RemoteException {
        ArrayList<String[]> data = bacaSemua();
        if (data.isEmpty()) return "DAFTAR MATA KULIAH:\nBelum ada data";

        StringBuilder sb = new StringBuilder("DAFTAR MATA KULIAH:\n");
        for (String[] mk : data) {
            sb.append(mk[0])
                .append(" - ")
                .append(mk[1])
                .append(" SKS - ")
                .append(mk[2])
                .append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String cari(String kode) throws RemoteException {
        ArrayList<String[]> data = bacaSemua();
        for (String[] mk : data) {
            if (mk[0].equals(kode)) return (
                "Ditemukan: " +
                mk[0] +
                " - " +
                mk[1] +
                " - " +
                " SKS - " +
                mk[2]
            );
        }
        return "Tidak ditemukan";
    }

    @Override
    public String update(String kode, String namaBaru, String sksBaru)
        throws RemoteException {
        ArrayList<String[]> data = bacaSemua();
        for (String[] mk : data) {
            if (mk[0].equals(kode)) {
                mk[1] = namaBaru;
                mk[2] = sksBaru;
                tulisSemua(data);
                return (
                    "Berhasil update: " +
                    kode +
                    " - " +
                    namaBaru +
                    " - " +
                    sksBaru +
                    " SKS "
                );
            }
        }
        return "Gagal: Kode " + kode + " tidak ditemukan";
    }

    @Override
    public String delete(String kode) throws RemoteException {
        ArrayList<String[]> data = bacaSemua();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equals(kode)) {
                data.remove(i);
                tulisSemua(data);
                return "Berhasil hapus: " + kode;
            }
        }
        return "Gagal: Kode " + kode + " tidak ditemukan";
    }
}
