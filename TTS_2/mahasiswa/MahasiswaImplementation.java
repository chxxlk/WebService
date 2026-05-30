package mahasiswa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MahasiswaImplementation
    extends UnicastRemoteObject
    implements MahasiswaInterface {

  private static final String FILE_NAME = "mahasiswa.txt";

  public MahasiswaImplementation() throws RemoteException {
    super();
  }

  private ArrayList<String[]> bacaSemua() {
    ArrayList<String[]> data = new ArrayList<>();

    try (
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.trim().isEmpty())
          continue;
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
  public String tambahMahasiswa(String nim, String nama, String alamat)
      throws RemoteException {
    if (nim == null || nim.trim().isEmpty())
      return "Nim tidak boleh kosong";
    if (nama == null || nama.trim().isEmpty())
      return "Nama tidak boleh kosong";
    if (alamat == null || alamat.trim().isEmpty())
      return "Alamat tidak boleh kosong";

    ArrayList<String[]> data = bacaSemua();
    for (String[] mhs : data) {
      if (mhs[0].equals(nim))
        return ("Gagal: NIM " + nim + "sudah terdaftar!");
    }

    data.add(new String[] { nim, nama, alamat });
    tulisSemua(data);
    return "Berhasil tambah: " + nim + " - " + nama;
  }

  @Override
  public String lihat() throws RemoteException {
    ArrayList<String[]> data = bacaSemua();
    if (data.isEmpty())
      return "Belum ada data";

    StringBuilder sb = new StringBuilder("Daftar Mahasiswa \n");

    for (String[] mhs : data) {
      sb.append(mhs[0])
          .append(" - ")
          .append(mhs[1])
          .append(" - ")
          .append(mhs[2])
          .append("\n");
    }
    return sb.toString();
  }

  @Override
  public String cari(String nim) throws RemoteException {
    ArrayList<String[]> data = bacaSemua();
    if (data.isEmpty())
      return "Belum ada data";

    for (String[] mhs : data) {
      if (mhs[0].equals(nim)) return ("Ditemukan : " + mhs[0] + " - " + mhs[1] + " - " + mhs[2]);
    }

    return "Data tidak ditemukan";
  }


  @Override
  public String update(String nim, String namaBaru, String alamatBaru) throws RemoteException {
    ArrayList<String[]> data = bacaSemua();
    for (String[] mhs : data) {
      if (mhs[0].equals(nim)) {
        mhs[1] = namaBaru;
        mhs[2] = alamatBaru;

        tulisSemua(data);
        return ("Berhasil Update : " + nim + " - " + namaBaru + " - " + alamatBaru);
      }
    }
    return "Gagal : NIM " + nim + "Tidak ditemukan";
  }

  @Override
  public String delete (String nim) throws RemoteException {
    ArrayList<String[]> data = bacaSemua();
    for (int i = 0; i < data.size(); i++) {
      if (data.get(i)[0].equals(nim)){
        data.remove(i);
        tulisSemua(data);
        return "Berhasil hapus : " + nim;
      }
    }

    return "Gagal: NIM " + nim + "tidak ditemukan";
  }
}
