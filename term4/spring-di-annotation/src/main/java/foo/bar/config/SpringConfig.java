package foo.bar.config;

import foo.bar.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("foo.bar")
public class SpringConfig {

    @Bean
    public Driver driverPeter() {
        return new Driver("Peter");
    }

    @Bean
    public Driver driverVasya() {
        return new Driver("Vasya");
    }

    @Bean
    public Driver driverTanya() {
        return new Driver("Tanya");
    }

    @Bean
    public Wagon wagon2(){
        Wagon wagon = new Wagon();
        wagon.setNumber("2");
        return wagon;
    }

    @Bean
    public Wagon wagon1(){
        Wagon wagon = new Wagon();
        wagon.setNumber("1");
        return wagon;
    }

    @Bean
    public Wagon wagon3(){
        Wagon wagon = new Wagon();
        wagon.setNumber("3");
        return wagon;
    }

    @Bean
    public Wagon wagon4(){
        Wagon wagon = new Wagon();
        wagon.setNumber("4");
        return wagon;
    }

    @Bean
    public Wagon wagon5(){
        Wagon wagon = new Wagon();
        wagon.setNumber("5");
        return wagon;
    }

    @Bean
    public Train train() {
        Train train = new Train();
        train.setDriver(driverTanya());
        List<Wagon> wagons = new ArrayList<>();
        wagons.add(wagon1());
        wagons.add(wagon3());
        wagons.add(wagon4());
        wagons.add(wagon5());
        train.setWagons(wagons);
        return train;
    }
}
