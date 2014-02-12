package foo.bar;

public class Driver implements AbstractDriver{

    private String name;

    public Driver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
