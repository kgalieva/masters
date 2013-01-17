package term2.exceptions;

public class TestSimpleException{
	//метод, который может выбросить исключение типа SimpleException
	public static void f() throws SimpleException {
		System.out.println("Throwing exception from method f()");
		/** 
		 * Можно возбудить любой тип исключений, происходящих от объекта Throwable.
		 * Throwable - это корневой класс иерархии исключений.
		 * Обычно для разных ошибок возбуждаются разные типы исключений.
		 * Информация о случившейся ошибке как содержится внутри объекта исключения, 
		 * так и указывается косвенно в самом типе этого объекта, 
		 * чтобы кто-то на более высоком уровне сумел выяснить, как поступить с исключением.
		 */
		//возбуждаем исключение SimpleException
		throw new SimpleException();
	}
	
	public static void main(String[] args) {
		try {
			//Фрагмент способный возбуждать исключения
			f();
		} catch (SimpleException e) {
			//Обработка исключения типа SimpleException
			System.out.println("SimpleException is caught");
		}
	}
}