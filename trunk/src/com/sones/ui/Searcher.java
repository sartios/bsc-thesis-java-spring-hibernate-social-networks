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
import java.awt.event.MouseAdapter;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import com.sones.controllers.KeywordMakerController;
import javax.swing.WindowConstants;

public class Searcher extends JFrame {

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
	private JMenuItem feedsMenuItem = null;

	/**
	 * This is the default constructor
	 */
	public Searcher() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(461, 272);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getSearchingOptionPanel(), null);
			jContentPane.add(getSearchButton(), null);
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
			searchingOptionPanel.setLayout(gridLayout);
			searchingOptionPanel.setBounds(new Rectangle(12, 12, 252, 151));
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
			searchButton.setBounds(new Rectangle(306, 156, 131, 43));
			searchButton.setText("Search");
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
			viewMenu.add(getFeedsMenuItem());
		}
		return viewMenu;
	}

	/**
	 * This method initializes feedsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getFeedsMenuItem() {
		if (feedsMenuItem == null) {
			feedsMenuItem = new JMenuItem();
			feedsMenuItem.setText("Feeds");
		}
		return feedsMenuItem;
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
