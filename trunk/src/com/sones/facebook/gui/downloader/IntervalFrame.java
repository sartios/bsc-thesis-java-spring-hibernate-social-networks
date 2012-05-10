package com.sones.facebook.gui.downloader;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.sones.facebook.controller.downloader.IntervalController;
import com.sones.facebook.downloader.logic.IFacebookPostDownloaderManagerService;
import com.sones.sharedDto.facebook.source.UserIdDto;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

import java.awt.Insets;

public class IntervalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JButton jButton = null;
	private IntervalController controller;
	private ApplicationUserViewDto userDto;  //  @jve:decl-index=0:

	/**
	 * This is the default constructor
	 */
	public IntervalFrame() {
		super();
		initialize();
		controller = new IntervalController();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(340, 199);
		this.setContentPane(getJContentPane());
		this.setTitle("Downloader interval");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.ipady = 9;
			gridBagConstraints2.gridwidth = 1;
			gridBagConstraints2.insets = new Insets(40, 0, 0, 0);
			gridBagConstraints2.ipadx = 3;
			gridBagConstraints2.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.ipadx = 146;
			gridBagConstraints1.ipady = 0;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Enter interval minutes");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabel, gridBagConstraints);
			jContentPane.add(getJTextField(), gridBagConstraints1);
			jContentPane.add(getJButton(), gridBagConstraints2);
		}
		return jContentPane;
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
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("OK");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(userDto == null)
					{
						System.out.println("UserDto is null");
					}
					String interval = jTextField.getText();
					String facebookUserId = userDto.getAccountID();
					controller.saveOption(facebookUserId, interval);
				}
			});
		}
		return jButton;
	}
	
	public void setService(IFacebookPostDownloaderManagerService service)
	{
		controller.setService(service);
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
