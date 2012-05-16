package com.sones.facebook.gui.searcher;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Insets;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;

import com.sones.facebook.controller.searcher.KeywordSelectorController;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;
import javax.swing.JButton;

public class KeywordSelectorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jUserKeywordScrollPane = null;
	private JList jUserKeywordList = null;
	private JScrollPane jSelectedKeywordScrollPane = null;
	private JList jSelectedKeywordList = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private JMenu jMenu1 = null;
	private JMenuItem jMenuItem1 = null;
	private DefaultListModel selectedKeywords = new DefaultListModel();;
	private DefaultListModel createdKeywords = new DefaultListModel();;
	private KeywordSelectorController controller = null;
	private ApplicationUserViewDto userDto;
	private JButton jCloseButton = null;

	/**
	 * This is the default constructor
	 */
	public KeywordSelectorFrame() {
		super();
		initialize();
		controller = new KeywordSelectorController();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(614, 339);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Select keywords");
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
			gridBagConstraints11.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints11.insets = new Insets(0, 0, 0, 10);
			gridBagConstraints11.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.insets = new Insets(10, 20, 10, 10);
			gridBagConstraints1.weightx = 1.0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(10, 10, 10, 20);
			gridBagConstraints.gridx = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJUserKeywordScrollPane(), gridBagConstraints);
			jContentPane.add(getJSelectedKeywordScrollPane(), gridBagConstraints1);
			jContentPane.add(getJCloseButton(), gridBagConstraints11);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jUserKeywordScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJUserKeywordScrollPane() {
		if (jUserKeywordScrollPane == null) {
			jUserKeywordScrollPane = new JScrollPane();
			jUserKeywordScrollPane.setViewportView(getJUserKeywordList());
		}
		return jUserKeywordScrollPane;
	}

	/**
	 * This method initializes jUserKeywordList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJUserKeywordList() {
		if (jUserKeywordList == null) {
			jUserKeywordList = new JList();
			jUserKeywordList.setModel(createdKeywords);
			jUserKeywordList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					if(e.getClickCount() == 2)
					{
						String keywordValue = (String) jUserKeywordList.getSelectedValue();
						selectedKeywords.addElement(keywordValue);
					}
				}
			});
		}
		return jUserKeywordList;
	}

	/**
	 * This method initializes jSelectedKeywordScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJSelectedKeywordScrollPane() {
		if (jSelectedKeywordScrollPane == null) {
			jSelectedKeywordScrollPane = new JScrollPane();
			jSelectedKeywordScrollPane.setViewportView(getJSelectedKeywordList());
		}
		return jSelectedKeywordScrollPane;
	}

	/**
	 * This method initializes jSelectedKeywordList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJSelectedKeywordList() {
		if (jSelectedKeywordList == null) {
			jSelectedKeywordList = new JList();
			jSelectedKeywordList.setModel(selectedKeywords);
		}
		return jSelectedKeywordList;
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
			jJMenuBar.add(getJMenu1());
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
			jMenu.setText("Load");
			jMenu.add(getJMenuItem());
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
			jMenuItem.setText("Keywords");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					Set<String> keywords = controller.getKeywords();
					for(String keyword : keywords)
					{
						createdKeywords.addElement(keyword);
					}
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenu1	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("Save");
			jMenu1.add(getJMenuItem1());
		}
		return jMenu1;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Selected keywords");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					Set<String> keywords = new HashSet<String>();
					for(Object object : selectedKeywords.toArray())
					{
						keywords.add( (String)object );
					}
					String appUser = userDto.getUserID();
					controller.submitSelectedKeywords(appUser,keywords );
				}
			});
		}
		return jMenuItem1;
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

	/**
	 * This method initializes jCloseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJCloseButton() {
		if (jCloseButton == null) {
			jCloseButton = new JButton();
			jCloseButton.setText("Close");
			jCloseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					show(false);
				}
			});
		}
		return jCloseButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
