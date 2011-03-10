package com.sones.ui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import javax.swing.JList;
import javax.swing.JLabel;


import com.sones.businessLogic.Facebook.FacebookSearchingList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

public class SourcesSelector extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane SourcesTabs = null;
	private JPanel FacebookSourcesPane = null;
	private JLabel FacebookFriedsLbl = null;
	private JScrollPane FacebookFriendListBoxScrollPane = null;
	private JList facebookFriendListBox = null;
	
	//private DefaultListModel facebookFriends = new DefaultListModel();
	private DefaultListModel usernames = new DefaultListModel();
	//private FacebookFriendList friends;
	private FacebookSearchingList sources = new FacebookSearchingList();  //  @jve:decl-index=0:
	
	private JButton addFacebookFriendButton = null;
	private JButton deleteFacebookFriendButton = null;
	private JLabel facebookGroupsLabel = null;
	private JScrollPane facebookGroupsScrollPane = null;
	private JList facebookGroupsListBox = null;
	private JScrollPane jScrollPane = null;
	private JList jList = null;
	private JPanel facebookGroupListBoxControlPane = null;
	private JButton addFacebookGroupButton = null;
	private JButton deleteFacebookGroupButton = null;
	private JPanel facebookFriendListBoxControlPane = null;
	
	private DefaultListModel facebookUsernames = new DefaultListModel();
	private DefaultListModel facebookNamesOfGroups = new DefaultListModel();
	
	/**
	 * This is the default constructor
	 */
	public SourcesSelector() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1100, 539);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getSourcesTabs(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes SourcesTabs	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getSourcesTabs() {
		if (SourcesTabs == null) {
			SourcesTabs = new JTabbedPane();
			SourcesTabs.setBounds(new Rectangle(0, 0, 1084, 494));
			SourcesTabs.addTab("Facebook Sources", null, getFacebookSourcesPane(), null);
		}
		return SourcesTabs;
	}

	/**
	 * This method initializes FacebookSourcesPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFacebookSourcesPane() {
		if (FacebookSourcesPane == null) {
			facebookGroupsLabel = new JLabel();
			facebookGroupsLabel.setBounds(new Rectangle(325, 10, 138, 26));
			facebookGroupsLabel.setText("Facebook Groups");
			FacebookFriedsLbl = new JLabel();
			FacebookFriedsLbl.setBounds(new Rectangle(27, 14, 128, 23));
			FacebookFriedsLbl.setText("Facebook Friends");
			FacebookSourcesPane = new JPanel();
			FacebookSourcesPane.setLayout(null);
			FacebookSourcesPane.add(FacebookFriedsLbl, null);
			FacebookSourcesPane.add(getFacebookFriendListBoxScrollPane(), null);
			FacebookSourcesPane.add(facebookGroupsLabel, null);
			FacebookSourcesPane.add(getFacebookGroupsScrollPane(), null);
			FacebookSourcesPane.add(getJScrollPane(), null);
			FacebookSourcesPane.add(getFacebookGroupListBoxControlPane(), null);
			FacebookSourcesPane.add(getFacebookFriendListBoxControlPane(), null);
		}
		return FacebookSourcesPane;
	}

	/**/
	/**
	 * This method initializes FacebookFriendListBoxScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getFacebookFriendListBoxScrollPane() {
		if (FacebookFriendListBoxScrollPane == null) {
			FacebookFriendListBoxScrollPane = new JScrollPane();
			FacebookFriendListBoxScrollPane.setBounds(new Rectangle(12, 44, 227, 295));
			FacebookFriendListBoxScrollPane.setViewportView(getFacebookFriendListBox());
		}
		return FacebookFriendListBoxScrollPane;
	}

	/**
	 * This method initializes FacebookFriendListBox	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getFacebookFriendListBox() {
		//this.initializeFacebookFriends();
		if (facebookFriendListBox == null) {
			//facebookFriendListBox = new FacebookFriendListBox();
			//FacebookFriendListBox.setModel(facebookFriends);
			facebookFriendListBox = new JList();
			facebookFriendListBox.setModel(facebookUsernames);
		}
		return facebookFriendListBox;
	}
/*	
	private void initializeFacebookFriends(){
		TokenDao tokenDao = new TokenDao();
		FacebookToken token = (FacebookToken)tokenDao.findAll().get(0);
		FacebookRestHandler handler=  new FacebookRestHandler();
		friends = handler.getFriendList("100000866964787",token.getToken());
		List list = friends.getUsernames();
		for(int i=0;i<list.size();i++){
			facebookFriends.add(i, list.get(i));
		}
	}*/

	/**
	 * This method initializes createSourceListButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCreateSourceListButton() {
		if (addFacebookFriendButton == null) {
			addFacebookFriendButton = new JButton();
			addFacebookFriendButton.setText("Add source");
/*			addFacebookFriendButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					//List ids = (((FacebookFriendListBox)FacebookFriendListBox).getSelectedSources()).getIDs();
					//for(int i=0;i<ids.size();i++){
						//usernames.add(i, ids.get(i));
					//}
					
					((FacebookFriendListBox)facebookFriendListBox).addSelectedSources(sources);
					//((FacebookGroupListBox)facebookGroupsListBox).addSelectedSources(sources);
					List ids = sources.getIDs();
					usernames.clear();
					for(int i=0;i<ids.size();i++){
						usernames.add(i, ids.get(i));
					}
				}
			});*/
		}
		return addFacebookFriendButton;
	}

	/**
	 * This method initializes deleteSourceButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteSourceButton() {
		if (deleteFacebookFriendButton == null) {
			deleteFacebookFriendButton = new JButton();
			deleteFacebookFriendButton.setText("Delete source");
			/*deleteFacebookFriendButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					((FacebookFriendListBox)facebookFriendListBox).deleteSelectedSources(sources);
					//((FacebookGroupListBox)facebookGroupsListBox).deleteSelectedSources(sources);
					List ids = sources.getIDs();
					usernames.clear();
					for(int i=0;i<ids.size();i++){
						usernames.add(i, ids.get(i));
					}
				}
			});*/
		}
		return deleteFacebookFriendButton;
	}

	/**
	 * This method initializes facebookGroupsScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getFacebookGroupsScrollPane() {
		if (facebookGroupsScrollPane == null) {
			facebookGroupsScrollPane = new JScrollPane();
			facebookGroupsScrollPane.setBounds(new Rectangle(294, 42, 221, 298));
			facebookGroupsScrollPane.setViewportView(getFacebookGroupsListBox());
		}
		return facebookGroupsScrollPane;
	}

	/**
	 * This method initializes facebookGroupsListBox	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getFacebookGroupsListBox() {
		if (facebookGroupsListBox == null) {
			//facebookGroupsListBox = new FacebookGroupListBox();
			facebookGroupsListBox = new JList();
			facebookGroupsListBox.setModel(facebookNamesOfGroups);
		}
		return facebookGroupsListBox;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(657, 20, 402, 438));
			jScrollPane.setViewportView(getJList());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setModel(usernames);
		}
		return jList;
	}

	/**
	 * This method initializes facebookGroupListBoxControlPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFacebookGroupListBoxControlPane() {
		if (facebookGroupListBoxControlPane == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(3);
			gridLayout.setVgap(10);
			facebookGroupListBoxControlPane = new JPanel();
			facebookGroupListBoxControlPane.setLayout(gridLayout);
			facebookGroupListBoxControlPane.setBounds(new Rectangle(299, 349, 215, 109));
			facebookGroupListBoxControlPane.add(getAddFacebookGroup(), null);
			facebookGroupListBoxControlPane.add(getDeleteFacebookGroupButton(), null);
		}
		return facebookGroupListBoxControlPane;
	}

	/**
	 * This method initializes addFacebookGroup	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddFacebookGroup() {
		if (addFacebookGroupButton == null) {
			addFacebookGroupButton = new JButton();
			addFacebookGroupButton.setText("Add source");
			addFacebookGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					((FacebookGroupListBox)facebookGroupsListBox).addSelectedSources(sources);
					List ids = sources.getIDs();
					usernames.clear();
					for(int i=0;i<ids.size();i++){
						usernames.add(i, ids.get(i));
					}
				}
			});
		}
		return addFacebookGroupButton;
	}

	/**
	 * This method initializes deleteFacebookGroupButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteFacebookGroupButton() {
		if (deleteFacebookGroupButton == null) {
			deleteFacebookGroupButton = new JButton();
			deleteFacebookGroupButton.setPreferredSize(new Dimension(99, 26));
			deleteFacebookGroupButton.setText("Delete source");
			deleteFacebookGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					((FacebookGroupListBox)facebookGroupsListBox).deleteSelectedSources(sources);
					List ids = sources.getIDs();
					usernames.clear();
					for(int i=0;i<ids.size();i++){
						usernames.add(i, ids.get(i));
					}
				}
			});
		}
		return deleteFacebookGroupButton;
	}

	/**
	 * This method initializes facebookFriendListBoxControlPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFacebookFriendListBoxControlPane() {
		if (facebookFriendListBoxControlPane == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(3);
			gridLayout1.setVgap(10);
			facebookFriendListBoxControlPane = new JPanel();
			facebookFriendListBoxControlPane.setLayout(gridLayout1);
			facebookFriendListBoxControlPane.setLocation(new Point(11, 354));
			facebookFriendListBoxControlPane.setSize(new Dimension(213, 109));
			facebookFriendListBoxControlPane.add(getCreateSourceListButton(), null);
			facebookFriendListBoxControlPane.add(getDeleteSourceButton(), null);
		}
		return facebookFriendListBoxControlPane;
	}
	
	/**
	 * 
	 */
	public void setFacebookFriends(List<String> facebookFriends){
		for(int i=0;i<facebookFriends.size();i++){
			facebookUsernames.add(i, facebookFriends.get(i));
		}
	}
	
	/**
	 * 
	 */
	public void setFacebookGroups(List<String> facebookGroups){
		for(int i=0;i<facebookGroups.size();i++){
			facebookNamesOfGroups.add(i, facebookGroups.get(i));
		}
	}
	
	/**
	 * 
	 */
	public void setListenerToAddFacebookFriendsButton(MouseAdapter adapter){
		addFacebookFriendButton.addMouseListener(adapter);
	}
	
	/**
	 * 
	 */
	public void setListenerToDeleteFacebookFriendsButton(MouseAdapter adapter){
		deleteFacebookFriendButton.addMouseListener(adapter);
	}
	
	/**
	 * 
	 */
	public void setListenerToAddFacebookGroupButton(MouseAdapter adapter){
		addFacebookGroupButton.addMouseListener(adapter);
	}
	
	/**
	 * 
	 */
	public void setListenerToDeleteFacebookGroupButton(MouseAdapter adapter){
		deleteFacebookGroupButton.addMouseListener(adapter);
	}
	
	/**
	 * 
	 */
	public int[] getSelectedFriends(){
		return facebookFriendListBox.getSelectedIndices();
	}

}  //  @jve:decl-index=0:visual-constraint="34,14"
