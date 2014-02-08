package foo.bar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Driver driver = (Driver)context.getBean("driverVasya");
        Car car = context.getBean(Car.class);
        car.driveTo("Moscow");
        System.out.println(driver.getName());

        Train train = context.getBean(Train.class);
        train.driveTo("Magadan");
    }
}
