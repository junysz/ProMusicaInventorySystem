package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	}
	
	/*
	public void populateCategoryBoxes(){
		categories=theModel.getMySomeCategories(); //i deleted this method from model, i can replace with proper one using query
		theView.getTabsPane().getReservationPanel().setComboBoxCategoryModel(categories);
	}


	class PopulateTableListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			temItemList=theModel.getMeSomeItems(); //i deleted this method from model, i can replace with proper one using query
			theView.getTabsPane().getReservationPanel().setTableModel(temItemList);	
		}	
	}
	*/
	
	class ComboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String selectedCategory=theView.getTabsPane().getReservationPanel().getSelectCategoryCBox().getSelectedItem().toString();
			System.out.println("ComboBox changed to: "+selectedCategory);
			
			//now populate all subcategories ....
			
		}
		
	}














	/*this is method implemented from CategoryListener Interface 
	 *Category object is passed form view 
	 *??we can pass it to the model from here??
	 */
	public void categoryAddedPerformed(CategoryFormEvent catFormEvent) 
	{	
		System.out.println(catFormEvent.getName());

		//dbCategory.getCategoryList().add(new Category(catFormEvent.getName()));
		//dbCategory.printCategory();		
	}

	/*
	 * Implementation of AccountListener passes account from view 
	 */
	public void accountAddedPerformed(AccountFormEvent accountFormEvent) {

		Account a = new Account();
		a.setAccountName(accountFormEvent.getUserName());
		a.setPassword(accountFormEvent.getPassword());
		a.setType(accountFormEvent.getType());
		System.out.println(a.getAccountName() + a.getPassword() + a.getType());
		
		theModel.addNewAccount(a);
	}


}
