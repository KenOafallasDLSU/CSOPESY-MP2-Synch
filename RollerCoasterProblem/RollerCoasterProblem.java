import java.util.Scanner;

public class RollerCoasterProblem {
  public static void main(String[] args) throws InterruptedException {

    Shared s = initShared();

    // start cars and passengers
    Thread[] car = new Thread[s.m];
    for (int i = 0; i < s.m; i++) {
      car[i] = new Thread(new Car(i, s));
      car[i].start();
    }
    Thread[] passenger = new Thread[s.n];
    for (int i = 0; i < s.n; i++) {
      passenger[i] = new Thread(new Passenger(i, s));
      passenger[i].start();
    }

    // join threads
    for (int i = 0; i < s.m; i++) {
      try {
        car[i].join();
      } catch (InterruptedException e) {
        System.out.println(" Car " + i + "could not join thread.");
      }
    }
    for (int i = 0; i < s.n; i++) {
      try {
        passenger[i].join();
      } catch (InterruptedException e) {
        System.out.println("Passenger " + i + "could not join thread.");
      }
    }

    // if (s.carTurn == s.m) {
    //   System.out.println("All rides completed");
    // }

    
  }

  static Shared initShared() {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter n (no. passengers): ");
    int n = sc.nextInt();
    System.out.print("Enter C (car capacity): ");
    int c = sc.nextInt();
    System.out.print("Enter m (no. cars): ");
    int m = sc.nextInt();

    sc.close();
    return new Shared(n, m, c);
  }
}