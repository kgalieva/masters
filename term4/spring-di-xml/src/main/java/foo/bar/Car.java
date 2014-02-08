package foo.bar;

public class Car {

    AbstractDriver driver;

    public Car(AbstractDriver driver) {
        this.driver = driver;
    }

    public void driveTo(String city) {
        if (driver != null) {
            System.out.println("Drive to " + city);
        }
    }
}
