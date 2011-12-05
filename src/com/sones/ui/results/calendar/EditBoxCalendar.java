package com.sones.ui.results.calendar;

import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JButton;

import org.apache.log4j.Logger;

import com.sones.controllers.results.calendar.EditBoxCalendarController;
import com.sones.controllers.results.calendar.ICalendarController;
import com.sones.ui.results.ResultsView;

import java.awt.Insets;
import java.awt.Dimension;

public class EditBoxCalendar extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField jFromDateTextField = null;
	private JTextField jToDateTextField = null;
	private JLabel jFromDateLabel = null;
	private JLabel jToDateLabel = null;
	private JButton jOkButton = null;
	private	ICalendarController	_controller;
	private	Logger	_logger;
	
	/**
	 * 
	 * @param owner
	 * @param controller
	 */
	public	EditBoxCalendar(Frame owner, ICalendarController controller)
	{
		super(owner);
		_logger	=	Logger.getLogger(EditBoxCalendar.class);
		_controller = controller;
		initialize();
	}
	
	/**
	 * @param owner
	 */
	public EditBoxCalendar(Frame owner)
	{
		super(new ResultsView());
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(407, 321);
		this.setTitle("Set dates");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.ipadx = 20;
			gridBagConstraints4.ipady = 15;
			gridBagConstraints4.insets = new Insets(80, 151, 0, 0);
			gridBagConstraints4.gridy = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints3.ipady = 2;
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridy = 1;
			jToDateLabel = new JLabel();
			jToDateLabel.setText("To date :");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(60, 0, 0, 0);
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridy = 0;
			jFromDateLabel = new JLabel();
			jFromDateLabel.setText("From date :");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(30, 10, 0, 30);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.insets = new Insets(60, 10, 0, 30);
			gridBagConstraints.gridx = 1;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJFromDateTextField(), gridBagConstraints);
			jContentPane.add(getJToDateTextField(), gridBagConstraints1);
			jContentPane.add(jFromDateLabel, gridBagConstraints2);
			jContentPane.add(jToDateLabel, gridBagConstraints3);
			jContentPane.add(getJOkButton(), gridBagConstraints4);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jFromDateTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJFromDateTextField() {
		if (jFromDateTextField == null) {
			jFromDateTextField = new JTextField();
		}
		return jFromDateTextField;
	}

	/**
	 * This method initializes jToDateTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJToDateTextField() {
		if (jToDateTextField == null) {
			jToDateTextField = new JTextField();
		}
		return jToDateTextField;
	}

	/**
	 * This method initializes jOkButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJOkButton() {
		if (jOkButton == null) {
			jOkButton = new JButton();
			jOkButton.setText("OK");
			jOkButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					SetDates();
					CloseDialog();
				}
			});
		}
		return jOkButton;
	}
	
	private	void	SetDates()
	{
		_controller.SetEndDate(jToDateTextField.getText());
		_logger.warn("End date: " 
				+jToDateTextField.getText());
		_controller.SetFromDate(jFromDateTextField.getText());
		_logger.warn("From date: " 
				+jFromDateTextField.getText());
	}
	
	private	void	CloseDialog()
	{
		this.dispose();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
