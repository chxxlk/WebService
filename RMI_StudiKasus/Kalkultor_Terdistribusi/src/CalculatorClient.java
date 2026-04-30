import java.rmi.Naming;

public class CalculatorClient {

  public static void main(String[] args) {
    try {
      CalculatorInterface stub = (CalculatorInterface) Naming.lookup("//localhost/calculatorservice");
      System.out.println("Addtion : " + stub.add(10, 10));
      System.out.println("Subtrack : " + stub.subtract(10, 10));
      System.out.println("Multiplication : " + stub.multiply(10, 10));
      System.out.println("Division : " + stub.divide(10, 10));
    } catch (Exception e) {
      System.err.println("Client exception: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
