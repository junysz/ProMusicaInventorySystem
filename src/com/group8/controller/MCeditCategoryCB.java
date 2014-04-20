package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.group8.model.MainModel;
import com.group8.view.MainFrame;

class MCeditCategoryCB implements ActionListener{
	
	private MainFrame theView;  	
	private MainModel theModel;		
	
	public MCeditCategoryCB(MainFrame theView, MainModel theModel){
		this.theView=theView;
		this.theModel=theModel;
		
		theView.getTabsPane().getMaintainPanel().addselectCategoryToEditcomboBoxListener(this);
	}
	
	//Responsible for listening on the Select Category Combo-Box(EDIT CATEGORY) 
	@Override
	public void actionPerformed(ActionEvent e) {
		String comboBox2= theView.getTabsPane().getMaintainPanel().getSelectCategoryToEditcomboBox().getSelectedItem().toString();
		System.out.println(comboBox2);
		/*
		 * We can now get category name from database
		 * populate text area to edit name of subcategory 
		 */
		//GET CATEGORY FORM DB TO EDIT
		ArrayList<String> cat= new ArrayList<>();
		cat=theModel.getCategoryNames();
		String getToTextField=null;
		for(int i=0;i<cat.size();i++){
			getToTextField=cat.get(i);
			if(getToTextField.equalsIgnoreCase(comboBox2)){
				break;
			}
		}
		theView.getTabsPane().getMaintainPanel().setEditCategoryNameTF(getToTextField);
	}
}