/***************************************************************
Names:  CHONG, Dianne
        GO, Thea
        MARCELO, Jan Uriel
        OAFALLAS, Kenneth
        TAN, Darren
Group: 1
Section: S15
***************************************************************/

import java.util.concurrent.*;
import java.util.Random;
import java.time.LocalTime;

public class Passenger implements Runnable {

  private int i;
  private Shared s;
  private Random randomizer;

  public Passenger(int i, Shared s) {
    this.i = i;
    this.s = s;
    this.randomizer = new Random();
  }

  void board() {
    System.out.println("[" + java.time.LocalTime.now() + "] " + "Passenger " + i + " is boarding.");

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
    System.out.println("[" + java.time.LocalTime.now() + "] " + "Passenger " + i + " is unboarding.");

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
    System.out.println("[" + java.time.LocalTime.now() + "] " + "Passenger " + i + " thread started.");

    int rando = randomizer.nextInt(1500);
    try {
      Thread.sleep(rando);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    System.out.println("[" + java.time.LocalTime.now() + "] " + "Passenger " + i + " in line for ride.");
    
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
