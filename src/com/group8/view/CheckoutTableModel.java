package com.group8.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.group8.model.CheckoutItem;
import com.group8.model.Item;
import com.group8.model.Sale;

public class CheckoutTableModel extends DefaultTableModel {
	
	private String[] columnNames= {"Brand","Model","Price","Quantity","Total Price"};
	private List<Integer> id;
	private List<CheckoutItem> basketItems;
	private static final long serialVersionUID = 7944308974044321712L;

	
	public CheckoutTableModel()
	{
		id=new ArrayList<>();
		basketItems=new ArrayList<>();
	}
	public CheckoutTableModel(List<Item>db, ArrayList<Integer>quantity)
	{
		id=new ArrayList<>();
		basketItems=new ArrayList<>();
		for (int i = 0 ; i < db.size() ; i++)
		{
			basketItems.add(new CheckoutItem(db.get(i).getBrand(), db.get(i).getModel(), db.get(i).getPrice(), quantity.get(i)));
		}
	}
	public void setValueAt(Object value, int row, int column)
	{
		switch(column)
		{
		case 0:	
			basketItems.get(row).setBrand((String)value);
			break;
		case 1:
			basketItems.get(row).setModel((String)value);
			break;
		case 2:
			basketItems.get(row).setPrice((double)value);
			break;
		case 3:
			basketItems.get(row).setQuantity((int)value);
			break;
		case 4:
			basketItems.get(row).setTotalPrice((double)value);
			break;
		}
	}
	public int getRowCount() {
		if(basketItems==null)
		{
			return 0;
		}
		else
		return basketItems.size();
	}
	public int getColumnCount() {
		return 5;
	}
	public String getColumnName(int column) {
		return columnNames[column];
	}
	public Object getValueAt(int row, int column) {  //set values of cells
		switch(column)
		{
		case 0:	
			return basketItems.get(row).getBrand();
		case 1:
			return basketItems.get(row).getModel();
		case 2:
			return basketItems.get(row).getPrice();
		case 3:
			return basketItems.get(row).getQuantity();
		case 4:
			return basketItems.get(row).getTotalPrice();
		}
		return null;
	}
	public void setTableModel(List<Item>db, List<Integer>quantities){
		id=new ArrayList<>();
		basketItems=new ArrayList<>();
		for (int i = 0 ; i < db.size() ; i++)
		{
			basketItems.add(new CheckoutItem(db.get(i).getBrand(), db.get(i).getModel(), db.get(i).getPrice(), quantities.get(i)));
		}
	}
		public boolean isCellEditable(int row,int column)  
        {
			switch(column){             
        
           case 0:  // select the cell you want make it not editable 
             return false; 
           case 1:  // select the cell you want make it not editable 
               return false;
           case 2:
        	   return true;
           case 3:
        	   return true;
           case 4:
        	   return false;
         default: return false;}  
        }
		@SuppressWarnings("unchecked")
		public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
		
	
}
