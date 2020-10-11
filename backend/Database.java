package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {
	
	private ArrayList<User> users;
	
	public Database()
	{
		users = new ArrayList<>();
	}
	public void addUser(User user)
	{
		users.add(user);
	}
	public Iterable<User> getIterable()
	{
		return users;
	}
	public void saveToFile(File file) throws Exception
	{
		FileOutputStream out = new FileOutputStream(file);
		ObjectOutputStream o_out = new ObjectOutputStream(out);
		
		User[] persons = users.toArray(new User[users.size()]);
		o_out.writeObject(persons);
		
		o_out.close();
	}
	public void loadFromFile(File file) throws Exception
	{
		FileInputStream in = new FileInputStream(file);
		ObjectInputStream o_in = new ObjectInputStream(in);
		
		User[] persons = (User[]) o_in.readObject();
		users.clear();
		users.addAll(Arrays.asList(persons));
		
		o_in.close();
	}

}
