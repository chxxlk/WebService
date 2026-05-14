import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LibraryInterface extends Remote {
    boolean login(String username, String password) throws RemoteException;
    String tambahBuku(String judul, String penulis) throws RemoteException;
    String tampilBuku() throws RemoteException;
    String cariBuku(String keyword) throws RemoteException;
    String hapusBuku(int id) throws RemoteException;
    String pinjamBuku(int idBuku, String peminjam) throws RemoteException;
    String kembalikanBuku(int idBuku) throws RemoteException;
    String getHistory() throws RemoteException;
}
