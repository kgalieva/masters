package foo.bar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {

    AbstractDriver driver;

    @Autowired
    public Car(@Qualifier("driverVasya") AbstractDriver driver) {
        this.driver = driver;
    }

    public void driveTo(String city) {
        if (driver != null) {
            System.out.println("Drive to " + city + " with " + driver.getName());
        }
    }
}
