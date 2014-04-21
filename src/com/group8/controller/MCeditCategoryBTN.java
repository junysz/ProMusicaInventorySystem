package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.group8.model.MainModel;
import com.group8.view.MainFrame;

class MCeditCategoryBTN implements ActionListener{
	//Updates Category that has been edited by the user
	private MainFrame theView; 
	private MainModel theModel; 
	
	public MCeditCategoryBTN(MainFrame theView, MainModel theModel){
		this.theView=theView;
		this.theModel=theModel;
		
		theView.getTabsPane().getMaintainPanel().addEditCategoryBtn(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> errorMessages = new ArrayList<String>();  //think about interface for error message 

		String categoryEdited=null;
		String categoryOld=null;
		int slectedComboCat=theView.getTabsPane().getMaintainPanel().getSelectCategoryToEditcomboBox().getSelectedIndex();
		categoryEdited=theView.getTabsPane().getMaintainPanel().getEditCategoryNameTF().getText();

		if(slectedComboCat==-1){
			errorMessages.add("CategorySelction");
		}
		else{
			categoryOld=theView.getTabsPane().getMaintainPanel().getSelectCategoryToEditcomboBox().getSelectedItem().toString();
		}
		if(categoryEdited.isEmpty()){
			errorMessages.add("Enter new name for Category");
		}
		if(errorMessages.isEmpty()){
			System.out.println("Old Category Name: "+categoryOld+ "\nNew Category Name: "+categoryEdited);
			theModel.updateCategory(categoryOld,categoryEdited);
			update();
			theView.getTabsPane().getMaintainPanel().clearEditCategoryForm();
		}
		else{
			theView.getTabsPane().getMaintainPanel().warnCreateSubCatFormErrors(errorMessages);
		}
	}
	
	
	//IT MIGHT BE IMPROVED 
	public void update() {

		//sets the model for all the category combo boxes in maintain panel
		theView.getTabsPane().getMaintainPanel().setCategoryModels(theModel.getCategoryNames());	
		theView.getTabsPane().getMaintainPanel().clearNewSubCatForm();
	}

}
