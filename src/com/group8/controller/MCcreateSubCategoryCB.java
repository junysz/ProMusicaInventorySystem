package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.group8.model.MainModel;
import com.group8.view.MainFrame;

class MCcreateSubCategoryCB implements ActionListener{
	private MainFrame theView;
	private MainModel theModel;
	public MCcreateSubCategoryCB(MainFrame theView, MainModel theModel){
		this.theView=theView;
		this.theModel=theModel;
		
		theView.getTabsPane().getMaintainPanel().addSelectCategoryForSubCatComboBoxListener(this);
	}
	//Class Responsible for listening to the Select Category Combo Box on the Create SubCategory Panel
	@Override
	public void actionPerformed(ActionEvent e) {
		theView.getTabsPane().getMaintainPanel().getSelectCategorycomboBox().getSelectedItem().toString();
	}


}
