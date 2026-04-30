import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {

  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      CalculatorImplementation obj = new CalculatorImplementation();

      Naming.rebind("//localhost/calculatorservice", obj);
      System.out.println("Calculator server is ready");
    } catch (Exception e) {
      System.err.println("server exception: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
