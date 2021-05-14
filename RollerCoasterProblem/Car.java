import java.util.concurrent.*;

public class Car implements Runnable {

  int i;

  public Car(int i) {
    this.i = i;
  }

  void load() {
    System.out.println("Loading passenger into car " + i + ".");
  }

  void unload() {
    System.out.println("Unoading passenger from car " + i + ".");
  }

  @Override
  public void run() {
    System.out.println("Car " + i + " thread started.");
  }
}
