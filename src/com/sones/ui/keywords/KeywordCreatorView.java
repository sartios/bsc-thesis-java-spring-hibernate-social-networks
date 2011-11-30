package com.sones.ui.keywords;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

import com.sones.businessLogic.UserManager.ApplicationUserID;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.controllers.keywords.IKeywordCreatorController;
import com.sones.controllers.keywords.KeywordCreatorController;

public class KeywordCreatorView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField jTextField = null;
	private JLabel jLabel = null;
	private	IKeywordCreatorController	_controller;
	private	IApplicationUserID	_userID;

	/**
	 * This is the default constructor
	 */
	public KeywordCreatorView() {
		super();
		_controller	=	new	KeywordCreatorController();
		_userID	=	new	ApplicationUserID("1");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Keyword Creator");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(2, 2, 3, 2);
			gridBagConstraints1.ipadx = 1;
			gridBagConstraints1.ipady = 1;
			gridBagConstraints1.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Enter keyword: ");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.ipadx = 16;
			gridBagConstraints.ipady = 1;
			gridBagConstraints.insets = new Insets(0, 3, 0, 3);
			gridBagConstraints.gridwidth = 0;
			gridBagConstraints.gridheight = 0;
			gridBagConstraints.gridx = 1;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJTextField(), gridBagConstraints);
			jContentPane.add(jLabel, gridBagConstraints1);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					System.out.println("keyReleased()"); // TODO Auto-generated Event stub keyReleased()
					if( e.getKeyChar() == '\n')
					{
						_controller.SaveUserKeyword(GetUserID(), jTextField.getText());
					}
				}
			});
		}
		return jTextField;
	}

	private	IApplicationUserID	GetUserID()
	{
		return	_userID;
	}
}
