public class Car {
  int i;

  public Car(int i) {
    this.i = i;
  }

  void load() {
    System.out.println("Loading passenger into car " + i + ".");
  }

  void unload() {
    System.out.println("Unoading passenger from car " + i + ".");
  }
}
