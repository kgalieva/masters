package foo.bar;

import foo.bar.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(SpringConfig.class);
        context.refresh();
        Driver driver = (Driver)context.getBean("driverVasya");
        Car car = context.getBean(Car.class);
        car.driveTo("Moscow");
        System.out.println(driver.getName());

        Train train = context.getBean(Train.class);
        train.driveTo("Magadan");

        Aircraft aircraft = context.getBean(Aircraft.class);
        aircraft.flyingTo("NY");

        System.out.println("Test singleton scope: " +
                (context.getBean("driverVasya") == context.getBean("driverVasya")));

        System.out.println("Test prototype scope: " +
                (context.getBean(Key.class) == context.getBean(Key.class)));
    }
}
