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
	private JLabel jLabel = null;
	private JTextField jKeywordTextField = null;
	private JButton jButton = null;
	private KeywordCreatorController controller;

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
			jLabel = new JLabel();
			jLabel.setText("Enter keyword value:");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabel, gridBagConstraints);
			jContentPane.add(getJKeywordTextField(), gridBagConstraints1);
			jContentPane.add(getJButton(), gridBagConstraints2);
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
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Create");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String keyword = jKeywordTextField.getText();
					controller.createKeyword(keyword);
				}
			});
		}
		return jButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
