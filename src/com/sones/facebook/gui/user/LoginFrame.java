package com.sones.facebook.gui.user;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.sones.facebook.controller.user.CreateUseController;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField jUsernameTextField = null;
	private JTextField jPasswordTextField = null;
	private JPanel jPanel = null;
	private JButton jButton = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private CreateUserFrame createFrame = null;
	private CreateUseController controller;
	private List<ILoginListener> listeners;
	
	/**
	 * This is the default constructor
	 */
	public LoginFrame() {
		super();
		initialize();
		controller = new CreateUseController();
		listeners = new ArrayList<ILoginListener>();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Login");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.insets = new Insets(20, 2, 0, 0);
			gridBagConstraints4.gridy = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(20, 30, 0, 0);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.insets = new Insets(0, 30, 0, 0);
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(20, 0, 0, 0);
			gridBagConstraints1.gridy = 1;
			jLabel1 = new JLabel();
			jLabel1.setText("Password");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Username");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabel, gridBagConstraints);
			jContentPane.add(jLabel1, gridBagConstraints1);
			jContentPane.add(getJUsernameTextField(), gridBagConstraints2);
			jContentPane.add(getJPasswordTextField(), gridBagConstraints3);
			jContentPane.add(getJPanel(), gridBagConstraints4);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jUsernameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJUsernameTextField() {
		if (jUsernameTextField == null) {
			jUsernameTextField = new JTextField();
		}
		return jUsernameTextField;
	}

	/**
	 * This method initializes jPasswordTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJPasswordTextField() {
		if (jPasswordTextField == null) {
			jPasswordTextField = new JTextField();
		}
		return jPasswordTextField;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJButton(), new GridBagConstraints());
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Login");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String username = jUsernameTextField.getText();
					String password = jPasswordTextField.getText();
					Boolean result = controller.login(username, password);
					if(result == true)
					{
						updateListeners();
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("New user");
			jMenu.add(getJMenuItem());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Create");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(createFrame == null)
					{
						createFrame = new CreateUserFrame();
						createFrame.setController(controller);
					}
					createFrame.show();
				}
			});
		}
		return jMenuItem;
	}
	
	public void addListener(ILoginListener listener)
	{
		listeners.add(listener);
	}
	
	private void updateListeners()
	{
		for(ILoginListener listener : listeners)
		{
			listener.successfullLogin();
		}
	}
}
