package foo.bar;

import java.util.List;

public class Train {

    private Driver driver;

    private List<Wagon> wagons;

    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void driveTo(String city) {
        if (driver != null) {
            System.out.println("Go on train to " + city);
            System.out.println("Wagon num " + wagons.get(wagons.size() - 1).getNumber());
        }
    }
}
