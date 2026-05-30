import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KstInterface extends Remote {
    String tambah(String nim, String kodeMK) throws RemoteException;
    String lihat() throws RemoteException;
    String cari(String nim) throws RemoteException;
    String delete(String nim, String kodeMK) throws RemoteException;
}
