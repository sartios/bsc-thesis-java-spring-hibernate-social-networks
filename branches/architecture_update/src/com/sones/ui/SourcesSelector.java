package com.sones.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class SourcesSelector extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainContentPane = null;
	private JMenuBar sourcesSelectorMenuBar = null;
	private JMenu facebookMenu = null;
	private JMenu twitterMenu = null;
	private JMenu facebookFriendsMenu = null;
	private JMenuItem loadFacebookFriendsMenuItem = null;
	private JMenuItem downloadFacebookFriendsMenuItem = null;
	private JMenu facebookGroupsMenu = null;
	private JMenuItem loadFacebookGroupsMenuItem = null;
	private JMenuItem downloadFacebookGroupsMenuItem = null;
	private JTabbedPane sourcesTabbedPane = null;
	private JPanel facebookPanel = null;
	private JPanel facebookFriendsPanel = null;
	private JLabel facebookFriendsLabel = null;
	private JScrollPane facebookFriendsScrollPane = null;
	private JList facebookFriendsList = null;
	private JPanel facebookGroupPanel = null;
	private JPanel facebookPagesPanel = null;
	private JLabel facebookGroupsLabel = null;
	private JScrollPane facebookGroupsScrollPane = null;
	private JList facebookGroupsList = null;
	private JPanel facebookFriendsControlsPanel = null;
	private JButton addFacebookFriendButton = null;
	private JPanel facebookGroupsControllsPanel = null;
	private JPanel jPanel = null;
	private JButton addFacebookGroupButton = null;
	private JLabel selectedSourcesLabel = null;
	private JScrollPane selectedSourcesScrollPane = null;
	private JList selectedSourcesList = null;
	
	private DefaultListModel facebookFriends = new DefaultListModel();
	private DefaultListModel facebookGroups = new DefaultListModel();
	private DefaultListModel selectedSources = new DefaultListModel();

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
		this.setSize(691, 445);
		this.setJMenuBar(getSourcesSelectorMenuBar());
		this.setContentPane(getMainContentPane());
		this.setTitle("Select sources");
	}

	/**
	 * This method initializes mainContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getMainContentPane() {
		if (mainContentPane == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.weightx = 1.0;
			mainContentPane = new JPanel();
			mainContentPane.setLayout(new GridBagLayout());
			mainContentPane.add(getSourcesTabbedPane(), gridBagConstraints);
		}
		return mainContentPane;
	}

	/**
	 * This method initializes sourcesSelectorMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getSourcesSelectorMenuBar() {
		if (sourcesSelectorMenuBar == null) {
			sourcesSelectorMenuBar = new JMenuBar();
			sourcesSelectorMenuBar.add(getFacebookMenu());
			sourcesSelectorMenuBar.add(getTwitterMenu());
		}
		return sourcesSelectorMenuBar;
	}

	/**
	 * This method initializes facebookMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFacebookMenu() {
		if (facebookMenu == null) {
			facebookMenu = new JMenu();
			facebookMenu.setText("Facebook");
			facebookMenu.add(getFacebookFriendsMenu());
			facebookMenu.add(getFacebookGroupsMenu());
		}
		return facebookMenu;
	}

	/**
	 * This method initializes twitterMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getTwitterMenu() {
		if (twitterMenu == null) {
			twitterMenu = new JMenu();
			twitterMenu.setText("Twitter");
		}
		return twitterMenu;
	}

	/**
	 * This method initializes facebookFriendsMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFacebookFriendsMenu() {
		if (facebookFriendsMenu == null) {
			facebookFriendsMenu = new JMenu();
			facebookFriendsMenu.setText("Friends");
			facebookFriendsMenu.add(getLoadFacebookFriendsMenuItem());
			facebookFriendsMenu.add(getDownloadFacebookFriendsMenuItem());
		}
		return facebookFriendsMenu;
	}

	/**
	 * This method initializes loadFacebookFriendsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getLoadFacebookFriendsMenuItem() {
		if (loadFacebookFriendsMenuItem == null) {
			loadFacebookFriendsMenuItem = new JMenuItem();
			loadFacebookFriendsMenuItem.setText("Load");
		}
		return loadFacebookFriendsMenuItem;
	}

	/**
	 * This method initializes downloadFacebookFriendsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDownloadFacebookFriendsMenuItem() {
		if (downloadFacebookFriendsMenuItem == null) {
			downloadFacebookFriendsMenuItem = new JMenuItem();
			downloadFacebookFriendsMenuItem.setText("Download");
		}
		return downloadFacebookFriendsMenuItem;
	}

	/**
	 * This method initializes facebookGroupsMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFacebookGroupsMenu() {
		if (facebookGroupsMenu == null) {
			facebookGroupsMenu = new JMenu();
			facebookGroupsMenu.setText("Groups");
			facebookGroupsMenu.add(getLoadFacebookGroupsMenuItem());
			facebookGroupsMenu.add(getDownloadFacebookGroupsMenuItem());
		}
		return facebookGroupsMenu;
	}

	/**
	 * This method initializes loadFacebookGroupsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getLoadFacebookGroupsMenuItem() {
		if (loadFacebookGroupsMenuItem == null) {
			loadFacebookGroupsMenuItem = new JMenuItem();
			loadFacebookGroupsMenuItem.setText("Load");
		}
		return loadFacebookGroupsMenuItem;
	}

	/**
	 * This method initializes downloadFacebookGroupsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDownloadFacebookGroupsMenuItem() {
		if (downloadFacebookGroupsMenuItem == null) {
			downloadFacebookGroupsMenuItem = new JMenuItem();
			downloadFacebookGroupsMenuItem.setText("Download");
		}
		return downloadFacebookGroupsMenuItem;
	}

	/**
	 * This method initializes sourcesTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getSourcesTabbedPane() {
		if (sourcesTabbedPane == null) {
			sourcesTabbedPane = new JTabbedPane();
			sourcesTabbedPane.addTab("Facebook Sources", null, getFacebookPanel(), null);
		}
		return sourcesTabbedPane;
	}

	/**
	 * This method initializes facebookPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFacebookPanel() {
		if (facebookPanel == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			gridLayout.setHgap(0);
			gridLayout.setColumns(1);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			facebookPanel = new JPanel();
			facebookPanel.setLayout(gridLayout);
			facebookPanel.add(getFacebookFriendsPanel(), null);
			facebookPanel.add(getFacebookGroupPanel(), null);
			facebookPanel.add(getFacebookPagesPanel(), null);
		}
		return facebookPanel;
	}

	/**
	 * This method initializes facebookFriendsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFacebookFriendsPanel() {
		if (facebookFriendsPanel == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.ipady = 0;
			gridBagConstraints7.gridwidth = 2;
			gridBagConstraints7.ipadx = 0;
			gridBagConstraints7.weighty = 0.0;
			gridBagConstraints7.gridy = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.ipadx = 0;
			gridBagConstraints3.ipady = 56;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 0);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.ipadx = 52;
			gridBagConstraints2.ipady = 40;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridx = 0;
			facebookFriendsLabel = new JLabel();
			facebookFriendsLabel.setText("Choose facebook friends :");
			facebookFriendsPanel = new JPanel();
			facebookFriendsPanel.setLayout(new GridBagLayout());
			facebookFriendsPanel.add(facebookFriendsLabel, gridBagConstraints2);
			facebookFriendsPanel.add(getFacebookFriendsScrollPane(), gridBagConstraints3);
			facebookFriendsPanel.add(getFacebookFriendsControlsPanel(), gridBagConstraints7);
		}
		return facebookFriendsPanel;
	}

	/**
	 * This method initializes facebookFriendsScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getFacebookFriendsScrollPane() {
		if (facebookFriendsScrollPane == null) {
			facebookFriendsScrollPane = new JScrollPane();
			facebookFriendsScrollPane.setViewportView(getFacebookFriendsList());
		}
		return facebookFriendsScrollPane;
	}

	/**
	 * This method initializes facebookFriendsList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getFacebookFriendsList() {
		if (facebookFriendsList == null) {
			facebookFriendsList = new JList();
			facebookFriendsList.setModel(facebookFriends);
		}
		return facebookFriendsList;
	}

	/**
	 * This method initializes facebookGroupPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFacebookGroupPanel() {
		if (facebookGroupPanel == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridy = 3;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints9.ipadx = 52;
			gridBagConstraints9.ipady = 40;
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.insets = new Insets(0, 0, 0, 0);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 2;
			gridBagConstraints6.ipadx = 0;
			gridBagConstraints6.ipady = 56;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.weighty = 1.0;
			gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.weightx = 1.0;
			facebookGroupsLabel = new JLabel();
			facebookGroupsLabel.setText("Choose facebook groups:");
			facebookGroupPanel = new JPanel();
			facebookGroupPanel.setLayout(new GridBagLayout());
			facebookGroupPanel.add(getFacebookGroupsScrollPane(), gridBagConstraints6);
			facebookGroupPanel.add(facebookGroupsLabel, gridBagConstraints9);
			facebookGroupPanel.add(getJPanel(), gridBagConstraints10);
		}
		return facebookGroupPanel;
	}

	/**
	 * This method initializes facebookPagesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFacebookPagesPanel() {
		if (facebookPagesPanel == null) {
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.BOTH;
			gridBagConstraints13.gridy = 1;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.weighty = 1.0;
			gridBagConstraints13.gridx = 0;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.ipadx = 0;
			gridBagConstraints12.ipady = 40;
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridy = 0;
			selectedSourcesLabel = new JLabel();
			selectedSourcesLabel.setText("Selected Sources");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 0;
			facebookPagesPanel = new JPanel();
			facebookPagesPanel.setLayout(new GridBagLayout());
			facebookPagesPanel.add(selectedSourcesLabel, gridBagConstraints12);
			facebookPagesPanel.add(getSelectedSourcesScrollPane(), gridBagConstraints13);
		}
		return facebookPagesPanel;
	}

	/**
	 * This method initializes facebookGroupsScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getFacebookGroupsScrollPane() {
		if (facebookGroupsScrollPane == null) {
			facebookGroupsScrollPane = new JScrollPane();
			facebookGroupsScrollPane.setViewportView(getFacebookGroupsList());
		}
		return facebookGroupsScrollPane;
	}

	/**
	 * This method initializes facebookGroupsList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getFacebookGroupsList() {
		if (facebookGroupsList == null) {
			facebookGroupsList = new JList();
			facebookGroupsList.setModel(facebookGroups);
		}
		return facebookGroupsList;
	}

	/**
	 * This method initializes facebookFriendsControlsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFacebookFriendsControlsPanel() {
		if (facebookFriendsControlsPanel == null) {
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 1;
			gridBagConstraints8.gridy = 0;
			facebookFriendsControlsPanel = new JPanel();
			facebookFriendsControlsPanel.setLayout(new GridBagLayout());
			facebookFriendsControlsPanel.add(getAddFacebookFriendButton(), gridBagConstraints8);
		}
		return facebookFriendsControlsPanel;
	}

	/**
	 * This method initializes addFacebookFriendButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddFacebookFriendButton() {
		if (addFacebookFriendButton == null) {
			addFacebookFriendButton = new JButton();
			addFacebookFriendButton.setText("Add");
		}
		return addFacebookFriendButton;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getAddFacebookGroupButton(), gridBagConstraints11);
		}
		return jPanel;
	}

	/**
	 * This method initializes addFacebookGroupButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddFacebookGroupButton() {
		if (addFacebookGroupButton == null) {
			addFacebookGroupButton = new JButton();
			addFacebookGroupButton.setText("Add");
		}
		return addFacebookGroupButton;
	}
	
	/**
	 * Facebook friends list box it's being initialized by this method. This list box
	 * contains the facebookFriends {@link DefaultListModel}. So we pass the usernames
	 * and set them inside the facebookFriends.
	 * @param usernames
	 */
	public void setFacebookFriendsUsernames(final List usernames){
		facebookFriends.clear();
		for(int i=0;i<usernames.size();i++){
			facebookFriends.add(i, usernames.get(i));
		}
	}
	
	/**
	 * We set the listener to menu item loadFacebookFriend.
	 * @param adapter
	 */
	public void setLoadFacebookFriendsMenuItemListerner(final ActionListener adapter){
		loadFacebookFriendsMenuItem.addActionListener(adapter);
	}
	
	/**
	 * We set the listener to menu item downloadFacebookFriends
	 * @param adapter
	 */
	public void setDownloadFacebookFriendsMenuItemListener(final ActionListener adapter){
		downloadFacebookFriendsMenuItem.addActionListener(adapter);
	}
	
	/**
	 * We add the given username into the facebookFriends {@link DefaultListModel}
	 * @param username
	 */
	public void addFacebookFriend(final String username){
		facebookFriends.addElement(username);
	}
	
	/**
	 * 
	 * @param adapter
	 */
	public void setLoadFacebookGroupsMenuItemListener(final ActionListener adapter){
		loadFacebookGroupsMenuItem.addActionListener(adapter);
	}
	
	public void setFacebookGroups(final List groups){
		facebookGroups.clear();
		for(int i=0;i<groups.size();i++){
			facebookGroups.add(i, groups.get(i));
		}
	}
	
	public void setDownloadFacebookGroupsMenuItemListener(final ActionListener adapter){
		downloadFacebookGroupsMenuItem.addActionListener(adapter);
	}
	
	public void addFacebookGroup(final String groupName){
		facebookGroups.addElement(groupName);
	}
	
	public void setAddFacebookFriendButtonListener(final MouseAdapter adapter){
		addFacebookFriendButton.addMouseListener(adapter);
		
	}
	
	public void setAddFacebookGroupButtonListener(final MouseAdapter adapter){
	
		addFacebookGroupButton.addMouseListener(adapter);

	}
	
	public List getFriends(){
		List<String> usernames = new ArrayList<String>();
		int[] indexes = facebookFriendsList.getSelectedIndices();
		for(int i=0;i<indexes.length;i++){
			usernames.add(i,(String) facebookFriends.get(indexes[i]));
		}
		return usernames;	
	}
	
	public List getGroups(){
		List<String> groups = new ArrayList<String>();
		int[] indexes = facebookGroupsList.getSelectedIndices();
		for(int i=0;i<indexes.length;i++){
			groups.add(i,(String) facebookGroups.get(indexes[i]));
		}
		return groups;	
	}

	/**
	 * This method initializes selectedSourcesScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSelectedSourcesScrollPane() {
		if (selectedSourcesScrollPane == null) {
			selectedSourcesScrollPane = new JScrollPane();
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
			selectedSourcesList.setModel(selectedSources);
		}
		return selectedSourcesList;
	}
	
	public void setSelectedSources(final List sources){
		selectedSources.clear();
		for(int i=0;i<sources.size();i++){
			selectedSources.add(i, sources.get(i));
		}
	}
	
	public void addDoubleClickEventToFacebookFriendsList(final MouseAdapter adapter){
		facebookFriendsList.addMouseListener(adapter);
	}
	
	public void addDoubleClickEventToFacebookGroupsList(final MouseAdapter adapter){
		facebookGroupsList.addMouseListener(adapter);
	}
}  //  @jve:decl-index=0:visual-constraint="27,29"
