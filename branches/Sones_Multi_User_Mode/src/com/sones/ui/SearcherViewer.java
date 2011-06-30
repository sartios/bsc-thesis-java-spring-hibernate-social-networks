package com.sones.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import com.sones.controllers.KeywordMakerController;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;

public class SearcherViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar searcherMenuBar = null;
	private JMenu keywordMenu = null;
	private JMenu sourceMenu = null;
	private JMenuItem createKeywordMenuItem = null;
	private JMenuItem createSourceListMenuItem = null;
	private JPanel searchingOptionPanel = null;
	private JCheckBox keywordsSearchingCheckBox = null;
	private JCheckBox hotFeedsSearchingCheckBox = null;
	private JButton searchButton = null;
	private JMenu viewMenu = null;
	private JMenuItem feedsAndKeywordsMenuItem = null;
	private JMenuItem viewSelectedSourcesMenuItem = null;
	private JSlider searchingWindowSlider = null;
	private JLabel searchingWindowLabel = null;
	private JLabel searchingWindowValueLabel = null;
	private JMenuItem hotFeedsMenuItem = null;

	/**
	 * This is the default constructor
	 */
	public SearcherViewer() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(576, 399);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(getSearcherMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Setup searcher options");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			searchingWindowValueLabel = new JLabel();
			searchingWindowValueLabel.setBounds(new Rectangle(189, 298, 76, 16));
			searchingWindowValueLabel.setHorizontalTextPosition(SwingConstants.LEADING);
			searchingWindowValueLabel.setText("0");
			searchingWindowLabel = new JLabel();
			searchingWindowLabel.setBounds(new Rectangle(14, 274, 165, 16));
			searchingWindowLabel.setText("Choose searching window");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getSearchingOptionPanel(), null);
			jContentPane.add(getSearchButton(), null);
			jContentPane.add(getSearchingWindowSlider(), null);
			jContentPane.add(searchingWindowLabel, null);
			jContentPane.add(searchingWindowValueLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes searcherMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getSearcherMenuBar() {
		if (searcherMenuBar == null) {
			searcherMenuBar = new JMenuBar();
			searcherMenuBar.add(getKeywordMenu());
			searcherMenuBar.add(getSourceMenu());
			searcherMenuBar.add(getViewMenu());
		}
		return searcherMenuBar;
	}

	/**
	 * This method initializes keywordMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getKeywordMenu() {
		if (keywordMenu == null) {
			keywordMenu = new JMenu();
			keywordMenu.setText("Keywords");
			keywordMenu.add(getCreateKeywordMenuItem());
		}
		return keywordMenu;
	}

	/**
	 * This method initializes sourceMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getSourceMenu() {
		if (sourceMenu == null) {
			sourceMenu = new JMenu();
			sourceMenu.setText("Sources");
			sourceMenu.add(getCreateSourceListMenuItem());
		}
		return sourceMenu;
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
	 * This method initializes createSourceListMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCreateSourceListMenuItem() {
		if (createSourceListMenuItem == null) {
			createSourceListMenuItem = new JMenuItem();
			createSourceListMenuItem.setText("Select");
		}
		return createSourceListMenuItem;
	}

	/**
	 * This method initializes searchingOptionPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSearchingOptionPanel() {
		if (searchingOptionPanel == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setHgap(13);
			gridLayout.setVgap(0);
			gridLayout.setColumns(2);
			searchingOptionPanel = new JPanel();
			searchingOptionPanel.setBounds(new Rectangle(0, 12, 288, 204));
			searchingOptionPanel.setLayout(gridLayout);
			searchingOptionPanel.add(getKeywordsSearchingCheckBox(), null);
			searchingOptionPanel.add(getHotFeedsSearchingCheckBox(), null);
		}
		return searchingOptionPanel;
	}

	/**
	 * This method initializes keywordsSearchingCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getKeywordsSearchingCheckBox() {
		if (keywordsSearchingCheckBox == null) {
			keywordsSearchingCheckBox = new JCheckBox();
			keywordsSearchingCheckBox.setText("Search for keywords");
		}
		return keywordsSearchingCheckBox;
	}

	/**
	 * This method initializes hotFeedsSearchingCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHotFeedsSearchingCheckBox() {
		if (hotFeedsSearchingCheckBox == null) {
			hotFeedsSearchingCheckBox = new JCheckBox();
			hotFeedsSearchingCheckBox.setText("Search for hot feeds");
		}
		return hotFeedsSearchingCheckBox;
	}

	/**
	 * This method initializes searchButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton();
			searchButton.setText("Search");
			searchButton.setBounds(new Rectangle(415, 287, 131, 43));
		}
		return searchButton;
	}

	/**
	 * This method initializes viewMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getViewMenu() {
		if (viewMenu == null) {
			viewMenu = new JMenu();
			viewMenu.setText("View");
			viewMenu.add(getFeedsAndKeywordsMenuItem());
			viewMenu.add(getViewSelectedSourcesMenuItem());
			viewMenu.add(getHotFeedsMenuItem());
		}
		return viewMenu;
	}

	/**
	 * This method initializes feedsAndKeywordsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getFeedsAndKeywordsMenuItem() {
		if (feedsAndKeywordsMenuItem == null) {
			feedsAndKeywordsMenuItem = new JMenuItem();
			feedsAndKeywordsMenuItem.setText("Feeds & Keywords");
		}
		return feedsAndKeywordsMenuItem;
	}
	
	/**
	 * Setting create keywords listener
	 */
	public void setCreateKeywordsMenuItemListener(final java.awt.event.ActionListener adapter){
		this.createKeywordMenuItem.addActionListener(adapter);
	}
	
	/**
	 * Setting create keywords listener
	 */
	public void setCreateSourceListMenuItemListener(final java.awt.event.ActionListener adapter){
		this.createSourceListMenuItem.addActionListener(adapter);
	}
	
	public void setViewSelectedSourcesMenuItemListener(final java.awt.event.ActionListener adapter){
		this.viewSelectedSourcesMenuItem.addActionListener(adapter);
	}

	/**
	 * This method initializes viewSelectedSourcesMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getViewSelectedSourcesMenuItem() {
		if (viewSelectedSourcesMenuItem == null) {
			viewSelectedSourcesMenuItem = new JMenuItem();
			viewSelectedSourcesMenuItem.setText("Selected Sources");
		}
		return viewSelectedSourcesMenuItem;
	}

	/**
	 * This method initializes searchingWindowSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getSearchingWindowSlider() {
		if (searchingWindowSlider == null) {
			searchingWindowSlider = new JSlider();
			searchingWindowSlider.setBounds(new Rectangle(14, 299, 141, 16));
			searchingWindowSlider.setMaximum(300);
			searchingWindowSlider.setValue(0);
		}
		return searchingWindowSlider;
	}
	
	public void setSearchingWindowSliderListener(final ChangeListener adapter){
		searchingWindowSlider.addChangeListener(adapter);
	}
	
	public int getSearchingWindowValue(){
		return searchingWindowSlider.getValue();
	}
	
	public void setSearchingWindowValue(final int minute,final int second){
		String value = new String(minute+":"+second);
		searchingWindowValueLabel.setText(value);
	}
	
	public void setFeedsAndKeywordsMenuItemListener(final java.awt.event.ActionListener adapter){
		this.feedsAndKeywordsMenuItem.addActionListener(adapter);
	}
	
	public void setViewHotFeedsMenuItemListener(final java.awt.event.ActionListener adapter){
		this.hotFeedsMenuItem.addActionListener(adapter);
	}

	/**
	 * This method initializes hotFeedsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getHotFeedsMenuItem() {
		if (hotFeedsMenuItem == null) {
			hotFeedsMenuItem = new JMenuItem();
			hotFeedsMenuItem.setText("Hot feeds");
		}
		return hotFeedsMenuItem;
	}
	
	public void setSearchButtonMouseListener(final java.awt.event.MouseAdapter adapter){
		searchButton.addMouseListener(adapter);
	}
	
	public String getSearchingOptions(){
		String option="0";
		
		if((true==keywordsSearchingCheckBox.isSelected())&&(false==hotFeedsSearchingCheckBox.isSelected())){
			option="keyword";
		}
		else if((false==keywordsSearchingCheckBox.isSelected())&&(true==hotFeedsSearchingCheckBox.isSelected())){
			option="hotFeed";
		}
		else if((true==keywordsSearchingCheckBox.isSelected())&&(true==hotFeedsSearchingCheckBox.isSelected())){
			option="all";	
		}
		else if((false==keywordsSearchingCheckBox.isSelected())&&(false==hotFeedsSearchingCheckBox.isSelected())){
			option="none";
		}
		return option;
	}

}  //  @jve:decl-index=0:visual-constraint="21,10"
