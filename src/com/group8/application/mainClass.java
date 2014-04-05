package com.group8.application;
import java.awt.EventQueue;

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
					Controller theController= new Controller(theView,theModel);		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
//Controller theController= new Controller(theView);
/*pass object that listen for an event this is going to be Controller
				 *we have to pass here an object that implements interface with method that 
				 *will be used whenever category event occurs
				 *if a category event occurs in the view tell controller about it set controller as a listener to the view  
				 */
				//theView.setCategoryListener(theController);
				//theView.setAccountListener(theController);
