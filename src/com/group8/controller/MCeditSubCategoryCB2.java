package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.group8.model.MainModel;
import com.group8.view.MainFrame;

class MCeditSubCategoryCB2 implements ActionListener{

	private MainFrame theView;  	
	private MainModel theModel;	
	
	public MCeditSubCategoryCB2(MainFrame theView, MainModel theModel) {
		this.theView=theView;
		this.theModel=theModel;
		
		theView.getTabsPane().getMaintainPanel().addEditSubCategory(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String subCategoryEdit= theView.getTabsPane().getMaintainPanel().getSelectSubCatToEditComboBox().getSelectedItem().toString();
		List<String> subCat= new ArrayList<>();
		subCat=theModel.getSubCategoryNames();
		String setSubCatTF=null;
		for(int i=0;i<subCat.size();i++){
			setSubCatTF=subCat.get(i);
			if(setSubCatTF.equalsIgnoreCase(subCategoryEdit)){
				break;
			}
		}
		theView.getTabsPane().getMaintainPanel().setSelectSubCatToEditComboBox(subCategoryEdit);
	}
}