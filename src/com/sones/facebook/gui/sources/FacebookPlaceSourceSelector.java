package com.sones.facebook.gui.sources;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import com.sones.facebook.controller.sources.FacebookPlaceSourceSelectorController;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

import java.awt.Insets;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

public class FacebookPlaceSourceSelector extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jCriteriaScrollPane = null;
	private JScrollPane jPlaceScrollPane = null;
	private JScrollPane jSelectedSourcesScrollPane = null;
	private JButton jShowPlacesButton = null;
	private JButton jSaveSourcesButton = null;
	private FacebookPlaceSourceSelectorController controller;
	private FacebookPlaceDownloaderFrame placeDownloaderFrame;
	private DefaultListModel criteriaModel = new DefaultListModel();
	private DefaultListModel placeModel = new DefaultListModel();
	private DefaultListModel selectedSourcesModel = new DefaultListModel();
	private JList jCriteriaList = null;
	private JList jPlaceList = null;
	private JList jSelectedSourcesList = null;
	private ApplicationUserViewDto userDto;
	private JMenuBar jJMenuBar = null;
	private JMenu jPlaceMenu = null;
	private JMenuItem jDownloadMenuItem = null;
	private JMenu jCriteriaMenu = null;
	private JMenuItem jLoadMenuItem = null;
	private JButton jClearPlacesButton = null;
	/**
	 * This is the default constructor
	 */
	public FacebookPlaceSourceSelector() {
		super();
		initialize();
		controller = new FacebookPlaceSourceSelectorController();
		setCriterias();
		placeDownloaderFrame = new FacebookPlaceDownloaderFrame();
	}
	
	private void setCriterias()
	{
		criteriaModel.removeAllElements();
		Iterable<String> criterias = controller.getCriteria();
		for(String criterion : criterias)
		{
			criteriaModel.addElement(criterion);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(609, 396);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Select public places");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.weightx = 0.5;
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridy = 2;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 3;
			gridBagConstraints10.gridy = 2;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.gridy = 2;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = -1;
			gridBagConstraints21.gridy = -1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.insets = new Insets(25, 7, 70, 0);
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.gridx = 3;
			gridBagConstraints2.weightx = 1.0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.insets = new Insets(25, 0, 70, 0);
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.weightx = 1.0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(25, 7, 70, 15);
			gridBagConstraints.gridx = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJCriteriaScrollPane(), gridBagConstraints);
			jContentPane.add(getJPlaceScrollPane(), gridBagConstraints1);
			jContentPane.add(getJSelectedSourcesScrollPane(), gridBagConstraints2);
			jContentPane.add(getJShowPlacesButton(), gridBagConstraints9);
			jContentPane.add(getJSaveSourcesButton(), gridBagConstraints10);
			jContentPane.add(getJClearPlacesButton(), gridBagConstraints11);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jCriteriaScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJCriteriaScrollPane() {
		if (jCriteriaScrollPane == null) {
			jCriteriaScrollPane = new JScrollPane();
			jCriteriaScrollPane.setViewportView(getJCriteriaList());
		}
		return jCriteriaScrollPane;
	}

	/**
	 * This method initializes jPlaceScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJPlaceScrollPane() {
		if (jPlaceScrollPane == null) {
			jPlaceScrollPane = new JScrollPane();
			jPlaceScrollPane.setViewportView(getJPlaceList());
		}
		return jPlaceScrollPane;
	}

	/**
	 * This method initializes jSelectedSourcesScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJSelectedSourcesScrollPane() {
		if (jSelectedSourcesScrollPane == null) {
			jSelectedSourcesScrollPane = new JScrollPane();
			jSelectedSourcesScrollPane.setViewportView(getJSelectedSourcesList());
		}
		return jSelectedSourcesScrollPane;
	}

	/**
	 * This method initializes jShowPlacesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJShowPlacesButton() {
		if (jShowPlacesButton == null) {
			jShowPlacesButton = new JButton();
			jShowPlacesButton.setText("Show places");
			jShowPlacesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String criterion = (String) jCriteriaList.getSelectedValue();
					Iterable<String> places =  controller.showPlaces(criterion);
					for(String place : places)
					{
						placeModel.addElement(place);
					}
				}
			});
		}
		return jShowPlacesButton;
	}

	/**
	 * This method initializes jSaveSourcesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSaveSourcesButton() {
		if (jSaveSourcesButton == null) {
			jSaveSourcesButton = new JButton();
			jSaveSourcesButton.setText("Save");
			jSaveSourcesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					Object[] objects = selectedSourcesModel.toArray();
					List<String> places = new ArrayList<String>();
					for(Object object : objects)
					{
						places.add((String) object);
					}
					String userID = userDto.getUserID();
					controller.saveSources(places, userID);
				}
			});
		}
		return jSaveSourcesButton;
	}

	/**
	 * This method initializes jCriteriaList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJCriteriaList() {
		if (jCriteriaList == null) {
			jCriteriaList = new JList();
			jCriteriaList.setModel(criteriaModel);
		}
		return jCriteriaList;
	}

	/**
	 * This method initializes jPlaceList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJPlaceList() {
		if (jPlaceList == null) {
			jPlaceList = new JList();
			jPlaceList.setModel(placeModel);
			jPlaceList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
					if(e.getClickCount() == 2)
					{
						String place = (String) jPlaceList.getSelectedValue();
						addSelectedSource(place);
					}
				}
			});
		}
		return jPlaceList;
	}
	
	private void addSelectedSource(String place) {
		if( selectedSourcesModel.contains(place) == false)
		{
			selectedSourcesModel.addElement(place);
		}
	}

	/**
	 * This method initializes jSelectedSourcesList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJSelectedSourcesList() {
		if (jSelectedSourcesList == null) {
			jSelectedSourcesList = new JList();
			jSelectedSourcesList.setModel(selectedSourcesModel);
		}
		return jSelectedSourcesList;
	}

	/**
	 * @param userDto the userDto to set
	 */
	public void setUserDto(ApplicationUserViewDto userDto)
	{
		this.userDto = new ApplicationUserViewDto(userDto);
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJPlaceMenu());
			jJMenuBar.add(getJCriteriaMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jPlaceMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJPlaceMenu() {
		if (jPlaceMenu == null) {
			jPlaceMenu = new JMenu();
			jPlaceMenu.setText("Place");
			jPlaceMenu.add(getJDownloadMenuItem());
		}
		return jPlaceMenu;
	}

	/**
	 * This method initializes jDownloadMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJDownloadMenuItem() {
		if (jDownloadMenuItem == null) {
			jDownloadMenuItem = new JMenuItem();
			jDownloadMenuItem.setText("Download");
			jDownloadMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					placeDownloaderFrame.show();
				}
			});
		}
		return jDownloadMenuItem;
	}

	/**
	 * This method initializes jCriteriaMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJCriteriaMenu() {
		if (jCriteriaMenu == null) {
			jCriteriaMenu = new JMenu();
			jCriteriaMenu.setText("Criteria");
			jCriteriaMenu.add(getJLoadMenuItem());
		}
		return jCriteriaMenu;
	}

	/**
	 * This method initializes jLoadMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJLoadMenuItem() {
		if (jLoadMenuItem == null) {
			jLoadMenuItem = new JMenuItem();
			jLoadMenuItem.setText("Load");
			jLoadMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setCriterias();
				}
			});
		}
		return jLoadMenuItem;
	}

	/**
	 * This method initializes jClearPlacesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJClearPlacesButton() {
		if (jClearPlacesButton == null) {
			jClearPlacesButton = new JButton();
			jClearPlacesButton.setText("Clear places");
			jClearPlacesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					placeModel.removeAllElements();
				}
			});
		}
		return jClearPlacesButton;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
