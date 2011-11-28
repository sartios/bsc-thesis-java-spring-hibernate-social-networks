package com.sones.ui.main;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.omg.PortableServer._ServantActivatorStub;

import com.sones.businessLogic.UserManager.ApplicationUserID;
import com.sones.controllers.main.FacebookSonesConsoleController;
import com.sones.controllers.main.ISonesConsoleController;

public class SonesConsole extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;
	private JMenuItem jMenuItem2 = null;
	private	ISonesConsoleController	_controller;
	private String _interval;  //  @jve:decl-index=0:
	private JMenu jMenu1 = null;
	private JMenuItem jMenuItem3 = null;
	private JMenuItem jMenuItem4 = null;
	private JMenuItem jMenuItem5 = null;
	private JMenuItem jMenuItem6 = null;
	private JMenu jMenu2 = null;
	private JMenuItem jMenuItem7 = null;

	/**
	 * This is the default constructor
	 */
	public SonesConsole() {
		super();
		_controller	=	new	FacebookSonesConsoleController();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(521, 399);
		this.setJMenuBar(getJJMenuBar());
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
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu1());
			jJMenuBar.add(getJMenu2());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Download");
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
			jMenu.add(getJMenuItem2());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Start");
			jMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
					_controller.SetFacebookDownloadingInterval("10000");
					_controller.StartDownload();
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Stop");
			jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
					_controller.StopDownload();
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jMenuItem2	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Options");
			jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
					SetFacebookDownloadInterval();
					_controller.SetApplicationUser(new ApplicationUserID("1"));
				}
			});
		}
		return jMenuItem2;
	}

	private	String	GetFacebookDownloadInterval()
	{
		return	_interval;
	}

	private void SetFacebookDownloadInterval()
	{
		_interval = "10000";
	}

	/**
	 * This method initializes jMenu1	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("Search");
			jMenu1.add(getJMenuItem3());
			jMenu1.add(getJMenuItem4());
			jMenu1.add(getJMenuItem5());
			jMenu1.add(getJMenuItem6());
		}
		return jMenu1;
	}

	/**
	 * This method initializes jMenuItem3	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("Add keyword");
		}
		return jMenuItem3;
	}

	/**
	 * This method initializes jMenuItem4	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem4() {
		if (jMenuItem4 == null) {
			jMenuItem4 = new JMenuItem();
			jMenuItem4.setText("Add location");
		}
		return jMenuItem4;
	}

	/**
	 * This method initializes jMenuItem5	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem5() {
		if (jMenuItem5 == null) {
			jMenuItem5 = new JMenuItem();
			jMenuItem5.setText("Start Keyword Search");
			jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
					_controller.SearchForKeywords();
				}
			});
		}
		return jMenuItem5;
	}

	/**
	 * This method initializes jMenuItem6	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem6() {
		if (jMenuItem6 == null) {
			jMenuItem6 = new JMenuItem();
			jMenuItem6.setText("Start Location Search");
			jMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
					_controller.SearchForLocations();
				}
			});
		}
		return jMenuItem6;
	}

	/**
	 * This method initializes jMenu2	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu2() {
		if (jMenu2 == null) {
			jMenu2 = new JMenu();
			jMenu2.setText("View");
			jMenu2.add(getJMenuItem7());
		}
		return jMenu2;
	}

	/**
	 * This method initializes jMenuItem7	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem7() {
		if (jMenuItem7 == null) {
			jMenuItem7 = new JMenuItem();
			jMenuItem7.setText("Keyword Searching Results");
			jMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
					_controller.ViewKeywordSearchingResults();
				}
			});
		}
		return jMenuItem7;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
