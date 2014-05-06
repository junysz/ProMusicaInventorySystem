package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
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

	//**************************Sala and cart pop-up buttons listener*******************
	class SaleListener implements ActionListener{

		boolean contains=false;
		int addingIndex=0;
		Item added = new Item();
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getpSale().getGoBckButton())
			{
				theView.getTabsPane().getMakeSalePanel().getpSale().dispose();
				if(!(total>0))
				{
					theView.getTabsPane().getMakeSalePanel().disableCartButtons();
				}
			}
			else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getpSale().getCompleteSaleButton())
			{
				//calculate total
				for(int i = 0 ; saleItems.size()>i ; i++)
				{
					total+=saleItems.get(i).getPrice()*quantities.get(i);
				}
				//Stores account that makes sale
				Account addingAccount = new Account();
				addingAccount.setAccountID(theModel.getLoggedID());
				addingAccount.setAccountName(theModel.getLoggedName());
				//get current date
				Calendar now = Calendar.getInstance();
				java.sql.Date date = new java.sql.Date(now.getTime().getTime());
				Sale thisSale = new Sale(0, date , total, theModel.getLoggedID(), theModel.getLoggedName());
				theModel.addNewSale(thisSale, addingAccount);
				int saleID = theModel.getLastSaleID();
				thisSale.setSaleID(saleID);
				//add sold items to item-sold table
				for(int i = 0  ; i<saleItems.size();i++)
				{
					System.out.println("Got here");
					theModel.addNewItemSold(saleItems.get(i), thisSale, (double) theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getValueAt(i, 2),quantities.get(i));
					Item updatingItem = saleItems.get(i);
					theModel.updateItemStock(updatingItem.getItemID(), updatingItem.getStockLevel()-quantities.get(i));
					theModel.updateItemAvailableStock(updatingItem.getItemID(), updatingItem.getAvailableStockLevel()-quantities.get(i));
					
				}
				theView.getTabsPane().getMakeSalePanel().getpSale().dispose();
				JOptionPane.showMessageDialog(theView, "Sale Completed.", "Success!", 1);
				theView.getTabsPane().getMakeSalePanel().setTableModel(new ItemTableModel(), new ArrayList<Item>());

				theView.getTabsPane().getMakeSalePanel().disableCartButtons();
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
						//Check if entered number is positive an integer
						if (testQuantity.matches("^[\\d+$]"))
						{
							validQuantity = Integer.parseInt(testQuantity);
							int testingQuantity=theModel.getItemByName(theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 1)
									+" "+theView.getTabsPane().getMakeSalePanel().getTable().getValueAt(row, 2)).getAvailableStockLevel();
							if(testingQuantity<0)
							{
								testingQuantity=0;
							}

							if(testingQuantity<validQuantity)
							{
								System.out.println("Testing quantity is:" + testingQuantity);
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
									else
										addingIndex=0;
								}

								if(contains)
								{
									if(testingQuantity<validQuantity+quantities.get(addingIndex))
									{
										System.out.println("Entered quantity is: " + validQuantity);
										System.out.println("At the moment you have: "+quantities.get(addingIndex)+" units of the item added.");
										JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Quantity exceeded availible stock level.", "Correct required quantity!", 2);
									}
									else{

										try
										{
											quantities.add(addingIndex, quantities.get(addingIndex)+validQuantity);
											theView.getTabsPane().getMakeSalePanel().enableCartButtons();
											JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Item added to the basket!", "Success", 1);
											System.out.println("Enabling buttons!");
											
										}
										catch (IndexOutOfBoundsException e1)
										{}
										theView.getTabsPane().getMakeSalePanel().enableCartButtons();
										
									}

								}
								else
								{
									saleItems.add(added);
									quantities.add(saleItems.indexOf(added), validQuantity);
									JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Item added to the basket!", "Success", 1);
									theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setModel(new CheckoutTableModel(saleItems, quantities));

									theView.getTabsPane().getMakeSalePanel().enableCartButtons();
								}
								if (saleItems.size()>0)	System.out.println(saleItems.get(0).getModel());

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
				}
				else
				{
					JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Please make sure one item is selected!", "No Selection Made!", 2);
				}
			}
			else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getBtnCheckout())
			{
				/*
				 * Make pop-up visible if it's not, and refresh table with the items
				 */
				theView.getTabsPane().getMakeSalePanel().getpSale().setTotal(total);
				theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().setModel(new CheckoutTableModel(saleItems, quantities));
				

				for(int i = 0 ; i<saleItems.size();i++)
				{
					total+=saleItems.get(i).getPrice()*(int)quantities.get(i);
				}

				theView.getTabsPane().getMakeSalePanel().getpSale().setTotal(total);//sets total to be displayed in the checkout pop-up
				//resets data in pop-up table
				theView.getTabsPane().getMakeSalePanel().getpSale().setSaleTableModel(new CheckoutTableModel(saleItems, quantities));
				//sets checkout dialog visible
				theView.getTabsPane().getMakeSalePanel().getpSale().setAlwaysOnTop(true);
				theView.getTabsPane().getMakeSalePanel().getpSale().setVisible(true);
				theView.getTabsPane().getMakeSalePanel().getpSale().repaint();
			}
			else if(e.getSource()==theView.getTabsPane().getMakeSalePanel().getBtnClearCart())
			{
				/*
				 * Integer option indicates if checkout will be cleared or not after prompting the user
				 */
				int option = JOptionPane.showConfirmDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Are you sure you want to delete all items in the basket?", "Please Confirm", 0);
				/*
				 * if option selected is yes (zero) then clear the checkout
				 */
				if(option==0)
				{
					saleItems = new ArrayList<Item>();
					quantities = new ArrayList<Integer>();
					addingIndex=0;
					JOptionPane.showMessageDialog(theView.getTabsPane().getMakeSalePanel().getpSale(), "Basket has been successfully cleared!", "Success!", 1);
					theView.getTabsPane().getMakeSalePanel().disableCartButtons();
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
	/*
	 * Following class updates the table model in sales panel
	 * when key is pressed
	 */

	class SearchListener implements KeyListener	{
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
		}
		@Override
		public void keyReleased(KeyEvent e) {
			/*
			 * Every time key is released in the search text field the table is refreshed
			 * and combo boxes selection is set to none
			 */
			String searchterm = theView.getTabsPane().getMakeSalePanel().getSearchTF().getText();
			theView.getTabsPane().getMakeSalePanel().setTableModel(new ItemTableModel(), theModel.getItemsByKeyword(searchterm));
			theView.getTabsPane().getMakeSalePanel().getSelectCategoryCB().setSelectedIndex(-1);
			theView.getTabsPane().getMakeSalePanel().getSearchTF().setText(searchterm);
		}
	}
	/*
	 * Following class is responsible for updating checkout dialog and sale items list and quantities
	 * after any changes are made in the table
	 */
	class QuantityChangeListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			// TODO Auto-generated method stub

			int curCol = theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getSelectedColumn();
			int curRow = theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getSelectedRow();
			if(curRow>=saleItems.size())
			{
				if(saleItems.size()<1)
					theView.getTabsPane().getMakeSalePanel().disableCartButtons();
				return;
			}
			if(curCol==2)
			{
				/*
				 * Regular expressions for checking correct format od decimal and integer
				 */
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
					total=0;
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
				else if(saleItems.get(curRow)!=null&&(int)theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().getValueAt(curRow, curCol)>saleItems.get(curRow).getAvailableStockLevel())
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
					total=0;
					if(newQuantity<=0)
					{
						saleItems.remove(curRow);
						quantities.remove(curRow);
					}
						theView.getTabsPane().getMakeSalePanel().getpSale().getCheckoutTable().repaint();	
						for(int i = 0 ; i<saleItems.size();i++)
						{
							if(saleItems.get(i)!=null)
								total+=saleItems.get(i).getPrice()*(int)quantities.get(i);
						}
						theView.getTabsPane().getMakeSalePanel().getpSale().setTotal(total);
					
				}
			}
			
			if(total>0.0)
			{
				System.out.println("Total is bigger than zero");
				theView.getTabsPane().getMakeSalePanel().getpSale().getCompleteSaleButton().setEnabled(true);
			}
			else
			{
				System.out.println("Total is less than zero");
				theView.getTabsPane().getMakeSalePanel().getpSale().getCompleteSaleButton().setEnabled(false);
			}
			System.out.println(total);
		}

	}
}//**************************End Sale panel and pop-up buttons****************