package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.group8.model.*;
import com.group8.view.CategoryComboBoxModel;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.CheckoutTableModel;
import com.group8.view.ItemTableModel;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;
import com.group8.view.PopupSaleDialog;

public class Controller implements CategoryListener{
	
	private MainFrame theView;  	
	private MainModel theModel;	
	protected CategoriesController categoriesController;
	protected ItemsController itemsController;
	protected AccountsController accountsController;
	protected ReserveController reserveController;
	protected ReportController reportController;


	//DANIEL.....
	private PopupSaleDialog pSale;
	private ArrayList<Item> saleItems = new ArrayList<Item>();
	private ArrayList<Integer> quantities = new ArrayList<Integer>();


	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;
		/*******************Login Panel******** */
		theView.addLoginListener(new LoginController(theView, theModel));
		theView.setCategoryListener(this);
		/*******************Maintain Panel*/
		theView.getTabsPane().getMaintainPanel().getEditAccountTypeComboBox().setSelectedIndex(-1);
		categoriesController= new CategoriesController(this);
		itemsController= new ItemsController(this);
		accountsController= new AccountsController(this);		
		update();//comboBoxes
		updteAccounts();		
		/*******************RESERVE PANEL*****************/ 
		reserveController= new ReserveController(this);
		/*******************REPORT PANEL*****************/ 		 
		reportController= new ReportController(this);
		
		
		
		
		
		
		
		
		
		
		
		
		
		//ACTIVATE MAKE SALE PANEL LISTENERS
		
				theView.getTabsPane().getMakeSalePanel().addCheckoutButtonListener(new SaleListener());
				theView.getTabsPane().getMakeSalePanel().addClearCartButtonListener(new SaleListener());
				theView.getTabsPane().getMakeSalePanel().addAddToCartButtonListener(new SaleListener());
				theView.getTabsPane().getMakeSalePanel().addCategoryBoxListener(new SaleListener());
				theView.getTabsPane().getMakeSalePanel().addSubCategoryListener(new SaleListener());
				theView.getTabsPane().getMakeSalePanel().addKeyListenerToSerchTextBox(new SearchListener());
				pSale = new PopupSaleDialog();
				pSale.addTableModelListener(new QuantityChangeListener());
				theView.getTabsPane().getMakeSalePanel().setSelectCategoryCBModel(new CategoryComboBoxModel(), theModel.getCategoryNames());



				/********************************************/


		
		
		
	}

	
	//**************************Sale panel and popup buttons*******************
	
class SaleListener implements ActionListener{
		

		boolean contains=false;
		int addingIndex=-1;
		Item added = new Item();

	
					
		

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

							added=theModel.getItemByName((String) theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 1)+" "+(String) theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 2));
							
							
							/*
							 * Check if item is in the basket already
							 */
							
							for (int i = 0 ; i < saleItems.size() ; i++)
							{
								if(added.getItemID()==(saleItems.get(i).getItemID()))
								{
										contains = true;
										addingIndex=i;
										break;
								}
							
							}

							if(contains)
							{
								if(theModel.getItemByName(theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 1)
										+" "+theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 2)).getAvailableStockLevel()
										<validQuantity+quantities.get(addingIndex))
								{
										JOptionPane.showMessageDialog(pSale, "Quantity exceeded availible stock level.", "Correct required quantity!", 2);
								}
								else{
									
									try
									{
										quantities.add(addingIndex, quantities.get(addingIndex)+validQuantity);

										JOptionPane.showMessageDialog(pSale, "Item added to the basket!", "Success", 1);
									}
									catch (IndexOutOfBoundsException e1)
									{}
								}
									
							}
							else
							{
								saleItems.add(added);
								quantities.add(saleItems.indexOf(added), validQuantity);
								JOptionPane.showMessageDialog(pSale, "Item added to the basket!", "Success", 1);
							}
							System.out.println(saleItems.get(0).getModel());
								
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
				double total=0;
				for(int i = 0 ; i < saleItems.size() ; i++ )
				{
					total+=(saleItems.get(i).getPrice()*quantities.get(i));
				}
				pSale.setTotal(total);
				pSale.setSaleTableModel(new CheckoutTableModel(saleItems, quantities));
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
class QuantityChangeListener implements TableModelListener{

	

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

//**************************End Sale panel and popup buttons****************





	/*//Inner Class that listens for the Create Account Button
	class RemoveItemBtn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String itemSelection=null;
			//read the values (itemSelection) from the view
			try{
				itemSelection=theView.getTabsPane().getMaintainPanel().getItemToEditComboBox().getSelectedItem().toString();
			}catch(Exception ex){

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
	}*/





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

	public MainFrame getView(){
		return theView;
	}
	public MainModel getModel(){
		return theModel;
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
