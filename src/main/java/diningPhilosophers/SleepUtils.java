package diningPhilosophers;

import java.time.Duration;

public class SleepUtils {
  public static void sleepSec(int seconds) {
    try {
      Thread.sleep(Duration.ofSeconds(seconds));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Важно сохранить статус прерывания
      throw new RuntimeException("Sleep interrupted", e);
    }
  }
}