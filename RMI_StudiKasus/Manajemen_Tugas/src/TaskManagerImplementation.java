import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerImplementation extends UnicastRemoteObject implements TaskManagerInterface {

  private List<String> tasks;

  protected TaskManagerImplementation() throws RemoteException {
    super();
    tasks = new ArrayList<>();
  }

  @Override
  public void addTask(String task) throws RemoteException {
    if (task == null || task.trim().isEmpty()) {
      throw new RemoteException("Task tidak boleh kosong.");
    }
    tasks.add(task.trim());
  }

  @Override
  public void removeTask(String task) throws RemoteException {
    if (task == null || !tasks.contains(task)) {
      throw new RemoteException("Task tidak ditemukan.");
    }
    tasks.remove(task);
  }

  @Override
  public void removeTaskByIndex(int index) throws RemoteException {
    if (index < 0 || index >= tasks.size()) {
      throw new RemoteException("Index task tidak valid: " + index);
    }
    tasks.remove(index);
  }

  @Override
  public List<String> getTask() throws RemoteException {
    return new ArrayList<>(tasks);
  }
}
