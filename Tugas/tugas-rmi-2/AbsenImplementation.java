import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AbsenImplementation
    extends UnicastRemoteObject
    implements AbsenInterface {

  private List<Students> students;

  static class Students implements java.io.Serializable {

    int id;
    String name, studentIds;

    public Students(int id, String name, String studentIds) {
      this.id = id;
      this.name = name;
      this.studentIds = studentIds;
    }
  }

  private int nextId;
  private static final String VALID_USER = "admin";
  private static final String VALID_PASS = "admin123";

  protected AbsenImplementation() throws RemoteException {
    super();
    students = new ArrayList<>();
    nextId = 1;
  }

  // Admin Auth
  @Override
  public boolean login(String username, String password)
      throws RemoteException {
    if (username == null || password == null)
      return false;
    if (username.trim().isEmpty() || password.trim().isEmpty())
      return false;
    return VALID_USER.equals(username) && VALID_PASS.equals(password);
  }

  // Tambah Mahasiswa
  @Override
  public String addStudents(String studentName, String studentId)
      throws RemoteException {
    if (studentName == null || studentName.trim().isEmpty())
      return "Error: Nama tidak boleh kosong!";
    if (studentId == null || studentId.trim().isEmpty())
      return "Error: NIM tidak boleh kosong!";

    for (Students s : students) {
      if (s.studentIds.equalsIgnoreCase(studentId.trim())) {
        return ("Error: NIM \"" + studentId.trim() + "\" telah tersedia");
      }
    }
    Students student = new Students(
        nextId++,
        studentName.trim(),
        studentId.trim());
    students.add(student);
    return ("Mahasiswa berhasil di tambahkan (ID: " +
        student.id +
        " - NIM : " +
        student.studentIds +
        ")");
  }

  // Lihat Mahasiswa
  @Override
  public String showStudents() throws RemoteException {
    if (students.isEmpty())
      return "Daftar Mahasiswa\nTidak ada Mahasiswa.";
    StringBuilder sb = new StringBuilder();
    sb.append("Daftar Mahasiswa \n");
    for (Students s : students) {
      sb
          .append(s.id)
          .append(". ")
          .append(s.name)
          .append(" - ")
          .append(s.studentIds);
      sb.append("\n");
    }
    return sb.toString();
  }

  // Cari Mahasiswa
  @Override
  public String searchStudent(String keyword) throws RemoteException {
    if (keyword == null || keyword.trim().isEmpty())
      return "Error: Pencarian tidak boleh kosong!";
    StringBuilder sb = new StringBuilder();
    sb.append("Hasil Pencarian \"").append(keyword).append("\"\n");

    boolean found = false;
    for (Students s : students) {
      if (s.name.toLowerCase().contains(keyword.toLowerCase()) ||
          s.studentIds.toLowerCase().contains(keyword.toLowerCase())) {
        sb
            .append(s.id)
            .append(". ")
            .append(s.studentIds)
            .append(" - ")
            .append(s.name);
        sb.append("\n");
        found = true;
      }
    }
    if (!found)
      return ("Error : Mahasiswa dengan Nama : " + keyword + " tidak ditemukan");

    return sb.toString();
  }
  // TODO: add other implements
}
