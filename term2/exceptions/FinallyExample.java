package term2.exceptions;

import java.util.Random;

public class FinallyExample {

	public static int f() throws SimpleException{
		Random rand = new Random();
		if(rand.nextBoolean()){
			throw new SimpleException();
		} else {
			return rand.nextInt(10);
		}
	}
	
	public static void main(String[] args) {
		try {
			int result = f();
			System.out.println("Result = " + result);
			return;
		} catch (SimpleException e) {
			//когда есть блок finally блок catch можно опускать
			e.printStackTrace();
			return;
		}finally{
			/*дейcтвие, которое выполняется в любом случае. 
			 * Даже если в блоке try или catch есть оператор return.*/
			System.out.println("Finally"); 
		}

	}

}
