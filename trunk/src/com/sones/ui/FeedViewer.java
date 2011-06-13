package com.sones.ui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import javax.swing.ListSelectionModel;

import com.sones.businessLogic.Feed;

import javax.swing.WindowConstants;

public class FeedViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane1 = null;
	private JPanel sourcesPanel = null;
	private JLabel sourcesLabel = null;
	private JScrollPane sourcesScrollPane = null;
	private JList sourceListBox = null;
	private JButton sourceFeedViewButton = null;
	private JPanel feedPanel = null;
	private JScrollPane feedScrollPane = null;
	private JTextArea feedTextArea = null;
	private JLabel feedLabel = null;
	private JPanel sourceFeedsPanel = null;
	private JButton feedViewerButton = null;
	private JLabel sourceFeedsLabel = null;
	private JScrollPane sourceFeedsScrollPane = null;
	private JList sourceFeedsListBox = null;
	
	private DefaultListModel sources_ = new DefaultListModel();
	private DefaultListModel sourceFeeds_ = new DefaultListModel();
	
	/**
	 * This is the default constructor
	 */
	public FeedViewer() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(668, 283);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane1());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane1() {
		if (jContentPane1 == null) {
			jContentPane1 = new JPanel();
			jContentPane1.setLayout(null);
			jContentPane1.add(getSourcesPanel(), null);
			jContentPane1.add(getFeedPanel(), null);
			jContentPane1.add(getSourceFeedsPanel(), null);
		}
		return jContentPane1;
	}

	/**
	 * This method initializes sourcesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSourcesPanel() {
		if (sourcesPanel == null) {
			sourcesLabel = new JLabel();
			sourcesLabel.setBounds(new Rectangle(8, 8, 67, 16));
			sourcesLabel.setText("Source ID :");
			sourcesPanel = new JPanel();
			sourcesPanel.setLayout(null);
			sourcesPanel.setBounds(new Rectangle(11, 10, 175, 229));
			sourcesPanel.add(sourcesLabel, null);
			sourcesPanel.add(getSourcesScrollPane(), null);
			sourcesPanel.add(getSourceFeedViewButton(), null);
		}
		return sourcesPanel;
	}

	/**
	 * This method initializes sourcesScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSourcesScrollPane() {
		if (sourcesScrollPane == null) {
			sourcesScrollPane = new JScrollPane();
			sourcesScrollPane.setBounds(new Rectangle(1, 36, 158, 156));
			sourcesScrollPane.setViewportView(getSourceListBox());
		}
		return sourcesScrollPane;
	}

	/**
	 * This method initializes sourceListBox	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getSourceListBox() {
		if (sourceListBox == null) {
			sourceListBox = new JList();
			sourceListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			sourceListBox.setModel(sources_);
		}
		return sourceListBox;
	}

	/**
	 * This method initializes sourceFeedViewButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSourceFeedViewButton() {
		if (sourceFeedViewButton == null) {
			sourceFeedViewButton = new JButton();
			sourceFeedViewButton.setBounds(new Rectangle(19, 201, 109, 22));
			sourceFeedViewButton.setText("View Feeds");
		}
		return sourceFeedViewButton;
	}

	/**
	 * This method initializes feedPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFeedPanel() {
		if (feedPanel == null) {
			feedLabel = new JLabel();
			feedLabel.setBounds(new Rectangle(2, 2, 87, 24));
			feedLabel.setText("Feed");
			feedPanel = new JPanel();
			feedPanel.setLayout(null);
			feedPanel.setBounds(new Rectangle(459, 17, 337, 218));
			feedPanel.add(getFeedScrollPane(), null);
			feedPanel.add(feedLabel, null);
		}
		return feedPanel;
	}

	/**
	 * This method initializes feedScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getFeedScrollPane() {
		if (feedScrollPane == null) {
			feedScrollPane = new JScrollPane();
			feedScrollPane.setBounds(new Rectangle(3, 35, 334, 184));
			feedScrollPane.setViewportView(getFeedTextArea());
		}
		return feedScrollPane;
	}

	/**
	 * This method initializes feedTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getFeedTextArea() {
		if (feedTextArea == null) {
			feedTextArea = new JTextArea();
		}
		return feedTextArea;
	}

	/**
	 * This method initializes sourceFeedsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSourceFeedsPanel() {
		if (sourceFeedsPanel == null) {
			sourceFeedsLabel = new JLabel();
			sourceFeedsLabel.setBounds(new Rectangle(2, 1, 109, 21));
			sourceFeedsLabel.setText("Source's feeds");
			sourceFeedsPanel = new JPanel();
			sourceFeedsPanel.setLayout(null);
			sourceFeedsPanel.setBounds(new Rectangle(202, 18, 235, 222));
			sourceFeedsPanel.add(getFeedViewerButton(), null);
			sourceFeedsPanel.add(sourceFeedsLabel, null);
			sourceFeedsPanel.add(getSourceFeedsScrollPane(), null);
		}
		return sourceFeedsPanel;
	}

	/**
	 * This method initializes feedViewerButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getFeedViewerButton() {
		if (feedViewerButton == null) {
			feedViewerButton = new JButton();
			feedViewerButton.setBounds(new Rectangle(20, 195, 101, 19));
			feedViewerButton.setText("View Feed");
		}
		return feedViewerButton;
	}

	/**
	 * This method initializes sourceFeedsScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSourceFeedsScrollPane() {
		if (sourceFeedsScrollPane == null) {
			sourceFeedsScrollPane = new JScrollPane();
			sourceFeedsScrollPane.setBounds(new Rectangle(2, 29, 205, 158));
			sourceFeedsScrollPane.setViewportView(getSourceFeedsListBox());
		}
		return sourceFeedsScrollPane;
	}

	/**
	 * This method initializes sourceFeedsListBox	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getSourceFeedsListBox() {
		if (sourceFeedsListBox == null) {
			sourceFeedsListBox = new JList();
			sourceFeedsListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			sourceFeedsListBox.setModel(sourceFeeds_);
		}
		return sourceFeedsListBox;
	}
	
	/**
	 * Sets sources id
	 */
	public void setSources(List sources){
		sources_.clear();
		for(int i=0;i<sources.size();i++){
			sources_.add(i, sources.get(i));
		}
	}
	
	/**
	 * Set source's feeds
	 */
	public void setSourceFeeds(Set sourceFeeds){
		Iterator it = sourceFeeds.iterator();
		int i=0;
		while (it.hasNext()) {
			Object feedID = it.next(); 
			sourceFeeds_.add(i,(String)feedID);
			i++;
		}

	}
	
	/**
	 * Set Listener to view source's feeds button
	 */
	public void setListenerToSourceFeedViewButton(MouseAdapter adapter){
		sourceFeedViewButton.addMouseListener(adapter);
	}
	
	
	/**
	 * Set Listener to view feed button
	 */
	public void setListenerToFeedViewerButton(MouseAdapter adapter){
		feedViewerButton.addMouseListener(adapter);
	}
	
	/**
	 * @return ID from the user we want to see the feeds
	 */
	public int getSelectedSource(){
		return sourceListBox.getSelectedIndex();
	}
	
	/**
	 * @return ID from the feed we want to see
	 */
	public String getSelectedFeed(){
		return (String) sourceFeedsListBox.getSelectedValue();
	}
	
	/**
	 * View the selected feed
	 */
	public void viewFeed(final Feed feed){
		feedTextArea.setText(feed.getCreatedTime_()+"\n");
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
