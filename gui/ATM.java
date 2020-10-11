package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import backend.Controller;
import backend.User;

public class ATM extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7925591248856074461L;
	
	private User current_user;
	private JPanel current_panel;
	
	private LoginPanel login_panel;
	private AccountPanel account_panel;
	private CreateAccountPanel create_account_panel;
	
	public ATM()
	{
		super("ATM by: Mario Velez");
		this.setSize(new Dimension(400, 500));
		this.setLocation(200, 100);
		
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		Controller controller = new Controller();
		File file = new File("data");
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		try {
			controller.loadFromFile(file);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
						
		SetPanelListener set_panel_listener = panel -> {
			if(current_panel != null)
			{
				ATM.this.remove(current_panel);
				ATM.this.add(panel, BorderLayout.CENTER);
				ATM.this.current_panel = panel;
				ATM.this.update(ATM.this.getGraphics());
				panel.updateUI();
			}
			
		};
		
		login_panel = new LoginPanel();
		this.add(login_panel, BorderLayout.CENTER);
		login_panel.setLoginListener(e -> {
			User user = controller.getUser(e);
			if(user == null) // invalid username and/or password
			{
				String message = "Username does not match password!\nTry again!";
				String title = "Invalid username and/or passord";
				JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
			}
			else // valid username and password
			{
				current_user = user;
				account_panel = new AccountPanel(current_user); // create new panel for user
				account_panel.setExitListener(() -> {
					set_panel_listener.setPanel(login_panel);
					account_panel = null;
				});
				set_panel_listener.setPanel(account_panel); // set it to the current panel
			}
		});
		login_panel.setCreateAccountButtonListener(() -> {
			set_panel_listener.setPanel(create_account_panel);
		});
		
		create_account_panel = new CreateAccountPanel();
		create_account_panel.setCreateAccountListener(e -> {
			controller.addUser(e);
			set_panel_listener.setPanel(login_panel);
		});
		create_account_panel.setExitListener(() -> {
			set_panel_listener.setPanel(login_panel);
		});
				
		current_panel = login_panel;
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent arg0) {}

			public void windowClosed(WindowEvent arg0) {}

			public void windowClosing(WindowEvent arg0)
			{
				try {
					controller.saveToFile(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void windowDeactivated(WindowEvent arg0) {}

			public void windowDeiconified(WindowEvent arg0) {}

			public void windowIconified(WindowEvent arg0) {}
			
			public void windowOpened(WindowEvent arg0) {}
			
		});
	}

}
