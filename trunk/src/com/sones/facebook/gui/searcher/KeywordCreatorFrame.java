package com.sones.facebook.gui.searcher;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.sones.facebook.controller.searcher.KeywordCreatorController;

import java.awt.Insets;

public class KeywordCreatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jKeywordLabel = null;
	private JTextField jKeywordTextField = null;
	private JButton jCreateButton = null;
	private KeywordCreatorController controller;
	private JButton jCloseButton = null;

	/**
	 * This is the default constructor
	 */
	public KeywordCreatorFrame() {
		super();
		initialize();
		controller = new KeywordCreatorController();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(440, 240);
		this.setContentPane(getJContentPane());
		this.setTitle("Create Keyword");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 2;
			gridBagConstraints21.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints21.insets = new Insets(0, 10, 0, 5);
			gridBagConstraints21.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.ipadx = 6;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.insets = new Insets(50, 0, 0, 2);
			gridBagConstraints2.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.ipadx = 150;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jKeywordLabel = new JLabel();
			jKeywordLabel.setText("Enter keyword value:");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jKeywordLabel, gridBagConstraints);
			jContentPane.add(getJKeywordTextField(), gridBagConstraints1);
			jContentPane.add(getJCreateButton(), gridBagConstraints2);
			jContentPane.add(getJCloseButton(), gridBagConstraints21);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jKeywordTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJKeywordTextField() {
		if (jKeywordTextField == null) {
			jKeywordTextField = new JTextField();
		}
		return jKeywordTextField;
	}

	/**
	 * This method initializes jCreateButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJCreateButton() {
		if (jCreateButton == null) {
			jCreateButton = new JButton();
			jCreateButton.setText("Create");
			jCreateButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed() for create button"); // TODO Auto-generated Event stub actionPerformed()
					String keyword = jKeywordTextField.getText();
					controller.createKeyword(keyword);
					jKeywordTextField.setText("");
				}
			});
		}
		return jCreateButton;
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
					System.out.println("actionPerformed() for close button"); // TODO Auto-generated Event stub actionPerformed()
					show(false);
				}
			});
		}
		return jCloseButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
