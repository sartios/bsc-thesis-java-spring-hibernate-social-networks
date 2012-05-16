package com.sones.facebook.gui.searcher;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;

import com.sones.facebook.controller.searcher.KeywordSearcherIntervalController;
import com.sones.facebook.keywordSearcher.logic.IKeywordSearcherManagerService;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

public class KeywordSearcherIntervalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jIntervalLabel = null;
	private JTextField jIntervalTextField = null;
	private JButton jSaveButton = null;
	private KeywordSearcherIntervalController controller;
	private ApplicationUserViewDto userDto;

	/**
	 * This is the default constructor
	 */
	public KeywordSearcherIntervalFrame() {
		super();
		initialize();
		controller = new KeywordSearcherIntervalController();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(350, 243);
		this.setContentPane(getJContentPane());
		this.setTitle("Keyword searcher interval");
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
			gridBagConstraints2.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints2.insets = new Insets(100, 10, 0, 10);
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(50, 10, 0, 10);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(50, 0, 0, 10);
			gridBagConstraints.gridy = 0;
			jIntervalLabel = new JLabel();
			jIntervalLabel.setText("Enter interval value:");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jIntervalLabel, gridBagConstraints);
			jContentPane.add(getJIntervalTextField(), gridBagConstraints1);
			jContentPane.add(getJSaveButton(), gridBagConstraints2);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jIntervalTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJIntervalTextField() {
		if (jIntervalTextField == null) {
			jIntervalTextField = new JTextField();
		}
		return jIntervalTextField;
	}

	/**
	 * This method initializes jSaveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSaveButton() {
		if (jSaveButton == null) {
			jSaveButton = new JButton();
			jSaveButton.setText("Save");
			jSaveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String interval = jIntervalTextField.getText();
					String appUserId = userDto.getUserID();
					controller.saveOptions(appUserId, interval);
				}
			});
		}
		return jSaveButton;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(KeywordSearcherIntervalController controller) {
		this.controller = controller;
	}

	/**
	 * @param userDto the userDto to set
	 */
	public void setUserDto(ApplicationUserViewDto userDto) {
		this.userDto = userDto;
	}

	public void setControllerService(IKeywordSearcherManagerService service){
		controller.setService(service);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
