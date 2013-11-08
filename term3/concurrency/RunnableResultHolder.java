import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public class RunnableResultHolder {

    //Можно написать свой holder результата
    private final static AtomicReference<String> name = new AtomicReference<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                name.set(Thread.currentThread().getName());
            }
        });
        t.start();
        //ждем завершения работы потока
        t.join();
        System.out.println(name.get());
    }


}
