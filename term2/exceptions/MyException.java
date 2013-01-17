package term2.exceptions;

/**
 * Создание собсвенного исключения с конструктором
 */
public class MyException extends Exception {

	public MyException() {		
	}

	/**
	 * Используем конструктор родительского класса, вызываемый ключевым словом super.
	 * Он принимает сообщение, которое будет содержаться в исключении
	 */
	public MyException(String message) {
		super(message);		
	}

}
