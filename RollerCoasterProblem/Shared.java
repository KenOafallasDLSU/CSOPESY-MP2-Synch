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

class Shared {

  int n; // no. passengers
  int m; // no. cars
  int c; // car capacity
  int boarders = 0;
  int unboarders = 0;

  boolean finished;
  int carRunCount = 0;
  
  Semaphore mutex = new Semaphore(1);
  Semaphore mutex2 = new Semaphore(1);
  Semaphore allAboard = new Semaphore(0);
  Semaphore allAshore = new Semaphore(0);
  Semaphore boardQueue = new Semaphore(0);
  Semaphore unboardQueue = new Semaphore(0);

  Semaphore[] loadingArea;
  Semaphore[] unloadingArea;


  public Shared(int n, int m, int c) {
    this.n = n;
    this.m = m;
    this.c = c;

    this.finished = false;

    this.loadingArea = new Semaphore[m];
    this.unloadingArea = new Semaphore[m];
    for (int i = 0; i < m; i++) {
      this.loadingArea[i] = new Semaphore(0);
      this.unloadingArea[i] = new Semaphore(0);
    }

    //allow first car to load/unload immediately
    this.loadingArea[0].release();
    this.unloadingArea[0].release();
  }
}
