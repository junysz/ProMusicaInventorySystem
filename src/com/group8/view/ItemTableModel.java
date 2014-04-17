package com.group8.view;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.group8.model.Item;
/*
 * This is wrapper class that sets model  the table form REserveationPanel class 
 * 
 */
@SuppressWarnings("serial")
public class ItemTableModel extends AbstractTableModel {
	
	private List<Item> db;
	private String[] columnNames= {"Id","Brand","Model","Stock Level","Available Stock Level","Price",};
	
	public ItemTableModel()
	{	
		db=new ArrayList<>();
	}
	//method used by ItemTableModel in the ReservationPanel class
	public void setTableModel(List<Item>db){
		this.db=db;
	}
	public int getRowCount() {
		return db.size();
	}
	public int getColumnCount() {
		return 6;
	}
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public Object getValueAt(int row, int column) {
		
		Item item =db.get(row);
		
		switch(column)
		{
		case 0:
			return item.getItemID();	
		case 1:	
			return item.getBrand();
		case 2:
			return item.getModel();
		case 3:
			return item.getStockLevel();
		case 4:
			return item.getAvailableStockLevel();
		case 5:
			return item.getPrice();	 
		}
		return null;
	}
	
	//this will update tableModel with new data
	/*@Override
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, row, column);
	}*/
	
	/*public void setValueAt(List<Item> value, int row, int col) {
	    db= value;
	    fireTableCellUpdated(row, col);
	}*/
}
