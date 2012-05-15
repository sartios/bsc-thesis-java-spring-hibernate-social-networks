package com.sones.facebook.gui.sources;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import com.sones.facebook.controller.sources.FacebookPlaceDownloaderController;

public class FacebookPlaceDownloaderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField jCriteriaTextField = null;
	private JTextField jCitiesTextField = null;
	private JTabbedPane jTabbedPane = null;
	private JList jCriteriaList = null;
	private JList jCityList = null;
	private JButton jAddCityButton = null;
	private JButton jAddCriterionButton = null;
	private JButton jDownloadButton = null;
	private JLabel jLabel = null;
	private JTextField jRadicalTextField = null;
	private DefaultListModel criteriaModel = new DefaultListModel();
	private DefaultListModel citiesModel = new DefaultListModel();
	private FacebookPlaceDownloaderController controller;
	/**
	 * This is the default constructor
	 */
	public FacebookPlaceDownloaderFrame() {
		super();
		initialize();
		controller = new FacebookPlaceDownloaderController();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(586, 424);
		this.setContentPane(getJContentPane());
		this.setTitle("Download public places");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.BOTH;
			gridBagConstraints8.gridy = 0;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.insets = new Insets(10, 35, 0, 250);
			gridBagConstraints8.gridx = 1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Radical :");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.gridy = 5;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.gridy = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 1;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = GridBagConstraints.BOTH;
			gridBagConstraints41.gridy = 4;
			gridBagConstraints41.weightx = 1.0;
			gridBagConstraints41.weighty = 1.0;
			gridBagConstraints41.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints41.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(10, 35, 0, 100);
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.insets = new Insets(10, 35, 0, 100);
			gridBagConstraints4.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridy = 2;
			jLabel2 = new JLabel();
			jLabel2.setText("Enter criteria name :");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridy = 1;
			jLabel1 = new JLabel();
			jLabel1.setText("Enter city name :");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabel1, gridBagConstraints1);
			jContentPane.add(jLabel2, gridBagConstraints3);
			jContentPane.add(getJCriteriaTextField(), gridBagConstraints4);
			jContentPane.add(getJCitiesTextField(), gridBagConstraints5);
			jContentPane.add(getJTabbedPane(), gridBagConstraints41);
			jContentPane.add(getJAddCityButton(), gridBagConstraints);
			jContentPane.add(getJAddCriterionButton(), gridBagConstraints6);
			jContentPane.add(getJDownloadButton(), gridBagConstraints2);
			jContentPane.add(jLabel, gridBagConstraints7);
			jContentPane.add(getJRadicalTextField(), gridBagConstraints8);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jCriteriaTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJCriteriaTextField() {
		if (jCriteriaTextField == null) {
			jCriteriaTextField = new JTextField();
			jCriteriaTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						addCriteria();
					}
				}
			});
		}
		return jCriteriaTextField;
	}
	
	private void addCriteria()
	{
		String criteria = jCriteriaTextField.getText();
		if(criteriaModel.contains(criteria) == false)
		{
			criteriaModel.addElement(criteria);
		}
		jCriteriaTextField.setText("");
	}
	
	/**
	 * This method initializes jCitiesTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJCitiesTextField() {
		if (jCitiesTextField == null) {
			jCitiesTextField = new JTextField();
			jCitiesTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						addCity();
					}
				}
			});
		}
		return jCitiesTextField;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Criteria", null, getJCriteriaList(), null);
			jTabbedPane.addTab("Cities", null, getJCityList(), null);
		}
		return jTabbedPane;
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
			jCriteriaList.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_DELETE)
					{
						String criterion = (String) jCriteriaList.getSelectedValue();
						criteriaModel.removeElement(criterion);
					}
				}
			});
		}
		return jCriteriaList;
	}

	/**
	 * This method initializes jCityList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJCityList() {
		if (jCityList == null) {
			jCityList = new JList();
			jCityList.setModel(citiesModel);
			jCityList.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_DELETE)
					{
						String city = (String) jCityList.getSelectedValue();
						citiesModel.removeElement(city);
					}
				}
			});
		}
		return jCityList;
	}

	/**
	 * This method initializes jAddCityButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJAddCityButton() {
		if (jAddCityButton == null) {
			jAddCityButton = new JButton();
			jAddCityButton.setText("Add");
			jAddCityButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					addCity();
				}
			});
		}
		return jAddCityButton;
	}
	

	private void addCity() {
		String city = jCitiesTextField.getText();
		if(citiesModel.contains(city) == false)
		{
			citiesModel.addElement(city);
		}
		jCitiesTextField.setText("");
	}

	/**
	 * This method initializes jAddCriterionButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJAddCriterionButton() {
		if (jAddCriterionButton == null) {
			jAddCriterionButton = new JButton();
			jAddCriterionButton.setText("Add");
			jAddCriterionButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					addCriteria();
				}
			});
		}
		return jAddCriterionButton;
	}

	/**
	 * This method initializes jDownloadButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJDownloadButton() {
		if (jDownloadButton == null) {
			jDownloadButton = new JButton();
			jDownloadButton.setText("Download");
			jDownloadButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
					Iterable<String> criteria = getCriterias();
					Iterable<String> cities = getCities();
					String radical = getRadical();
					String appUserID = getAppUserID();
					controller.downloadPublicPlaces(criteria, cities, radical, appUserID);
				}
			});
		}
		return jDownloadButton;
	}

	protected String getAppUserID() 
	{
		return "0";
	}

	protected String getRadical() 
	{
		return jRadicalTextField.getText();
	}

	protected Iterable<String> getCities() 
	{
		Set<String> cities = new HashSet<String>();
		Object[] objects = citiesModel.toArray();
		for(Object object : objects)
		{
			cities.add((String) object);
		}
		return cities;
	}

	private Iterable<String> getCriterias() 
	{
		Set<String> criterias = new HashSet<String>();
		Object[] objects = criteriaModel.toArray();
		for(Object object : objects)
		{
			criterias.add((String) object);
		}
		return criterias;
	}

	/**
	 * This method initializes jRadicalTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJRadicalTextField() {
		if (jRadicalTextField == null) {
			jRadicalTextField = new JTextField();
		}
		return jRadicalTextField;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
