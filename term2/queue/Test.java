package term2.queue;

public class Test {
	
	public static void testQueue(Queue q){
		for(int i = 0; i < 5; i++) {
			q.offer(i);
		}
		for(int i = 0; i < 6; i++) {
			System.out.println(q.poll());
		}
	}
	
	public static void main(String[] args) {
		testQueue(new LinkedQueue());
		testQueue(new QueueWithCounter(4));
		testQueue(new QueueWithoutCounter(4));
	}

}
