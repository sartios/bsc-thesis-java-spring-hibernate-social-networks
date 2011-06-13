package com.sones.ui;

import java.awt.BorderLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.JScrollPane;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.controllers.DisplayableFeedController;
import com.sones.controllers.FeedViewerFactory;
import javax.swing.WindowConstants;

public class FeedsWithCommentTrafficViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel searchingDateLabel = null;
	private JComboBox searchingDateComboBox = null;
	private JScrollPane feedScrollPane = null;
	private JList feedList = null;
	
	private DefaultListModel feeds_ = new DefaultListModel();
	private DefaultComboBoxModel searchingDates_ = new DefaultComboBoxModel();
	/**
	 * This is the default constructor
	 */
	public FeedsWithCommentTrafficViewer() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 395);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("See feeds with comment traffic");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.ipadx = 21;
			gridBagConstraints7.ipady = 133;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.weighty = 1.0;
			gridBagConstraints7.insets = new Insets(4, 95, 18, 109);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.ipadx = 149;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.insets = new Insets(6, 141, 4, 163);
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(10, 167, 5, 184);
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.ipadx = 9;
			gridBagConstraints5.ipady = 7;
			gridBagConstraints5.gridx = 0;
			searchingDateLabel = new JLabel();
			searchingDateLabel.setText("Chose searching date");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(searchingDateLabel, gridBagConstraints5);
			jContentPane.add(getSearchingDateComboBox(), gridBagConstraints6);
			jContentPane.add(getFeedScrollPane(), gridBagConstraints7);
		}
		return jContentPane;
	}

	/**
	 * This method initializes searchingDateComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSearchingDateComboBox() {
		if (searchingDateComboBox == null) {
			searchingDateComboBox = new JComboBox();
			searchingDateComboBox.setModel(searchingDates_);
		}
		return searchingDateComboBox;
	}

	/**
	 * This method initializes feedScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getFeedScrollPane() {
		if (feedScrollPane == null) {
			feedScrollPane = new JScrollPane();
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
	
	public void setSearchingDates(final List<String> dates){
		searchingDates_.addElement("Searching Dates");
		for(int i=0;i<dates.size();i++){
			searchingDates_.addElement(dates.get(i));
		}
	}
	
	public void setFeeds(final List<String> feeds){
		feeds_.clear();
		for(int i=0;i<feeds.size();i++){
			feeds_.add(i, feeds.get(i));
		}
	}
	
	public void setListenerToSearchingDateSelection(final ItemListener listener){
		searchingDateComboBox.addItemListener(listener);
	}
	
	public String getSelectedDate(){
		int index = searchingDateComboBox.getSelectedIndex();
		return (String) searchingDates_.getElementAt(index);
	}
	
	public void setListenerToFeedsSelection(){
		
	}
	
	public void addDoubleClickEventToFeedSelection(final MouseAdapter adapter){
		feedList.addMouseListener(adapter);
	}
	
	public String getSelectedFeed(){
		int index = feedList.getSelectedIndex();
		return (String) feeds_.getElementAt(index);
	}

}  //  @jve:decl-index=0:visual-constraint="12,4"
