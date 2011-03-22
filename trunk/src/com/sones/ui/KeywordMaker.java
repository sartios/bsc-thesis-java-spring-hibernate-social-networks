package com.sones.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.WindowConstants;

public class KeywordMaker extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel keywordPanel = null;
	private JLabel keywordLabel = null;
	private JTextField keywordTextField = null;
	private JPanel keywordButtonsPanel = null;
	private JButton saveKeywordButton = null;

	/**
	 * This is the default constructor
	 */
	public KeywordMaker() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(226, 202);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Create Keywords");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getKeywordPanel(), null);
			jContentPane.add(getKeywordButtonsPanel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes keywordPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getKeywordPanel() {
		if (keywordPanel == null) {
			keywordLabel = new JLabel();
			keywordLabel.setBounds(new Rectangle(9, 13, 93, 22));
			keywordLabel.setText("Enter keyword :");
			keywordPanel = new JPanel();
			keywordPanel.setLayout(null);
			keywordPanel.setBounds(new Rectangle(11, 10, 173, 90));
			keywordPanel.add(keywordLabel, null);
			keywordPanel.add(getKeywordTextField(), null);
		}
		return keywordPanel;
	}

	/**
	 * This method initializes keywordTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKeywordTextField() {
		if (keywordTextField == null) {
			keywordTextField = new JTextField();
			keywordTextField.setBounds(new Rectangle(6, 43, 156, 28));
		}
		return keywordTextField;
	}

	/**
	 * This method initializes keywordButtonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getKeywordButtonsPanel() {
		if (keywordButtonsPanel == null) {
			keywordButtonsPanel = new JPanel();
			keywordButtonsPanel.setLayout(null);
			keywordButtonsPanel.setBounds(new Rectangle(10, 105, 174, 51));
			keywordButtonsPanel.add(getSaveKeywordButton(), null);
		}
		return keywordButtonsPanel;
	}

	/**
	 * This method initializes saveKeywordButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveKeywordButton() {
		if (saveKeywordButton == null) {
			saveKeywordButton = new JButton();
			saveKeywordButton.setBounds(new Rectangle(9, 10, 97, 29));
			saveKeywordButton.setText("Save");
		}
		return saveKeywordButton;
	}
	
	/**
	 * Set listener to save button
	 * @param adapter to be set
	 */
	public void setMouseListenerToSaveButton(final MouseAdapter adapter){
		saveKeywordButton.addMouseListener(adapter);
	}
	
	/**
	 * Returns the value we have write into the keyword's text box
	 */
	public String getKeywordValue(){
		return keywordTextField.getText().toString();
	}
	
	/**
	 * Clears the keyword's text box
	 */
	public void clearKeywordTextBox(){
		keywordTextField.setText("");
		keywordTextField.setFocusable(true);
	}
	
	/**
	 * Sets key adapter to text field
	 */
	public void setKeyboardListenerToKeywordTextField(final KeyAdapter adapter){
		keywordTextField.addKeyListener(adapter);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
