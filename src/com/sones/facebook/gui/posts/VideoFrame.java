package com.sones.facebook.gui.posts;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextArea;

import com.sones.facebook.controller.posts.VideoController;
import com.sones.sharedDto.facebook.view.posts.VideoViewDto;

public class VideoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jImageLabel = null;
	private JPanel jPanel = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JLabel jLabel1 = null;
	private JLabel jUsernameValueLabel = null;
	private JLabel jDescriptionLabel = null;
	private JTextArea jDescriptionTextArea = null;
	private VideoViewDto postDto;
	private VideoController controller;
	private CommentFrame commentFrame;
	
	/**
	 * This is the default constructor
	 */
	public VideoFrame() {
		super();
		initialize();
		controller = new VideoController();
		commentFrame = new CommentFrame();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(591, 414);
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
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridy = 2;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.weighty = 1.0;
			gridBagConstraints6.insets = new Insets(150, 0, 150, 0);
			gridBagConstraints6.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 2;
			jDescriptionLabel = new JLabel();
			jDescriptionLabel.setText("Description");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.insets = new Insets(0, 0, 0, 220);
			gridBagConstraints4.gridy = 0;
			jUsernameValueLabel = new JLabel();
			jUsernameValueLabel.setText("username");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 0;
			jLabel1 = new JLabel();
			jLabel1.setText("Username");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 4;
			gridBagConstraints1.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints1.gridy = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 2;
			jImageLabel = new JLabel();
			jImageLabel.setText("Video Image");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jImageLabel, gridBagConstraints);
			jContentPane.add(getJPanel(), gridBagConstraints1);
			jContentPane.add(jLabel1, gridBagConstraints3);
			jContentPane.add(jUsernameValueLabel, gridBagConstraints4);
			jContentPane.add(jDescriptionLabel, gridBagConstraints5);
			jContentPane.add(getJDescriptionTextArea(), gridBagConstraints6);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJButton(), new GridBagConstraints());
			jPanel.add(getJButton1(), gridBagConstraints2);
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
			jButton.setText("Show comments");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					commentFrame.setComments(postDto.getComments());
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Play video");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String url = postDto.getSource();
					System.out.println(url);
					controller.goToVideo(url);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jDescriptionTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJDescriptionTextArea() {
		if (jDescriptionTextArea == null) {
			jDescriptionTextArea = new JTextArea();
		}
		return jDescriptionTextArea;
	}

	public void setPost(VideoViewDto postDto) 
	{
		this.postDto = postDto;
		updateFrame();
	}

	private void updateFrame() 
	{
		jUsernameValueLabel.setText(postDto.getUser().getUsername());
		jDescriptionTextArea.setText(postDto.getDescription());
		Icon icon = new ImageIcon(postDto.getPicture());
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
