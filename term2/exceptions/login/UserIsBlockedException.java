package term2.exceptions.login;

public class UserIsBlockedException extends Exception {

	public UserIsBlockedException() {		
	}

	public UserIsBlockedException(String message) {
		super(message);
	}
}
