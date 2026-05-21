import java.rmi.RemoteException;
import java.rmi.Remote;

public interface AbsenInterface extends Remote {
  boolean login(String username, String password) throws RemoteException;

  String addStudents(String studentName, String studentId) throws RemoteException;

  String showStudents() throws RemoteException;

  String searchStudent(String keyword) throws RemoteException;
  String removeStudent(int id) throws RemoteException;
  String markAttendance(int studentId) throws RemoteException;
  String showAttendance() throws RemoteException;
}
