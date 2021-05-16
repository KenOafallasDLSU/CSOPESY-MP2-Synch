import java.util.concurrent.*;

public class Passenger implements Runnable {

  int i;

  public Passenger(int i) {
    this.i = i;
  }

  void board() {
    System.out.println("Passenger " + i + "is boarding.");
  }

  void unboard() {
    System.out.println("Passenger " + i + "is unboarding.");
  }

  @Override
  public void run() {
    System.out.println("Passenger " + i + " thread started.");
  }

}
