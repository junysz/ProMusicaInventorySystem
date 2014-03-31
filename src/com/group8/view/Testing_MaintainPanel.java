package com.group8.view;

	

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;




@SuppressWarnings("serial")
public class Testing_MaintainPanel extends JFrame {

	private JPanel contentPane;
	private MaintainPanel maintainPanel;
	private ReservationPanel reservationPanel;

	public Testing_MaintainPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		maintainPanel= new MaintainPanel( );
		reservationPanel= new ReservationPanel();
		
		
		contentPane.add(reservationPanel);
		

	}

	//this will accept any object that implements CategoryListener 
	//this is going to be class form controller packet
	public void setCategoryListener(CategoryListener categoryListenr){
		System.out.println("I will accept any object that implemnts CategoryListener Class:Testing_MaintainPanel");
		maintainPanel.setCategoryListenr(categoryListenr);
	}

	public void setAccountListener(AccountListner accountListener) {
		maintainPanel.setAccountListener(accountListener);
	}


}

 	
 	
 	
 	
 	

