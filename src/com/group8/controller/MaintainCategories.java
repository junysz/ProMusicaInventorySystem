package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.group8.model.Category;
import com.group8.model.MainModel;
import com.group8.model.SubCategory;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;

public class MaintainCategories {
	private MainFrame theView;  	
	private MainModel theModel;	

	public MaintainCategories(MainFrame theView, MainModel theModel){
		this.theView=theView;
		this.theModel=theModel;

		theView.getTabsPane().getMaintainPanel().addselectCategoryToEditcomboBoxListener(new MCeditCategoryCB());
		theView.getTabsPane().getMaintainPanel().addEditCategoryBtn(new MCeditCategoryBTN());
		getMaintainPanel().addCreateSubCategoryBtn(new MCcreateSubCategoryBTN());
		getMaintainPanel().addSelectCategoryForSubCatComboBoxListener(new MCcreateSubCategoryCB());
		getMaintainPanel().addSubmitSubCategoryBtn(new MCeditSubCategoryBTN());
		getMaintainPanel().addfindCatForSubCatToEditComboBoxListener(new MCeditSubCategoryCB1());
		getMaintainPanel().addEditSubCategory(new MCeditSubCategoryCB2());
	}



	class MCeditCategoryCB implements ActionListener{
		//Responsible for listening on the Select Category Combo-Box(EDIT CATEGORY) 
		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox2= theView.getTabsPane().getMaintainPanel().getSelectCategoryToEditcomboBox().getSelectedItem().toString();
			System.out.println(comboBox2);
			/*
			 * We can now get category name from database
			 * populate text area to edit name of subcategory 
			 */
			//GET CATEGORY FORM DB TO EDIT
			ArrayList<String> cat= new ArrayList<>();
			cat=theModel.getCategoryNames();
			String getToTextField=null;
			for(int i=0;i<cat.size();i++){
				getToTextField=cat.get(i);
				if(getToTextField.equalsIgnoreCase(comboBox2)){
					break;
				}
			}
			theView.getTabsPane().getMaintainPanel().setEditCategoryNameTF(getToTextField);
		}
	}

	class MCeditCategoryBTN implements ActionListener{
		//Updates Category that has been edited by the user
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
	}

	class MCcreateSubCategoryBTN implements ActionListener{
		//Inner Class that listens for the Create SubCategory Button
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
	}

	class MCcreateSubCategoryCB implements ActionListener{

		//Class Responsible for listening to the Select Category Combo Box on the Create SubCategory Panel
		@Override
		public void actionPerformed(ActionEvent e) {
			theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
		}
	}

	class MCeditSubCategoryBTN implements ActionListener{
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

	class MCeditSubCategoryCB1 implements ActionListener{
		//Gets string from bomboBox
		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox3= theView.getTabsPane().getMaintainPanel().getFindCategoryComboBox().getSelectedItem().toString();
			theView.getTabsPane().getMaintainPanel().setSubCategoryModels(theModel.getSubCategories(comboBox3));
		}
	}

	class MCeditSubCategoryCB2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			String subCategoryEdit= theView.getTabsPane().getMaintainPanel().getSelectSubCatToEditComboBox().getSelectedItem().toString();
			List<String> subCat= new ArrayList<>();
			subCat=theModel.getSubCategoryNames();
			String setSubCatTF=null;
			for(int i=0;i<subCat.size();i++){
				setSubCatTF=subCat.get(i);
				if(setSubCatTF.equalsIgnoreCase(subCategoryEdit)){
					break;
				}
			}
			theView.getTabsPane().getMaintainPanel().setSelectSubCatToEditComboBox(subCategoryEdit);
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
