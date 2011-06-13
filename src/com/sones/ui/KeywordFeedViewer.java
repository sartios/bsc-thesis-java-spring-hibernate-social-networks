package com.sones.ui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.WindowConstants;

public class KeywordFeedViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane KeywordScrollPane = null;
	private JList KeywordList = null;
	private JLabel keywordLabel = null;
	private JLabel feedLabel = null;
	private JScrollPane feedScrollPane = null;
	private JList feedList = null;
	private JPanel feedButtonPanel = null;
	private JButton viewButton = null;
	private JPanel keywordButtonPanel = null;
	private JButton viewFeedsButton = null;

	
	private DefaultListModel keywords_=new DefaultListModel();
	private DefaultListModel feeds_ =new DefaultListModel();
	/**
	 * This is the default constructor
	 */
	public KeywordFeedViewer() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(495, 342);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
			feedLabel = new JLabel();
			feedLabel.setBounds(new Rectangle(268, 20, 83, 16));
			feedLabel.setText("Feeds: ");
			keywordLabel = new JLabel();
			keywordLabel.setBounds(new Rectangle(8, 15, 91, 16));
			keywordLabel.setText("Keywords: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getKeywordScrollPane(), null);
			jContentPane.add(keywordLabel, null);
			jContentPane.add(feedLabel, null);
			jContentPane.add(getFeedScrollPane(), null);
			jContentPane.add(getFeedButtonPanel(), null);
			jContentPane.add(getKeywordButtonPanel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes KeywordScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getKeywordScrollPane() {
		if (KeywordScrollPane == null) {
			KeywordScrollPane = new JScrollPane();
			KeywordScrollPane.setBounds(new Rectangle(5, 39, 150, 190));
			KeywordScrollPane.setViewportView(getKeywordList());
		}
		return KeywordScrollPane;
	}

	/**
	 * This method initializes KeywordList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getKeywordList() {
		if (KeywordList == null) {
			KeywordList = new JList();
			KeywordList.setModel(keywords_);
		}
		return KeywordList;
	}

	/**
	 * This method initializes feedScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getFeedScrollPane() {
		if (feedScrollPane == null) {
			feedScrollPane = new JScrollPane();
			feedScrollPane.setBounds(new Rectangle(268, 41, 150, 187));
			feedScrollPane.setViewportView(getFeedList());
		}
		return feedScrollPane;
	}

	/**
	 * This method initializes feedList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getFeedList() {
		if (feedList == null) {
			feedList = new JList();
			feedList.setModel(feeds_);
		}
		return feedList;
	}

	/**
	 * This method initializes feedButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFeedButtonPanel() {
		if (feedButtonPanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			feedButtonPanel = new JPanel();
			feedButtonPanel.setLayout(new GridBagLayout());
			feedButtonPanel.setBounds(new Rectangle(287, 245, 122, 43));
			feedButtonPanel.add(getViewButton(), gridBagConstraints);
		}
		return feedButtonPanel;
	}

	/**
	 * This method initializes viewButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getViewButton() {
		if (viewButton == null) {
			viewButton = new JButton();
			viewButton.setText("View Feed");
		}
		return viewButton;
	}

	/**
	 * This method initializes keywordButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getKeywordButtonPanel() {
		if (keywordButtonPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			keywordButtonPanel = new JPanel();
			keywordButtonPanel.setLayout(new GridBagLayout());
			keywordButtonPanel.setBounds(new Rectangle(16, 245, 113, 46));
			keywordButtonPanel.add(getViewFeedsButton(), gridBagConstraints1);
		}
		return keywordButtonPanel;
	}

	/**
	 * This method initializes viewFeedsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getViewFeedsButton() {
		if (viewFeedsButton == null) {
			viewFeedsButton = new JButton();
			viewFeedsButton.setText("View Feeds");
		}
		return viewFeedsButton;
	}
	
	/**
	 * Sets the keywordList with the keywords of the user
	 */
	public void setKeywordList(List<String> keywords){
		for(int i=0;i<keywords.size();i++){
			keywords_.add(i, keywords.get(i));
		}
	}
	
	/**
	 * Returns the index of selected keyword
	 */
	public int getSelectedKeywordIndex(){
		return KeywordList.getSelectedIndex();
	}
	
	/**
	 * Set feeds for a specific keyword
	 */
	public void setKeywordsFeeds(List<String> feedIDs){
		for(int i=0;i<feedIDs.size();i++){
			feeds_.add(i, feedIDs.get(i));
		}
	}
	
	/**
	 * Set action listener to viewFeedsButton
	 */
	public void setListernerToViewFeedsButton(MouseAdapter adapter){
		viewFeedsButton.addMouseListener(adapter);
	}
	
	/**
	 * Set action listener to viewFeedsButton
	 */
	public void setListernerToViewButton(MouseAdapter adapter){
		viewButton.addMouseListener(adapter);
	}
	
	public String getSelectedFeed(){
		String feedID = null;
		int selectedIndex = feedList.getSelectedIndex();
		if(selectedIndex>=0){
			feedID = feedList.getSelectedValue().toString();
		}
		return feedID;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
