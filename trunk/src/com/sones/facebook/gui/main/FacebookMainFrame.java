package com.sones.facebook.gui.main;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import com.sones.facebook.gui.downloader.DownloaderMainFrame;
import com.sones.facebook.gui.sources.FacebookSourceSelectorFrame;
import com.sones.facebook.gui.user.FacebookTokenFrame;

import java.awt.GridBagConstraints;

public class FacebookMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private FacebookSourceSelectorFrame sourceFrame;
	private FacebookTokenFrame tokenFrame;
	private DownloaderMainFrame downloaderFrame;

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
		this.setSize(628, 341);
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
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJButton(), gridBagConstraints);
			jContentPane.add(getJButton1(), gridBagConstraints1);
			jContentPane.add(getJButton2(), gridBagConstraints2);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Token");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					tokenFrame.show();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Sources");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					sourceFrame.show();
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Downloader");
			jButton2.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					downloaderFrame.show();
				}
			
			});
		}
		return jButton2;
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
