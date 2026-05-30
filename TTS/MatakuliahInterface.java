import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatakuliahInterface extends Remote {
    String tambah(String kode, String nama, String sks) throws RemoteException;
    String lihat() throws RemoteException;
    String cari(String kode) throws RemoteException;
    String update(String kode, String namaBaru, String sksBaru)
        throws RemoteException;
    String delete(String kode) throws RemoteException;
}
