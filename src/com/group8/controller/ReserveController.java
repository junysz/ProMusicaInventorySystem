package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.group8.controller.Controller.ComboBoxListener;
import com.group8.controller.Controller.ComboBoxSubCatListener;
import com.group8.model.Account;
import com.group8.model.Item;
import com.group8.model.MainModel;
import com.group8.model.ReservedItem;
import com.group8.view.MainFrame;



public class ReserveController {
	
	private MainFrame theView;  	
	private MainModel theModel;	

	public ReserveController(MainFrame theView, MainModel theModel){
		this.theView=theView;
		this.theModel=theModel;
		
		theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
		theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		theView.getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());		
		populateCategoryReservPanel();	
		theView.getTabsPane().getReservationPanel().addListListener(new myListListener());		
		populateList();   
		theView.getTabsPane().getReservationPanel().addUpdateListener(new UpdateBtn());
		theView.getTabsPane().getReservationPanel().addRemoveListener(new RemoveBtn());
		theView.getTabsPane().getReservationPanel().addReserveListener(new ReserveBtn());
	

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
						//Repopulate Table
						populateTable();
						//repopulate the List in the FindReservation Panel

						populateList();
					}
				}	
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

			public void  populateList()
			{
				theView.getTabsPane().getReservationPanel().removeList();
				ArrayList<ReservedItem> List=new ArrayList <ReservedItem>();    
				ArrayList<ReservedItem> EmptyList=new ArrayList <ReservedItem>();   
				List=theModel.getReservedItems(); 
				theView.getTabsPane().getReservationPanel().setListModel(List);
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

		
			}


