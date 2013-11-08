import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunnableFuture<String> f = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //типа усердно выполняем сложную задачу
                Thread.sleep(1000);
                return Thread.currentThread().getName();
            }
        });
        new Thread(f).start();
        //get дождется завершения работы потока
        System.out.println(f.get());
    }
}
