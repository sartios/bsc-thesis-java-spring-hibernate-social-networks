package com.sones.ui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.util.List;

import javax.swing.JList;

public class SourcesViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane sourcesScrollPane = null;
	private JList sourcesList = null;

	private DefaultListModel sources_ = new  DefaultListModel();
	
	/**
	 * This is the default constructor
	 */
	public SourcesViewer() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(249, 266);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("View selected sources");
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getSourcesScrollPane(), gridBagConstraints);
		}
		return jContentPane;
	}

	/**
	 * This method initializes sourcesScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSourcesScrollPane() {
		if (sourcesScrollPane == null) {
			sourcesScrollPane = new JScrollPane();
			sourcesScrollPane.setViewportView(getSourcesList());
		}
		return sourcesScrollPane;
	}

	/**
	 * This method initializes sourcesList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getSourcesList() {
		if (sourcesList == null) {
			sourcesList = new JList();
			sourcesList.setModel(sources_);
		}
		return sourcesList;
	}

	public void setWindowOpenListener(final WindowAdapter adapter){
		this.addWindowListener(adapter);
	}
	
	public void setWindowKeyPressedListener(final KeyAdapter adapter){
		sourcesList.addKeyListener(adapter);
	}
	
	public void setSources(final List sources){
		for(int i=0;i<sources.size();i++){
			this.sources_.add(i, sources.get(i));
		}
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
