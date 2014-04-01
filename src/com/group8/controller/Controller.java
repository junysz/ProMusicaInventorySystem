package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import com.group8.model.Item;
import com.group8.model.MainModel;
import com.group8.view.AccountFormEvent;
import com.group8.view.AccountListner;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.MainFrame;
import com.group8.view.Testing_MaintainPanel;

public class Controller implements CategoryListener, AccountListner {
	
	private List <Item> temItemList;	
	private MainFrame theView;  	
	private MainModel theModel;											

	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;
		
		
		theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
	}
	
	
	class PopulateTableListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			temItemList=theModel.getMeSomeItems();
			theView.getTabsPane().getReservationPanel().setTableModel(temItemList);	
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
		
		System.out.println(accountFormEvent.getUserName());
		System.out.println(accountFormEvent.getPassword());
		System.out.println(accountFormEvent.getConfirmPassword());
		System.out.println(accountFormEvent.getType());
		System.out.println();
	}
	
	
}
