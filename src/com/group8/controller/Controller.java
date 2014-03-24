package com.group8.controller;




import com.group8.view.AccountFormEvent;
import com.group8.view.AccountListner;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.Testing_MaintainPanel;

public class Controller implements CategoryListener, AccountListner {
	
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
	
	
	
	
	
	
}
