package term2.exceptions.login;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws LoginException {
		Scanner s = new Scanner(System.in);
		System.out.println("login:");
		String login = s.next();
		System.out.println("password:");
		String password = s.next();
		LoginManagement loginManagement = new LoginManagement();
		/*TODO обработайте исключение LoginException, так чтобы оно не завершало программу.
		 * Попробуйте выводить разные сообщения об ошибках, в зависимости от того, 
		 * что указано в причине исключения. Получить объект-причину можно с помощью 
		 * метода getCause() класса Throwable*/
		loginManagement.signIn(login, password);
		s.close();
	}

}
