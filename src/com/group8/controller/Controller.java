package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.group8.model.*;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;

public class Controller implements CategoryListener{
	
	private MainFrame theView;  	
	private MainModel theModel;	
	protected CategoriesController categoriesController;
	protected ItemsController itemsController;
	protected AccountsController accountsController;
	protected ReserveController reserveController;
	protected ReportController reportController;





	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;
		/*******************Login Panel******** */
		theView.addLoginListener(new LoginController(theView, theModel));
		theView.setCategoryListener(this);
		/*******************Maintain Panel*/
		theView.getTabsPane().getMaintainPanel().getEditAccountTypeComboBox().setSelectedIndex(-1);
		categoriesController= new CategoriesController(theView, theModel);
		itemsController= new ItemsController(theView, theModel);
		accountsController= new AccountsController(theView, theModel);		
		update();//comboBoxes
		updteAccounts();		
		/*******************RESERVE PANEL*****************/ 
		reserveController= new ReserveController(theView, theModel);
		/*******************REPORT PANEL*****************/ 		 
		reportController= new ReportController(theView, theModel);
	}






	//Inner Class that listens for the Create Account Button
	class RemoveItemBtn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String itemSelection=null;
			//read the values (itemSelection) from the view
			try{
				itemSelection=theView.getTabsPane().getMaintainPanel().getItemToEditComboBox().getSelectedItem().toString();
			}catch(Exception ex){

			}
			//Now validate the data and add errors to errorMessages
			ArrayList<String> errorMessages = new ArrayList<String>();
			if(itemSelection==null){
				errorMessages.add("Select Item");
			}

			if(errorMessages.isEmpty()){

				System.out.println("Remove Item successful");
				//Now that data processing is complete, clear the GUI form
				theView.getTabsPane().getMaintainPanel().clearEditItemForm();
			}
			else{
				theView.getTabsPane().getMaintainPanel().warnEditItemFormErrors(errorMessages);
			}
		}
	}





	public void categoryAddedPerformed(CategoryFormEvent catFormEvent) 
	{	
		boolean flagExist=false;
		String newCategoryName=catFormEvent.getName();
		ArrayList<String>listCategories= new ArrayList<>();
		listCategories=theModel.getCategoryNames();

		for(int i=0;i<listCategories.size();i++){

			String copmapareCat=listCategories.get(i);
			if(copmapareCat.equalsIgnoreCase(newCategoryName)){
				flagExist=true;
				break;
			}
		}
		if(newCategoryName.isEmpty()){
			theView.getTabsPane().getMaintainPanel().warnCategoryFieldEmpty();

		}
		else if(flagExist){
			theView.getTabsPane().getMaintainPanel().warnCategoryExist();
			theView.getTabsPane().getMaintainPanel().clearNewCategoryForm();
		}
		else{
			System.out.println("I am adding new category to dataBase: "+catFormEvent.getName());
			Category c = new Category(catFormEvent.getName());
			theModel.addNewCategory(c);
			//update dataBase and other comboBoxes

			//update MainTainPanel
			update();
		}
		theView.getTabsPane().getMaintainPanel().clearNewCategoryForm();
	}




	//MaintainPanel: populates all ComboBoxes:SelectCategory
	public void update() {

		//sets the model for all the category combo boxes in maintain panel
		theView.getTabsPane().getMaintainPanel().setCategoryModels(theModel.getCategoryNames());	
		getMaintainPanel().clearNewSubCatForm();
	}

	public MaintainPanel getMaintainPanel(){
		return theView.getTabsPane().getMaintainPanel();
	}

	public void updteAccounts(){
		ArrayList<String>accountNames= new ArrayList<String>();

		for(int i=0;i<theModel.getAllAccounts().size();i++){

			String accN=theModel.getAllAccounts().get(i).getAccountName();
			accountNames.add(accN);
			System.out.println(accN);
		}

		theView.getTabsPane().getMaintainPanel().setAccountModel(accountNames);
	}
}
