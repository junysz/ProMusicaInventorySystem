package com.group8.application;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
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
					theView.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
					/*this line closes javaw.exe process in system processes
					 * Without this line when closing program javaw process stays in memory
					 */
					theView.addWindowListener(new WindowAdapter() {

						  @Override
						  public void windowClosing(WindowEvent we)
						  { 
						    String ObjButtons[] = {"Yes","No"};
						    int PromptResult = JOptionPane.showOptionDialog(null, 
						        "Are you sure you want to exit?", "Pro Musica Inventory System", 
						        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
						        ObjButtons,ObjButtons[1]);
						    if(PromptResult==0)
						    {
						      System.exit(0);          
						    }
						  }
						});
					new Controller(theView,theModel).getView().setVisible(true);	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
