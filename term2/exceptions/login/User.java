package term2.exceptions.login;

public class User {
	private String login;
	private String password;
	private Boolean blocked;
	
	public User(String login, String password, Boolean blocked) {
		this.login = login;
		this.password = password;
		this.blocked = blocked;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getBlocked() {
		return blocked;
	}
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}	

}
