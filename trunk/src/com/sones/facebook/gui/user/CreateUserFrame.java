package com.sones.facebook.gui.user;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.sones.facebook.controller.user.CreateUseController;

import java.awt.Insets;

public class CreateUserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField jNameTextField = null;
	private JTextField jUsernameTextField = null;
	private JTextField jPasswordTextField = null;
	private JPanel jPanel = null;
	private JButton jButton = null;
	private CreateUseController controller;
	
	/**
	 * This is the default constructor
	 */
	public CreateUserFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(344, 236);
		this.setContentPane(getJContentPane());
		this.setTitle("Create new application user");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints6.gridy = 3;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(10, 10, 0, 110);
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.insets = new Insets(10, 10, 0, 110);
			gridBagConstraints4.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(0, 10, 0, 110);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridy = 0;
			jLabel2 = new JLabel();
			jLabel2.setText("Name");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints1.gridy = 2;
			jLabel1 = new JLabel();
			jLabel1.setText("Password");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.gridy = 1;
			jLabel = new JLabel();
			jLabel.setText("Username");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabel, gridBagConstraints);
			jContentPane.add(jLabel1, gridBagConstraints1);
			jContentPane.add(jLabel2, gridBagConstraints2);
			jContentPane.add(getJNameTextField(), gridBagConstraints3);
			jContentPane.add(getJUsernameTextField(), gridBagConstraints4);
			jContentPane.add(getJPasswordTextField(), gridBagConstraints5);
			jContentPane.add(getJPanel(), gridBagConstraints6);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jNameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJNameTextField() {
		if (jNameTextField == null) {
			jNameTextField = new JTextField();
		}
		return jNameTextField;
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
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints7.insets = new Insets(0, 0, 0, 0);
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJButton(), gridBagConstraints7);
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
			jButton.setText("Create");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String name = jNameTextField.getText();
					String username = jUsernameTextField.getText();
					String password = jPasswordTextField.getText();
					controller.createNewUser(name, username, password);
				}
			});
		}
		return jButton;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(CreateUseController controller) {
		this.controller = controller;
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
