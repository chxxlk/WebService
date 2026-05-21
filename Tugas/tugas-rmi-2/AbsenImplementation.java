import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AbsenImplementation
    extends UnicastRemoteObject
    implements AbsenInterface {

  private List<Students> students;
  private List<Attendance> attendanceRecords;

  static class Students implements java.io.Serializable {

    int id;
    String name, studentIds;

    public Students(int id, String name, String studentIds) {
      this.id = id;
      this.name = name;
      this.studentIds = studentIds;
    }
  }

  static class Attendance implements java.io.Serializable {
    int studentId;
    String studentName;
    String studentNim;
    String timestamp;
    String status;

    public Attendance(int studentId, String studentName, String studentNim, String status) {
      this.studentId = studentId;
      this.studentName = studentName;
      this.studentNim = studentNim;
      this.status = status;
      this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
  }

  private int nextId;
  private static final String VALID_USER = "admin";
  private static final String VALID_PASS = "admin123";

  protected AbsenImplementation() throws RemoteException {
    super();
    students = new ArrayList<>();
    attendanceRecords = new ArrayList<>();
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

  // Hapus Mahasiswa
  @Override
  public String removeStudent(int id) throws RemoteException {
    if (students.isEmpty()) {
      return "Error: Tidak ada mahasiswa untuk dihapus!";
    }

    Students target = null;
    for (Students s : students) {
      if (s.id == id) {
        target = s;
        break;
      }
    }

    if (target == null) {
      return "Error: Mahasiswa dengan ID " + id + " tidak ditemukan!";
    }

    students.remove(target);
    return "Mahasiswa \"" + target.name + "\" (ID: " + target.id + ") berhasil dihapus.";
  }

  // Absen Mahasiswa
  @Override
  public String markAttendance(int studentId) throws RemoteException {
    if (students.isEmpty()) {
      return "Error: Tidak ada mahasiswa terdaftar!";
    }

    Students target = null;
    for (Students s : students) {
      if (s.id == studentId) {
        target = s;
        break;
      }
    }

    if (target == null) {
      return "Error: Mahasiswa dengan ID " + studentId + " tidak ditemukan!";
    }

    Attendance record = new Attendance(target.id, target.name, target.studentIds, "Hadir");
    attendanceRecords.add(record);
    return "Absen berhasil: " + target.name + " (" + target.studentIds + ") - Hadir pada " + record.timestamp;
  }

  // Lihat Daftar Absen
  @Override
  public String showAttendance() throws RemoteException {
    if (attendanceRecords.isEmpty()) {
      return "Daftar Absensi\nBelum ada data absensi.";
    }

    StringBuilder sb = new StringBuilder();
    sb.append("Daftar Absensi\n");
    int index = 1;
    for (Attendance a : attendanceRecords) {
      sb.append(index++)
          .append(". ")
          .append(a.studentName)
          .append(" - ")
          .append(a.studentNim)
          .append(" | Status: ")
          .append(a.status)
          .append(" | Waktu: ")
          .append(a.timestamp);
      sb.append("\n");
    }
    return sb.toString();
  }
}
