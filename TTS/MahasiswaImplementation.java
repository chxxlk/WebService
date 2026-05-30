import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MahasiswaImplementation
    extends UnicastRemoteObject
    implements MahasiswaInterface
{

    private static final String FILE_NAME = "mahasiswa.txt";

    protected MahasiswaImplementation() throws RemoteException {
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
    public String tambah(String nim, String nama, String alamat)
        throws RemoteException {
        if (
            nim == null || nim.trim().isEmpty()
        ) return "Gagal: NIM Tidak Boleh Kosong!";
        if (
            nama == null || nama.trim().isEmpty()
        ) return "Gagal: Nama Tidak Boleh Kosong";

        ArrayList<String[]> data = bacaSemua();
        for (String[] mhs : data) {
            if (mhs[0].equals(nim)) return (
                "Gagal: NIM " + nim + " sudah terdaftar!"
            );
        }
        data.add(new String[] { nim, nama, alamat });
        tulisSemua(data);
        return "Berhasil tambah: " + nim + " - " + nama;
    }

    @Override
    public String lihat() throws RemoteException {
        ArrayList<String[]> data = bacaSemua();
        if (data.isEmpty()) return "DAFTAR MAHASISWA:\nBelum ada data";

        StringBuilder sb = new StringBuilder("DAFTAR MAHASISWA:\n");
        for (String[] mhs : data) {
            sb.append(mhs[0])
                .append(" - ")
                .append(mhs[1])
                .append(" - ")
                .append(mhs[2])
                .append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String cari(String nim) throws RemoteException {
        ArrayList<String[]> data = bacaSemua();
        for (String[] mhs : data) {
            if (mhs[0].equals(nim)) return (
                "Ditemukan: " + mhs[0] + " - " + mhs[1] + " - " + mhs[2]
            );
        }
        return "Tidak ditemukan";
    }

    @Override
    public String update(String nim, String namaBaru, String alamatBaru)
        throws RemoteException {
        if (
            nim == null || nim.trim().isEmpty()
        ) return "Gagal: NIM Tidak Boleh Kosong!";
        if (
            namaBaru == null || namaBaru.trim().isEmpty()
        ) return "Gagal: Nama Tidak Boleh Kosong";

        ArrayList<String[]> data = bacaSemua();
        for (String[] mhs : data) {
            if (mhs[0].equals(nim)) {
                mhs[1] = namaBaru;
                mhs[2] = alamatBaru;
                tulisSemua(data);
                return (
                    "Berhasil update: " +
                    nim +
                    " - " +
                    namaBaru +
                    " - " +
                    alamatBaru
                );
            }
        }
        return "Gagal: NIM " + nim + " tidak ditemukan";
    }

    @Override
    public String delete(String nim) throws RemoteException {
        ArrayList<String[]> data = bacaSemua();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equals(nim)) {
                data.remove(i);
                tulisSemua(data);
                return "Berhasil hapus: " + nim;
            }
        }
        return "Gagal: NIM " + nim + " tidak ditemukan";
    }
}
