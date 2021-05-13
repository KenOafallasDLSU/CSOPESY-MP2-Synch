public class Passenger {

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

}
