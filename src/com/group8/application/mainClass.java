package com.group8.application;
import java.awt.EventQueue;

import javax.swing.WindowConstants;

import com.group8.controller.Controller;
import com.group8.model.MainModel;
import com.group8.view.*;

public class mainClass {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame theView= new MainFrame();
					MainModel theModel= new MainModel();
					
					/*this line closes javaw.exe process in system processes
					 * Without this line when closing program javaw process stays in memory
					 */
					theView.setSize(450, 560);
					theView.setResizable(false);
					theView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					new Controller(theView,theModel);		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
