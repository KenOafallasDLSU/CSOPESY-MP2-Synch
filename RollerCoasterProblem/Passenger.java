import java.util.concurrent.*;

public class Passenger implements Runnable {

  private int i;
  private Shared s;

    // 1 boardQueue.wait ()       // wait for car before boarding
    // 2 board ()
    // 3
    // 4 mutex.wait ()            
    // 5  boarders += 1
    // 6  if boarders == C :
    // 7    allAboard.signal ()
    // 8    boarders = 0
    // 9 mutex.signal ()
    // 10
    // 11 unboardQueue.wait ()     // wait for car to stop before leaving
    // 12 unboard ()
    // 13
    // 14 mutex2.wait ()
    // 15   unboarders += 1
    // 16   if unboarders == C :    // last passenger to board
    // 17     allAshore.signal ()   // signal car
    // 18     unboarders = 0        // reset passenger counter
    // 19 mutex2.signal ()


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
      s.boardQueue.acquire();    // wait for car
      board();                // board when car arrives
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
