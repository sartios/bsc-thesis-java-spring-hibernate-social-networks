package com.sones.facebook.gui.posts;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import javax.swing.JButton;

import com.sones.facebook.controller.posts.CommentController;
import com.sones.sharedDto.facebook.view.posts.CommentViewDto;

import java.awt.Insets;
import java.util.Set;

public class CommentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextArea jTextArea = null;
	private JPanel jPanel = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JLabel jLabel2 = null;
	private CommentController controller;
	/**
	 * This is the default constructor
	 */
	public CommentFrame() {
		super();
		initialize();
		controller = new CommentController();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(525, 305);
		this.setContentPane(getJContentPane());
		this.setTitle("Comment frame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 3;
			gridBagConstraints6.gridy = 0;
			jLabel2 = new JLabel();
			jLabel2.setText("The contect of a comment");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 3;
			gridBagConstraints4.gridy = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.insets = new Insets(70, 0, 70, 0);
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridx = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.insets = new Insets(0, 10, 0, 10);
			gridBagConstraints1.gridy = 1;
			jLabel1 = new JLabel();
			jLabel1.setText("");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			jLabel = new JLabel();
			jLabel.setText("Username");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabel, gridBagConstraints);
			jContentPane.add(jLabel1, gridBagConstraints1);
			jContentPane.add(getJTextArea(), gridBagConstraints2);
			jContentPane.add(getJPanel(), gridBagConstraints4);
			jContentPane.add(jLabel2, gridBagConstraints6);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(0, 0, 0, 10);
			gridBagConstraints5.fill = GridBagConstraints.NONE;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints3.fill = GridBagConstraints.NONE;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJButton1(), gridBagConstraints5);
			jPanel.add(getJButton2(), gridBagConstraints3);
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
			jButton1.setText("Previous");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(controller.hasPrevious() != false)
					{
						CommentViewDto comment = controller.getPrevious();
						showComment(comment);
					}
				}
			});
		}
		return jButton1;
	}
	
	private void showComment(CommentViewDto comment)
	{
		String username = comment.getUser().getUsername();
		jLabel1.setText(username);
		String content = comment.getValue();
		jTextArea.setText(content);
	}
	
	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Next");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(controller.hasNext() != false)
					{
						CommentViewDto comment = controller.getNext();
						showComment(comment);
					}
				}
			});
		}
		return jButton2;
	}
	
	public void setComments( Set<CommentViewDto> commentViewDtos )
	{
		controller.addComments(commentViewDtos);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
