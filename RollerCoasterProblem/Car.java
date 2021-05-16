import java.util.concurrent.*;
import java.util.Random;

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
    System.out.println("Loading passenger into car " + i + ".");
    s.mutex.release();
  }

  void unload() {
    System.out.println("Unloading passenger from car " + i + ".");
    s.mutex2.release();
  }

  void runRide() {
    System.out.println("Car " + i + " ride has started! Woooh!");
    Thread.sleep(randomizer.nextInt(5000));
  }

  int next(int current) {
    int next = (current + 1) / s.m;
    return next;
  }

  @Override
  public void run() {
    while(true) {
      s.loadingArea[this.i].acquire();
      this.load();
      s.boardQueue.release(s.c);
      s.allAboard.acquire();
      s.loadingArea[this.next(this.i)].release();

      this.runRide();

      s.unloadingArea[this.i].acquire();
      this.unload();
      s.unboardQueue.release(s.c);
      s.allAshore.acquire();
      s.unloadingArea[this.next(this.i)].release();
    }
  }
}
