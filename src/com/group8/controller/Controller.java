package com.group8.controller;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import com.group8.model.*;
import com.group8.view.CategoryComboBoxModel;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.ItemTableModel;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;
import com.group8.view.ReservationPanel;
import com.group8.view.SalesPanel;

public class Controller implements CategoryListener{

	private MainFrame theView;  	
	private MainModel theModel;	
	protected SalesController salesController;
	protected CategoriesController categoriesController;
	protected ItemsController itemsController;
	protected AccountsController accountsController;
	protected ReservationController reserveController;
	protected ReportsController reportController;

	public Controller(MainFrame theView, MainModel theModel )
	{
		this.theView=theView;
		this.theModel=theModel;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		theView.setLocation(dim.width/2-theView.getSize().width/2, dim.height/2-theView.getSize().height/2);
		/***************SalesPanel******************/
		salesController= new SalesController(this);
		/*******************Login Panel******** */
		theView.addLoginListener(new LoginController(theView, theModel));
		theView.setCategoryListener(this);
		/*******************Maintain Panel*/
		theView.getTabsPane().getMaintainPanel().getEditAccountTypeComboBox().setSelectedIndex(-1);
		categoriesController= new CategoriesController(this);
		itemsController= new ItemsController(this);
		accountsController= new AccountsController(this);		
		update();//comboBoxes
		updteAccounts();		
		/*******************RESERVE PANEL*****************/ 
		reserveController= new ReservationController(this);
		/*******************REPORT PANEL*****************/ 		 
		reportController= new ReportsController(this);
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
		getSalesPanel().setSelectCategoryCBModel(new CategoryComboBoxModel(),getModel().getCategoryNames());
		getSalesPanel().getSelectCategoryCB().setSelectedIndex(-1);
		getSalesPanel().getTable().setModel(new ItemTableModel());
		getReservationPanel().setSelectCategoryCBModel(new CategoryComboBoxModel(),getModel().getCategoryNames());
		getReservationPanel().getSelectCategoryCBox().setSelectedIndex(-1);
		getReservationPanel().setTableModel(new ArrayList<>());;
		getReservationPanel().setSelectCategoryCBModel(new CategoryComboBoxModel(), getModel().getCategoryNames());
	}

	public MainFrame getView(){
		return theView;
	}
	public MainModel getModel(){
		return theModel;
	}

	public MaintainPanel getMaintainPanel(){
		return theView.getTabsPane().getMaintainPanel();
	}
	public SalesPanel getSalesPanel(){
		return theView.getTabsPane().getMakeSalePanel();
	}
	public ReservationPanel getReservationPanel(){
		return getView().getTabsPane().getReservationPanel();
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
