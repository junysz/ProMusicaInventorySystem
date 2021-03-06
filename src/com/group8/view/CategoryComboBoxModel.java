package com.group8.view;


import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;


public class CategoryComboBoxModel  implements ComboBoxModel<String> {

	  
	
	List<String>comboBoxList;
	
	Object selectedCategory;
	
	public CategoryComboBoxModel() {
		this.comboBoxList=new ArrayList<>();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return comboBoxList.size();
	}

	@Override
	public String getElementAt(int index) {
		
		return comboBoxList.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedItem(Object anItem) {
		if(anItem==null)
		{
			selectedCategory = new String("");
		}
		else
			selectedCategory=anItem.toString();
		
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return selectedCategory;
	}
	
	
	
	public void setComboBoxList(List<String>comboBoxList){
		this.comboBoxList=comboBoxList;
	}

}
