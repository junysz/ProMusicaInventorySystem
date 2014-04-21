package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.group8.model.*;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;

public class Controller implements CategoryListener{

	private List <Item> temItemList;
	private List<String> test;
	private MainFrame theView;  	
	private MainModel theModel;	
    private  ArrayList<ReservedItem> List;


	protected MCeditCategoryCB mcEditCategoryCB;
	protected MCeditCategoryBTN mcEditCategoryBTN;
	protected MCcreateSubCategoryCB mcCreateSubCategoryCB;
	protected MCcreateSubCategoryBTN mcCreateSubCategoryBTN;
	protected MCeditSubCategoryCB1 mcEditSubCategoryCB1;
	protected MCeditSubCategoryCB2 mcEditSubCategoryCB2;
	protected MCeditSubCategoryBTN mcEditSubCategoryBTN;


	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;
		


		//LOGIN LISTENER
		theView.addLoginListener(new LoginListener(theView, theModel));

		/******MAINTAIN CATEGORIES PANEL**************/


		//Adds Category To DataBase when btn clicked
		theView.setCategoryListener(this);


		/*******************************************************************/
		/******Maintain Categories Controller Classes***********************/
		/*******************************************************************/
		mcEditCategoryCB= new MCeditCategoryCB(theView,theModel);
		mcEditCategoryBTN= new MCeditCategoryBTN(theView,theModel); //button confirms editing Category
		mcCreateSubCategoryCB = new MCcreateSubCategoryCB(theView,theModel);
		mcCreateSubCategoryBTN= new MCcreateSubCategoryBTN(theView,theModel);
		mcEditSubCategoryCB1= new MCeditSubCategoryCB1(theView,theModel);
		mcEditSubCategoryCB2 = new MCeditSubCategoryCB2(theView,theModel);
		mcEditSubCategoryBTN= new MCeditSubCategoryBTN(theView,theModel);
		/*******************************************************************/
		/*******************************************************************/

		//update all comboBoxes
		update();




		//ACTIVATE MAINTENANCE PANEL BUTTON LISTENERS
		theView.getTabsPane().getMaintainPanel().addCreateSubCategoryBtn(new MCcreateSubCategoryBTN(theView,theModel));
		//ACTIVATE MAINTENACE PANEL BUTTON LISTENERS

		theView.getTabsPane().getMaintainPanel().addCreateItemBtn(new CreateItemBtn());
		theView.getTabsPane().getMaintainPanel().addEditItemBtn(new ConfirmItemChangesBtn());
		theView.getTabsPane().getMaintainPanel().addRemoveItemBtn(new RemoveItemBtn());
		theView.getTabsPane().getMaintainPanel().addCreateAccountBtn(new CreateAccountBtn());

		theView.getTabsPane().getMaintainPanel().addSubmitSubCategoryBtn(new MCeditSubCategoryBTN(theView,theModel));




		/********************************************/

		/******Maintain Items Panel**************/
		getMaintainPanel().addCategoryListnerCreateItem(new CategoryListnerCreateItemComBox());
		getMaintainPanel().addEditCategoryComboBoxItem(new CategoryListnerEditItemCB());

		getMaintainPanel().addEditSubCatComboBox(new SubCategoryListenerEditItemCB());
		getMaintainPanel().addSelectItemToEditComboBox(new ListenSelectItemEditCB());
		/********************************************/


		theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
		theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		theView.getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());


		populateCategoryReservPanel();


/**************************************
		***********REPORT PANEL*****************
		 * *********************************************
		 * */

	
		
		theView.getTabsPane().getReportPanel().addTableListener(new PopulateTable2Listener());
		 
		
		
		
	/************************************************************/
	/******RESERVATION PANEl******/ 
	/************************************************************/
		
			
				
		theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
		theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		theView.getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());		
		populateCategoryReservPanel();	
	    theView.getTabsPane().getReservationPanel().addListListener(new myListListener());		
        populateList();   
	    theView.getTabsPane().getReservationPanel().addUpdateListener(new UpdateBtn());
	    theView.getTabsPane().getReservationPanel().addRemoveListener(new RemoveBtn());
	    theView.getTabsPane().getReservationPanel().addReserveListener(new ReserveBtn());
		theView.getTabsPane().getReportPanel().addTableListener(new PopulateTable2Listener());


		//Populate List in ReservePanel

		ArrayList<ReservedItem> List=new ArrayList <ReservedItem>();     		       
		List=theModel.getReservedItems(); //query database for Sales between the two dates	
		theView.getTabsPane().getReservationPanel().setListModel(List);
	}
	
  public void populateTable()
  {
		
		String subCat = null;
		ArrayList<Item> tempList = new ArrayList<Item>();
	
	if ( theView.getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem()!=null)
		{ 
		 subCat=	theView.getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem().toString();
		 temItemList=theModel.getItemsInSubcategory(subCat); 
		 for (int i=0;i<temItemList.size();i++)
			 if (temItemList.get(i).getAvailableStockLevel()>0)			 tempList.add(temItemList.get(i));
		 theView.getTabsPane().getReservationPanel().setTableModel(tempList);
		 theView.getTabsPane().getReservationPanel().getBtnReserveItem().setEnabled(true);	
		}
	else 
	{
		theView.getTabsPane().getReservationPanel().warnSubCategoryNull();
		
	}	
  }
	

	class PopulateTableListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		populateTable();
						
		}
	}

	
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


	//class for populating the Textfields in Make new reservation JPanel
	

	class myListListener implements ListSelectionListener{
		private String item;
		private double totalPrice,deposit;
		private String docketSelected;
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	    if (theView.getTabsPane().getReservationPanel().getList().getSelectedValue()!=null)	
		  docketSelected = theView.getTabsPane().getReservationPanel().getList().getSelectedValue().toString();
		 
		ArrayList<ReservedItem> myList=new ArrayList<ReservedItem>();
		myList=theModel.getReservedItems();
		int size=myList.size();
		for (int i=0;i<size;i++)
		if (myList.get(i).getDocketNo().equals(docketSelected) )			
		{
		int itemID=myList.get(i).getItemID();
		
		
		item=theModel.getItemByID(itemID).getBrand()+" "+theModel.getItemByID(itemID).getModel();
		totalPrice=theModel.getItemByID(itemID).getPrice();
		deposit=myList.get(i).getDeposit();
		
		}
		
		 theView.getTabsPane().getReservationPanel().getDocketNoTF().setText(docketSelected);
		 theView.getTabsPane().getReservationPanel().getBrandModelTF().setText(item);
		 theView.getTabsPane().getReservationPanel().getCurrentDepositTF().setText(deposit+"");
		 theView.getTabsPane().getReservationPanel().getTotalPriceTF().setText(totalPrice+"");
		 
		 theView.getTabsPane().getReservationPanel().getEndReservation().setEnabled(true);
		 theView.getTabsPane().getReservationPanel().getNewButton().setEnabled(true);
	}	
	}
			
	//Class for listening the button to Update a Reservation
	 
	class UpdateBtn implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			double newDeposit;
			ReservedItem reserved;
			
			reserved=new ReservedItem();
			
		
			
			String docket=theView.getTabsPane().getReservationPanel().getDocketNoTF().getText();			
			String newDepositString=theView.getTabsPane().getReservationPanel().getupdateDepositTF().getText();
			try
			{
			newDeposit = Double.parseDouble(newDepositString);		    					
			reserved.setDeposit(newDeposit);
			double price=Double.parseDouble(theView.getTabsPane().getReservationPanel().getTotalPriceTF().getText());
			
			if (newDeposit>price-1)  theView.getTabsPane().getReservationPanel().warnUpdate();
			else 	
				{theModel.updateReservedItem(docket,newDeposit);
			theView.getTabsPane().getReservationPanel().getCurrentDepositTF().setText(newDeposit+"");		
			}
			}
			
		
		catch (Exception io) {
			theView.getTabsPane().getReservationPanel().warnUpdateNull();	
		}
		}
		
		
	}
		//Class to remove a Reservation  
	class RemoveBtn implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {				
						
			String docket=theView.getTabsPane().getReservationPanel().getDocketNoTF().getText();
			
		     theModel.removeReservedItem(docket);
			 theView.getTabsPane().getReservationPanel().getDocketNoTF().setText("");
			 theView.getTabsPane().getReservationPanel().getBrandModelTF().setText("");
			 theView.getTabsPane().getReservationPanel().getCurrentDepositTF().setText("");
			 theView.getTabsPane().getReservationPanel().getTotalPriceTF().setText("");		
			 populateList();
		}
		
	}
	
	//the scope of this class is to create a new ReservationItem in the database.
	// For this I need five parameters ,to use the method InsertNewReservation
	class ReserveBtn implements ActionListener
		{
        
		@Override
		public void actionPerformed(ActionEvent e) {
		String docket="",depositString="";
		double deposit=0;
		int accountID=0,itemID=0;	
		 
		
		//get docket
		docket=theView.getTabsPane().getReservationPanel().getEnterDocketNoTF().getText();
		if (docket.equals(""))
		
		theView.getTabsPane().getReservationPanel().warnDocketNull();
		//get deposit			
		try
		{ 
			 depositString=theView.getTabsPane().getReservationPanel().getDepositTF().getText();
			deposit = Double.parseDouble(depositString);								
		}
		catch (Exception io) //warning if the deposit field is null or not a number
		{
			theView.getTabsPane().getReservationPanel().warnUpdateNull();
		}
		
		
	//	getting the account name and password using the LoginPanel 
		String username=theView.getUsernameLoginString();
		String pass=theView.getPasswordLoginString();
		
		//getting the AccountID
		ArrayList<Account> myList=theModel.getAllAccounts();
		for (int i=0;i<myList.size();i++)
		{
		if (myList.get(i).getAccountName().equals(username))
		if(		 myList.get(i).getPassword().equals(pass) )  
				accountID=myList.get(i).getAccountID();
		}
		
		//Getting current date
		 Calendar calendar = Calendar.getInstance();
		 java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
		 
		 
		 //getting the itemID
		 
	    int index=theView.getTabsPane().getReservationPanel().getTableIndex();
	    if  (index==-1) 
	    	     theView.getTabsPane().getReservationPanel().warnItemNull();
	    else	    	
	    {
	    	itemID=theView.getTabsPane().getReservationPanel().getItemID();    
	    } 
	    
	    if (index>-1 && !(depositString.equals("")) && !(docket.equals(""))  )
	    {
	    theModel.insertNewReservation(accountID, docket, date, deposit, itemID);	
	    theView.getTabsPane().getReservationPanel().success();
	    theView.getTabsPane().getReservationPanel().getDepositTF().setText("");
	    theView.getTabsPane().getReservationPanel().getEnterDocketNoTF().setText("");
	    
	   
	    //update Available Stock Level for the designated item
	    int itemID1=theView.getTabsPane().getReservationPanel().getItemID();
	    int Stock=theView.getTabsPane().getReservationPanel().getAvailableStock();	    
	   	theModel.updateItemAvailableStock(itemID1,Stock-1);
	   	//repopulate Table
	    populateTable();
	    //repopulate the List in the FindReservation Panel
	  
	    populateList();
	    }
		}	
		}                                                               
	/******************************************************************/
	
		public void  populateList()
		{
			  theView.getTabsPane().getReservationPanel().removeList();
		 ArrayList<ReservedItem> List=new ArrayList <ReservedItem>();    
		 ArrayList<ReservedItem> EmptyList=new ArrayList <ReservedItem>();   
	     List=theModel.getReservedItems(); 
	   	 theView.getTabsPane().getReservationPanel().setListModel(List);
		}
		
	

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
























	/*this is method implemented from CategoryListener Interface 
	 *Category object is passed form view 
	 *??we can pass it to the model from here??
	 */


	/*
	 * ***************************************************************
	 * ***************Maintain item***********************************
	 */

	// * ***************Select Item CB***********************************
	class ListenSelectItemEditCB implements ActionListener
	{//class is responsible for finding item selected by user for editing and displaying items's details in text fields

		@Override
		public void actionPerformed(ActionEvent e) {

			String selectedItem=getMaintainPanel().getItemToEditComboBox().getSelectedItem().toString();

			String selectedSubCategory= getMaintainPanel().getItemToEditSubCatComboBox().getSelectedItem().toString();

			System.out.println("testgin"+selectedItem);

			//extract model and brand from combo-box select item
			String getItem[]=selectedItem.split("\\s");
			String brand=getItem[0];
			String model=getItem[1];

			//create list with all items for selected sub-category
			List<Item>itemsInSubCat= new ArrayList<Item>();
			//use query itemsInSubCategory
			itemsInSubCat=theModel.getItemsInSubcategory(selectedSubCategory);

			for(int i=0;i<itemsInSubCat.size();i++){

				//iterate throughout the list and compare brand and model
				//get correct item and get price and all details	
				if((brand.equalsIgnoreCase(itemsInSubCat.get(i).getBrand())) && (model.equalsIgnoreCase(itemsInSubCat.get(i).getModel())))	
				{
					//populate comboBoxes
					getMaintainPanel().setEditBrandTF(itemsInSubCat.get(i).getBrand());
					getMaintainPanel().setEditModelTF(itemsInSubCat.get(i).getModel());
					getMaintainPanel().setEditPriceTF(String.valueOf(itemsInSubCat.get(i).getPrice()));
				}
			}	
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
	//
	class SubCategoryListenerEditItemCB implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String subCatSelected= getMaintainPanel().getItemToEditSubCatComboBox().getSelectedItem().toString();
			System.out.println("Testing subCat "+subCatSelected);

			List<Item> listItems= new ArrayList<>();

			listItems=theModel.getItemsInSubcategory(subCatSelected);


			List<String>listItemBrands= new ArrayList<>();
			String brandModel=null;
			for(int i=0;i<listItems.size();i++){


				brandModel=listItems.get(i).getBrand()+" "+listItems.get(i).getModel();


				listItemBrands.add(brandModel);
			}

			getMaintainPanel().setItemModle(listItemBrands);


			//set item details on the text fields



		}
	}



	// *************************End maintain item panel************************


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


	//when program starts categories are loaded into the combo-boxes
	public void populateCategoryReservPanel(){
		try{
			List<String> categories;
			categories=theModel.getCategoryNames();
			theView.getTabsPane().getReservationPanel().setComboBoxCategoryModel(categories);
		}catch(Exception e){
			System.out.println("data base is empty");
		}

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









































	//THOSE CLASSESS HAS BEEN MOVED OUT OF THE MAIN CONTROLER


	/**********************MAINTAIN CATEGORIES Classes moved away START********************************/

	/*class MCeditCategoryCB implements ActionListener{
		//Responsible for listening on the Select Category Combo-Box(EDIT CATEGORY) 
		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox2= theView.getTabsPane().getMaintainPanel().getSelectCategoryToEditcomboBox().getSelectedItem().toString();
			System.out.println(comboBox2);

	 * We can now get category name from database
	 * populate text area to edit name of subcategory 

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
	}*/

	/*class MCeditCategoryBTN implements ActionListener{
		//Updates Category that has been edited by the user
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
				theView.getTabsPane().getMaintainPanel().clearEditCategoryForm();
			}
			else{
				theView.getTabsPane().getMaintainPanel().warnCreateSubCatFormErrors(errorMessages);
			}
		}
	}*/

	/*class MCcreateSubCategoryCB implements ActionListener{
		//Class Responsible for listening to the Select Category Combo Box on the Create SubCategory Panel
		@Override
		public void actionPerformed(ActionEvent e) {
			theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
		}
	}*/

	/*class MCcreateSubCategoryBTN implements ActionListener{
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
				errorMessages.add("CategorySelction");
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
	}*/

	/*class MCeditSubCategoryCB1 implements ActionListener{
		//Gets string from bomboBox
		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox3= theView.getTabsPane().getMaintainPanel().getFindCategoryComboBox().getSelectedItem().toString();
			theView.getTabsPane().getMaintainPanel().setSubCategoryModels(theModel.getSubCategories(comboBox3));
		}
	}
	 */
	/*class MCeditSubCategoryCB2 implements ActionListener{

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
	}*/

	/*class MCeditSubCategoryBTN implements ActionListener{
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
	}*/


		}	
/***********************MAINTAIN CATEGORIES END*******************************/




