import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CounterImplementation extends UnicastRemoteObject implements CounterInterface {
  public int counterValue = 0;

  protected CounterImplementation() throws RemoteException {
    super();
  }

  @Override
  public int getCounterValue() throws RemoteException {
    return counterValue;
  };

  @Override
  public int incCounterValue() throws RemoteException {
    counterValue += 1;
    System.out.println("Counter di increment menjadi = " + counterValue);
    return counterValue;
  };

  @Override
  public int decCounterValue() throws RemoteException {
    counterValue -= 1;
    System.out.println("Counter di decrement menjadi = " + counterValue);
    return counterValue;
  };

  @Override
  public int resetCounterValue() throws RemoteException {
    counterValue = 0;
    System.out.println("Counter di reset menjadi = " + counterValue);
    return counterValue;
  }
}
