package com.group8.controller;

import java.sql.SQLException;

import javax.swing.JComboBox;

import com.group8.model.Category;
import com.group8.model.Testing_Data_Base_Class;
import com.group8.view.CategoryFormEvent;
import com.group8.view.CategoryListener;
import com.group8.view.MaintainPanel;
import com.group8.view.Testing_MaintainPanel;
import com.mysql.jdbc.PreparedStatement;

public class Controller implements CategoryListener {
	
	private Testing_MaintainPanel tMp;   //this is like MainFrame for now >>Testing
	private Category categoryTheModel;
	private Testing_Data_Base_Class dbCategory = new Testing_Data_Base_Class();
	
	
	private MaintainPanel maintainPanel;
	
	
	public Controller(Testing_MaintainPanel tMp ){
		this.tMp=tMp;
	}

	//this is method implemented from CategoryListener Interface 
	//Category object is passed form view 
	//??we can pass it to the model from here??
	public void categoryAddedPerformed(CategoryFormEvent catFormEvent) 
	{	
		System.out.println(catFormEvent.getName());
		
		//dbCategory.getCategoryList().add(new Category(catFormEvent.getName()));
		//dbCategory.printCategory();
		
	
		
		populateComboBoxCategory();
	}
	
	
	public void populateComboBoxCategory()
	{
		try {
			dbCategory.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbCategory.initializeCBox();
		JComboBox jcb=new JComboBox();
		for(int i=0;i<dbCategory.getCategoryList().size();i++)
		{
			
		
			System.out.println(dbCategory.getCategoryList().get(i).getCategoryName());
			maintainPanel= new MaintainPanel();
			
			
			
			
			jcb.addItem(dbCategory.getCategoryList().get(i).getCategoryName());
			
			
		}
		maintainPanel.setSelectCategoryToEditcomboBox();
	}
	
	
	
}
