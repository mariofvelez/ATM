package gui;

public class LoginEvent {
	
	private String username;
	private char[] password;
	
	public LoginEvent(String username, char[] password)
	{
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return new String(username);
	}

	public char[] getPassword() {
		char[] password = new char[this.password.length];
		for(int i = 0; i < password.length; i++)
			password[i] = this.password[i];
		return password;
	}

}
