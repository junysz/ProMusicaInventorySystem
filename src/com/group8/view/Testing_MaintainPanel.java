package com.group8.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.group8.controller.Controller;

public class Testing_MaintainPanel extends JFrame {

	private JPanel contentPane;
	private MaintainPanel maintainPanel;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Testing_MaintainPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		maintainPanel= new MaintainPanel( );
		contentPane.add(maintainPanel);
		 
		
		
	}

	//this will accept any object that implements CategoryListener 
	//this is going to be class form controller packet
 public void setCategoryListener(CategoryListener categoryListenr){
	 System.out.println("I will accept any object that implemnts CategoryListener Class:Testing_MaintainPanel");
	 maintainPanel.setCategoryListenr(categoryListenr);
 }
	

}
