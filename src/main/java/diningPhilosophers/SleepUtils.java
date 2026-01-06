package diningPhilosophers;


public class SleepUtils {
  public static void sleepSec(int seconds) {
    try {
      Thread.sleep(seconds * 1000L );
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Важно сохранить статус прерывания
      throw new RuntimeException("Sleep interrupted", e);
    }
  }
}