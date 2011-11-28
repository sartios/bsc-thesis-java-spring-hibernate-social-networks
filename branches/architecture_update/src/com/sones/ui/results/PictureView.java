package com.sones.ui.results;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookPicture;
import com.sones.controllers.results.IPictureViewController;
import com.sones.controllers.results.PictureViewController;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.net.MalformedURLException;
import java.net.URL;

public class PictureView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel imageLabel = null;
	private JLabel jLabel1 = null;
	private JLabel creationDateLabel = null;
	private JLabel creatorNameLabel = null;
	private JLabel creationDateContentLabel = null;
	private JLabel imageDescriptionLabel = null;
	private JButton showCommentsButton = null;
	private	String	_feedID;
	private	IPictureViewController	_controller;

	/**
	 * This is the default constructor
	 */
	public PictureView() {
		super();
		_controller	=	new	PictureViewController();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(605, 362);
		this.setContentPane(getJContentPane());
		this.setTitle("See Image Feed");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				System.out.println("windowOpened()"); // TODO Auto-generated Event stub windowOpened()
				SetImage(GetFeedID());
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
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(12, 63, 19, 23);
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.gridy = 3;
			gridBagConstraints6.ipadx = 49;
			gridBagConstraints6.ipady = 16;
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridwidth = 2;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(10, 37, 11, 153);
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(8, 31, 95, 19);
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.gridx = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(26, 33, 15, 17);
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.ipadx = 1;
			gridBagConstraints3.gridx = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(8, 31, 95, 19);
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(26, 33, 15, 17);
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.ipadx = 1;
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(5, 7, 9, 30);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 153;
			gridBagConstraints.ipady = 138;
			gridBagConstraints.gridheight = 2;
			imageDescriptionLabel = new JLabel();
			imageDescriptionLabel.setText("Here is image description");
			creationDateContentLabel = new JLabel();
			creationDateContentLabel.setText("Creation Time");
			creatorNameLabel = new JLabel();
			creatorNameLabel.setText("Creator name");
			creationDateLabel = new JLabel();
			creationDateLabel.setText("Creation Date");
			jLabel1 = new JLabel();
			jLabel1.setText("Creator");
			imageLabel = new JLabel();
			imageLabel.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(imageLabel, gridBagConstraints);
			jContentPane.add(jLabel1, gridBagConstraints1);
			jContentPane.add(creationDateLabel, gridBagConstraints2);
			jContentPane.add(creatorNameLabel, gridBagConstraints3);
			jContentPane.add(creationDateContentLabel, gridBagConstraints4);
			jContentPane.add(imageDescriptionLabel, gridBagConstraints5);
			jContentPane.add(getShowCommentsButton(), gridBagConstraints6);
		}
		return jContentPane;
	}

	/**
	 * This method initializes showCommentsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getShowCommentsButton() {
		if (showCommentsButton == null) {
			showCommentsButton = new JButton();
			showCommentsButton.setText("Show Comments");
		}
		return showCommentsButton;
	}
	
	private void setImage(final String url){
		if(url!=null){	
			java.net.URL where=null;
			try {
				where = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			ImageIcon image = new ImageIcon(where);
			imageLabel.setIcon(image);
		}
	}
	
	private void setImageDescription(final String description){
		if(description!=null){
			imageDescriptionLabel.setText(description);
		}
	}
	
	private void setCreatorName(final String creatorName){
		if(creatorName!=null){
			creatorNameLabel.setText(creatorName);
		}
	}
	
	private void setCreationDate(final String creationDate){
		if(creationDate!=null){
			creationDateContentLabel.setText(creationDate);			
		}
	}

	public void SetFeedID(String _feedID) {
		this._feedID = _feedID;
	}

	public String GetFeedID() {
		return _feedID;
	}
	
	private void SetImage(String feedID) 
	{
		IFacebookPicture	picture	=	_controller.GetPicture(feedID);
		this.setImage(picture.GetUrl());
		this.setImageDescription(picture.GetMessage());
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
