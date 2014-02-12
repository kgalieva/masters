package foo.bar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("aircraft")
public class Aircraft {

    @Autowired
    @Qualifier("firstPilot")
    private AbstractDriver firstPilot;

    @Autowired
    @Qualifier("secondPilot")
    private AbstractDriver secondPilot;

    public void flyingTo(String city) {
        if(firstPilot != null && secondPilot != null) {
            System.out.println("Flying to " + city);
        }
    }


}
