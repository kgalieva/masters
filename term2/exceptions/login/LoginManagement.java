package term2.exceptions.login;

import java.util.ArrayList;
import java.util.List;


public class LoginManagement {
	
	private List<User> users;
	
	public LoginManagement() {
		users = new ArrayList<User>();
		users.add(new User("user1", "pass1", false));
		users.add(new User("user2", "pass2", true));
		users.add(new User("user3", "pass3", false));
		users.add(new User("user4", "pass4", true));
		users.add(new User("user5", "pass5", false));
	}
	
	/*
	 * Оборачиваем все исключения, которые возникают в этом методе в LoginException.
	 * Если пользователю понадобится различать причины неудачной авторизации, 
	 * он сможет это сделать с помощью объекта-прчины(cause), который передается вместе 
	 * с исключкнием LoginException.
	 */
	public void signIn(String login, String password) throws LoginException {
		try {
			User user = getUser(login);
			checkStatus(user);
			checkPassword(user, password);					
		} catch (Exception e) { //перехват всех исключений	
			/*создаем новое исключение, а старое исключение передаем в качестве причины(cause)*/
			throw new LoginException(e);
			/* Если нет конструктора, который принимает в качестве аргумента объект типа Throwable,
			 * то исключение-причину можно задать с помощью метода initCause()
			 * throw new LoginException().initCause(e);
			 */			
		}		
	}

	private User getUser(String login) throws UserNotFoundException {
		for(User user: users) {
			if(user.getLogin().equals(login)) {
				return user;				
			}
		} 
		throw new UserNotFoundException();		
	}

	private void checkPassword(User user, String password) {
		/* TODO реализуйте метод checkPassword так,
		 * чтобы он кидал исключение WrongPasswordException
		 * в случае, когда введенный пароль не совпадает с паролем пользователя user.
		 * Аналогично методу checkStatus()
		 */		
	}

	private void checkStatus(User user) throws UserIsBlockedException {
		if(user.getBlocked()) {
			throw new UserIsBlockedException();
		}		
	}

}
