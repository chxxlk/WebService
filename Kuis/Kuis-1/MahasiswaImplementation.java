import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MahasiswaImplementation
    extends UnicastRemoteObject
    implements MahasiswaInterface {

  private ArrayList<String[]> daftarMahasiswa = new ArrayList<>();

  protected MahasiswaImplementation() throws RemoteException {
    super();
  }

  @Override
  public String tambah(String nim, String nama, String alamat)
      throws RemoteException {
    if (nim == null || nim.trim().isEmpty())
      return "Gagal: NIM Tidak Boleh Kosong!";
    if (nama == null || nama.trim().isEmpty())
      return "Gagal: Nama TIdak Boleh Kosong";
    for (String[] mhs : daftarMahasiswa) {
      if (mhs[0].equals(nim))
        return "Gagal: NIM " + nim + " sudah terdaftar!";
    }
    daftarMahasiswa.add(new String[] { nim, nama, alamat });
    return "Berhasil tambah: " + nim + " - " + nama;

  }

  @Override
  public String lihat() throws RemoteException {
    if (daftarMahasiswa.isEmpty())
      return "DAFTAR MAHASISWA:\nBelum ada data";

    StringBuilder sb = new StringBuilder("DAFTAR MAHASISWA:\n");
    for (String[] mhs : daftarMahasiswa) {
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
    for (String[] mhs : daftarMahasiswa) {
      if (mhs[0].equals(nim))
        return "Ditemukan: " + mhs[0] + " - " + mhs[1] + " - " + mhs[2];
    }
    return "Tidak ditemukan";

  }

  @Override
  public String update(String nim, String namaBaru, String alamatBaru)
      throws RemoteException {
    if (nim == null || nim.trim().isEmpty())
      return "Gagal: NIM Tidak Boleh Kosong!";
    if (namaBaru == null || namaBaru.trim().isEmpty())
      return "Gagal: Nama TIdak Boleh Kosong";
    for (String[] mhs : daftarMahasiswa) {
      if (mhs[0].equals(nim)) {
        mhs[1] = namaBaru;
        mhs[2] = alamatBaru;
        return ("Berhasil update: " +
            nim +
            " - " +
            namaBaru +
            " - " +
            alamatBaru);
      }
    }
    return "Gagal: NIM " + nim + " tidak ditemukan";
  }

  @Override
  public String delete(String nim) throws RemoteException {
    for (int i = 0; i < daftarMahasiswa.size(); i++) {
      if (daftarMahasiswa.get(i)[0].equals(nim)) {
        daftarMahasiswa.remove(i);
        return "Berhasil hapus: " + nim;
      }
    }
    return "Gagal: NIM " + nim + " tidak ditemukan";
  }
}
