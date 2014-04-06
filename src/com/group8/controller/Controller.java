package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import com.group8.model.*;
import com.group8.view.AccountFormEvent;
import com.group8.view.AccountListner;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.MainFrame;

public class Controller implements CategoryListener, AccountListner {

	private List <Item> temItemList;
	private List<String> categories;
	private List<String> test;
	private MainFrame theView;  	
	private MainModel theModel;											

	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;

		//theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
		//theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		//categoryComboBox populate form DB
		//populateCategoryBoxes();


		/*
	public void populateCategoryBoxes(){
		categories=theModel.getMySomeCategories(); //i deleted this method from model, i can replace with proper one using query
		theView.getTabsPane().getReservationPanel().setComboBoxCategoryModel(categories);
	}
=======
		/******Maintain Categories Panel**************/

		//Adds Category To DataBase when btn clicked
		theView.setCategoryListener(this);


		//Adding Listeners: Combo-boxes
		theView.getTabsPane().getMaintainPanel().addselectCategorycomboBoxListener(new SelectCategorycomboBoxListener());
		theView.getTabsPane().getMaintainPanel().addselectCategoryToEditcomboBoxListener(new SelectCategoryToEditcomboBoxListener());
		theView.getTabsPane().getMaintainPanel().addfindCategoryComboBoxListener(new FindCategoryComboBoxListener());
		//button confirms editing Category
		theView.getTabsPane().getMaintainPanel().addbtnConfirmChanges_2Listener(new ConfirmChanges_2Listener());
		//update all comboBoxes 
		update();



		//BUTTONS
		theView.getTabsPane().getMaintainPanel().addCreateSubCategoryBtn(new CreateSubCategoryBtn());

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


	//Gets string form bomboBox
	class SelectCategorycomboBoxListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox1=theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
			System.out.println(comboBox1);
			/*
			 * We can create Sub-Category here 
			 */
		}
	}
	//BUTONS:
	//Inner Class that listens for the Create SubCategory Button
	class CreateSubCategoryBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String categorySelection=null;
			String subCatTF=null;
			try{
				categorySelection=theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
				subCatTF= theView.getTabsPane().getMaintainPanel().getEnterSubCatNameTF();
			}catch(Exception ex){
				theView.getTabsPane().getMaintainPanel().warnCategoryNull();
			}
			if(subCatTF.isEmpty()){
				theView.getTabsPane().getMaintainPanel().warnSubCategoryFieldEmpty();
			}
			//else we can go ahead and make sub category
			else{
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
			}
		}
	}
	//Inner Class that listens for the Create Item Button
	class CreateItemyBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String categorySelection=null;
			String subCatSelection=null;
			String itemBrand=null;
			String itemModel=null;
			double itemPrice=0;
			int stockLevel=0;//optional parameter from the view
			
			//read the values from the view
			try{
				subCatSelection=theView.getTabsPane().getMaintainPanel().getNewItemSubCatComboBox().getSelectedItem().toString();
				itemBrand= theView.getTabsPane().getMaintainPanel().getEnterBrandTF();
				itemModel= theView.getTabsPane().getMaintainPanel().getEnterModelTF();
				itemPrice= Double.parseDouble(theView.getTabsPane().getMaintainPanel().getEnterPriceTF());
				stockLevel = Integer.parseInt(theView.getTabsPane().getMaintainPanel().getEnterStockLevelTF());
			}catch(Exception ex){
				theView.getTabsPane().getMaintainPanel().warnCategoryNull();
			}
			//if(subCatTF.isEmpty()){
				//theView.getTabsPane().getMaintainPanel().warnSubCategoryFieldEmpty();}
			ArrayList<String> errorMessages = new ArrayList<String>();
			if(subCatSelection==null){
				errorMessages.add("SubCategory");
			}
			else if(itemBrand.isEmpty()){
				errorMessages.add("Brand");
			}
			else if(itemModel.isEmpty()){
				errorMessages.add("Model");
			}
			else if(!(itemPrice>0)){
				errorMessages.add("Price");
			}
			//else we can go ahead and make sub category
			else{
				errorMessages.clear();
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
			}
		}
	}



	//Gets string form bomboBox
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
			cat=theModel.getListOfCategories();
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
	class FindCategoryComboBoxListener implements ActionListener{
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
		listCategories=theModel.getListOfCategories();

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
			theView.getTabsPane().getMaintainPanel().clearCategoryTF();
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

		//sets the mode lfor all the category combo boxes in maintain panel
		theView.getTabsPane().getMaintainPanel().setCategoryModels(theModel.getListOfCategories());	
	}
	/**************************************END COMBO-BOXES**********************************/
	/***************************************************************************************/







	/**************************NOT WORKING NOW UNTIL MAINTAN CATEGORY FINISHED***************/
	/***************************ACCOUNT STUFF************************************************/
	/***************************************************************************************/
	/*
	 * Implementation of AccountListener passes account from view 
	 */
	public void accountAddedPerformed(AccountFormEvent accountFormEvent) {

		Account a = new Account();
		a.setAccountName(accountFormEvent.getUserName());
		a.setPassword(accountFormEvent.getPassword());
		a.setType(accountFormEvent.getType());
		System.out.println(a.getAccountName() + a.getPassword() + a.getType());

		//theModel.addNewAccount(a);
	}
	/****************************END********************************************************/

}
