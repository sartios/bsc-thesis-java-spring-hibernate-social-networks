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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class SourcesSelector_old extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane SourcesTabs = null;
	private JPanel FacebookSourcesPane = null;
	private JLabel FacebookFriedsLbl = null;
	private JScrollPane FacebookFriendListBoxScrollPane = null;
	private JList facebookFriendListBox = null;
	
	private DefaultListModel usernames = new DefaultListModel();
	private DefaultListModel sources = new DefaultListModel();
	
	private JButton addFacebookFriendButton = null;
	private JButton deleteFacebookFriendButton = null;
	private JLabel facebookGroupsLabel = null;
	private JScrollPane facebookGroupsScrollPane = null;
	private JList facebookGroupsListBox = null;
	private JPanel facebookGroupListBoxControlPane = null;
	private JButton addFacebookGroupButton = null;
	private JButton deleteFacebookGroupButton = null;
	private JPanel facebookFriendListBoxControlPane = null;
	
	private DefaultListModel facebookUsernames = new DefaultListModel();
	private DefaultListModel facebookNamesOfGroups = new DefaultListModel();
	private JMenuBar sourcesSelectorMenuBar = null;
	private JMenu fileMenu = null;
	private JMenuItem fileMenuItem = null;
	private JMenu keywordsMenu = null;
	private JMenuItem createKeywordMenuItem = null;
	private JScrollPane selectedSourcesScrollPane = null;
	private JList selectedSourcesList = null;
	private JLabel selectedSourcesLabel = null;
	private JPanel selectedSourcesControllersPanel = null;
	private JButton deleteSourceButton = null;
	/**
	 * This is the default constructor
	 */
	public SourcesSelector_old() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(844, 573);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setJMenuBar(getSourcesSelectorMenuBar());
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
			selectedSourcesLabel = new JLabel();
			selectedSourcesLabel.setBounds(new Rectangle(577, 11, 145, 31));
			selectedSourcesLabel.setText("Selected Sources");
			facebookGroupsLabel = new JLabel();
			facebookGroupsLabel.setBounds(new Rectangle(296, 13, 138, 26));
			facebookGroupsLabel.setText("Facebook Groups");
			FacebookFriedsLbl = new JLabel();
			FacebookFriedsLbl.setBounds(new Rectangle(12, 16, 128, 23));
			FacebookFriedsLbl.setText("Facebook Friends");
			FacebookSourcesPane = new JPanel();
			FacebookSourcesPane.setLayout(null);
			FacebookSourcesPane.add(FacebookFriedsLbl, null);
			FacebookSourcesPane.add(getFacebookFriendListBoxScrollPane(), null);
			FacebookSourcesPane.add(facebookGroupsLabel, null);
			FacebookSourcesPane.add(getFacebookGroupsScrollPane(), null);
			FacebookSourcesPane.add(getFacebookGroupListBoxControlPane(), null);
			FacebookSourcesPane.add(getFacebookFriendListBoxControlPane(), null);
			FacebookSourcesPane.add(getSelectedSourcesScrollPane(), null);
			FacebookSourcesPane.add(selectedSourcesLabel, null);
			FacebookSourcesPane.add(getSelectedSourcesControllersPanel(), null);
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
		if (facebookFriendListBox == null) {
			facebookFriendListBox = new JList();
			facebookFriendListBox.setModel(facebookUsernames);
		}
		return facebookFriendListBox;
	}

	/**
	 * This method initializes createSourceListButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCreateSourceListButton() {
		if (addFacebookFriendButton == null) {
			addFacebookFriendButton = new JButton();
			addFacebookFriendButton.setText("Add source");

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
			facebookGroupsListBox = new JList();
			facebookGroupsListBox.setModel(facebookNamesOfGroups);
		}
		return facebookGroupsListBox;
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
			gridLayout1.setRows(2);
			gridLayout1.setVgap(10);
			facebookFriendListBoxControlPane = new JPanel();
			facebookFriendListBoxControlPane.setLayout(gridLayout1);
			facebookFriendListBoxControlPane.setLocation(new Point(11, 354));
			facebookFriendListBoxControlPane.setSize(new Dimension(212, 93));
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
	public void setFacebookSourceList(List<String> facebookSources){
		sources.clear();
		for(int i=0;i<facebookSources.size();i++){
			sources.add(i, facebookSources.get(i));
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
	
	/**
	 * 
	 */
	public int[] getSelectedGroups(){
		return facebookGroupsListBox.getSelectedIndices();
	}
	
	/**
	 * Returns indexes of selected sources which they exist in sources list
	 */
	public int[] getSelectedSources(){
		return selectedSourcesList.getSelectedIndices();
	}
	
	/**
	 * Set listener to delete source button
	 */
	public void setMouseListenerToDeleteSourcesButton(final MouseAdapter adapter){
		this.deleteSourceButton.addMouseListener(adapter);
	}

	/**
	 * This method initializes sourcesSelectorMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getSourcesSelectorMenuBar() {
		if (sourcesSelectorMenuBar == null) {
			sourcesSelectorMenuBar = new JMenuBar();
			sourcesSelectorMenuBar.add(getFileMenu());
			sourcesSelectorMenuBar.add(getKeywordsMenu());
		}
		return sourcesSelectorMenuBar;
	}

	/**
	 * This method initializes fileMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getFileMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes fileMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getFileMenuItem() {
		if (fileMenuItem == null) {
			fileMenuItem = new JMenuItem();
			fileMenuItem.setText("Open");
		}
		return fileMenuItem;
	}

	/**
	 * This method initializes keywordsMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getKeywordsMenu() {
		if (keywordsMenu == null) {
			keywordsMenu = new JMenu();
			keywordsMenu.setText("Keyword");
			keywordsMenu.add(getCreateKeywordMenuItem());
		}
		return keywordsMenu;
	}

	/**
	 * This method initializes createKeywordMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCreateKeywordMenuItem() {
		if (createKeywordMenuItem == null) {
			createKeywordMenuItem = new JMenuItem();
			createKeywordMenuItem.setText("Create");
		}
		return createKeywordMenuItem;
	}

	/**
	 * This method initializes selectedSourcesScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSelectedSourcesScrollPane() {
		if (selectedSourcesScrollPane == null) {
			selectedSourcesScrollPane = new JScrollPane();
			selectedSourcesScrollPane.setBounds(new Rectangle(571, 48, 245, 290));
			selectedSourcesScrollPane.setViewportView(getSelectedSourcesList());
		}
		return selectedSourcesScrollPane;
	}

	/**
	 * This method initializes selectedSourcesList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getSelectedSourcesList() {
		if (selectedSourcesList == null) {
			selectedSourcesList = new JList();
		}
		return selectedSourcesList;
	}

	/**
	 * This method initializes selectedSourcesControllersPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSelectedSourcesControllersPanel() {
		if (selectedSourcesControllersPanel == null) {
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(3);
			gridLayout2.setVgap(10);
			gridLayout2.setHgap(0);
			selectedSourcesControllersPanel = new JPanel();
			selectedSourcesControllersPanel.setLayout(gridLayout2);
			selectedSourcesControllersPanel.setBounds(new Rectangle(570, 349, 248, 107));
			selectedSourcesControllersPanel.add(getDeleteButton(), null);
		}
		return selectedSourcesControllersPanel;
	}

	/**
	 * This method initializes deleteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteButton() {
		if (deleteSourceButton == null) {
			deleteSourceButton = new JButton();
			deleteSourceButton.setText("Delete source");
		}
		return deleteSourceButton;
	}

}  //  @jve:decl-index=0:visual-constraint="34,14"
