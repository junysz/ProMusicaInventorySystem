package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.group8.model.*;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;

public class Controller implements CategoryListener{
	private MainFrame theView;  	
	private MainModel theModel;	
	protected MaintainCategories maintainCategories;
	protected MaintainItems maintainItems;
	protected MaintainAccounts maintainAccounts;
	
	private List<String> test;
	
	

	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;
	
		theView.addLoginListener(new LoginListener(theView, theModel));
		theView.setCategoryListener(this);

		maintainCategories= new MaintainCategories(theView, theModel);
		maintainItems= new MaintainItems(theView, theModel);
		maintainAccounts= new MaintainAccounts(theView, theModel);
	
	
		
		update();//comboBoxes
		updteAccounts();
		
		theView.getTabsPane().getMaintainPanel().getEditAccountTypeComboBox().setSelectedIndex(-1);
		

<<<<<<< HEAD
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
=======
		/***********RESERVE PANEL*****************/ 
		reserveController= new ReserveController(theView, theModel);
		theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		theView.getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());
		
		
		/***********REPORT PANEL*****************/ 		 
		reportController= new ReportController(theView, theModel);
>>>>>>> FETCH_HEAD

	}

	//method to Populate the table in the Reservation Panel
	public void populateTable()
	{
		String keyword="";
		int size=0;
		keyword= theView.getTabsPane().getReservationPanel().getSearchKeywordTF().getText();//get value of the keyword
		ArrayList<Item> tempList = new ArrayList<Item>();
		ArrayList<Item>newList =   new ArrayList<Item>();

		if (!(keyword.equals("")))   //if the selection is made by the keyword
		{
			newList=theModel.getItemsByKeyword(keyword); //get all the items with keyword
			size=newList.size();
		}
		else //if selection is made using Subcategories and categories
		{
			String subCat = null;
			if ( theView.getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem()!=null)//if subcategory is selected
			{ 
				subCat=	theView.getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem().toString();
				newList=theModel.getItemsInSubcategory(subCat); //get all items in subcategory
				size=newList.size();
			}

			else 
			{
				theView.getTabsPane().getReservationPanel().warnSubCategoryNull();
			}	

		}


		for (int i=0;i<size;i++)
			if (newList.get(i).getAvailableStockLevel()>0)			
				tempList.add(newList.get(i));//check  if AvailableStock>0
		theView.getTabsPane().getReservationPanel().setTableModel(tempList);//populate table
		theView.getTabsPane().getReservationPanel().getBtnReserveItem().setEnabled(true);	//enable the reserve button 
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
			double price=0,deposit=0;
			int accountID=0,itemID=0;	


			//get docket
			docket=theView.getTabsPane().getReservationPanel().getEnterDocketNoTF().getText();
			if (docket.equals(""))		theView.getTabsPane().getReservationPanel().warnDocketNull();
			//get deposit			
			try
			{ 
				depositString=theView.getTabsPane().getReservationPanel().getDepositTF().getText();
				deposit = Double.parseDouble(depositString);

				price =  theView.getTabsPane().getReservationPanel().getPrice();
				if (deposit>price)
					theView.getTabsPane().getReservationPanel().warnUpdate();	
				if (deposit==0)
					theView.getTabsPane().getReservationPanel().warnUpdateNull();

			}
			catch (Exception io) //warning if the deposit field is null or not a number
			{
				theView.getTabsPane().getReservationPanel().warnUpdateNull();
			}


			//name and password using the LoginPanel 
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

			if (index>-1 && !(depositString.equals("")) && !(docket.equals("")) && deposit>0 && deposit<price )
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

				System.out.println("Remove Item successful");
				//Now that data processing is complete, clear the GUI form
				theView.getTabsPane().getMaintainPanel().clearEditItemForm();
			}
			else{
				theView.getTabsPane().getMaintainPanel().warnEditItemFormErrors(errorMessages);
			}


		}
	}

	
	


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

	public void updteAccounts(){
		ArrayList<String>accountNames= new ArrayList<String>();

		for(int i=0;i<theModel.getAllAccounts().size();i++){

			String accN=theModel.getAllAccounts().get(i).getAccountName();
			accountNames.add(accN);
			System.out.println(accN);
		}

		theView.getTabsPane().getMaintainPanel().setAccountModel(accountNames);
	}
}
