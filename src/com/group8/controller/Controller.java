package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Key;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;









import javax.swing.JOptionPane;

import com.group8.model.*;
import com.group8.view.CategoryComboBoxModel;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.CheckoutTableModel;
import com.group8.view.ItemTableModel;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;
import com.group8.view.PopupSaleDialog;

public class Controller implements CategoryListener {

	private List <Item> temItemList;
	private ArrayList <Sale> saleList;
	private List<String> categories;
	private List<String> test;
	private MainFrame theView;  	
	private MainModel theModel;	
	private PopupSaleDialog pSale;
	private ArrayList<Item> saleItems = new ArrayList<Item>();
	private ArrayList<Integer> quantities = new ArrayList<Integer>();

	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;
		pSale = new PopupSaleDialog();


		//LOGIN LISTENER
		theView.addLoginListener(new LoginListener(theView, theModel));
		
		

		/******Maintain Categories Panel**************/

		//Adds Category To DataBase when btn clicked
		theView.setCategoryListener(this);
		//button confirms editing Category
		theView.getTabsPane().getMaintainPanel().addEditCategoryBtn(new ConfirmCategoryChangesListener());



		//Adding Listeners to Combo-boxes to trigger item selections
		//Create SubCat Panel
		theView.getTabsPane().getMaintainPanel().addSelectCategoryForSubCatComboBoxListener(new SelectCategoryForSubCatComboBoxListener());      ///WE CAN REMOVE IT IF NOT NEDDED
		//Edit SubCat Pane;
		theView.getTabsPane().getMaintainPanel().addfindCatForSubCatToEditComboBoxListener(new FindCatForSubCatToEditComboBoxListener());
		
		

		theView.getTabsPane().getMaintainPanel().addselectCategoryToEditcomboBoxListener(new SelectCategoryToEditcomboBoxListener());

		theView.getTabsPane().getMaintainPanel().addEditSubCategory(new EditSubCategoryMaintainP());
		
		

		//update all comboBoxes
		update();



		//ACTIVATE MAINTENACE PANEL BUTTON LISTENERS
		theView.getTabsPane().getMaintainPanel().addCreateSubCategoryBtn(new CreateSubCategoryBtn());
		theView.getTabsPane().getMaintainPanel().addCreateItemBtn(new CreateItemBtn());
		theView.getTabsPane().getMaintainPanel().addEditItemBtn(new ConfirmItemChangesBtn());
		theView.getTabsPane().getMaintainPanel().addRemoveItemBtn(new RemoveItemBtn());
		theView.getTabsPane().getMaintainPanel().addCreateAccountBtn(new CreateAccountBtn());
		theView.getTabsPane().getMaintainPanel().addSubmitSubCategoryBtn(new ConfirmSubCatChangesBtn());
		
		//ACTIVATE MAKE SALE PANEL LISTENERS
		
		theView.getTabsPane().getMakeSalePanel().addCheckoutButtonListener(new SaleListener());
		theView.getTabsPane().getMakeSalePanel().addClearCartButtonListener(new SaleListener());
		theView.getTabsPane().getMakeSalePanel().addAddToCartButtonListener(new SaleListener());
		theView.getTabsPane().getMakeSalePanel().addCategoryBoxListener(new SaleListener());
		theView.getTabsPane().getMakeSalePanel().addSubCategoryListener(new SaleListener());
		theView.getTabsPane().getMakeSalePanel().addKeyListenerToSerchTextBox(new SearchListener());
		theView.getTabsPane().getMakeSalePanel().setSelectCategoryCBModel(new CategoryComboBoxModel(), theModel.getCategoryNames());



		/********************************************/


		/******Maintain Items Panel**************/
		getMaintainPanel().addCategoryListnerCreateItem(new CategoryListnerCreateItemComBox());
		getMaintainPanel().addEditCategoryComboBoxItem(new CategoryListnerEditItemCB());

		getMaintainPanel().addEditSubCatComboBox(new SubCategoryListenerEditItemCB());


		
		getMaintainPanel().addSelectItemToEditComboBox(new EditItemListener());
		/********************************************/

		theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
		theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		theView.getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());

		
		populateCategoryReservPanel();



		/***********REPORT PANEL*****************
		 * */
	
	
		
		theView.getTabsPane().getReportPanel().addTableListener(new PopulateTable2Listener());
		 
		
	/************************************************************/
	/******ReservationPanel******/ 
	/************************************************************/
	
	
	 //Populate List in ReservePanel
	
    ArrayList<ReservedItem> List=new ArrayList <ReservedItem>();     		       
	List=theModel.getReservedItems(); //query database for Sales between the two dates	
	theView.getTabsPane().getReservationPanel().setListModel(List);
	
	
	
	}
	
	
	
	
	public void populateCategoryReservPanel(){
		try{
			categories=theModel.getCategoryNames();
			theView.getTabsPane().getReservationPanel().setComboBoxCategoryModel(categories);
		}catch(Exception e){
			System.out.println("data base is empty");
		}

	}
	

	

	class PopulateTableListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String subCat = null;
		if ( theView.getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem()!=null)
			{ 
			 subCat=	theView.getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem().toString();
			}
		else 
		{
			theView.getTabsPane().getReservationPanel().warnSubCategoryNull();
		}	
							
			if (subCat!=null)
			{
			temItemList=theModel.getItemsInSubcategory(subCat); 
			theView.getTabsPane().getReservationPanel().setTableModel(temItemList);	
			}
		}	
	}

	
  
	
	/*
	 * 
	 * *****************************************************
	 * Class for setting the Table in the Report Panel */
	
	 
	class PopulateTable2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {


			
			
		    ArrayList<Sale> saleList=new ArrayList <Sale>();		        		       
			java.sql.Date date1=  theView.getTabsPane().getReportPanel().getDate1();//get first date from the ReportPanel
			java.sql.Date date2=	theView.getTabsPane().getReportPanel().getDate2();	//get the second date			
			saleList=theModel.getSalesByDate(date1,date2); //query database for Sales between the two dates
			if (date1!=null && date2!=null)
			{
			theView.getTabsPane().getReportPanel().setTableModel(saleList);	//set the table if dates are not null
			if (date1.after(date2))
			{
				theView.getTabsPane().getReportPanel().warnDateAfter();//if first date after second warn user
			}
			}
			else 
			{  theView.getTabsPane().getReportPanel().warnDateNull(); //if any date null warn user
			}
			}
	}

	
			
			
		  
	
			
			
	
	/******************************************************************/

	class ComboBoxListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {

			String selectedCategory=theView.getTabsPane().getReservationPanel().getSelectCategoryCBox().getSelectedItem().toString();
			System.out.println("ComboBox Category changed to: "+selectedCategory);

			//now populate all sub-categories ....
			test=theModel.getSubCategories(selectedCategory);

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
	
	//Updates Category that has been edited by the user
	class ConfirmCategoryChangesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> errorMessages = new ArrayList<String>();

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
				//FIX PROBLEM left panel category+++++++++++++++++++++++
				
				
				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				
				theView.getTabsPane().getMaintainPanel().clearEditCategoryForm();
			}
			else{
				theView.getTabsPane().getMaintainPanel().warnCreateSubCatFormErrors(errorMessages);
			}




		}
	}


	/*
	 * *********** INNER CLASSES TO LISTEN TO BUTTONS ON THE MAINTAIN PANEL ********************
	 * ALL THESE CLASSES HANDLE THE EVENTS WHEN A BUTTON IS CLICKED ON A FORM FROM MAINTAIN PANEL
	 * ******************************************************************************************
	 */


	//Confirm changes butn edit sub-category
	class ConfirmSubCatChangesBtn implements ActionListener{

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
				//oldWarningHandeler --- theView.getTabsPane().getMaintainPanel().warnSubCategoryFieldEmpty();
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

				System.out.println("Old subCat name: "+subCatOldName+"\nNew subCat name: "+subCatNewName);
				theModel.updateSubCategory(subCatOldName, subCatNewName);
				//theView.getTabsPane().getMaintainPanel().setSubCategoryModels(theModel.getSubCategoryNames());

				//UPDATE 
				theView.getTabsPane().getMaintainPanel().setSubCategoryModels(theModel.getSubCategories(categorySelected));

				//set text filed to "" 
				theView.getTabsPane().getMaintainPanel().clearEditSubCatForm();
				update();

			}
			else{
				theView.getTabsPane().getMaintainPanel().warnCreateSubCatFormErrors(errorMessages);
			}


		}

	}



	//Inner Class that listens for the Create SubCategory Button
	class CreateSubCategoryBtn implements ActionListener{
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
				errorMessages.add("CategorySelction");
				//oldWarningHandeler --- theView.getTabsPane().getMaintainPanel().warnSubCategoryFieldEmpty();
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


	//Inner Class that listens for the Create Item Button
	class CreateItemBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String subCatSelection=null;
			String itemBrand=null;
			String itemModel=null;
			double itemPrice=0.0;
			int stockLevel=0;//optional parameter from the view

			//read the values (subCat, brand, model, price, stockLevel) from the view
			try{

				subCatSelection=getMaintainPanel().getNewItemSubCatComboBox().getSelectedItem().toString();
				System.out.println("testing: "+subCatSelection);



				itemBrand= getMaintainPanel().getEnterBrandTF();
				System.out.println("testing: "+itemBrand);
				itemModel= getMaintainPanel().getEnterModelTF();
				System.out.println("testing: "+itemModel);
				itemPrice= Double.parseDouble(getMaintainPanel().getEnterPriceTF());
				stockLevel = Integer.parseInt(getMaintainPanel().getEnterStockLevelTF());

				System.out.println("testing: "+subCatSelection+" "+itemBrand+" "+itemModel+" "+itemPrice+" "+stockLevel);
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
				
				//update sub-cat
				theView.getTabsPane().getMaintainPanel().clearEditItemForm();
				
				
				theView.getTabsPane().getMaintainPanel().setCategoryModels(theModel.getCategoryNames());	
				
		
				update();
	
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

	/*
>>>>>>> origin/Gab3
	 * *********** INNER CLASSES TO LISTEN TO COMBO BOXES ON THE MAINTAIN PANEL **********************
	 * ALL THESE CLASSES HANDLE THE EVENTS WHEN SELECTIONS ARE MADE ON COMBO BOXES FROM MAINTAIN PANEL
	 * ***********************************************************************************************
	 */
	
	
	class EditSubCategoryMaintainP implements ActionListener{

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




	//Inner Class Responsible for listening to the Select Category Combo Box on the Create SubCategory Panel
	class SelectCategoryForSubCatComboBoxListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox1=theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
			System.out.println("I'm comboBox Listener Create sub Category: "+comboBox1);

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
			System.out.println("I'm CatListner get me all sub-cats for: "+comboBox3);



			//get all subCategory for Category
			//set model maintain panel





			//System.out.println(test.size());

			theView.getTabsPane().getMaintainPanel().setSubCategoryModels(theModel.getSubCategories(comboBox3));


		}
	}

	/*this is method implemented from CategoryListener Interface 
	 *Category object is passed form view 
	 *??we can pass it to the model from here??
	 */


	/*
	 * ***************************************************************
	 * ***************Maintain item***********************************
	 */

	
	class EditItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			@SuppressWarnings("unused")
			String selectedItem=getMaintainPanel().getItemToEditComboBox().getSelectedItem().toString();
			
			
			
			
		}
		
	}
	
	
	class CategoryListnerCreateItemComBox implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String catSelected=theView.getTabsPane().getMaintainPanel().getNewItemCategoryComboBox().getSelectedItem().toString();
			System.out.println("Testing   "+catSelected);
			theView.getTabsPane().getMaintainPanel().setSubCategoryModelItems(theModel.getSubCategories(catSelected));

		}

	}
	class CategoryListnerEditItemCB implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String catSelected=theView.getTabsPane().getMaintainPanel().getEditCategoryComboBox().getSelectedItem().toString();

			theView.getTabsPane().getMaintainPanel().setSubCategoryModelItems2(theModel.getSubCategories(catSelected));

		}

	}
	class SubCategoryListenerEditItemCB implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String subCatSelected= getMaintainPanel().getItemToEditSubCatComboBox().getSelectedItem().toString();
			System.out.println("Testing subCat "+subCatSelected);

			List<Item> listItems= new ArrayList<>();

			listItems=theModel.getItemsInSubcategory(subCatSelected);


			List<String>listItemBrands= new ArrayList<>();
			for(int i=0;i<listItems.size();i++){




				listItemBrands.add(listItems.get(i).getModel());
			}

			getMaintainPanel().setItemModle(listItemBrands);
			
			
			//set item details on the text fields
			
			
			
		}
	}
	
	

	// *************************End maintain item panel************************
	
	//**************************Sale panel and popup buttons*******************
	

	class SaleListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {
					if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getBtnAddToCart())
					{
						int row =theView.getTabsPane().getMakeSalePanel().getTable().getSelectedRow();
						if (row!=-1)
						{
							String testQuantity="0";
							int validQuantity=0;
							
							boolean validNumber = false;
							
							/*
							 * Enter quantity required
							 */
							
							while(!validNumber){
								testQuantity = (String) JOptionPane.showInputDialog(pSale, "Quantity Required:", "Please Enter Quantity", 1, null, null, "1");

								//Check if entered number is positive an int
								
								if (testQuantity.matches("^[\\d+$]"))
								{
								validQuantity = Integer.parseInt(testQuantity);
								
								if(theModel.getItemByName(theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 1)+" "+theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 2)).getAvailableStockLevel()<validQuantity)
								{
									JOptionPane.showMessageDialog(pSale, "Quantity exceeded availible stock level.", "Correct required quantity!", 2);
								}
								else
								{
									Item added = new Item();
									added.setItemID((int) theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 0));
									added.setBrand((String) theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 1));
									added.setModel((String) theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 2));
									added.setStockLevel((int) theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 3));
									added.setAvailableStockLevel((int) theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 4));
									added.setPrice((double) theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 5));
									
									/*
									 * Check if item is in the basket already
									 */
									
									if(saleItems.contains(added))
									{
										quantities.add(saleItems.indexOf(added), validQuantity);
									}
									else
									{
										saleItems.add(added);
										quantities.add(saleItems.indexOf(added), validQuantity);
									}

									JOptionPane.showMessageDialog(pSale, "Item added to the basket!", "Success", 1);
								}
								validNumber=true;
								}
							else
							{
								JOptionPane.showMessageDialog(pSale, "Enter Valid Quantity", "Invalid entry!", 2);
							}
							
							}
							
							/*
							 * End entering quantity
							 */
							
							
							//check if quantity for sale does not exceeds availible quantity
						
							
						}
						else
						{
							JOptionPane.showMessageDialog(pSale, "Please make sure one item is selected!", "No Selection Made!", 2);
						}
					}
					else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getBtnCheckout())
					{
						/*
						 * Make popup visible if it's not, and refresh table with the items
						 */
						if(!pSale.isVisible())
						{
						pSale.setAlwaysOnTop(true);
						pSale.setVisible(true);
						}
						CheckoutTableModel ctm = new CheckoutTableModel();
						ctm.setTableModel(saleItems, quantities);
						pSale.setSaleTableModel(ctm);
						//Debugging code next
						System.out.println("Checkout pressed!!");
					}
					else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getBtnClearCart())
					{
						int option = JOptionPane.showConfirmDialog(pSale, "Are you sure you want to delete all items in the basket?", "Please Confirm", 0);
						if(option==0)
						{
						saleItems = new ArrayList<Item>();
						JOptionPane.showMessageDialog(pSale, "Basket has been successfully cleared!", "Success!", 1);
						}
					}
					else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getSelectCategoryCB())
					{
						System.out.println("Category box clicked!");
						theView.getTabsPane().getMakeSalePanel().
						setSelectSubCategoryCBModel(new CategoryComboBoxModel(), theModel.
						getSubCategories(theView.getTabsPane().getMakeSalePanel().getSelectCategoryCB().getSelectedItem().toString()));
						theView.getTabsPane().getMakeSalePanel().getSearchTF().setText("");
					}
					else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getSelectSubCategoryCB())
					{
						String subCatName = theView.getTabsPane().getMakeSalePanel().getSelectSubCategoryCB().getSelectedItem().toString();
						theView.getTabsPane().getMakeSalePanel().setTableModel(new ItemTableModel(), theModel.getItemsInSubcategory(subCatName));
						theView.getTabsPane().getMakeSalePanel().getSearchTF().setText("");
					}
		}



	}
	class SearchListener implements KeyListener	{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			String searchterm = theView.getTabsPane().getMakeSalePanel().getSearchTF().getText();
			

			theView.getTabsPane().getMakeSalePanel().setTableModel(new ItemTableModel(), theModel.getItemsByKeyword(searchterm));
			
		}
		
	}

	//**************************End Sale panel and popup buttons****************
	
	


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
		theView.getTabsPane().getMaintainPanel().clearNewCategoryForm();
	}

	//MaintainPanel: populates all ComboBoxes:SelectCategory
	public void update() {

		//sets the model for all the category combo boxes in maintain panel
		theView.getTabsPane().getMaintainPanel().setCategoryModels(theModel.getCategoryNames());	
		getMaintainPanel().clearNewSubCatForm();
	}

	public MaintainPanel getMaintainPanel(){
		return theView.getTabsPane().getMaintainPanel();
	}
}



