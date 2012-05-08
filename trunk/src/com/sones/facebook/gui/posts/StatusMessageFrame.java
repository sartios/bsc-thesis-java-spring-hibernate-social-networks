package com.sones.facebook.gui.posts;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JButton;

import com.sones.sharedDto.facebook.view.posts.StatusMessageViewDto;
import com.sones.sharedDto.facebook.view.posts.UserViewDto;

public class StatusMessageFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextArea jStatusMessageTextArea = null;
	private JLabel jUsernameLabel = null;
	private JLabel jUsernameValueLabel = null;
	private JLabel jLabel2 = null;
	private JPanel jPanel = null;
	private JButton jButton = null;
	private StatusMessageViewDto postDto;  //  @jve:decl-index=0:
	
	/**
	 * This is the default constructor
	 */
	public StatusMessageFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(507, 309);
		this.setContentPane(getJContentPane());
		this.setTitle("Status message");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 2;
			gridBagConstraints5.ipadx = 0;
			gridBagConstraints5.gridy = 2;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 2;
			gridBagConstraints4.gridy = 0;
			jLabel2 = new JLabel();
			jLabel2.setText("Facebook status message");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.insets = new Insets(0, 0, 120, 10);
			gridBagConstraints3.gridy = 1;
			jUsernameValueLabel = new JLabel();
			jUsernameValueLabel.setText("JLabel");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(0, 0, 120, 10);
			gridBagConstraints2.gridy = 1;
			jUsernameLabel = new JLabel();
			jUsernameLabel.setText("Username");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.insets = new Insets(90, 0, 90, 0);
			gridBagConstraints1.gridx = 2;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJStatusMessageTextArea(), gridBagConstraints1);
			jContentPane.add(jUsernameLabel, gridBagConstraints2);
			jContentPane.add(jUsernameValueLabel, gridBagConstraints3);
			jContentPane.add(jLabel2, gridBagConstraints4);
			jContentPane.add(getJPanel(), gridBagConstraints5);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jStatusMessageTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJStatusMessageTextArea() {
		if (jStatusMessageTextArea == null) {
			jStatusMessageTextArea = new JTextArea();
		}
		return jStatusMessageTextArea;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJButton(), gridBagConstraints);
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
		}
		return jButton;
	}

	public void setPost(StatusMessageViewDto postDto) 
	{
		this.postDto = postDto;
		updateFrame();
	}

	private void updateFrame() 
	{
		updateUser();
		updateMessage();
	}
	
	private void updateMessage()
	{
		jStatusMessageTextArea.setText( postDto.getMessage() );
	}
	
	private void updateUser()
	{
		UserViewDto userViewDto = postDto.getUser();
		jUsernameValueLabel.setText( userViewDto.getUsername() );
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
