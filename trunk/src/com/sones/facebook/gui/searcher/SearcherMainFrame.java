package com.sones.facebook.gui.searcher;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.sones.facebook.controller.searcher.SearcherMainController;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;
import java.awt.GridBagConstraints;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import java.awt.Insets;
import javax.swing.JButton;

public class SearcherMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jCreateMenu = null;
	private JMenuItem jCreateKeywordMenuItem = null;
	private JMenu jSelectMenu = null;
	private JMenuItem jSelectKeywordMenuItem = null;
	private JMenuItem jSelectLocationMenuItem = null;
	private JMenu jKeywordSearchMenu = null;
	private JMenu jSearchMenu = null;
	private JMenuItem jStartSearchMenuItem = null;
	private JMenuItem jStopSearchMenuItem = null;
	private JMenu jMenu4 = null;
	private JMenuItem jMenuItem6 = null;
	private JMenuItem jMenuItem7 = null;
	private SearcherMainController controller;
	private ApplicationUserViewDto userDto;  //  @jve:decl-index=0:
	private JMenuItem jCreateOptionMenuItem = null;
	private KeywordSearcherIntervalFrame intervalFrame;
	private JTabbedPane jTabbedPane = null;
	private JList jKeywordList = null;
	private JList jOptionList = null;
	private DefaultListModel keywordModel = new DefaultListModel();
	private DefaultListModel keywordOptionsModels = new DefaultListModel();
	private JButton jButton = null;
	
	/**
	 * This is the default constructor
	 */
	public SearcherMainFrame() {
		super();
		initialize();
		controller = new SearcherMainController();
		intervalFrame = new KeywordSearcherIntervalFrame();
		intervalFrame.setControllerService(controller.getService());
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(363, 282);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Searcher Main Window");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 75);
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(10, 30, 10, 135);
			gridBagConstraints.gridx = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJTabbedPane(), gridBagConstraints);
			jContentPane.add(getJButton(), gridBagConstraints3);
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
			jJMenuBar.add(getJCreateMenu());
			jJMenuBar.add(getJSelectMenu());
			jJMenuBar.add(getJSearchMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jCreateMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJCreateMenu() {
		if (jCreateMenu == null) {
			jCreateMenu = new JMenu();
			jCreateMenu.setText("Create");
			jCreateMenu.add(getJCreateKeywordMenuItem());
			jCreateMenu.add(getJCreateOptionMenuItem());
		}
		return jCreateMenu;
	}

	/**
	 * This method initializes jCreateKeywordMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJCreateKeywordMenuItem() {
		if (jCreateKeywordMenuItem == null) {
			jCreateKeywordMenuItem = new JMenuItem();
			jCreateKeywordMenuItem.setText("Keyword");
			jCreateKeywordMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					controller.createKeywords();
				}
			});
		}
		return jCreateKeywordMenuItem;
	}

	/**
	 * This method initializes jSelectMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJSelectMenu() {
		if (jSelectMenu == null) {
			jSelectMenu = new JMenu();
			jSelectMenu.setText("Select");
			jSelectMenu.add(getJSelectKeywordMenuItem());
			jSelectMenu.add(getJSelectLocationMenuItem());
		}
		return jSelectMenu;
	}

	/**
	 * This method initializes jSelectKeywordMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJSelectKeywordMenuItem() {
		if (jSelectKeywordMenuItem == null) {
			jSelectKeywordMenuItem = new JMenuItem();
			jSelectKeywordMenuItem.setText("Keyword");
			jSelectKeywordMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					controller.selectKeywords(userDto);
				}
			});
		}
		return jSelectKeywordMenuItem;
	}

	/**
	 * This method initializes jSelectLocationMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJSelectLocationMenuItem() {
		if (jSelectLocationMenuItem == null) {
			jSelectLocationMenuItem = new JMenuItem();
			jSelectLocationMenuItem.setText("Location");
		}
		return jSelectLocationMenuItem;
	}

	/**
	 * This method initializes jKeywordSearchMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJKeywordSearchMenu() {
		if (jKeywordSearchMenu == null) {
			jKeywordSearchMenu = new JMenu();
			jKeywordSearchMenu.setText("Keyword ...");
			jKeywordSearchMenu.add(getJStartSearchMenuItem());
			jKeywordSearchMenu.add(getJStopSearchMenuItem());
		}
		return jKeywordSearchMenu;
	}

	/**
	 * This method initializes jSearchMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJSearchMenu() {
		if (jSearchMenu == null) {
			jSearchMenu = new JMenu();
			jSearchMenu.setText("Search");
			jSearchMenu.add(getJKeywordSearchMenu());
			jSearchMenu.add(getJMenu4());
		}
		return jSearchMenu;
	}

	/**
	 * This method initializes jStartSearchMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJStartSearchMenuItem() {
		if (jStartSearchMenuItem == null) {
			jStartSearchMenuItem = new JMenuItem();
			jStartSearchMenuItem.setText("Start");
			jStartSearchMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String appUser = userDto.getUserID();
					controller.search(appUser);
				}
			});
		}
		return jStartSearchMenuItem;
	}

	/**
	 * This method initializes jStopSearchMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJStopSearchMenuItem() {
		if (jStopSearchMenuItem == null) {
			jStopSearchMenuItem = new JMenuItem();
			jStopSearchMenuItem.setText("Stop");
		}
		return jStopSearchMenuItem;
	}

	/**
	 * This method initializes jMenu4	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu4() {
		if (jMenu4 == null) {
			jMenu4 = new JMenu();
			jMenu4.setText("Location ...");
			jMenu4.add(getJMenuItem6());
			jMenu4.add(getJMenuItem7());
		}
		return jMenu4;
	}

	/**
	 * This method initializes jMenuItem6	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem6() {
		if (jMenuItem6 == null) {
			jMenuItem6 = new JMenuItem();
			jMenuItem6.setText("Start");
		}
		return jMenuItem6;
	}

	/**
	 * This method initializes jMenuItem7	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem7() {
		if (jMenuItem7 == null) {
			jMenuItem7 = new JMenuItem();
			jMenuItem7.setText("Stop");
		}
		return jMenuItem7;
	}

	/**
	 * @param userDto the userDto to set
	 */
	public void setUserDto(ApplicationUserViewDto userDto) {
		this.userDto = new ApplicationUserViewDto(userDto);
		intervalFrame.setUserDto(userDto);
	}

	/**
	 * @return the userDto
	 */
	public ApplicationUserViewDto getUserDto() {
		return userDto;
	}

	/**
	 * This method initializes jCreateOptionMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJCreateOptionMenuItem() {
		if (jCreateOptionMenuItem == null) {
			jCreateOptionMenuItem = new JMenuItem();
			jCreateOptionMenuItem.setText("Option");
			jCreateOptionMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					intervalFrame.show();
				}
			});
		}
		return jCreateOptionMenuItem;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Keywords", null, getJKeywordList(), null);
			jTabbedPane.addTab("Options", null, getJOptionList(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jKeywordList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJKeywordList() {
		if (jKeywordList == null) {
			jKeywordList = new JList();
			jKeywordList.setModel(keywordModel);
		}
		return jKeywordList;
	}

	/**
	 * This method initializes jOptionList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJOptionList() {
		if (jOptionList == null) {
			jOptionList = new JList();
			jOptionList.setModel(keywordOptionsModels);
		}
		return jOptionList;
	}
	
	private void showKeywords()
	{
		keywordModel.setSize(0);
		String applicationUserId = userDto.getUserID();
		Iterable<String> keywords = controller.getKeywords(applicationUserId);
		for(String keyword : keywords)
		{
			keywordModel.addElement(keyword);
		}
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Load");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed() for load button"); // TODO Auto-generated Event stub actionPerformed()
					showKeywords();
					showOptions();
				}
			});
		}
		return jButton;
	}
	
	private void showOptions()
	{
		keywordOptionsModels.setSize(0);
		String appUserId = userDto.getUserID();
		String options = controller.getOptions(appUserId);
		keywordOptionsModels.addElement(options);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
