import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

class Task implements Runnable {
    private int start = 0;
    private int end = 0;
    private CyclicBarrier barrier;
    private AtomicInteger result;

    Task(CyclicBarrier barrier, AtomicInteger result, int start, int end) {
        this.start = start;
        this.end = end;
        this.barrier = barrier;
        this.result = result;
    }

    public void run() {
        add(start, end);
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            return;
        }
    }

    void add(int start, int end) {
        int sum = 0;
        for (int s = start; s <= end; s++) {
            sum += s;
        }
        result.addAndGet(sum);
        System.out.println("Per Thread Addition: " + sum);
    }
}