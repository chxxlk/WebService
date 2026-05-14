import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LibraryImplementation
    extends UnicastRemoteObject
    implements LibraryInterface {

  private List<Book> books;
  private List<String> history;
  private int nextId;
  private static final String VALID_USER = "admin";
  private static final String VALID_PASS = "admin123";

  protected LibraryImplementation() throws RemoteException {
    super();
    books = new ArrayList<>();
    history = new ArrayList<>();
    nextId = 1;
  }

  @Override
  public boolean login(String username, String password)
      throws RemoteException {
    if (username == null || password == null)
      return false;
    if (username.trim().isEmpty() || password.trim().isEmpty())
      return false;
    return VALID_USER.equals(username) && VALID_PASS.equals(password);
  }

  @Override
  public String tambahBuku(String judul, String penulis)
      throws RemoteException {
    if (judul == null || judul.trim().isEmpty()) {
      return "Error: Judul buku tidak boleh kosong";
    }
    if (penulis == null || penulis.trim().isEmpty()) {
      return "Error: Nama penulis tidak boleh kosong";
    }
    for (Book b : books) {
      if (b.judul.equalsIgnoreCase(judul.trim()) &&
          b.penulis.equalsIgnoreCase(penulis.trim())) {
        return ("Error: Buku dengan judul \"" +
            judul.trim() +
            "\" dan penulis \"" +
            penulis.trim() +
            "\" sudah ada");
      }
    }
    Book book = new Book(nextId++, judul.trim(), penulis.trim());
    books.add(book);
    history.add(
        "[TAMBAH] Buku \"" +
            judul.trim() +
            "\" oleh " +
            penulis.trim() +
            " (ID: " +
            book.id +
            ")");
    return "Data berhasil ditambahkan (ID: " + book.id + ")";
  }

  @Override
  public String tampilBuku() throws RemoteException {
    if (books.isEmpty()) {
      return "=== DAFTAR BUKU ===\nTidak ada buku tersedia.";
    }
    StringBuilder sb = new StringBuilder();
    sb.append("=== DAFTAR BUKU ===\n");
    for (Book b : books) {
      sb
          .append(b.id)
          .append(". ")
          .append(b.judul)
          .append(" - ")
          .append(b.penulis);
      if (b.tersedia) {
        sb.append(" [Tersedia]");
      } else {
        sb.append(" [Dipinjam oleh: ").append(b.peminjam).append("]");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  @Override
  public String cariBuku(String keyword) throws RemoteException {
    if (keyword == null || keyword.trim().isEmpty()) {
      return "Error: Kata kunci pencarian tidak boleh kosong";
    }
    StringBuilder sb = new StringBuilder();
    sb.append("=== HASIL PENCARIAN: \"").append(keyword).append("\" ===\n");
    boolean found = false;
    for (Book b : books) {
      if (b.judul.toLowerCase().contains(keyword.toLowerCase()) ||
          b.penulis.toLowerCase().contains(keyword.toLowerCase())) {
        sb
            .append(b.id)
            .append(". ")
            .append(b.judul)
            .append(" - ")
            .append(b.penulis);
        if (b.tersedia) {
          sb.append(" [Tersedia]");
        } else {
          sb
              .append(" [Dipinjam oleh: ")
              .append(b.peminjam)
              .append("]");
        }
        sb.append("\n");
        found = true;
      }
    }
    if (!found) {
      return ("Error: Buku dengan kata kunci \"" +
          keyword +
          "\" tidak ditemukan");
    }
    return sb.toString();
  }

  @Override
  public String hapusBuku(int id) throws RemoteException {
    if (id <= 0) {
      return "Error: ID buku tidak valid";
    }
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).id == id) {
        Book removed = books.remove(i);
        history.add(
            "[HAPUS] Buku \"" + removed.judul + "\" (ID: " + id + ")");
        return "Buku \"" + removed.judul + "\" berhasil dihapus";
      }
    }
    return "Error: Buku dengan ID " + id + " tidak ditemukan";
  }

  @Override
  public String pinjamBuku(int idBuku, String peminjam)
      throws RemoteException {
    if (idBuku <= 0) {
      return "Error: ID buku tidak valid";
    }
    if (peminjam == null || peminjam.trim().isEmpty()) {
      return "Error: Nama peminjam tidak boleh kosong";
    }
    for (Book b : books) {
      if (b.id == idBuku) {
        if (!b.tersedia) {
          return ("Error: Buku \"" +
              b.judul +
              "\" sedang dipinjam oleh " +
              b.peminjam);
        }
        b.tersedia = false;
        b.peminjam = peminjam.trim();
        history.add(
            "[PINJAM] Buku \"" +
                b.judul +
                "\" dipinjam oleh " +
                peminjam.trim());
        return ("Buku \"" +
            b.judul +
            "\" berhasil dipinjam oleh " +
            peminjam.trim());
      }
    }
    return "Error: Buku dengan ID " + idBuku + " tidak ditemukan";
  }

  @Override
  public String kembalikanBuku(int idBuku) throws RemoteException {
    if (idBuku <= 0) {
      return "Error: ID buku tidak valid";
    }
    for (Book b : books) {
      if (b.id == idBuku) {
        if (b.tersedia) {
          return ("Error: Buku \"" + b.judul + "\" tidak sedang dipinjam");
        }
        String peminjam = b.peminjam;
        b.tersedia = true;
        b.peminjam = null;
        history.add(
            "[KEMBALI] Buku \"" +
                b.judul +
                "\" dikembalikan oleh " +
                peminjam);
        return ("Buku \"" +
            b.judul +
            "\" berhasil dikembalikan oleh " +
            peminjam);
      }
    }
    return "Error: Buku dengan ID " + idBuku + " tidak ditemukan";
  }

  @Override
  public String getHistory() throws RemoteException {
    if (history.isEmpty()) {
      return "=== HISTORY ===\nBelum ada aktivitas.";
    }
    StringBuilder sb = new StringBuilder();
    sb.append("=== HISTORY ===\n");
    for (String h : history) {
      sb.append("- ").append(h).append("\n");
    }
    return sb.toString();
  }

  static class Book implements java.io.Serializable {

    int id;
    String judul;
    String penulis;
    boolean tersedia;
    String peminjam;

    Book(int id, String judul, String penulis) {
      this.id = id;
      this.judul = judul;
      this.penulis = penulis;
      this.tersedia = true;
      this.peminjam = null;
    }
  }
}
