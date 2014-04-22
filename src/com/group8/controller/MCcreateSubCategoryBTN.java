package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.group8.model.Category;
import com.group8.model.MainModel;
import com.group8.model.SubCategory;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;

class MCcreateSubCategoryBTN implements ActionListener{
	//Inner Class that listens for the Create SubCategory Button
	private MainFrame theView;  	
	private MainModel theModel;	
	
	
	
	public MCcreateSubCategoryBTN(MainFrame theView, MainModel theModel){
		this.theView=theView;
		this.theModel=theModel;
		
		getMaintainPanel().addCreateSubCategoryBtn(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String categorySelection=null;
		String subCatTF=null;
		ArrayList<String> errorMessages;
		boolean nameExists = false; //used to see if the name already exists
		int slectedCombo=theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedIndex();
		subCatTF= theView.getTabsPane().getMaintainPanel().getEnterSubCatNameTF();
		errorMessages = new ArrayList<String>();
		if(slectedCombo==-1){
			errorMessages.add("CategorySelection");
		}
		else{
			categorySelection=theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
		}
		if(subCatTF.isEmpty()){
			errorMessages.add("SubCategory Name");
		}
		//now that we know they have selected a category entered a subCatname we need to see if it already exists
		else{
			ArrayList<String>listSubCategories= new ArrayList<>();
			listSubCategories=theModel.getSubCategoryNames();
			for(String copmapareSubCat: listSubCategories){

				if(copmapareSubCat.equalsIgnoreCase(subCatTF)){
					nameExists=true;
					break;
				}
			}
		}
		if(nameExists){
			errorMessages.add("Sub Category Name already Exists");
		}
		//else we can go ahead and make sub category
		if(errorMessages.isEmpty()){
			//first we get the category id based on the name that was selected
			int catID = theModel.getCategoryIdFromName(categorySelection);
			System.out.println("TESTING CATID: "+catID);
			//then we create the Category Object to pass to the model
			Category c = new Category(catID, categorySelection);
			//Now create the SubCategory Object we want to write to the database
			SubCategory s=new SubCategory(subCatTF);
			//send both object to the model to handle the database insert
			theModel.addNewSubCategory(c,s);
			//Testing Prints
			System.out.println("Test if works: CategoryName: "+ c.getCategoryName()+"\n SubCategoryName: "+s.getSubCatName());
			//Now that data processing is complete, clear the GUI form
			theView.getTabsPane().getMaintainPanel().clearNewSubCatForm();
			//working now on it
			//reset category combo-box
			update();
		}
		else{
			theView.getTabsPane().getMaintainPanel().warnCreateSubCatFormErrors(errorMessages);
		}
	}
	
	
	
	
	
	
	
	
	
	public void update() {

		//sets the model for all the category combo boxes in maintain panel
		theView.getTabsPane().getMaintainPanel().setCategoryModels(theModel.getCategoryNames());	
		getMaintainPanel().clearNewSubCatForm();
	}

	public MaintainPanel getMaintainPanel(){
		return theView.getTabsPane().getMaintainPanel();
	}
	
	
}