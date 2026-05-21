import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MahasiswaInterface extends Remote {
    String tambah(String nim, String nama, String alamat) throws RemoteException;
    String lihat() throws RemoteException;
    String cari(String nim) throws RemoteException;
    String update(String nim, String namaBaru, String alamatBaru) throws RemoteException;
    String delete(String nim) throws RemoteException;
}
