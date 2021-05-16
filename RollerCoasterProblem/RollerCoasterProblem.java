import java.util.Scanner;

class Shared {
  static int n;
  static int c;
  static int m;

  private Shared() {
    // not called
  }
}

public class RollerCoasterProblem {
  public static void main(String[] args) throws InterruptedException {

    getInput();

    // cars
    Thread[] car = new Thread[Shared.m];
    for (int i = 0; i < Shared.m; i++) {
      car[i] = new Thread(new Car(i));
      car[i].start();
    }
    for (int i = 0; i < Shared.m; i++) {
      try {
        car[i].join();
      } catch (InterruptedException e) {
        System.out.println("Car " + i + "could not join thread.");
      }
    }

    // passengers
    Thread passenger[] = new Thread[Shared.n];
    for (int i = 0; i < Shared.n; i++) {
      passenger[i] = new Thread(new Passenger(i));
      passenger[i].start();
    }
    for (int i = 0; i < Shared.n; i++) {
      try {
        passenger[i].join();
      } catch (InterruptedException e) {
        System.out.println("Passenger " + i + "could not join thread.");
      }
    }

  }

  static void getInput() {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter n (no. passengers): ");
    Shared.n = sc.nextInt();
    System.out.print("Enter C (car capacity): ");
    Shared.c = sc.nextInt();
    System.out.print("Enter m (no. cars): ");
    Shared.m = sc.nextInt();

    sc.close();
  }
}