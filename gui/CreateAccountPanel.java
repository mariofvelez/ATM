package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountPanel extends JPanel {
	
	JLabel create_account_label;
	
	private JLabel name_label;
	private JTextField name_field;
	
	private JLabel username_label;
	private JTextField username_field;
	
	private JLabel password_label;
	private JPasswordField password_field;
	
	private JLabel confirm_password_label;
	private JPasswordField confirm_password_field;
	
	private JButton create_account_button;
	private CreateAccountListener create_account_listener;
	
	private JButton exit_button;
	private ExitListener exit_listener;
	
	public CreateAccountPanel()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		create_account_label = new JLabel("Create an account");
		gc.anchor = GridBagConstraints.SOUTH;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 2;
		gc.insets = new Insets(5, 5, 5, 5);
		this.add(create_account_label, gc);
		
		name_label = new JLabel("Name:");
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 1;
		this.add(name_label, gc);
		
		name_field = new JTextField(10);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		this.add(name_field, gc);
		
		username_label = new JLabel("Username:");
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 0;
		gc.gridy++;
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
		
		confirm_password_label = new JLabel("Confirm password:");
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 0;
		gc.gridy++;
		this.add(confirm_password_label, gc);
		
		confirm_password_field = new JPasswordField(10);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		this.add(confirm_password_field, gc);
		
		create_account_button = new JButton("Create Account");
		
		create_account_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = name_field.getText();
				String username = username_field.getText();
				char[] password = password_field.getPassword();
				char[] confirm = confirm_password_field.getPassword();
				
				boolean username_valid = true;
				
				if(username.length() < 8)
				{
					username_valid = false;
					String message = "Username must contain:\n - 8 characters minimum";
					String title = "Invalid username!";
					JOptionPane.showMessageDialog(CreateAccountPanel.this, message, title, JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				boolean password_valid = true;
				
				if(password.length < 8)
				{
					password_valid = false;
					String message = "Password must contain:\n - 8 characters minimum";
					String title = "Invalid password!";
					JOptionPane.showMessageDialog(CreateAccountPanel.this, message, title, JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(password.length != confirm.length)
					password_valid = false;
				
				if(password_valid)
				{
					for(int i = 0; i < password.length; i++)
					{
						if(password[i] != confirm[i])
						{
							password_valid = false;
							break;
						}
					}
				}
				
				if(password_valid && username_valid) // creates a valid account
				{
					String pass = "";
					for(int i = 0; i < password.length; i++)
						pass += password[i];
					
					name_field.setText("");
					username_field.setText("");
					password_field.setText("");
					confirm_password_field.setText("");
					
					CreateAccountEvent event = new CreateAccountEvent(name, username, pass);
					
					if(create_account_listener != null)
						create_account_listener.createAccountPerformed(event);
				}
				else
				{
					String message = "Passwords do not match!";
					String title = "Invalid password!";
					JOptionPane.showMessageDialog(CreateAccountPanel.this, message, title, JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			
		});
		
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 2;
		this.add(create_account_button, gc);
		
		exit_button = new JButton("Exit");
		
		exit_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(exit_listener != null)
					exit_listener.exitPerformed();
			}
		});
		
		gc.gridy++;
		this.add(exit_button, gc);
	}
	public void setCreateAccountListener(CreateAccountListener create_account_listener)
	{
		this.create_account_listener = create_account_listener;
	}
	public void setExitListener(ExitListener exit_listener)
	{
		this.exit_listener = exit_listener;
	}

}
