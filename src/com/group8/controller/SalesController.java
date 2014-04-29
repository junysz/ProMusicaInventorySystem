package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import com.group8.view.PopupSaleDialog;



public class SalesController {
	private Controller controller;  
	private PopupSaleDialog pSale;
	private ArrayList<Item> saleItems = new ArrayList<Item>();
	private ArrayList<Integer> quantities = new ArrayList<Integer>();
	private MainFrame theView;
	public MainModel theModel;
	double total = 0;
	private Date dateOfSale;
	
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
pSale = new PopupSaleDialog();
pSale.addPopupButtonsListener(new SaleListener());
pSale.addTableModelListener(new QuantityChangeListener());
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

if(e.getSource()==pSale.getGoBckButton())
{
	pSale.dispose();
}
else if(e.getSource()==pSale.getCompleteSaleButton())
{
	for(int i = 0 ; saleItems.size()>i ; i++)
	{
		total+=saleItems.get(i).getPrice()*quantities.get(i);
	}
	Account addingAccount = new Account();
	addingAccount.setAccountID(theModel.getLoggedID());
	addingAccount.setAccountName(theModel.getLoggedName());
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH); // Note: zero based!
	int day = now.get(Calendar.DAY_OF_MONTH);
	Sale thisSale = new Sale(0, new Date(year, month, day) , total, theModel.getLoggedID(), theModel.getLoggedName());
	theModel.addNewSale(thisSale, addingAccount);
	for(int i = 0  ; i<saleItems.size();i++)
	{
		System.out.println(""+pSale.getCheckoutTable().getValueAt(i, 2));
		theModel.addNewItemSold(saleItems.get(i), thisSale, (double) pSale.getCheckoutTable().getValueAt(i, 2));
		if (quantities.get(i)>1)
		{
			for(int j = 0 ; j<quantities.get(i);)
			{
				theModel.addNewItemSold(saleItems.get(i), thisSale, Double.parseDouble((String) pSale.getCheckoutTable().getValueAt(i, 2)));
			}
		i++;
		}
		
	}
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
		testQuantity = (String) JOptionPane.showInputDialog(pSale, "Quantity Required:", "Please Enter Quantity", 1, null, null, "1");

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
				JOptionPane.showMessageDialog(pSale, "Available Stock Level insufficient!.", "Correct required quantity!", 2);
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
}

}
class QuantityChangeListener implements TableModelListener{



@Override
public void tableChanged(TableModelEvent e) {
// TODO Auto-generated method stub

}

}

}

//**************************End Sale panel and popup buttons****************



