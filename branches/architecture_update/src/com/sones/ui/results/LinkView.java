package com.sones.ui.results;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import com.sones.businessLogic.Facebook.FeedManager.ILinkContent;
import com.sones.controllers.results.ILinkViewController;
import com.sones.controllers.results.LinkViewController;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.net.MalformedURLException;
import java.net.URL;

public class LinkView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel imageLabel = null;
	private JLabel creatorLabel = null;
	private JLabel creatorNameLabel = null;
	private JLabel creationDateLabel = null;
	private JLabel creationDateContentLabel = null;
	private JButton openWebLinkButton = null;
	private JLabel linkDescriptionLabel = null;
	private JButton commentButton = null;
	private	ILinkViewController	_controller;
	private	String	feedID;  //  @jve:decl-index=0:
	private JLabel linkNameLbl = null;
	/**
	 * This is the default constructor
	 */
	public LinkView() {
		super();
		_controller	=	new	LinkViewController();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(592, 307);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.addWindowListener(new java.awt.event.WindowAdapter() {   
			public void windowClosing(java.awt.event.WindowEvent e) {    
				System.out.println("windowClosing()");
				_controller	=	null;
			}
			public void windowOpened(java.awt.event.WindowEvent e) 
			{
				SetLinkContent( GetFeedID() );
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
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.insets = new Insets(14, 19, 6, 21);
			gridBagConstraints11.ipadx = 44;
			gridBagConstraints11.ipady = 14;
			gridBagConstraints11.gridy = 2;
			linkNameLbl = new JLabel();
			linkNameLbl.setText("Name of the link");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.insets = new Insets(7, 80, 12, 8);
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.gridy = 4;
			gridBagConstraints7.ipadx = 40;
			gridBagConstraints7.ipady = 15;
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridwidth = 2;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(14, 19, 6, 21);
			gridBagConstraints6.gridy = 3;
			gridBagConstraints6.ipadx = 44;
			gridBagConstraints6.ipady = 14;
			gridBagConstraints6.gridx = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(24, 8, 1, 71);
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.ipadx = 0;
			gridBagConstraints5.ipady = 8;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(19, 0, 39, 31);
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.ipadx = 93;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridx = 3;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(19, 0, 39, 17);
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.ipadx = 10;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridx = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(23, 0, 20, 31);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.ipadx = 93;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridx = 3;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(21, 0, 23, 31);
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 35;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridx = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(21, 14, 13, 7);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 129;
			gridBagConstraints.ipady = 111;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridheight = 2;
			linkDescriptionLabel = new JLabel();
			linkDescriptionLabel.setText("Description for the link");
			creationDateContentLabel = new JLabel();
			creationDateContentLabel.setText("Creation date");
			creationDateLabel = new JLabel();
			creationDateLabel.setText("Creation Date :");
			creatorNameLabel = new JLabel();
			creatorNameLabel.setText("Creator name");
			creatorLabel = new JLabel();
			creatorLabel.setText("Creator :");
			imageLabel = new JLabel();
			imageLabel.setText("Link Image");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(imageLabel, gridBagConstraints);
			jContentPane.add(creatorLabel, gridBagConstraints1);
			jContentPane.add(creatorNameLabel, gridBagConstraints2);
			jContentPane.add(creationDateLabel, gridBagConstraints3);
			jContentPane.add(creationDateContentLabel, gridBagConstraints4);
			jContentPane.add(getOpenWebLinkButton(), gridBagConstraints5);
			jContentPane.add(linkDescriptionLabel, gridBagConstraints6);
			jContentPane.add(getCommentButton(), gridBagConstraints7);
			jContentPane.add(linkNameLbl, gridBagConstraints11);
		}
		return jContentPane;
	}

	/**
	 * This method initializes openWebLinkButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOpenWebLinkButton() {
		if (openWebLinkButton == null) {
			openWebLinkButton = new JButton();
			openWebLinkButton.setText("Go to link");
		}
		return openWebLinkButton;
	}

	/**
	 * This method initializes commentButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCommentButton() {
		if (commentButton == null) {
			commentButton = new JButton();
			commentButton.setText("Comments");
		}
		return commentButton;
	}
	
	private void SetLinkPhoto(final String url){
		if(null!=url)
		{
			java.net.URL where=null;
			try 
			{
				where = new URL(url);
			} 
			catch (MalformedURLException e)
			{
				e.printStackTrace();
			}
			ImageIcon image = new ImageIcon(where);
			this.imageLabel.setIcon(image);
		}
	}
	
	private void SetCreatorName(final String name)
	{
		if(null!=name)
		{
			this.creatorNameLabel.setText(name);
		}
	}
	
	private void SetCreationDate(final String date)
	{
		if(null!=date)
		{
			this.creationDateContentLabel.setText(date);
		}
	}
	
	private void SetLinkDescription(final String description)
	{
		if(null!=description)
		{
			this.linkDescriptionLabel.setText(description);
		}
	}
	
	private	void	SetLinkName( final String name )
	{
		if(null!=name)
		{
			this.linkNameLbl.setText(name);
		}
	}

	public void SetFeedID(String feedID) {
		this.feedID = feedID;
	}

	public String GetFeedID() {
		return feedID;
	}
	
	private void SetLinkContent(String getFeedID) 
	{
		ILinkContent link = _controller.GetLink(feedID);
		this.SetLinkPhoto(link.GetPhotoURL());
		this.SetLinkDescription(link.GetDescription());
		this.SetLinkName(link.GetName());
	}


} 
