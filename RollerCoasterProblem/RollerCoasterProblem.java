import java.util.Scanner;

public class RollerCoasterProblem {

  static int n;
  static int c;
  static int m;

  public static void main(String[] args) {
    getInput();

    // init cars
    Car[] car = new Car[m];
    for (int i = 0; i < m; i++) {
      car[i] = new Car(i);
    }

    // init passengers
    Passenger[] passenger = new Passenger[n];
    for (int i = 0; i < n; i++) {
      passenger[i] = new Passenger(i);
    }
  }

  static void getInput() {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter n (no. passengers): ");
    n = sc.nextInt();
    System.out.print("Enter C (car capacity): ");
    c = sc.nextInt();
    System.out.print("Enter m (no. cars): ");
    m = sc.nextInt();

    sc.close();
  }
}