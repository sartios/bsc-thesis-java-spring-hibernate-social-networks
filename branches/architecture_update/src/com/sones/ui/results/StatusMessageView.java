package com.sones.ui.results;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookStatusMessage;
import com.sones.controllers.results.IStatusMessageViewController;
import com.sones.controllers.results.StatusMessageViewController;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class StatusMessageView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel StatusMessageContentLabel = null;
	private JLabel StatusMessageCreatorLabel = null;
	private JLabel StatusMessageCreatorNameLabel = null;
	private JLabel StatusMessageCreationDateLabel = null;
	private JLabel StatusMessageCreationDateContentLabel = null;
	private JButton jButton = null;
	private	String	_feedID;
	private	IStatusMessageViewController	_controller;

	/**
	 * This is the default constructor
	 */
	public StatusMessageView() {
		super();
		_controller	=	new	StatusMessageViewController();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(655, 340);
		this.setContentPane(getJContentPane());
		this.setTitle("Status Message Viewer");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e)
			{
				System.out.println("windowOpened()"); // TODO Auto-generated Event stub windowOpened()
				SetStatusMessage( GetFeedID() );
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(18, 29, 14, 213);
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.ipadx = 10;
			gridBagConstraints5.ipady = 3;
			gridBagConstraints5.anchor = GridBagConstraints.EAST;
			gridBagConstraints5.gridwidth = 2;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(45, 6, 54, 191);
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.ipadx = 34;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridx = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(45, 3, 59, 0);
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.ipadx = 23;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridwidth = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(0, 5, 4, 204);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.ipadx = 30;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridx = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(0, 0, 0, 20);
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 24;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(34, 16, 17, 21);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 424;
			gridBagConstraints.ipady = 218;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.gridheight = 3;
			StatusMessageCreationDateContentLabel = new JLabel();
			StatusMessageCreationDateContentLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			StatusMessageCreationDateContentLabel.setText("JLabel");
			StatusMessageCreationDateLabel = new JLabel();
			StatusMessageCreationDateLabel.setText("Creation Date :");
			StatusMessageCreationDateLabel.setVerticalAlignment(SwingConstants.TOP);
			StatusMessageCreatorNameLabel = new JLabel();
			StatusMessageCreatorNameLabel.setText("JLabel");
			StatusMessageCreatorLabel = new JLabel();
			StatusMessageCreatorLabel.setText("Creator :");
			StatusMessageContentLabel = new JLabel();
			StatusMessageContentLabel.setText("Here is the status message");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(StatusMessageContentLabel, gridBagConstraints);
			jContentPane.add(StatusMessageCreatorLabel, gridBagConstraints1);
			jContentPane.add(StatusMessageCreatorNameLabel, gridBagConstraints2);
			jContentPane.add(StatusMessageCreationDateLabel, gridBagConstraints3);
			jContentPane.add(StatusMessageCreationDateContentLabel, gridBagConstraints4);
			jContentPane.add(getJButton(), gridBagConstraints5);
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
			jButton.setText("Show Comments");
		}
		return jButton;
	}

	/**
	 * Sets the message of user's status into the label box
	 */
	private void setStatusMessageContent(final String message)
	{
		this.StatusMessageContentLabel.setText(message);
	}
	
	/**
	 * Sets name of the user who created the post
	 */
	private void setStatusMessageCreator(final String creator)
	{
		this.StatusMessageCreatorNameLabel.setText(creator);
	}
	
	/**
	 * Sets date which the status had been created
	 */
	private void setStatusMessageDate(final String date)
	{
		this.StatusMessageCreationDateContentLabel.setText(date);
	}
	

	private void SetStatusMessage(String feedID) 
	{
		IFacebookStatusMessage	statusMessage	=	_controller.GetStatusMessage(feedID);
		this.setStatusMessageContent(statusMessage.GetMessage());
	}
	
	public	void	SetFeedID( final String feedID )
	{
		_feedID	=	feedID;
	}
	
	public	String	GetFeedID()
	{
		return	_feedID;
	}
}  //  @jve:decl-index=0:visual-constraint="34,5"
