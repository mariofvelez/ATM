package backend;

import java.awt.Component;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6366906552075172847L;
	private String name;
	private String username;
	private String password;
	private int balance;
	
	public User(String name, String username, String password)
	{
		this.name = name;
		this.username = username;
		this.password = password;
		this.balance = 0;
	}
	public boolean check(String username, String password)
	{
		return this.username.equals(username) && this.password.equals(password);
	}
	public void withdraw(Component parent, int amount)
	{
		if(amount > balance)
			JOptionPane.showMessageDialog(parent, "You do not have that much money!",
										  "Invalid Amount",
										  JOptionPane.WARNING_MESSAGE);
		else
		{
			int[] money = WithdrawManager.withdraw(amount);
			for(int i = 0; i < money.length; i++)
			{
				this.balance -= money[i];
				System.out.println("withdrew: $" + money[i] + " bill");
			}
		}
	}
	public void deposit(Component parent, int amount)
	{
		this.balance += amount;
	}
	public String getName()
	{
		return name;
	}
	public String getUsername()
	{
		return username;
	}
	public String getBalance()
	{
		return "$" + balance + ".00";
	}

}
