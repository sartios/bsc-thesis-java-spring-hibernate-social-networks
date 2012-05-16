package com.sones.facebook.gui.searcher;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.sones.facebook.controller.searcher.SearcherMainController;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

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
		this.setSize(583, 282);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
