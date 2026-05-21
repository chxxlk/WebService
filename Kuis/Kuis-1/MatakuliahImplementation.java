import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class MatakuliahImplementation
    extends UnicastRemoteObject
    implements MatakuliahInterface {

  private HashMap<String, String[]> daftarMatakuliah = new HashMap<>();

  protected MatakuliahImplementation() throws RemoteException {
    super();
  }

  @Override
  public String tambah(String kode, String nama, String sks)
      throws RemoteException {
    if (kode == null || kode.trim().isEmpty())
      return "Error: Kode tidak boleh kosong";
    if (nama == null || nama.trim().isEmpty())
      return "Error: Nama tidak boleh kosong";
    if (daftarMatakuliah.containsKey(kode))
      return "Gagal: Kode " + kode + " sudah terdaftar!";

    daftarMatakuliah.put(kode, new String[] { nama, sks });
    return "Berhasil tambah: " + kode + " - " + nama + " - " + sks + " SKS";
  }

  @Override
  public String lihat() throws RemoteException {
    if (daftarMatakuliah.isEmpty())
      return "DAFTAR MATA KULIAH:\nBelum ada data";

    StringBuilder sb = new StringBuilder("DAFTAR MATA KULIAH:\n");
    for (HashMap.Entry<String, String[]> entry : daftarMatakuliah.entrySet()) {
      String kode = entry.getKey();
      String[] value = entry.getValue();
      sb.append(kode)
          .append(" - ")
          .append(value[0])
          .append(" - ")
          .append(value[1])
          .append(" SKS\n");
    }
    return sb.toString().trim();
  }

  @Override
  public String cari(String kode) throws RemoteException {
    if (daftarMatakuliah.containsKey(kode)) {
      String[] data = daftarMatakuliah.get(kode);
      return ("Ditemukan: " +
          kode +
          " - " +
          data[0] +
          " - " +
          data[1] +
          " SKS");
    }
    return "Tidak ditemukan";
  }

  @Override
  public String update(String kode, String namaBaru, String sksBaru)
      throws RemoteException {
    if (daftarMatakuliah.containsKey(kode)) {
      daftarMatakuliah.put(kode, new String[] { namaBaru, sksBaru });
      return ("Berhasil update: " +
          kode +
          " - " +
          namaBaru +
          " - " +
          sksBaru +
          " SKS");
    }
    return "Gagal: Kode " + kode + " tidak ditemukan";
  }

  @Override
  public String delete(String kode) throws RemoteException {
    if (daftarMatakuliah.containsKey(kode)) {
      daftarMatakuliah.remove(kode);
      return "Berhasil hapus: " + kode;
    }
    return "Gagal: Kode " + kode + " tidak ditemukan";
  }
}
