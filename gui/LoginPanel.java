package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5100212021194753173L;
	
	private JLabel welcome_label;
	
	private JLabel username_label;
	private JTextField username_field;
	
	private JLabel password_label;
	private JPasswordField password_field;
	
	private JButton login_button;
	private LoginListener login_listener;
	
	private JButton create_account_button;
	private CreateAccountButtonListener create_account_listener;

	public LoginPanel()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		welcome_label = new JLabel("Welcome to the ATM!");
		gc.anchor = GridBagConstraints.SOUTH;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 2;
		gc.insets = new Insets(5, 5, 5, 5);
		this.add(welcome_label, gc);
		
		username_label = new JLabel("Username:");
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 1;
		this.add(username_label, gc);
		
		username_field = new JTextField(10);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		this.add(username_field, gc);
		
		password_label = new JLabel("Password:");
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 0;
		gc.gridy++;
		this.add(password_label, gc);
		
		password_field = new JPasswordField(10);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		this.add(password_field, gc);
		
		login_button = new JButton("Login");
		
		login_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LoginEvent login_event = new LoginEvent(username_field.getText(),
														password_field.getPassword());
				
				username_field.setText("");
				password_field.setText("");
				
				if(login_listener != null)
					login_listener.loginEventPerformed(login_event);
			}
			
		});
		
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 2;
		this.add(login_button, gc);
		
		create_account_button = new JButton("Create Account");
		
		create_account_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				create_account_listener.createAccountButtonPerformed();
			}
		});
		
		gc.gridx = 0;
		gc.gridy++;
		this.add(create_account_button, gc);
	}
	public void setLoginListener(LoginListener login_listener)
	{
		this.login_listener = login_listener;
	}
	public void setCreateAccountButtonListener(CreateAccountButtonListener create_account_listener)
	{
		this.create_account_listener = create_account_listener;
	}

}
