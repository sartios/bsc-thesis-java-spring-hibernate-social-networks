package com.sones.facebook.gui.posts;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

import com.sones.sharedDto.facebook.view.posts.PhotoViewDto;

import java.awt.Insets;
import java.net.MalformedURLException;
import java.net.URL;

public class PhotoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jImageLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jUsernameValueLabel = null;
	private JButton jButton = null;
	private PhotoViewDto postDto;
	/**
	 * This is the default constructor
	 */
	public PhotoFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(378, 259);
		this.setContentPane(getJContentPane());
		this.setTitle("Photo frame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.insets = new Insets(0, 0, 120, 0);
			gridBagConstraints2.gridy = 0;
			jUsernameValueLabel = new JLabel();
			jUsernameValueLabel.setText("username");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(0, 0, 120, 10);
			gridBagConstraints1.gridy = 0;
			jLabel1 = new JLabel();
			jLabel1.setText("Username :");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			jImageLabel = new JLabel();
			jImageLabel.setText("Image");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jImageLabel, gridBagConstraints);
			jContentPane.add(jLabel1, gridBagConstraints1);
			jContentPane.add(jUsernameValueLabel, gridBagConstraints2);
			jContentPane.add(getJButton(), gridBagConstraints3);
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
			jButton.setText("Show comments");
		}
		return jButton;
	}

	public void setPost(PhotoViewDto postDto) 
	{
		this.postDto = postDto;
		updateFrame();
	}

	private void updateFrame() 
	{
		jUsernameValueLabel.setText( postDto.getUser().getUsername() );
		showPhoto(postDto.getPicture());
	}
	
	private void showPhoto(String url)
	{
		jImageLabel.setText("");
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
