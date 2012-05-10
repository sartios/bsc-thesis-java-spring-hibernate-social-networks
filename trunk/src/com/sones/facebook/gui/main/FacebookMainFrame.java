package com.sones.facebook.gui.main;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import com.sones.facebook.gui.downloader.DownloaderMainFrame;
import com.sones.facebook.gui.searcher.SearcherMainFrame;
import com.sones.facebook.gui.sources.FacebookSourceSelectorFrame;
import com.sones.facebook.gui.user.FacebookTokenFrame;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	private JPanel jPanel = null;
	private JButton jSearcherButton = null;
	private JPanel jPanel1 = null;
	private JButton jButton4 = null;

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
					tokenFrame.show();
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
					sourceFrame.show();
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
					downloaderFrame.show();
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
			jPanel.add(getJSearcherButton(), new GridBagConstraints());
		}
		return jPanel;
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
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					searcherFrame.show();
				}
			});
		}
		return jSearcherButton;
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
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton4;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
