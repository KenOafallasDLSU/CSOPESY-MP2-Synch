import java.util.concurrent.*;
import java.util.Random;
import java.time.LocalTime;

public class Car implements Runnable {

  private int i;
  private Shared s;
  private Random randomizer;

  public Car(int i, Shared s) {
    this.i = i;
    this.s = s;
    this.randomizer = new Random();
  }

  void load() {
    System.out.println("[" + java.time.LocalTime.now() + "] " + "Loading passenger into car " + this.i + ".");
    s.mutex.release();
  }

  void unload() {
    System.out.println("[" + java.time.LocalTime.now() + "] " + "Unloading passenger from car " + this.i + ".");
    s.mutex2.release();
  }

  void runRide() {
    System.out.println("[" + java.time.LocalTime.now() + "] " + "Car " + this.i + " ride has started! Woooh!");
    //int rando = randomizer.nextInt(5000);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  int next() {
    int next = (this.i + 1) % s.m;
    //System.out.println("This: " + this.i);
    //System.out.println("Next: " + next);
    return next;
  }

  @Override
  public void run() {
    System.out.println("[" + java.time.LocalTime.now() + "] " + "Car " + this.i + " thread started.");
    while (!s.finished) {
      try {
        s.loadingArea[this.i].acquire();
        //System.out.println("Car " + this.i + " loading permits: " + s.loadingArea[this.i].availablePermits());
        this.load();
        s.boardQueue.release(s.c);
        s.allAboard.acquire();
        System.out.println("[" + java.time.LocalTime.now() + "] " + "All aboard car [" + this.i + "]");
        s.loadingArea[this.next()].release();
        //System.out.println("Car " + this.next() + " loading permits: " + s.loadingArea[this.next()].availablePermits());

        this.runRide();

        s.unloadingArea[this.i].acquire();
        //System.out.println("Car " + this.i + " unloading permits: " + s.unloadingArea[this.i].availablePermits());
        this.unload();
        s.unboardQueue.release(s.c);
        s.allAshore.acquire();
        System.out.println("[" + java.time.LocalTime.now() + "] " + "All ashore car [" + this.i + "]");
        s.unloadingArea[this.next()].release();
        //System.out.println("Car " + this.next() + " unloading permits: " + s.unloadingArea[this.next()].availablePermits());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      s.carRunCount++;

      if (s.carRunCount == s.n / s.c) {
        System.out.println("[" + java.time.LocalTime.now() + "] " + "All rides completed");
        System.exit(0);
      }
      
    }
    //System.out.println("[" + java.time.LocalTime.now() + "] " + "All rides completed");
  }
}
