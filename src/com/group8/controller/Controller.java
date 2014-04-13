package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import com.group8.model.*;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.MainFrame;

public class Controller implements CategoryListener {

	private List <Item> temItemList;
	private List<String> categories;
	private List<String> test;
	private MainFrame theView;  	
	private MainModel theModel;											

	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;


		/******Maintain Categories Panel**************/

		//Adds Category To DataBase when btn clicked
		theView.setCategoryListener(this);
		//button confirms editing Category
		theView.getTabsPane().getMaintainPanel().addbtnConfirmChanges_2Listener(new ConfirmChanges_2Listener());



		//Adding Listeners to Combo-boxes to trigger item selections
		//Create SubCat Panel
		theView.getTabsPane().getMaintainPanel().addSelectCategoryForSubCatComboBoxListener(new SelectCategoryForSubCatComboBoxListener());
		//Edit SubCat Pane;
		theView.getTabsPane().getMaintainPanel().addfindCatForSubCatToEditComboBoxListener(new FindCatForSubCatToEditComboBoxListener());

		theView.getTabsPane().getMaintainPanel().addselectCategoryToEditcomboBoxListener(new SelectCategoryToEditcomboBoxListener());


		//update all comboBoxes
		update();



		//ACTIVATE MAINTENACE PANEL BUTTON LISTENERS
		theView.getTabsPane().getMaintainPanel().addCreateSubCategoryBtn(new CreateSubCategoryBtn());
		theView.getTabsPane().getMaintainPanel().addCreateItemBtn(new CreateItemBtn());
		theView.getTabsPane().getMaintainPanel().addEditItemBtn(new ConfirmItemChangesBtn());
		theView.getTabsPane().getMaintainPanel().addRemoveItemBtn(new RemoveItemBtn());
		theView.getTabsPane().getMaintainPanel().addCreateAccountBtn(new CreateAccountBtn());
		theView.addLoginListener(new LoginListener(theView, theModel));

		/********************************************/


		theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
		theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		theView.getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());

		//categoryComboBox populate form DB NOT WORKING NOW UNTIL MAINTAN CATEGORY FINISHED 
		populateCategoryReservPanel();



		/***********REPORT PANEL*****************
		 * theView.getTabsPane().getReportPanel().
		 */








	}
	/************************************************************/
	/******NOT WORKING NOW UNTIL MAINTAN CATEGORY FINISHED******/ 
	/************************************************************/
	public void populateCategoryReservPanel(){
		try{
			categories=theModel.getMySomeCategories(); //i deleted this method from model, i can replace with proper one using query
			theView.getTabsPane().getReservationPanel().setComboBoxCategoryModel(categories);
		}catch(Exception e){
			System.out.println("data base is empty");
		}

	}

	class PopulateTableListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			temItemList=theModel.getMeSomeItems(); //i deleted this method from model, i can replace with proper one using query
			theView.getTabsPane().getReservationPanel().setTableModel(temItemList);	
		}	
	}
	class ComboBoxListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {

			String selectedCategory=theView.getTabsPane().getReservationPanel().getSelectCategoryCBox().getSelectedItem().toString();
			System.out.println("ComboBox Category changed to: "+selectedCategory);

			//now populate all sub-categories ....
			test=theModel.getMeSomeSubCategories();

			theView.getTabsPane().getReservationPanel().setComboBoxSubCategoryModel(test);
		}



	}
	class ComboBoxSubCatListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				String subCat=	theView.getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem().toString();
				System.out.println("ComboBox Sub-category changed to: "+ subCat);


			}catch(Exception exception)
			{
				exception.printStackTrace();
				System.out.println("No data in comboBox");
			}



			//get Items for sub-category and display in the table

		}

	}
	/************************************************************/
	/***********************END*********************************/






	/***************************************************************************************/
	/*****************?????????????START COMBO-BOXES MAINTAIN_PANEL??????????*****************************/
	/***************************************************************************************/

	//Updates Category that has been edited by the user
	class ConfirmChanges_2Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String categoryEdited=theView.getTabsPane().getMaintainPanel().getEditCategoryNameTF().getText();
			String categoryOld=	theView.getTabsPane().getMaintainPanel().getSelectCategoryToEditcomboBox().getSelectedItem().toString();
			System.out.println("Old Category Name: "+categoryOld+ "\nNew Category Name: "+categoryEdited);

			theModel.updateCategory(categoryOld,categoryEdited);
			update();
		}
	}


	/*
	 * *********** INNER CLASSES TO LISTEN TO BUTTONS ON THE MAINTAIN PANEL ********************
	 * ALL THESE CLASSES HANDLE THE EVENTS WHEN A BUTTON IS CLICKEDON A FORM FROM MAINTAIN PANEL
	 * ******************************************************************************************
	 */

	//Inner Class that listens for the Create SubCategory Button
	class CreateSubCategoryBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String categorySelection=null;
			String subCatTF=null;
			boolean nameExists = false; //used to see if the name already exists
			try{
				categorySelection=theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
				subCatTF= theView.getTabsPane().getMaintainPanel().getEnterSubCatNameTF();
			}catch(Exception ex){
				System.out.println("Problem reading data from Create SubCategory Form");
				//oldWarningHandeler --- theView.getTabsPane().getMaintainPanel().warnCategoryNull();
			}

			ArrayList<String> errorMessages = new ArrayList<String>();
			if(categorySelection==null){
				errorMessages.add("CategorySelction");
				//oldWarningHandeler --- theView.getTabsPane().getMaintainPanel().warnSubCategoryFieldEmpty();
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
			}
			else{
				theView.getTabsPane().getMaintainPanel().warnCreateSubCatFormErrors(errorMessages);
			}
		}
	}
	//Inner Class that listens for the Create Item Button
	class CreateItemBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String subCatSelection=null;
			String itemBrand=null;
			String itemModel=null;
			double itemPrice=0;
			int stockLevel=0;//optional parameter from the view

			//read the values (subCat, brand, model, price, stockLevel) from the view
			try{
				subCatSelection=theView.getTabsPane().getMaintainPanel().getNewItemSubCatComboBox().getSelectedItem().toString();
				itemBrand= theView.getTabsPane().getMaintainPanel().getEnterBrandTF();
				itemModel= theView.getTabsPane().getMaintainPanel().getEnterModelTF();
				itemPrice= Double.parseDouble(theView.getTabsPane().getMaintainPanel().getEnterPriceTF());
				stockLevel = Integer.parseInt(theView.getTabsPane().getMaintainPanel().getEnterStockLevelTF());
			}catch(Exception ex){
				System.out.println("Problem reading input fron Create Item Form");
			}
			//Now validate the data and add errors to errorMessages
			ArrayList<String> errorMessages = new ArrayList<String>();
			if(subCatSelection==null){
				errorMessages.add("SubCategory Selection");
			}
			if(itemBrand.isEmpty()){
				errorMessages.add("Brand Name");
			}
			if(itemModel.isEmpty()){
				errorMessages.add("Model Name");
			}
			if(!(itemPrice>0)){
				errorMessages.add("Price");
			}
			//If there is no errors then we can go ahead and make sub category
			if(errorMessages.isEmpty()){
				//first we get the SubCategory id based on the name that was selected
				int subCatID = theModel.getSubCatIdFromName(subCatSelection);
				//then we create the SubCategory Object to pass to the model
				SubCategory s=new SubCategory(subCatID, subCatSelection);
				Item i = new Item();
				i.setBrand(itemBrand);
				i.setModel(itemModel);
				i.setPrice(itemPrice);
				i.setStockLevel(stockLevel);
				i.setAvailableStockLevel(stockLevel);
				//send both object to the model to handle the database insert
				theModel.addNewItem(i,s);
				//Testing Prints
				System.out.println("Test if works: ItemName: "+ i.getBrand()+" " +i.getModel() +"\n Price: "+i.getPrice());
				//Now that data processing is complete, clear the GUI form
				theView.getTabsPane().getMaintainPanel().clearNewItemForm();

			}
			//Now handle the error Messages if there was any by sending the errors list to the view to be displayed
			else{
				theView.getTabsPane().getMaintainPanel().warnCreateItemFormErrors(errorMessages);
			}
		}
	}
	//Inner Class that listens for the Confirm Item Changes Button
	class ConfirmItemChangesBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String itemSubCat=null;
			String itemSelection=null;
			String changeBrand=null;
			String changeModel=null;
			double changePrice=0;
			String changeSubCatSelection=null;

			//read the values (item, editBrand, editModel, editPrice, changeOfSubCategory) from the view
			try{
				itemSubCat=theView.getTabsPane().getMaintainPanel().getItemToEditSubCatComboBox().getSelectedItem().toString();
				itemSelection=theView.getTabsPane().getMaintainPanel().getItemToEditComboBox().getSelectedItem().toString();
				changeBrand= theView.getTabsPane().getMaintainPanel().getEditBrandTF();
				changeModel= theView.getTabsPane().getMaintainPanel().getEditModelTF();
				changePrice= Double.parseDouble(theView.getTabsPane().getMaintainPanel().getEditPriceTF());
				changeSubCatSelection=theView.getTabsPane().getMaintainPanel().getItemMoveSubCatComboBox().getSelectedItem().toString();
			}catch(Exception ex){
				System.out.println("Problem reading input fron Edit Existing Item Form");
			}
			//Now validate the data and add errors to errorMessages
			ArrayList<String> errorMessages = new ArrayList<String>();
			if(itemSelection==null){
				errorMessages.add("Item Selection");
			}
			if(changeBrand.isEmpty()){
				errorMessages.add("Brand Name");
			}
			if(changeModel.isEmpty()){
				errorMessages.add("Model Name");
			}
			if(!(changePrice>0)){
				errorMessages.add("Price");
			}
			if(changeSubCatSelection==null){
				errorMessages.add("Move Sub Category");
			}
			//If there is no errors then we can go ahead and edit the item accordingly
			if(errorMessages.isEmpty()){
				//First we get the Item based on the string that was selected (contains "Brand Model")
				Item item = theModel.getItemByName(itemSelection);

				//Now we check if there was any change in the Brand, Model, and Price entered by the user
				if(!(item.getBrand().equals(changeBrand))){
					item.setBrand(changeBrand);
				}
				if(!(item.getModel().equals(changeModel))){
					item.setModel(changeModel);
				}
				if(item.getPrice() != changePrice){
					item.setPrice(changePrice);
				}
				//Now i need the items existing subCatID and the changeSubCatID selected by user
				int itemSubCatID = theModel.getSubCatIdFromName(itemSubCat);
				int changeSubCatID = theModel.getSubCatIdFromName(changeSubCatSelection);
				//check if the user selected a change for SubCat and then give the changed ID to itemSubCatID
				if(itemSubCatID != changeSubCatID){
					itemSubCatID = changeSubCatID;
				}
				theModel.updateItem(item, itemSubCatID);
				//Testing Prints
				System.out.println();
				//Now that data processing is complete, clear the GUI form
				theView.getTabsPane().getMaintainPanel().clearEditItemForm();
			}
			//Now handle the error Messages if there was any by sending the errors list to the view to be displayed
			else{
				theView.getTabsPane().getMaintainPanel().warnEditItemFormErrors(errorMessages);
			}
		}
	}
	//Inner Class that listens for the Create Account Button
	class RemoveItemBtn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String itemSelection=null;

			//read the values (itemSelection) from the view
			try{
				itemSelection=theView.getTabsPane().getMaintainPanel().getItemToEditComboBox().getSelectedItem().toString();
			}catch(Exception ex){
				System.out.println("Problem reading input from Form");
			}
			//Now validate the data and add errors to errorMessages
			ArrayList<String> errorMessages = new ArrayList<String>();
			if(itemSelection==null){
				errorMessages.add("Select Item");
			}

			if(errorMessages.isEmpty()){
				Item item = theModel.getItemByName(itemSelection);
				theModel.removeItem(item);
				System.out.println("Remove Item successful");
				//Now that data processing is complete, clear the GUI form
				theView.getTabsPane().getMaintainPanel().clearEditItemForm();
			}
			else{
				theView.getTabsPane().getMaintainPanel().warnEditItemFormErrors(errorMessages);
			}


		}
	}
	//Inner Class that listens for the Create Account Button
	class CreateAccountBtn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username=null;
			String password1=null;
			String password2=null;
			String accountTypeSelection=null;

			//read the values (username, enterPassword, confirmPassword, accountType) from the view
			try{
				username=theView.getTabsPane().getMaintainPanel().getEnterUsernameTF();
				password1=theView.getTabsPane().getMaintainPanel().getEnterPasswordTF();
				password2= theView.getTabsPane().getMaintainPanel().getConfirmPasswordTF();
				accountTypeSelection=theView.getTabsPane().getMaintainPanel().getSelectAccountTypeComboBox().getSelectedItem().toString();
			}catch(Exception ex){
				System.out.println("Problem reading input fron Create New Account Form");
			}
			//Now validate the data and add errors to errorMessages
			ArrayList<String> errorMessages = new ArrayList<String>();
			if(username.isEmpty()){
				errorMessages.add("Username");
			}
			if(password1.isEmpty() || password2.isEmpty()){
				errorMessages.add("Enter Password Twice");
			}
			if(!(password1.equals(password2))){
				errorMessages.add("Passwords Do Not Match");
			}
			if(accountTypeSelection==null){
				errorMessages.add("Account Type");
			}

			if(errorMessages.isEmpty()){
				Account a = new Account();
				a.setAccountName(username);
				a.setPassword(password1);
				a.setType(accountTypeSelection);

				theModel.addNewAccount(a);
				System.out.println("Account Added successful");
				//Now that data processing is complete, clear the GUI form
				theView.getTabsPane().getMaintainPanel().clearNewAccountForm();
			}
			else{
				theView.getTabsPane().getMaintainPanel().warnCreateAccountFormErrors(errorMessages);
			}



		}
	}

	//Inner Class Responsible for listening to the Select Category Combo Box on the Create SubCategory Panel
	class SelectCategoryForSubCatComboBoxListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox1=theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
			System.out.println(comboBox1);

		}
	}

	//Inner Class Responsible for listening to the Select Category Combo Box on the Edit Category Panel
	class SelectCategoryToEditcomboBoxListener implements ActionListener{

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
	//Gets string form bomboBox
	class FindCatForSubCatToEditComboBoxListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox3= theView.getTabsPane().getMaintainPanel().getFindCategoryComboBox().getSelectedItem().toString();
			System.out.println(comboBox3);

		}
	}

	/*this is method implemented from CategoryListener Interface 
	 *Category object is passed form view 
	 *??we can pass it to the model from here??
	 */
	public void categoryAddedPerformed(CategoryFormEvent catFormEvent) 
	{	
		boolean flagExist=false;
		String newCategoryName=catFormEvent.getName();
		ArrayList<String>listCategories= new ArrayList<>();
		listCategories=theModel.getCategoryNames();

		for(int i=0;i<listCategories.size();i++){

			String copmapareCat=listCategories.get(i);
			if(copmapareCat.equalsIgnoreCase(newCategoryName)){
				flagExist=true;
				break;
			}

		}



		if(newCategoryName.isEmpty()){
			theView.getTabsPane().getMaintainPanel().warnCategoryFieldEmpty();

		}
		else if(flagExist){
			theView.getTabsPane().getMaintainPanel().warnCategoryExist();
			theView.getTabsPane().getMaintainPanel().clearNewCategoryForm();
		}
		else{
			System.out.println("I am adding new category to dataBase: "+catFormEvent.getName());
			Category c = new Category(catFormEvent.getName());
			theModel.addNewCategory(c);
			//update dataBase and other comboBoxes

			//update MainTainPanel
			update();
		}
	}

	//MaintainPanel: populates all ComboBoxes:SelectCategory
	public void update() {

		//sets the model for all the category combo boxes in maintain panel
		theView.getTabsPane().getMaintainPanel().setCategoryModels(theModel.getCategoryNames());	
	}

}

