package com.group8.application;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.group8.controller.Controller;
import com.group8.view.*;

public class Main_TEST {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
	
		//Testing_Data_Base_Class theModel= new Testing_Data_Base_Class();
		Testing_MaintainPanel theView= new Testing_MaintainPanel();
		JTabbedPane contentPane=new JTabbedPane();
	    contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		 MaintainPanel maintainPanel = new MaintainPanel();
		 f.add(contentPane);
         contentPane.add("Maintain",maintainPanel);
         
         StockBrowserPanel stockBrowserPanel = new StockBrowserPanel();
		 contentPane.add("StockBrowserPanel",stockBrowserPanel);
         
		 ReservationPanel reservationPanel = new ReservationPanel();
		 contentPane.add("Reservation",reservationPanel);
		 
		 f.setSize(900, 600);
		 f.setVisible(true);
		
		
		
		
		
		Controller theController= new Controller(theView);
		
		/*pass object that listen for an event this is going to be Controller
		 *we have to pass here an object that implements interface with method that 
		 *will be used whenever category event occurs
		 *if a category event occurs in the view tell controller about it set controller as a listener to the view  
		 */
		theView.setCategoryListener(theController);
		theView.setAccountListener(theController);
		

	}

}
