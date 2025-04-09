package diningPhilosophers;

import java.time.Duration;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Main {
  public static final int PERSONS = 5;
  public static final int EATINGTIME = 10;
  public static void main(String[] args) {
    // philosopher 0 has spoon 0 at right and spoon 1 at left
    // number of spoons = number of persons
/*
    Semaphore[] spoons = new Semaphore[PERSONS];
    for( int i = 0; i < PERSONS; i++ ){
      spoons[i] = new Semaphore(1);
    }
*/
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