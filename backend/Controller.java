package backend;

import java.io.File;

import gui.CreateAccountEvent;
import gui.LoginEvent;

public class Controller {
	
	private Database db = new Database();
	
	public void addUser(CreateAccountEvent e)
	{
		String name = e.getName();
		String username = e.getUsername();
		String password = e.getPassword();
		
		User user = new User(name, username, password);
		db.addUser(user);
	}
	public User getUser(LoginEvent e)
	{
		String username = e.getUsername();
		String password = "";
		char[] c = e.getPassword();
		for(int i = 0; i < c.length; i++)
			password += c[i];
		
		for(User user : db.getIterable())
			if(user.check(username, password))
				return user;
		
		return null;
	}
	public void saveToFile(File file) throws Exception
	{
		db.saveToFile(file);
	}
	public void loadFromFile(File file) throws Exception
	{
		db.loadFromFile(file);
	}

}
