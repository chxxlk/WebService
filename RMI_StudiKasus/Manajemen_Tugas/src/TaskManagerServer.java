import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TaskManagerServer {

  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      TaskManagerImplementation obj = new TaskManagerImplementation();
      Naming.rebind("//localhost/TaskManager", obj);
      System.out.println("Task Manager Server is ready");
    } catch (Exception e) {
      System.err.println("Server exception : " + e.getMessage());
      e.printStackTrace();
    }
  }
}
