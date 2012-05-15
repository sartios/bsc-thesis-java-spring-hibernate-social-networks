package com.sones.facebook.gui.results;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sones.facebook.controller.results.FacebookPostKeywordSearchResultsController;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;
import com.sones.usermanager.model.ApplicationUser;

import java.awt.Insets;
import java.util.List;
import java.util.Vector;

public class FacebookPostKeywordSearchResultsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JPanel jPanel = null;
	private JButton jButton1 = null;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private FacebookPostKeywordSearchResultsController controller;
	private ApplicationUserViewDto userDto;
	
	/**
	 * This is the default constructor
	 */
	public FacebookPostKeywordSearchResultsFrame() {
		super();
		initialize();
		controller = new FacebookPostKeywordSearchResultsController();
		initTableModel();
	}

	private void initTableModel() {
		String[] columnNames = controller.getColumnNames();
		for(String columnName : columnNames)
		{
			tableModel.addColumn(columnName);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(629, 398);
		this.setContentPane(getJContentPane());
		this.setTitle("Facebook post results from keyword search");
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
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.gridx = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPane(), gridBagConstraints1);
			jContentPane.add(getJPanel(), gridBagConstraints2);
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
			jButton.setText("Show Results");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ApplicationUser appUser = new ApplicationUser();
					appUser.setId(userDto.getUserID());
					List< Vector< String > > rows = controller.getFacebookResults(appUser);
					for( Vector< String > row : rows )
					{
						tableModel.addRow( row );
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setModel(tableModel);
		}
		return jTable;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.ipadx = 50;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints3.ipady = 15;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = -1;
			gridBagConstraints.ipadx = 50;
			gridBagConstraints.ipady = 15;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.insets = new Insets(0, 0, 0, 8);
			gridBagConstraints.gridy = -1;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJButton(), gridBagConstraints);
			jPanel.add(getJButton1(), gridBagConstraints3);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Show Post");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int position = jTable.getSelectedRow();
					controller.showFacebookPost(position);
				}
			});
		}
		return jButton1;
	}

	/**
	 * @param userDto the userDto to set
	 */
	public void setUserDto(ApplicationUserViewDto userDto) {
		this.userDto = userDto;
	}

	/**
	 * @return the userDto
	 */
	public ApplicationUserViewDto getUserDto() {
		return userDto;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
