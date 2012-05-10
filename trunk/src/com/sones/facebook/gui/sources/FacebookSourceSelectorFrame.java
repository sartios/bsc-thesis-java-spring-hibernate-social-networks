package com.sones.facebook.gui.sources;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import java.awt.Container;
import java.awt.GridBagLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import com.sones.facebook.controller.sources.FacebookSourceSelectorController;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

public class FacebookSourceSelectorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;
	private JScrollPane jScrollPane = null;
	private JList jList = null;
	private JScrollPane jScrollPane1 = null;
	private JList jList1 = null;
	private JPanel jPanel = null;
	private JButton jButton = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private FacebookSourceSelectorController controller;
	private DefaultListModel friendModel = new DefaultListModel();
	private DefaultListModel sourceModel = new DefaultListModel();
	private Map<String, Integer> selectedSources = new HashMap<String, Integer>();  //  @jve:decl-index=0:
	private ApplicationUserViewDto userDto;  //  @jve:decl-index=0:
	private final Logger _LOGGER;
	private Set<String> selectedNames;
	/**
	 * This is the default constructor
	 */
	public FacebookSourceSelectorFrame() {
		super();
		initialize();
		controller = new FacebookSourceSelectorController();
		_LOGGER = Logger.getLogger(FacebookSourceSelectorFrame.class);
		selectedNames = new HashSet<String>();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(705, 466);
		this.setJMenuBar(getJJMenuBar());
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
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 0;
			jLabel1 = new JLabel();
			jLabel1.setText("Selected Friends");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Facebook Friends");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.insets = new Insets(0, 220, 0, 0);
			gridBagConstraints3.gridy = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 3;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.insets = new Insets(0, 0, 30, 30);
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.ipadx = 0;
			gridBagConstraints.insets = new Insets(0, 0, 30, 30);
			gridBagConstraints.gridx = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPane(), gridBagConstraints);
			jContentPane.add(getJScrollPane1(), gridBagConstraints1);
			jContentPane.add(getJPanel(), gridBagConstraints2);
			jContentPane.add(getJButton(), gridBagConstraints3);
			jContentPane.add(jLabel, gridBagConstraints4);
			jContentPane.add(jLabel1, gridBagConstraints5);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Friends");
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Load");

			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String facebookUserId = userDto.getAccountID();
					Iterable<String> friends = controller.getFriendNames(facebookUserId);
					for(String name : friends)
					{
						friendModel.addElement(name);
					}
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Download");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String facebookUserId = userDto.getAccountID();
					controller.downloadFacebookFriends(facebookUserId);
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
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
			jList.setModel(friendModel);
			jList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount() == 2)
					{
						//addSource();
						addSourceName();
					}
				}
			});
		}
		return jList;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJList1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jList1	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList1() {
		if (jList1 == null) {
			jList1 = new JList();
			jList1.setModel(sourceModel);
		}
		return jList1;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Save");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					//saveSelectedSources();
					saveSelectedSourceName();
				}
			});
		}
		return jButton;
	}

	private void saveSelectedSources() {
		Set<String> keys = selectedSources.keySet();
		List<Integer> indices = new ArrayList<Integer>();
		for(String key : keys)
		{
			int index = selectedSources.get(key);
			indices.add(index);
			_LOGGER.warn("Index " + index);
		}
		String appUserID = userDto.getUserID();
		controller.saveFriendSources(indices,appUserID );
	}
	
	private void saveSelectedSourceName() {
		String appUserID = userDto.getUserID();
		controller.saveFriendSourcesByNames(selectedNames,appUserID );
	}
	
	private void addSource()
	{
		int position = jList.getSelectedIndex();
		String value = (String) jList.getSelectedValue();
		selectedSources.put(value, position);
		_LOGGER.warn("Position " + position);

		if(sourceModel.contains(value) == false)
		{
			sourceModel.addElement(value);
		}
	}
	
	private void addSourceName()
	{
		String name = (String) jList.getSelectedValue();
		selectedNames.add(name);
		_LOGGER.warn("Name " + name);

		if(sourceModel.contains(name) == false)
		{
			sourceModel.addElement(name);
		}
	}

	/**
	 * @param userDto the userDto to set
	 */
	public void setUserDto(ApplicationUserViewDto userDto) {
		this.userDto = new ApplicationUserViewDto(userDto);
	}

	/**
	 * @return the userDto
	 */
	public ApplicationUserViewDto getUserDto() {
		return userDto;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
