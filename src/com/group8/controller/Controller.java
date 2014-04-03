package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;


import com.group8.model.Item;
import com.group8.model.MainModel;
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

		theView.getTabsPane().getReservationPanel().addTableListener(new PopulateTableListener());
		theView.getTabsPane().getReservationPanel().addComboBoxCatListener(new ComboBoxListener());
		theView.getTabsPane().getReservationPanel().addComboBoxSubCatListener(new ComboBoxSubCatListener());
		//categoryComboBox populate form DB
		populateCategoryReservPanel();
	}


	public void populateCategoryReservPanel(){
		categories=theModel.getMySomeCategories();
		theView.getTabsPane().getReservationPanel().setComboBoxCategoryModel(categories);
	}


	class PopulateTableListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			temItemList=theModel.getMeSomeItems();
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





	/*this is method implemented from CategoryListener Interface 
	 *Category object is passed form view 
	 *??we can pass it to the model from here??
	 */
	public void categoryAddedPerformed(CategoryFormEvent catFormEvent) 
	{	
		if(catFormEvent.getName().isEmpty()){
			JOptionPane.showMessageDialog(
					null, "Please enter new Category: ");
		}
		else{
			System.out.println(catFormEvent.getName()+ "append new category to dataBase");
			//update dataBase and other comboBoxes
		}


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
