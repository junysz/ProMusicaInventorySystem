package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.group8.model.MainModel;
import com.group8.view.MainFrame;

class MCeditSubCategoryCB1 implements ActionListener{
	
	private MainFrame theView;  	
	private MainModel theModel;		
	
	
	
	public MCeditSubCategoryCB1(MainFrame theView, MainModel theModel) {
		
		this.theView=theView;
		this.theModel=theModel;
		theView.getTabsPane().getMaintainPanel().addfindCatForSubCatToEditComboBoxListener(this);
	}

	//Gets string from bomboBox
	@Override
	public void actionPerformed(ActionEvent e) {
		String comboBox3= theView.getTabsPane().getMaintainPanel().getFindCategoryComboBox().getSelectedItem().toString();
		theView.getTabsPane().getMaintainPanel().setSubCategoryModels(theModel.getSubCategories(comboBox3));
	}
}



