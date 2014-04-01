package com.group8.application;



import com.group8.controller.Controller;
import com.group8.model.MainModel;
import com.group8.view.*;

public class Main_TEST {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
				//PUT MODEL IN THERE AS WELL AND PASS TO CONTROLLER CONSTRUCTOR
				MainFrame theView= new MainFrame();
				
				MainModel theModel= new MainModel();
				Controller theController= new Controller(theView,theModel);		
	}
}



/*pass object that listen for an event this is going to be Controller
				 *we have to pass here an object that implements interface with method that 
				 *will be used whenever category event occurs
				 *if a category event occurs in the view tell controller about it set controller as a listener to the view  
				 */
				//theView.setCategoryListener(theController);
				//theView.setAccountListener(theController);