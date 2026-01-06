package diningPhilosophers.service;

import diningPhilosophers.Philosopher;
import diningPhilosophers.SleepUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class EatingService {

  private ReentrantLock[] spoons;
  private Philosopher[] philosophers;
  // philosopher 0 has spoon 0 at right and spoon 1 at left
  // number of spoons = number of persons

  public static final int PERSONS = 5;
  public static final int EATING_TIME = 10;
  public static final int POLITE_TIME = 2;

  @PostConstruct
  public void initAndStartSimulation() {
    System.out.println("=== Initializing Dining Philosophers ===");

    spoons = new ReentrantLock[PERSONS];
    for(int i = 0; i < PERSONS; i++) {
      spoons[i] = new ReentrantLock();
    }

    philosophers = new Philosopher[PERSONS];
    for(int i = 0; i < PERSONS; i++) {
      philosophers[i] = new Philosopher(i, spoons);
    }


    for(int i = 0; i < PERSONS; i++) {
      philosophers[i].start();
      SleepUtils.sleepSec(2);
    }

    System.out.println("=== All " + PERSONS + " philosophers are running ===");
  }

  public List<Map<String, Object>> getPhilosophersForChart() {
    List<Map<String, Object>> result = new ArrayList<>();

    for (int i = 0; i < PERSONS; i++) {
      Philosopher p = philosophers[i];

      boolean isEating = "EATING".equals( p.status );
      result.add(Map.of(
              "id", i,
              "name", "Философ " + i,
              "isEating", isEating,
              "meals", 100
      ));
    }
    return result;
  }

}
