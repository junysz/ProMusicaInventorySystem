package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.group8.model.Account;
import com.group8.model.Item;
import com.group8.model.MainModel;
import com.group8.model.Sale;
import com.group8.view.CategoryComboBoxModel;
import com.group8.view.CheckoutTableModel;
import com.group8.view.ItemTableModel;
import com.group8.view.MainFrame;



public class SalesController {
	private ArrayList<Item> saleItems = new ArrayList<Item>();
	private ArrayList<Integer> quantities = new ArrayList<Integer>();
	private MainFrame theView;
	public MainModel theModel;
	double total = 0;
	public SalesController(Controller controller)
	{
//ACTIVATE MAKE SALE PANEL LISTENERS

theView=controller.getView();
theModel=controller.getModel();
theView.getTabsPane().getMakeSalePanel().addCheckoutButtonListener(new SaleListener());
theView.getTabsPane().getMakeSalePanel().addClearCartButtonListener(new SaleListener());
theView.getTabsPane().getMakeSalePanel().addAddToCartButtonListener(new SaleListener());
theView.getTabsPane().getMakeSalePanel().addLogoutButtonListener(new SaleListener());
theView.getTabsPane().getMakeSalePanel().addCategoryBoxListener(new SaleListener());
theView.getTabsPane().getMakeSalePanel().addSubCategoryListener(new SaleListener());
theView.getTabsPane().getMakeSalePanel().addKeyListenerToSerchTextBox(new SearchListener());
theView.getTabsPane().getMakeSalePanel().getpSale().addPopupButtonsListener(new SaleListener());
theView.getTabsPane().getMakeSalePanel().getpSale().addTableModelListener(new QuantityChangeListener());
theView.getTabsPane().getMakeSalePanel().setSelectCategoryCBModel(new CategoryComboBoxModel(), theModel.getCategoryNames());

	}
	
//**************************Sale panel and popup buttons*******************

class SaleListener implements ActionListener{


boolean contains=false;
int addingIndex=-1;
Item added = new Item();


	


@SuppressWarnings("deprecation")
@Override
public void actionPerformed(ActionEvent e) {

if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getpSale().getGoBckButton())
{
	theView.getTabsPane().getMakeSalePanel().getpSale().dispose();
}
else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getpSale().getCompleteSaleButton())
{
	//calculate total
	for(int i = 0 ; saleItems.size()>i ; i++)
	{
		total+=saleItems.get(i).getPrice()*quantities.get(i);
	}
	//stoer account that makes sale
	Account addingAccount = new Account();
	addingAccount.setAccountID(theModel.getLoggedID());
	addingAccount.setAccountName(theModel.getLoggedName());
	//get current date
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH); // Note: zero based!
	int day = now.get(Calendar.DAY_OF_MONTH);
	//init sale
	Sale thisSale = new Sale(0, new Date(year, month, day) , total, theModel.getLoggedID(), theModel.getLoggedName());
	theModel.addNewSale(thisSale, addingAccount);
	int saleID = theModel.getLastSaleID();
	thisSale.setSaleID(saleID);
	//add sol items to itemsold table
	for(int i = 0  ; i<saleItems.size();i++)
	{
		theModel.addNewItemSold(saleItems.get(i), thisSale, (double) theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getValueAt(i, 2));
		Item updatingItem = saleItems.get(i);
		theModel.updateItemLevels(updatingItem, quantities.get(i));
		theModel.updateItem(updatingItem	, theModel.getItemSubCatID(updatingItem.getItemID()));
		if (quantities.get(i)>1)
		{
			for(int j = 0 ; j<quantities.get(i);)
			{
				theModel.addNewItemSold(saleItems.get(i), thisSale, Double.parseDouble((String) theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getValueAt(i, 2)));
			}
		i++;
		}
		
	}

	theView.getTabsPane().getMakeSalePanel().getpSale().dispose();
	JOptionPane.showMessageDialog(theView, "Sale Completed.", "Success!", 1);
	saleItems = new ArrayList<>();
	quantities = new ArrayList<>();
	total=0;
}
else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getBtnAddToCart())
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
		testQuantity = (String) JOptionPane.showInputDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Quantity Required:", "Please Enter Quantity", 1, null, null, "1");

		//Check if entered number is positive an int
		if (testQuantity.matches("^[\\d+$]"))
		{
			validQuantity = Integer.parseInt(testQuantity);
			int testingQuantity=theModel.getItemByName(theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 1)+" "+theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 2)).getAvailableStockLevel();
			if(testingQuantity<0)
			{
				testingQuantity=0;
			}
			
			if(testingQuantity<validQuantity)
			{
				JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Available Stock Level insufficient!.", "Correct required quantity!", 2);
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
						JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Quantity exceeded availible stock level.", "Correct required quantity!", 2);
				}
				else{
					
					try
					{
						quantities.add(addingIndex, quantities.get(addingIndex)+validQuantity);

						JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Item added to the basket!", "Success", 1);
					}
					catch (IndexOutOfBoundsException e1)
					{}
				}
					
			}
			else
			{
				
				saleItems.add(added);
				quantities.add(saleItems.indexOf(added), validQuantity);
				JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Item added to the basket!", "Success", 1);
				theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setModel(new CheckoutTableModel(saleItems, quantities));
			}
			System.out.println(saleItems.get(0).getModel());
				
		}

			validNumber=true;
	}
	else
	{
		JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Enter Valid Quantity", "Invalid entry!", 2);
	}
	
	}
	
	/*
	 * End entering quantity
	 */
	
	
	//check if quantity for sale does not exceeds availible quantity

	
}
else
{
	JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Please make sure one item is selected!", "No Selection Made!", 2);
}
}
else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getBtnCheckout())
{
	/*
	 * Make popup visible if it's not, and refresh table with the items
	 */
		theView.getTabsPane().getMakeSalePanel().getpSale().setAlwaysOnTop(true);
		theView.getTabsPane().getMakeSalePanel().getpSale().setVisible(true);
		theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setModel(new CheckoutTableModel(saleItems, quantities));
	double total=0;
	for(int i = 0 ; i < saleItems.size() ; i++ )
	{
		total+=(saleItems.get(i).getPrice()*quantities.get(i));
	}
	theView.getTabsPane().getMakeSalePanel().getpSale().setTotal(total);
	theView.getTabsPane().getMakeSalePanel().getpSale().setSaleTableModel(new CheckoutTableModel(saleItems, quantities));
	//Debugging code nextd
	System.out.println("Checkout pressed!!");
	theView.getTabsPane().getMakeSalePanel().getpSale().repaint();
}
else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getBtnClearCart())
{
int option = JOptionPane.showConfirmDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Are you sure you want to delete all items in the basket?", "Please Confirm", 0);
if(option==0)
{
saleItems = new ArrayList<Item>();
JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Basket has been successfully cleared!", "Success!", 1);
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

else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getLogoutButton())
{
	theView.logout();
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
theView.getTabsPane().getMakeSalePanel().getSelectCategoryCB().setSelectedIndex(-1);
theView.getTabsPane().getMakeSalePanel().getSearchTF().setText(searchterm);
}

}
	class QuantityChangeListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub

			int curCol = theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getSelectedColumn();
			int curRow = theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getSelectedRow();
			if(curCol==2)
			{

				String regexDecimal = "^-?\\d*\\.\\d+$";
				String regexInteger = "^-?\\d+$";
				String regexDouble = regexDecimal + "|" + regexInteger;
				if(!theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getValueAt(curRow, curCol).toString().matches(regexDouble))
				{
					theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setValueAt(saleItems.get(curRow).getPrice(), curRow, curCol);
					JOptionPane.showMessageDialog(theView, "Enter only valid price!");
				}
				else
				{
					
					double newPrice = (double) theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getValueAt(curRow, curCol);

					theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setValueAt(newPrice, curRow, curCol);
					theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setValueAt((newPrice*(double)quantities.get(curRow)), curRow, 4);
					saleItems.get(curRow).setPrice(newPrice);
					theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().repaint();
					double total=0;
					for(int i = 0 ; i<saleItems.size();i++)
					{
						total+=saleItems.get(i).getPrice()*(int)quantities.get(i);
					}
					theView.getTabsPane().getMakeSalePanel().getpSale().setTotal(total);
				}
			}
			else if(curCol==3)
			{
				if(!theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getValueAt(curRow, curCol).toString().matches("^\\d+$"))
				{
					theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setValueAt(quantities.get(curRow), curRow, curCol);
					JOptionPane.showMessageDialog(theView, "Enter only whole positive numbers!");
				}
				else if((int)theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getValueAt(curRow, curCol)>saleItems.get(curRow).getAvailableStockLevel())
				{
					theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setValueAt(quantities.get(curRow), curRow, curCol);

					JOptionPane.showMessageDialog(theView, "Exeeded availible stock!");
					
				}
				else
				{
					int newQuantity = (int) theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getValueAt(curRow, curCol);
	
					theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setValueAt(newQuantity, curRow, curCol);
					theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setValueAt((saleItems.get(curRow).getPrice()*newQuantity), curRow, 4);
					quantities.set(curRow, newQuantity);
					theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().repaint();	
					double total=0;
					for(int i = 0 ; i<saleItems.size();i++)
					{
						total+=saleItems.get(i).getPrice()*(int)quantities.get(i);
					}
					theView.getTabsPane().getMakeSalePanel().getpSale().setTotal(total);
				}
			}
		}
	
	
	

	
	}
	

}

//**************************End Sale panel and popup buttons****************



