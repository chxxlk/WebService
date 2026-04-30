import java.rmi.Naming;
import java.util.List;

public class TaskManagerClient {

  public static void main(String[] args) {
    try {
    TaskManagerInterface stub = (TaskManagerInterface) Naming.lookup("//localhost/TaskManager");
    stub.addTask("Finish RMI Tutorial");
    List<String> tasks = stub.getTask();
    System.out.println("Tasks : " + tasks);

    stub.removeTask("Finish RMI Tutorial");
    tasks = stub.getTask();
    System.out.println("Task after removal : " + tasks);
    } catch (Exception e){
      System.err.println("Client Exception: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
