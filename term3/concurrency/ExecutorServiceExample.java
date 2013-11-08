
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample implements Callable<String> {

    @Override
    public String call() throws Exception {
        //типа усердно выполняем сложную задачу
        Thread.sleep(1000);
        return Thread.currentThread().getName();
    }

    public static void main(String args[]){
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<String>> list = new ArrayList<>();
        Callable<String> callable = new ExecutorServiceExample();
        for(int i=0; i < 40; i++){
            //сабмитим задачу, чтобы она запустилась на выполнение в пуле
            Future<String> future = executor.submit(callable);
            //сохраняем Future чтобы потом получить результат
            list.add(future);
        }
        for(Future<String> future : list){
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

}