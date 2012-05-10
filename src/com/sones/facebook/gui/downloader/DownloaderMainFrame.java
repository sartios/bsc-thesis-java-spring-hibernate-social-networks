package com.sones.facebook.gui.downloader;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import com.sones.facebook.controller.downloader.DownloaderMainFrameController;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

import java.awt.GridBagConstraints;

public class DownloaderMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private IntervalFrame intervalFrame = null;
	private JButton jButton = null;
	private DownloaderMainFrameController controller;
	private ApplicationUserViewDto userDto;
	
	/**
	 * This is the default constructor
	 */
	public DownloaderMainFrame() {
		super();
		initialize();
		controller = new DownloaderMainFrameController();
		intervalFrame.setService(controller.getService());
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(586, 341);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Downloader Options");
		intervalFrame = new IntervalFrame();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJButton(), gridBagConstraints);
		}
		return jContentPane;
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
			jMenu.setText("Settings");
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
			jMenuItem.setText("Interval");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					intervalFrame.setUserDto(userDto);
					intervalFrame.show(true);
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Start Downloading");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String facebookAccountId = userDto.getAccountID();
					controller.startDownloading(facebookAccountId);
				}
			});
		}
		return jButton;
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
