package com.group8.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.group8.model.Account;
import com.group8.model.Item;
import com.group8.model.MainModel;
import com.group8.model.Reservation;
import com.group8.view.MainFrame;




public class ReservationController {

	private Controller controller;  	


	public ReservationController(Controller controller){
		this.controller= controller;

		getView().getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());


		populateCategoryReservPanel();	
		getView().getTabsPane().getReservationPanel().addListListener(new myListListener());		
		populateList();   
		getView().getTabsPane().getReservationPanel().addUpdateListener(new UpdateBtn());
		getView().getTabsPane().getReservationPanel().addRemoveListener(new RemoveBtn());
		getView().getTabsPane().getReservationPanel().addReserveListener(new ReserveBtn());
		getView().getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		getView().getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());
		getView().getTabsPane().getReservationPanel().logoutButtonListener(new logoutButtonListener());
		getView().getTabsPane().getReservationPanel().logoutButtonListener2(new logoutButtonListener2());


	}


	class PopulateTableListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			populateTable();
		}
	}


	class myListListener implements ListSelectionListener{
		private String item;
		private double totalPrice,deposit;
		private String docketSelected;
		Date date;
		@Override
		public void valueChanged(ListSelectionEvent e) {

			if (getView().getTabsPane().getReservationPanel().getList().getSelectedValue()!=null)	
				docketSelected = getView().getTabsPane().getReservationPanel().getList().getSelectedValue().toString();

			ArrayList<Reservation> myList=new ArrayList<Reservation>();
			myList=getModel().getReservedItems();
			int size=myList.size();
			for (int i=0;i<size;i++)
				if (myList.get(i).getDocketNo().equals(docketSelected) )			
				{
					int itemID=myList.get(i).getItemID();


					item=getModel().getItemByID(itemID).getBrand()+" "+getModel().getItemByID(itemID).getModel();
					totalPrice=getModel().getItemByID(itemID).getPrice();
					deposit=myList.get(i).getDeposit();
					date = myList.get(i).getDate();

				}

			getView().getTabsPane().getReservationPanel().getDocketNoTF().setText(docketSelected);
			getView().getTabsPane().getReservationPanel().getBrandModelTF().setText(item);
			getView().getTabsPane().getReservationPanel().getCurrentDepositTF().setText(deposit+"");
			getView().getTabsPane().getReservationPanel().getTotalPriceTF().setText(totalPrice+"");
			getView().getTabsPane().getReservationPanel().getDateTF().setText(""+ date);

			getView().getTabsPane().getReservationPanel().getEndReservation().setEnabled(true);
			getView().getTabsPane().getReservationPanel().getNewButton().setEnabled(true);
		}	
	}

	//Class for listening the button to Update a Reservation

	class UpdateBtn implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			double newDeposit,oldDeposit;
			Reservation reserved;

			reserved=new Reservation();
			String docket=getView().getTabsPane().getReservationPanel().getDocketNoTF().getText();			
			String newDepositString=getView().getTabsPane().getReservationPanel().getupdateDepositTF().getText();
			String oldDepositString=getView().getTabsPane().getReservationPanel().getCurrentDepositTF().getText();
			try
			{
				newDeposit = Double.parseDouble(newDepositString);	
				oldDeposit= Double.parseDouble(oldDepositString);
				reserved.setDeposit(newDeposit+oldDeposit);
				double price=Double.parseDouble(getView().getTabsPane().getReservationPanel().getTotalPriceTF().getText());

				if (newDeposit+oldDeposit>price-1)  getView().getTabsPane().getReservationPanel().warnUpdate();
				else 	
				{
					getModel().updateReservedItem(docket,newDeposit+oldDeposit);
					getView().getTabsPane().getReservationPanel().getCurrentDepositTF().setText((newDeposit+oldDeposit)+"");	
					getView().getTabsPane().getReservationPanel().successful();
					getView().getTabsPane().getReservationPanel().getupdateDepositTF().setText("");				
				}
			}
			catch (Exception io) {
				getView().getTabsPane().getReservationPanel().warnUpdateNull();	
			}
		}
	}


	//Class to remove a Reservation  
	class RemoveBtn implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {				

			String docket=getView().getTabsPane().getReservationPanel().getDocketNoTF().getText();
			getModel().removeReservedItem(docket);
			populateList();
			getView().getTabsPane().getReservationPanel().getDocketNoTF().setText("");
			getView().getTabsPane().getReservationPanel().getBrandModelTF().setText("");
			getView().getTabsPane().getReservationPanel().getCurrentDepositTF().setText("");
			getView().getTabsPane().getReservationPanel().getTotalPriceTF().setText("");
			getView().getTabsPane().getReservationPanel().getDateTF().setText("");
			getView().getTabsPane().getReservationPanel().successfuly();

		}

	}


	//the scope of this class is to create a new ReservationItem in the database.
	// For this I need five parameters ,to use the method InsertNewReservation
	class ReserveBtn implements ActionListener
	{
		String docket="";
		String depositString="";
		double price=0;
		double deposit=0;
		int accountID=0;
		int itemID=0;
		ArrayList<String> errorMessages;

		@Override
		public void actionPerformed(ActionEvent e) {

			errorMessages = new ArrayList<String>();
			//read the values (docketNo, deposit) from the Reservation Panel
			try{
				docket=getView().getTabsPane().getReservationPanel().getEnterDocketNoTF().getText();
				depositString=getView().getTabsPane().getReservationPanel().getDepositTF().getText();
				price =  getView().getTabsPane().getReservationPanel().getPrice();

			}catch(Exception ex){
				System.out.println("Problem reading input from Make New Reservation form");
			}
			//get the item ID
			int index=getView().getTabsPane().getReservationPanel().getTableIndex();
			//if nothing selected
			if  (index==-1) {
				errorMessages.add("Select an Item to Reserve");
			}
			//else we can set the item ID
			else {
				itemID=getView().getTabsPane().getReservationPanel().getItemID();
			}
			//if not docketNo entered
			if (docket.isEmpty()){
				errorMessages.add("Enter a Docket Number");
			}
			//if no deposit entered
			if (depositString.isEmpty()){
				errorMessages.add("Enter a Deposit");
			}
			//else assign the deposit
			else{
				deposit = Double.parseDouble(depositString);
				//if it not greater than 0
				if (deposit == 0){
					errorMessages.add("Deposit must be greater than 0");
				}
				//if the deposit is greater than item price
				if (deposit>price){
					errorMessages.add("Deposit exceeds Item Price");	
				}
			}

			/* If there were no errors with input, we can process the data 
			 * from the reservation panel and make a new reservation
			 */
			if(errorMessages.isEmpty()){

				//get account name and password using the LoginPanel 
				String username=getView().getUsernameLoginString();
				String pass=getView().getPasswordLoginString();

				//getting the AccountID
				ArrayList<Account> myList=getModel().getAllAccounts();
				for (int i=0;i<myList.size();i++)
				{
					if (myList.get(i).getAccountName().equals(username))
						if(		 myList.get(i).getPassword().equals(pass) )  
							accountID=myList.get(i).getAccountID();
				}

				//Getting current date
				Calendar calendar = Calendar.getInstance();
				java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());


				//insert reservation tuple to database
				getModel().insertNewReservation(accountID, docket, date, deposit, itemID);
				//update Available Stock Level for the designated item
				int itemID1=getView().getTabsPane().getReservationPanel().getItemID();
				int Stock=getView().getTabsPane().getReservationPanel().getAvailableStock();	    
				getModel().updateItemAvailableStock(itemID1,Stock-1);

				//print success message and clear fields
				getView().getTabsPane().getReservationPanel().success();
				getView().getTabsPane().getReservationPanel().getDepositTF().setText("");
				getView().getTabsPane().getReservationPanel().getEnterDocketNoTF().setText("");

				//Re-populate Table
				populateTable();
				//re-populate the List in the FindReservation Panel
				populateList();

			}
			else{
				getView().getTabsPane().getReservationPanel().warnNewReservationInput(errorMessages);
			}





		}	
	}                                                               


	class ComboBoxListener implements ActionListener{
		private List<String> test;
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedCategory=getView().getTabsPane().getReservationPanel().getSelectCategoryCBox().getSelectedItem().toString();
			System.out.println("ComboBox Category changed to: "+selectedCategory);
			//now populate all sub-categories ....
			test=getModel().getSubCategories(selectedCategory);
			getView().getTabsPane().getReservationPanel().setComboBoxSubCategoryModel(test);
		}
	}


	class ComboBoxSubCatListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				String subCat=	getView().getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem().toString();

			}catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}




	//when program starts categories are loaded into the combo-boxes
	public void populateCategoryReservPanel(){
		try{
			List<String> categories;
			categories=getModel().getCategoryNames();
			getView().getTabsPane().getReservationPanel().setComboBoxCategoryModel(categories);
		}catch(Exception e){
			System.out.println("data base is empty");
		}
	}


	public void setListModel(List<Reservation> list)
	{

		int size=list.size();
		for (int i=0;i<size;i++)
		{
			DefaultListModel<String>model=getView().getTabsPane().getReservationPanel().getModel();
			list=getModel().getReservedItems();  		
			model.addElement(list.get(i).getDocketNo());
		}

	}
	public void  populateList()
	{
		getView().getTabsPane().getReservationPanel().removeList();
		ArrayList<Reservation> List=new ArrayList <Reservation>();    

		List=getModel().getReservedItems(); 		
		setListModel(List);
	}
	//method to Populate the table in the Reservation Panel
	public void populateTable()
	{
		String keyword="";
		int size=0;
		keyword= getView().getTabsPane().getReservationPanel().getSearchKeywordTF().getText();//get value of the keyword
		ArrayList<Item> tempList = new ArrayList<Item>();
		ArrayList<Item>newList =   new ArrayList<Item>();

		if (!(keyword.equals("")))   //if the selection is made by the keyword
		{
			newList=getModel().getItemsByKeyword(keyword); //get all the items with keyword
			size=newList.size();
		}
		else //if selection is made using Subcategories and categories
		{
			String subCat = null;
			if ( getView().getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem()!=null)//if subcategory is selected
			{ 
				subCat=	getView().getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem().toString();
				newList=getModel().getItemsInSubcategory(subCat); //get all items in subcategory
				size=newList.size();
			}

			else 
			{
				getView().getTabsPane().getReservationPanel().warnSubCategoryNull();
			}	
		}

		for (int i=0;i<size;i++)
			if (newList.get(i).getAvailableStockLevel()>0)			
				tempList.add(newList.get(i));//check  if AvailableStock>0
		getView().getTabsPane().getReservationPanel().setTableModel(tempList);//populate table
		getView().getTabsPane().getReservationPanel().getBtnReserveItem().setEnabled(true);	//enable the reserve button 
	}

	class logoutButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				getView().logout();

			}catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}
	class logoutButtonListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				getView().logout();

			}catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}

	public MainFrame getView(){
		return controller.getView();
	}
	public MainModel getModel(){
		return controller.getModel();
	}


}


