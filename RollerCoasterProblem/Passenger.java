import java.util.concurrent.*;

public class Passenger implements Runnable {

  private int i;
  private Shared s;

  public Passenger(int i, Shared s) {
    this.i = i;
    this.s = s;
  }

  void board() {
    System.out.println("Passenger " + i + "is boarding.");

    try {
      s.mutex.acquire();
      s.boarders += 1;

      if (s.boarders == s.c) {
        s.allAboard.release();
        s.boarders = 0;
      }

      s.mutex.release();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  void unboard() {
    System.out.println("Passenger " + i + "is unboarding.");

    try {
      s.mutex2.acquire();
      s.unboarders += 1;

      if (s.unboarders == s.c) {
        s.allAshore.release();
        s.unboarders = 0;
      }

      s.mutex2.release();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    System.out.println("Passenger " + i + " thread started.");

    try {
      s.boardQueue.acquire(); // wait for car
      board(); // board when car arrives
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      Thread.sleep(10);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      s.unboardQueue.acquire();
      unboard();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}