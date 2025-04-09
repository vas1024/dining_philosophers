package diningPhilosophers;

import java.time.Duration;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

import static diningPhilosophers.Main.EATINGTIME;
import static diningPhilosophers.Main.PERSONS;

public class Philosopher extends Thread {
  int name;
  ReentrantLock leftSpoon;
  ReentrantLock rightSpoon;

  public Philosopher(int name, ReentrantLock[] spoons) {
    this.name = name;
    this.rightSpoon = spoons[name];
    this.leftSpoon = spoons[(name + 1) % PERSONS];
    System.out.println("Hello, I am philosopher " + name
            + "  my right spoon is " + rightSpoon
            + "  my left spoon is " + leftSpoon);
  }

  @Override
  public void run() {
    ReentrantLock firstSpoon;
    ReentrantLock secondSpoon;
    boolean randomBoolean;

    while (true) {
      randomBoolean = ThreadLocalRandom.current().nextBoolean();
      if (randomBoolean) {
        firstSpoon = leftSpoon;
        secondSpoon = rightSpoon;
      } else {
        firstSpoon = rightSpoon;
        secondSpoon = leftSpoon;
      }
      if ( firstSpoon.tryLock()) {
        try {
          System.out.println("philosopher " + name + " got first spoon");
          if ( secondSpoon.tryLock()) {
            try {
              System.out.println("philosopher " + name + " got second spoon");
              System.out.println("philosopher " + name + " EATING");

              SleepUtils.sleepSec(EATINGTIME);

            } finally {
              System.out.println("philosopher " + name + " finally released second spoon");
              secondSpoon.unlock();
            }
          } else {
            System.out.println("philosopher " + name + " released first spoon because can not get second");
          }
        } finally {
          System.out.println("philosopher " + name + " finally released first spoon");
          firstSpoon.unlock();
        }
      } else {
        System.out.println("philosopher " + name + " can not get first spoon");
      }
      SleepUtils.sleepSec(2);
    }
  }
}
