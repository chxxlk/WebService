public class App {
  public static void main(String[] args) throws Exception {
    new Thread(() -> {
      try {
        RMIServer.main(null);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();

    try {
      Thread.sleep(2000);
      RMIClient.main(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
