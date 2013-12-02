import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    final CyclicBarrier barrierTest;
    List<Integer> list=new ArrayList<>();

    CyclicBarrierTest(){
        barrierTest = new CyclicBarrier(3,new Runnable() {
            public void run() {
                addListvalue();
            }
        });
        new Thread(new Task(1,3)).start();
        new Thread(new Task(4,6)).start();
        new Thread(new Task(7,9)).start();
    }

    void  add(int start,int end){
        int sum=0;
        for(int s=start;s<=end;s++){
            sum+=s;
        }
        list.add(sum);
        System.out.println("Per Thread Addition:"+sum);
    }
    void addListvalue(){
        int total=0;
        for (Integer aList : list) {
            total += aList;
        }
        System.out.println("Total addtion:"+total);
    }

    class Task implements Runnable {
        int start;
        int end;
        Task(int start,int end) {
            this.start = start;
            this.end=end;
        }
        public void run() {
            add(start,end);
            try {
                barrierTest.await();
            } catch (InterruptedException | BrokenBarrierException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String... args){
        new CyclicBarrierTest();
    }
}