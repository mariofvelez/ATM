package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import backend.User;

public class AccountPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7602817557611454632L;

	private User user;
	
	private JPanel info_panel;
	private JPanel task_panel;
	
	private JLabel name_title;
	private JLabel name_label;
	
	private JLabel balance_title;
	private JLabel balance_label;
	
	private JButton withdraw_button;
	private JButton deposit_button;
	
	private JButton logout_button;
	private ExitListener exit_listener;
	
	public AccountPanel(User user)
	{
		this.user = user;
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc1 = new GridBagConstraints();
		
		info_panel = new JPanel();
		gc1.gridx = 0;
		gc1.gridy = 0;
		info_panel.setLayout(new GridBagLayout());
		{
			GridBagConstraints gc = new GridBagConstraints();
			
			name_title = new JLabel("Welcome:");
			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			gc.gridx = 0;
			gc.gridy = 0;
			gc.weightx = 0;
			gc.insets = new Insets(0, 20, 0, 0);
			info_panel.add(name_title, gc);
			
			name_label = new JLabel(user.getName());
			name_label.setFont(new Font("", Font.BOLD, 30));
			gc.gridy++;
			gc.insets = new Insets(0, 20, 20, 0);
			info_panel.add(name_label, gc);
			
			balance_title = new JLabel("Your balance:");
			gc.gridx = 0;
			gc.gridy++;
			gc.insets = new Insets(0, 20, 0, 0);
			info_panel.add(balance_title, gc);
			
			balance_label = new JLabel(user.getBalance());
			balance_label.setFont(new Font("", Font.BOLD, 30));
			gc.gridx = 0;
			gc.gridy++;
			info_panel.add(balance_label, gc);
			
		}
		this.add(info_panel, gc1);
		
		task_panel = new JPanel();
		gc1.gridx = 1;
		gc1.gridy = 0;
		task_panel.setLayout(new GridBagLayout());
		{
			GridBagConstraints gc = new GridBagConstraints();
			
			withdraw_button = new JButton("Withdraw");
			
			withdraw_button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String message = "Enter the amount you wish to withdraw:";
					String title = "Withdraw";
					
					String s = JOptionPane.showInputDialog(AccountPanel.this, message, title, JOptionPane.PLAIN_MESSAGE);
					
					if(s != null)
					{
						int amount = Integer.parseInt(s);
						user.withdraw(AccountPanel.this, amount);
						
						balance_label.setText(user.getBalance());
						balance_label.updateUI();
					}
				}
			});
			
			gc.anchor = GridBagConstraints.CENTER;
			gc.gridx = 0;
			gc.gridy = 0;
			gc.insets = new Insets(10, 10, 10, 10);
			task_panel.add(withdraw_button, gc);
			
			deposit_button = new JButton("Deposit");
			
			deposit_button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String message = "Enter the amount you wish to deposit: $";
					String title = "Deposit";
					
					String s = JOptionPane.showInputDialog(AccountPanel.this, message, title, JOptionPane.PLAIN_MESSAGE);
					
					if(s != null)
					{
						int amount = Integer.parseInt(s);
						user.deposit(AccountPanel.this, amount);
						
						balance_label.setText(user.getBalance());
						balance_label.updateUI();
					}
				}
			});
			
			gc.gridx = 0;
			gc.gridy++;
			task_panel.add(deposit_button, gc);
			
			logout_button = new JButton("Logout");
			
			logout_button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(exit_listener != null)
						exit_listener.exitPerformed();
				}
				
			});
			
			gc.gridx = 0;
			gc.gridy++;
			task_panel.add(logout_button, gc);
		}
		this.add(task_panel, gc1);
		
	}
	public void setExitListener(ExitListener exit_listener)
	{
		this.exit_listener = exit_listener;
	}

}
