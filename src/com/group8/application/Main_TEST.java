package com.group8.application;



import com.group8.controller.Controller;
import com.group8.view.*;

public class Main_TEST {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
	
		//Testing_Data_Base_Class theModel= new Testing_Data_Base_Class();
				Testing_MaintainPanel theView= new Testing_MaintainPanel();
				theView.setSize(900, 600);
				theView.setVisible(true);
				
				
				
				
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
