package com.sones.facebook.gui.user;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sones.facebook.controller.user.FacebookTokenController;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

import java.awt.Insets;

public class FacebookTokenFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JButton jButton1 = null;
	private JPanel jPanel = null;
	private FacebookTokenController controller;
	private JLabel jLabel1 = null;
	private JTextField jTextField1 = null;
	private ApplicationUserViewDto userDto;  //  @jve:decl-index=0:
	
	/**
	 * This is the default constructor
	 */
	public FacebookTokenFrame() {
		super();
		initialize();
		controller = new FacebookTokenController();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(466, 344);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.insets = new Insets(0, 0, 40, 0);
			gridBagConstraints6.gridx = 2;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.insets = new Insets(0, 0, 40, 0);
			gridBagConstraints5.gridy = 0;
			jLabel1 = new JLabel();
			jLabel1.setText("Token Request");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 2;
			gridBagConstraints4.insets = new Insets(100, 0, 0, 0);
			gridBagConstraints4.gridy = 4;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 0.0;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.insets = new Insets(0, 0, 0, 10);
			gridBagConstraints11.gridy = 1;
			jLabel = new JLabel();
			jLabel.setText("Token Value");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabel, gridBagConstraints11);
			jContentPane.add(getJTextField(), gridBagConstraints2);
			jContentPane.add(getJPanel(), gridBagConstraints4);
			jContentPane.add(jLabel1, gridBagConstraints5);
			jContentPane.add(getJTextField1(), gridBagConstraints6);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Get Request");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String url = controller.getTokenRequestUrl();
					jTextField1.setText(url);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Save Token");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String value = jTextField.getText();
					String appUserId = userDto.getUserID();
					controller.saveToken(appUserId, value);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(0, 0, 0, 10);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = -1;
			gridBagConstraints1.insets = new Insets(0, 9, 0, 0);
			gridBagConstraints1.gridy = -1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = -1;
			gridBagConstraints.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints.gridy = -1;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJButton(), gridBagConstraints3);
			jPanel.add(getJButton1(), gridBagConstraints);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
		}
		return jTextField1;
	}

	/**
	 * @param userDto the userDto to set
	 */
	public void setUserDto(ApplicationUserViewDto userDto) {
		this.userDto = new ApplicationUserViewDto(userDto);
	}

	/**
	 * @return the userDto
	 */
	public ApplicationUserViewDto getUserDto() {
		return userDto;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
