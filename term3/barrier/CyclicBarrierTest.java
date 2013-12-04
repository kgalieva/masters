import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierTest {

    public static void main(String... args) {
        final AtomicInteger result = new AtomicInteger();
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println("Total result: " + result);
            }
        });
        new Thread(new Task(barrier, result, 1, 3)).start();
        new Thread(new Task(barrier, result, 4, 6)).start();
        new Thread(new Task(barrier, result, 7, 9)).start();
    }
}