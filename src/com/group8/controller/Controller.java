package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import com.group8.model.*;
import com.group8.view.AccountFormEvent;
import com.group8.view.AccountListner;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.MainFrame;

public class Controller implements CategoryListener, AccountListner {

	private List <Item> temItemList;
	private List<String> categories;
	private List<String> test;
	private MainFrame theView;  	
	private MainModel theModel;											

	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;
		/******Maintain Categories Panel**************/
		//addCategoryToDataBase when btn clicked
		theView.setCategoryListener(this);
		
		//theView.getTabsPane().getMaintainPanel().addselectCategorycomboBoxListener(new SelectCategorycomboBoxListener());
		
		//populate left side 
		update();
		/********************************************/
		
		
		
		
		
		theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
		theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		theView.getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());
		//categoryComboBox populate form DB
		populateCategoryReservPanel();

	}

	class MaintanLeftComboBoxCategory implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			update();
			System.out.println("left clicked");
		}
		
	}


	public void populateCategoryReservPanel(){
		categories=theModel.getMySomeCategories(); //i deleted this method from model, i can replace with proper one using query
		theView.getTabsPane().getReservationPanel().setComboBoxCategoryModel(categories);
	}

	class PopulateTableListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			temItemList=theModel.getMeSomeItems(); //i deleted this method from model, i can replace with proper one using query
			theView.getTabsPane().getReservationPanel().setTableModel(temItemList);	
		}	
	}
	class ComboBoxListener implements ActionListener{
		

		@Override
		public void actionPerformed(ActionEvent e) {

			String selectedCategory=theView.getTabsPane().getReservationPanel().getSelectCategoryCBox().getSelectedItem().toString();
			System.out.println("ComboBox Category changed to: "+selectedCategory);

			//now populate all sub-categories ....
			test=theModel.getMeSomeSubCategories();

			theView.getTabsPane().getReservationPanel().setComboBoxSubCategoryModel(test);
		}



	}
	class ComboBoxSubCatListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
			String subCat=	theView.getTabsPane().getReservationPanel().getSelectSubcategoryCBox().getSelectedItem().toString();
				System.out.println("ComboBox Sub-category changed to: "+ subCat);
				

			}catch(Exception exception)
			{
				exception.printStackTrace();
			System.out.println("No data in comboBox");
			}

			

			//get Items for sub-category and display in the table

		}

	}






/***************************START COMBO-BOXES MAINTAIN_PANEL*****************************/


	/*this is method implemented from CategoryListener Interface 
	 *Category object is passed form view 
	 *??we can pass it to the model from here??
	 */
	public void categoryAddedPerformed(CategoryFormEvent catFormEvent) 
	{	
		
		if(catFormEvent.getName().isEmpty()){
			theView.getTabsPane().getMaintainPanel().warnCategoryFieldEmpty();
		}
		else{
			System.out.println("I am adding new category to dataBase: "+catFormEvent.getName());
		Category c = new Category(catFormEvent.getName());
		theModel.addNewCategory(c);
			//update dataBase and other comboBoxes
		
		//update MainTainPanel
			update();
		}
	}

	//MaintainPanel: populates all ComboBoxes:SelectCategory
	public void update() {
		
		theView.getTabsPane().getMaintainPanel().setNewModel(theModel.getListOfCategories());	
	}
	
	
	//Get strings form comboboxes
	
	
	
	/***************************END COMBO-BOXES*****************************/


	/*
	 * Implementation of AccountListener passes account from view 
	 */
	public void accountAddedPerformed(AccountFormEvent accountFormEvent) {

		Account a = new Account();
		a.setAccountName(accountFormEvent.getUserName());
		a.setPassword(accountFormEvent.getPassword());
		a.setType(accountFormEvent.getType());
		System.out.println(a.getAccountName() + a.getPassword() + a.getType());
		
		//theModel.addNewAccount(a);
	}


}
