package com.group8.application;
import java.awt.EventQueue;

import javax.swing.WindowConstants;

import com.group8.controller.Controller;
import com.group8.model.MainModel;
import com.group8.view.*;

public class mainClass {

	static MainFrame theView= new MainFrame();
	static MainModel theModel= new MainModel();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					theView.setResizable(false);
					theView.setSize(450, 560);
					theView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					/*this line closes javaw.exe process in system processes
					 * Without this line when closing program javaw process stays in memory
					 */
					new Controller(theView,theModel).getView().setVisible(true);	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
