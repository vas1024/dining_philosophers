package diningPhilosophers.config;

import diningPhilosophers.Philosopher;
import diningPhilosophers.SleepUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@Component
public class DataInitializer {

  public static final int PERSONS = 5;
  public static final int EATINGTIME = 10;

  public static void main(String[] args) {
    // philosopher 0 has spoon 0 at right and spoon 1 at left
    // number of spoons = number of persons

    ReentrantLock[] spoons = new ReentrantLock[PERSONS];
    for( int i = 0; i < PERSONS; i++ ){
      spoons[i] = new ReentrantLock();
    }


    Philosopher[] philosophers = new Philosopher[PERSONS];
    for( int i = 0; i < PERSONS; i++ ){
      philosophers[i] = new Philosopher( i, spoons );
    }
    for( int i = 0; i < PERSONS; i++ ){
      philosophers[i].start();
      SleepUtils.sleepSec(2);
    }
  }


}
