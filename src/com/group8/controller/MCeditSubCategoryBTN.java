package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.group8.model.MainModel;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;

class MCeditSubCategoryBTN implements ActionListener{
	
	private MainFrame theView;  	
	private MainModel theModel;	
	
	public MCeditSubCategoryBTN(MainFrame theView, MainModel theModel) {
		this.theView=theView;
		this.theModel=theModel;
		
		theView.getTabsPane().getMaintainPanel().addSubmitSubCategoryBtn(this);
	}

	//Confirm changes butn edit sub-category
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> errorMessages = new ArrayList<String>();

		String categorySelected=null;
		String subCatNewName =null;
		String subCatOldName=null;
		int slectedComboCat=theView.getTabsPane().getMaintainPanel().getFindCategoryComboBox().getSelectedIndex();
		int slectedComboSubCat=theView.getTabsPane().getMaintainPanel().getSelectSubCatToEditComboBox().getSelectedIndex();

		if(slectedComboCat==-1){
			errorMessages.add("CategorySelction");
		}
		else{
			categorySelected=theView.getTabsPane().getMaintainPanel().getFindCategoryComboBox().getSelectedItem().toString();
		}
		if(slectedComboSubCat==-1){
			errorMessages.add("SubCategorySelction");
		}
		else{
			subCatOldName= theView.getTabsPane().getMaintainPanel().getSelectSubCatToEditComboBox().getSelectedItem().toString();
		}

		if(errorMessages.isEmpty())
		{
			subCatNewName=	theView.getTabsPane().getMaintainPanel().getEditSubCatNameTF().getText();
			theModel.updateSubCategory(subCatOldName, subCatNewName);
			theView.getTabsPane().getMaintainPanel().setSubCategoryModels(theModel.getSubCategories(categorySelected));
			theView.getTabsPane().getMaintainPanel().clearEditSubCatForm();
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