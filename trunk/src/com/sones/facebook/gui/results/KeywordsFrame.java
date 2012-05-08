package com.sones.facebook.gui.results;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sones.facebook.controller.results.KeywordsController;
import com.sones.usermanager.model.ApplicationUser;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class KeywordsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private KeywordsController controller = new KeywordsController();  //  @jve:decl-index=0:
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField jNumOfFoundTextField = null;
	private JTextField jTimeRangeTextField = null;
	private JButton jButton = null;
	private ApplicationUser appUser = null;
	
	/**
	 * This is the default constructor
	 */
	public KeywordsFrame() {
		super();
		initialize();
		initTableModel();
		appUser = new ApplicationUser();
		appUser.setId("1");
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(868, 493);
		this.setContentPane(getJContentPane());
		this.setTitle("Keywords");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new Insets(130, 39, 188, 15);
			gridBagConstraints8.gridy = 0;
			gridBagConstraints8.ipadx = 27;
			gridBagConstraints8.ipady = 7;
			gridBagConstraints8.gridx = 1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 0;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.weighty = 1.0;
			gridBagConstraints7.insets = new Insets(5, 134, 26, 38);
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPane(), gridBagConstraints7);
			jContentPane.add(getJPanel(), gridBagConstraints8);
		}
		return jContentPane;
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
			jTable.setShowGrid(true);
			jTable.setModel(tableModel);
		}
		return jTable;
	}
	
	private void initTableModel()
	{
		String[] columnNames = controller.getColumnNames();
		for(String column : columnNames)
		{
			tableModel.addColumn(column);
		}
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints2.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints2.ipadx = 12;
			gridBagConstraints2.ipady = 13;
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridy = 2;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.ipadx = 20;
			gridBagConstraints4.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.ipadx = 20;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			jLabel1 = new JLabel();
			jLabel1.setText("Time range (minutes)");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Minimum found times");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(jLabel1, gridBagConstraints1);
			jPanel.add(getJNumOfFoundTextField(), gridBagConstraints3);
			jPanel.add(getJTimeRangeTextField(), gridBagConstraints4);
			jPanel.add(getJButton(), gridBagConstraints2);
		}
		return jPanel;
	}

	/**
	 * This method initializes jNumOfFoundTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJNumOfFoundTextField() {
		if (jNumOfFoundTextField == null) {
			jNumOfFoundTextField = new JTextField();
		}
		return jNumOfFoundTextField;
	}

	/**
	 * This method initializes jTimeRangeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTimeRangeTextField() {
		if (jTimeRangeTextField == null) {
			jTimeRangeTextField = new JTextField();
		}
		return jTimeRangeTextField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Show");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					clearKeywordResults();
					Integer timeOfFounds = new Integer( jNumOfFoundTextField.getText() );
					Integer time = new Integer( jTimeRangeTextField.getText() );
					Map<String, Long> results = controller.getKeywordResults(appUser,timeOfFounds, time);
					Set<String> keys = results.keySet();
					for(String key : keys)
					{
						Long value = results.get(key);
						Vector<String> row = new Vector<String>();
						row.add(key);
						row.add(value.toString());
						tableModel.addRow(row);
					}
				}
			});
		}
		return jButton;
	}
	
	private void clearKeywordResults()
	{
		tableModel.setRowCount(0);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
