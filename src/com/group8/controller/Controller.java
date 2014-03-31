package com.group8.controller;




import java.util.ArrayList;
import java.util.List;


import com.group8.model.Item;
import com.group8.view.AccountFormEvent;
import com.group8.view.AccountListner;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.Testing_MaintainPanel;

public class Controller implements CategoryListener, AccountListner {
	
	List <Item> temItemList;
	
	private Testing_MaintainPanel tMp;   //this is like MainFrame for now >>Testing
	//private Category categoryTheModel;
	//private Testing_Data_Base_Class dbCategory = new Testing_Data_Base_Class();
	
	
	//private MaintainPanel maintainPanel;
	
	
	public Controller(Testing_MaintainPanel tMp ){
		this.tMp=tMp;
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
	
	
	/*Temporary method 
	 * Gets me list of items 
	 * I can display in the table
	 * ReservationPanel calss
	 */
	public List<Item> getMeSomeItems(){
		Item i1= new Item(1, 900.00, "Fender", "MetalXW", 10, 10);
		Item i2= new Item(2, 1200.00, "Fender", "Pinki", 1, 1);
		Item i3= new Item(3, 400.00, "Fender", "Grany", 3, 3);
		Item i4= new Item(4, 200.00, "Fender", "YUPI", 55, 55);
		Item i5= new Item(5, 1200.00, "Fender", "XHa", 6, 6);
		Item i6= new Item(6, 3300.00, "Fender", "JJJJ", 10, 10);
		Item i7= new Item(7, 1.00, "F", "J", 10, 10);

		 temItemList= new ArrayList<Item>();
		 temItemList.add(i1);
		 temItemList.add(i2);
		 temItemList.add(i3);
		 temItemList.add(i4);
		 temItemList.add(i5);
		 temItemList.add(i6);
		 temItemList.add(i7);
	
		 return temItemList;	
	}	
	
}
