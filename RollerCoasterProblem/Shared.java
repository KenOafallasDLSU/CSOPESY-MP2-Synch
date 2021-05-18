import java.util.concurrent.*;

class Shared {

  int n; // no. passengers
  int m; // no. cars
  int c; // car capacity
  int boarders = 0;
  int unboarders = 0;
  Semaphore mutex = new Semaphore(1);
  Semaphore mutex2 = new Semaphore(1);
  Semaphore allAboard = new Semaphore(0);
  Semaphore allAshore = new Semaphore(0);
  Semaphore boardQueue = new Semaphore(0);
  Semaphore unboardQueue = new Semaphore(0);

  public Shared(int n, int m, int c) {
    this.n = n;
    this.m = m;
    this.c = c;
  }
}