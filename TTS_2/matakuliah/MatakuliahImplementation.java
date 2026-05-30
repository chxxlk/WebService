package matakuliah;

import java.io.BufferedReader;
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
    private ArrayList<String[]> data = new ArrayList<>();

    public MatakuliahImplementation() throws RemoteException {
        super();
    }

    private ArrayList<String[]> bacaSemua() {
        data.clear();
        try (
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";", 3);

                if (parts.length == 3) data.add(parts);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file : " + e.getMessage());
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
        ) return "Error : Kode tidak boleh kosong";
        if (
            nama == null || nama.trim().isEmpty()
        ) return "Error : Nama Matakuliah tidak boleh kosong";
        if (
            sks == null || sks.trim().isEmpty() || Integer.parseInt(sks) < 1
        ) return "Error : SKS tidak boleh kurang dari 1";

        ArrayList<String[]> data = bacaSemua();
        for (String[] mk : data) {
            if (mk[0].equals(kode)) return ("Gagal : " + kode + " telah ada");
        }

        data.add(new String[] { kode, nama, sks });

        tulisSemua(data);
        return ("Berhasil : " + kode + " - " + nama + " - " + sks + " SKS");
    }

    @Override
    public String lihat() throws RemoteException {
        ArrayList<String[]> data = bacaSemua();

        if (data.isEmpty()) return "Data Matakuliah Kosong";

        StringBuilder sb = new StringBuilder("Daftar Matakuliah\n");
        for (String[] mk : data) {
            sb.append(mk[0])
                .append(" - ")
                .append(mk[1])
                .append(" - ")
                .append(mk[2])
                .append("SKS")
                .append("\n");
        }

        return sb.toString().trim();
    }

    @Override
    public String cari(String kode) throws RemoteException {
        ArrayList<String[]> data = bacaSemua();

        for (String[] mk : data) {
            if (mk[0].equals(kode)) return (
                "Ditemukan : " +
                mk[0] +
                " - " +
                mk[1] +
                " - " +
                mk[2] +
                "SKS \n"
            );
        }

        return "Tidak ditemukan";
    }

    @Override
    public String update(String kode, String namaBaru, String sksBaru)
        throws RemoteException {
        if (
            kode == null || kode.trim().isEmpty()
        ) return "Error : Kode tidak boleh kosong";

        ArrayList<String[]> data = bacaSemua();
        for (String[] mk : data) {
            if (mk[0].equals(kode)) {
                if (namaBaru != null && !namaBaru.trim().isEmpty()) mk[1] =
                    namaBaru;
                if (sksBaru != null && !sksBaru.trim().isEmpty()) mk[2] =
                    sksBaru;
                tulisSemua(data);
                return (
                    "Berhasil : " +
                    mk[0] +
                    " - " +
                    mk[1] +
                    " - " +
                    mk[2] +
                    " SKS"
                );
            }
        }

        return "Gagal : " + kode + " tidak ditemukan";
    }

    @Override
    public String hapus(String kode) throws RemoteException {
        if (
            kode == null || kode.trim().isEmpty()
        ) return "Error : Kode tidak boleh kosong";

        ArrayList<String[]> data = bacaSemua();
        for (String[] mk : data) {
            if (mk[0].equals(kode)) {
                data.remove(mk);
                tulisSemua(data);
                return "Berhasil : " + kode + " dihapus";
            }
        }

        return "Gagal : " + kode + " tidak ditemukan";
    }
}
