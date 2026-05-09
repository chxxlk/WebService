import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CounterInterface extends Remote {
  int resetCounterValue() throws RemoteException;

  int incCounterValue() throws RemoteException;

  int decCounterValue() throws RemoteException;

  int getCounterValue() throws RemoteException;
}
