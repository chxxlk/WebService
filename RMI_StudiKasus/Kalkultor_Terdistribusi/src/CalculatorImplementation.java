import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImplementation extends UnicastRemoteObject implements CalculatorInterface {
  protected CalculatorImplementation() throws RemoteException {
    super();
  }

  @Override
  public double add(double a, double b) throws RemoteException {
    return a + b;
  }

  @Override
  public double subtract(double a, double b) throws RemoteException {
    return a - b;
  }

  @Override
  public double multiply(double a, double b) throws RemoteException {
    return a * b;
  }

  @Override
  public double divide(double a, double b) throws RemoteException {
    return a / b;
  }
}
