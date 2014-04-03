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
		/*
		 * Adding Listeners: Combo-boxes
		 */
		
		//addCategoryToDataBase when btn clicked
		theView.setCategoryListener(this);
		
		theView.getTabsPane().getMaintainPanel().addselectCategorycomboBoxListener(new SelectCategorycomboBoxListener());
		theView.getTabsPane().getMaintainPanel().addselectCategoryToEditcomboBoxListener(new SelectCategoryToEditcomboBoxListener());
		theView.getTabsPane().getMaintainPanel().addfindCategoryComboBoxListener(new FindCategoryComboBoxListener());
		
		
		//populate left side 
		update();
		/********************************************/
		
		
		
		
		
		theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
		theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		theView.getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());
		//categoryComboBox populate form DB
		populateCategoryReservPanel();

	}

	/*class MaintanLeftComboBoxCategory implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			update();
			System.out.println("left clicked");
		}
		
	}*/


	public void populateCategoryReservPanel(){
		try{
			categories=theModel.getMySomeCategories(); //i deleted this method from model, i can replace with proper one using query
		theView.getTabsPane().getReservationPanel().setComboBoxCategoryModel(categories);
		}catch(Exception e){
			System.out.println("data base is empty");
		}
		
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

	//Gets string form bomboBox
	class SelectCategorycomboBoxListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String comboBox1=theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
		System.out.println(comboBox1);
		/*
		 * We can create Sub-Category here 
		 */
		
	}
		
	}
	
	class SelectCategoryToEditcomboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox2= theView.getTabsPane().getMaintainPanel().getSelectCategoryToEditcomboBox().getSelectedItem().toString();
			System.out.println(comboBox2);
			/*
			 * We can now get category name from database
			 * populate text area to edit name of subcategory 
			 */
		}
		
	}
	class FindCategoryComboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String comboBox3= theView.getTabsPane().getMaintainPanel().getFindCategoryComboBox().getSelectedItem().toString();
			System.out.println(comboBox3);
			
		}
		
	}
	
	
	
	
	
	
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
