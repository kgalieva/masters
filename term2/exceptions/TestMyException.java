package term2.exceptions;

public class TestMyException {
	
	public static void f() throws MyException {
		System.out.println("Throwing exception from method f()");
		//возбуждаем исключение MyException
		throw new MyException();
	}
	
	public static void g() throws MyException {
		System.out.println("Throwing exception from method g()");
		//возбуждаем исключение MyException
		throw new MyException("exception in method g()");
	}
	
	public static void main(String[] args) {
		try{
			f();
		} catch(MyException e) {
			e.printStackTrace();
		}
		try{
			g();
		} catch(MyException e) {
			e.printStackTrace();
		}
	}

}
