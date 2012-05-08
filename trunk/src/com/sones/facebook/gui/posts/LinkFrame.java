package com.sones.facebook.gui.posts;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;

import com.sones.facebook.controller.posts.LinkController;
import com.sones.sharedDto.facebook.view.posts.LinkViewDto;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.net.MalformedURLException;
import java.net.URL;

public class LinkFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jImageLabel = null;
	private JLabel jDescriptionLabel = null;
	private JButton jButton = null;
	private JLabel jLabel2 = null;
	private JLabel jUsernameLabel = null;
	private LinkController controller;
	private String linkURL;
	
	/**
	 * This is the default constructor
	 */
	public LinkFrame() {
		super();
		initialize();
		controller = new LinkController();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(465, 294);
		this.setContentPane(getJContentPane());
		this.setTitle("Link frame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 3;
			gridBagConstraints4.gridy = 0;
			jUsernameLabel = new JLabel();
			jUsernameLabel.setText("");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.gridy = 0;
			jLabel2 = new JLabel();
			jLabel2.setText("Username:");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.weightx = 0.5;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.weightx = 0.0;
			gridBagConstraints1.weighty = 0.0;
			gridBagConstraints1.gridy = 1;
			jDescriptionLabel = new JLabel();
			jDescriptionLabel.setText("Link description");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weightx = 0.3;
			gridBagConstraints.weighty = 0.1;
			gridBagConstraints.gridy = 0;
			jImageLabel = new JLabel();
			jImageLabel.setText("Link image");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jImageLabel, gridBagConstraints);
			jContentPane.add(jDescriptionLabel, gridBagConstraints1);
			jContentPane.add(getJButton(), gridBagConstraints2);
			jContentPane.add(jLabel2, gridBagConstraints3);
			jContentPane.add(jUsernameLabel, gridBagConstraints4);
		}
		return jContentPane;
	}

	public void setPost(LinkViewDto postDto) 
	{
		String description = postDto.getDescription();
		linkURL = postDto.getLink();
		String picture = postDto.getPicture();
		showPhoto(picture);
		String username = postDto.getUser().getUsername();
		showUsername(username);
		showDescription(description);
	}
	
	private void showDescription(String description)
	{
		jDescriptionLabel.setText(description);
	}
	
	private void showUsername(String username)
	{
		jUsernameLabel.setText(username);
	}
	
	private void showPhoto(String url)
	{
		if(null!=url){
			java.net.URL where=null;
			try {
				where = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			ImageIcon image = new ImageIcon(where);
			this.jImageLabel.setIcon(image);
		}
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Go  to Link");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					controller.goToLink(linkURL);
				}
			});
		}
		return jButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
