package com.sones.facebook.gui.main;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import com.sones.facebook.gui.downloader.DownloaderMainFrame;
import com.sones.facebook.gui.results.FacebookPostKeywordSearchResultsFrame;
import com.sones.facebook.gui.results.KeywordsFrame;
import com.sones.facebook.gui.searcher.SearcherMainFrame;
import com.sones.facebook.gui.sources.FacebookPlaceSourceSelector;
import com.sones.facebook.gui.sources.FacebookSourceSelectorFrame;
import com.sones.facebook.gui.user.FacebookTokenFrame;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FacebookMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jTokenButton = null;
	private JButton jSourcesButton = null;
	private JButton jDownloaderButton = null;
	private FacebookSourceSelectorFrame sourceFrame;
	private FacebookTokenFrame tokenFrame;
	private DownloaderMainFrame downloaderFrame;
	private SearcherMainFrame searcherFrame;
	private FacebookPlaceSourceSelector placeFrame;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JButton jButton4 = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jDownloaderMenu = null;
	private JMenu jSourceMenu = null;
	private JMenu jTokenMenu = null;
	private JMenu jSearcherMenu = null;
	private JMenuItem jFriendsMenuItem = null;
	private JMenuItem jCreateTokenMenuItem = null;
	private JMenuItem jOpenSearcherMenuItem = null;
	private JMenuItem jOpenDownloaderMenuItem = null;
	private JButton jSearcherButton = null;
	private JMenuItem jPlacesMenuItem = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private FacebookPostKeywordSearchResultsFrame postResultFrame;
	private KeywordsFrame keywordResultFrame;
	private JMenuItem jMenuItem1 = null;

	/**
	 * This is the default constructor
	 */
	public FacebookMainFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(556, 333);
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
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.insets = new Insets(110, 20, 0, 0);
			gridBagConstraints3.gridy = 3;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.insets = new Insets(88, 30, 0, 0);
			gridBagConstraints11.gridy = 2;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJPanel(), gridBagConstraints11);
			jContentPane.add(getJPanel1(), gridBagConstraints3);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTokenButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJTokenButton() {
		if (jTokenButton == null) {
			jTokenButton = new JButton();
			jTokenButton.setText("Token");
			jTokenButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					showCreateToken();
				}
			});
		}
		return jTokenButton;
	}

	/**
	 * This method initializes jSourcesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSourcesButton() {
		if (jSourcesButton == null) {
			jSourcesButton = new JButton();
			jSourcesButton.setText("Sources");
			jSourcesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					showFriendSources();
				}
			});
		}
		return jSourcesButton;
	}

	/**
	 * This method initializes jDownloaderButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJDownloaderButton() {
		if (jDownloaderButton == null) {
			jDownloaderButton = new JButton();
			jDownloaderButton.setText("Downloader");
			jDownloaderButton.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					showDownloader();
				}
			
			});
		}
		return jDownloaderButton;
	}

	/**
	 * @param sourceFrame the sourceFrame to set
	 */
	public void setSourceFrame(FacebookSourceSelectorFrame sourceFrame) {
		this.sourceFrame = sourceFrame;
	}

	/**
	 * @return the sourceFrame
	 */
	public FacebookSourceSelectorFrame getSourceFrame() {
		return sourceFrame;
	}

	/**
	 * @param tokenFrame the tokenFrame to set
	 */
	public void setTokenFrame(FacebookTokenFrame tokenFrame) {
		this.tokenFrame = tokenFrame;
	}

	/**
	 * @return the tokenFrame
	 */
	public FacebookTokenFrame getTokenFrame() {
		return tokenFrame;
	}

	/**
	 * @param downloaderFrame the downloaderFrame to set
	 */
	public void setDownloaderFrame(DownloaderMainFrame downloaderFrame) {
		this.downloaderFrame = downloaderFrame;
	}

	/**
	 * @return the downloaderFrame
	 */
	public DownloaderMainFrame getDownloaderFrame() {
		return downloaderFrame;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 3;
			gridBagConstraints5.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = -1;
			gridBagConstraints.gridy = -1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = -1;
			gridBagConstraints1.gridy = -1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = -1;
			gridBagConstraints2.gridy = -1;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJDownloaderButton(), gridBagConstraints2);
			jPanel.add(getJSourcesButton(), gridBagConstraints1);
			jPanel.add(getJTokenButton(), gridBagConstraints);
			jPanel.add(getJSearcherButton(), gridBagConstraints5);
		}
		return jPanel;
	}

	/**
	 * @param searcherFrame the searcherFrame to set
	 */
	public void setSearcherFrame(SearcherMainFrame searcherFrame) {
		this.searcherFrame = searcherFrame;
	}

	/**
	 * @return the searcherFrame
	 */
	public SearcherMainFrame getSearcherFrame() {
		return searcherFrame;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getJButton4(), new GridBagConstraints());
		}
		return jPanel1;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("Exit");
			jButton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				}
			});
		}
		return jButton4;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJDownloaderMenu());
			jJMenuBar.add(getJSourceMenu());
			jJMenuBar.add(getJTokenMenu());
			jJMenuBar.add(getJSearcherMenu());
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jDownloaderMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJDownloaderMenu() {
		if (jDownloaderMenu == null) {
			jDownloaderMenu = new JMenu();
			jDownloaderMenu.add(getJOpenDownloaderMenuItem());
			jDownloaderMenu.setText("Downloader");
		}
		return jDownloaderMenu;
	}

	/**
	 * This method initializes jSourceMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJSourceMenu() {
		if (jSourceMenu == null) {
			jSourceMenu = new JMenu();
			jSourceMenu.setText("Sources");
			jSourceMenu.add(getJFriendsMenuItem());
			jSourceMenu.add(getJPlacesMenuItem());
		}
		return jSourceMenu;
	}

	/**
	 * This method initializes jTokenMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJTokenMenu() {
		if (jTokenMenu == null) {
			jTokenMenu = new JMenu();
			jTokenMenu.add(getJCreateTokenMenuItem());
			jTokenMenu.setText("Token");
		}
		return jTokenMenu;
	}

	/**
	 * This method initializes jSearcherMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJSearcherMenu() {
		if (jSearcherMenu == null) {
			jSearcherMenu = new JMenu();
			jSearcherMenu.add(getJOpenSearcherMenuItem());
			jSearcherMenu.setText("Searcher");
		}
		return jSearcherMenu;
	}

	/**
	 * This method initializes jFriendsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJFriendsMenuItem() {
		if (jFriendsMenuItem == null) {
			jFriendsMenuItem = new JMenuItem();
			jFriendsMenuItem.setText("Friends");
			jFriendsMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					showFriendSources();
				}
			});
		}
		return jFriendsMenuItem;
	}

	protected void showFriendSources() {
		this.sourceFrame.show();
	}

	/**
	 * This method initializes jCreateTokenMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJCreateTokenMenuItem() {
		if (jCreateTokenMenuItem == null) {
			jCreateTokenMenuItem = new JMenuItem();
			jCreateTokenMenuItem.setText("Create");
			jCreateTokenMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					showCreateToken();
				}
			});
		}
		return jCreateTokenMenuItem;
	}

	protected void showCreateToken() {
		this.tokenFrame.show();
	}

	/**
	 * This method initializes jOpenSearcherMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJOpenSearcherMenuItem() {
		if (jOpenSearcherMenuItem == null) {
			jOpenSearcherMenuItem = new JMenuItem();
			jOpenSearcherMenuItem.setText("Open");
			jOpenSearcherMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					showSearcher();
				}
			});
		}
		return jOpenSearcherMenuItem;
	}

	protected void showSearcher() {
		this.searcherFrame.show();
	}

	/**
	 * This method initializes jOpenDownloaderMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJOpenDownloaderMenuItem() {
		if (jOpenDownloaderMenuItem == null) {
			jOpenDownloaderMenuItem = new JMenuItem();
			jOpenDownloaderMenuItem.setText("Open");
			jOpenDownloaderMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					showDownloader();
				}
			});
		}
		return jOpenDownloaderMenuItem;
	}

	protected void showDownloader() {
		this.downloaderFrame.show();
	}

	/**
	 * This method initializes jSearcherButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSearcherButton() {
		if (jSearcherButton == null) {
			jSearcherButton = new JButton();
			jSearcherButton.setText("Searcher");
			jSearcherButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					showSearcher();
				}
			});
		}
		return jSearcherButton;
	}

	/**
	 * @param placeFrame the placeFrame to set
	 */
	public void setPlaceFrame(FacebookPlaceSourceSelector placeFrame) {
		this.placeFrame = placeFrame;
	}

	/**
	 * This method initializes jPlacesMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJPlacesMenuItem() {
		if (jPlacesMenuItem == null) {
			jPlacesMenuItem = new JMenuItem();
			jPlacesMenuItem.setText("Places");
			jPlacesMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					placeFrame.show();
				}
			});
		}
		return jPlacesMenuItem;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Results");
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
			jMenuItem.setText("View posts");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					showPostResults();
				}
			});
		}
		return jMenuItem;
	}

	protected void showPostResults() {
		postResultFrame.show();
	}

	/**
	 * @param postResultFrame the postResultFrame to set
	 */
	public void setPostResultFrame(FacebookPostKeywordSearchResultsFrame postResultFrame) {
		this.postResultFrame = postResultFrame;
	}
	
	/**
	 * @param keywordResultFrame the keywordResultFrame to set
	 */
	public void setKeywordResultFrame(KeywordsFrame keywordResultFrame) {
		this.keywordResultFrame = keywordResultFrame;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("View keywords");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					showKeywordResults();
				}
			});
		}
		return jMenuItem1;
	}

	protected void showKeywordResults() {
		keywordResultFrame.show();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
