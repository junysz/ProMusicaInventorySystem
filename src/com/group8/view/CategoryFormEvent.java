package com.group8.view;

/* Stores temporary data form the view this is later used in the controller to get data 	
 * This class is for category name which is typed by the user inside the MaintainPanel 
 * Name is passed to controller by the CategoryListener 
 * 
 */
public class CategoryFormEvent {

	private String name;
	
	public CategoryFormEvent(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
