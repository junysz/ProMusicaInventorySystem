package com.group8.view;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.group8.model.CheckoutItem;
import com.group8.model.Item;
/*
 * This is wrapper class that sets model  the table form REserveationPanel class 
 * 
 */
@SuppressWarnings("serial")
public class CheckoutTableModel extends DefaultTableModel {
	
	private List<CheckoutItem> checkoutItems;
	private boolean[][] editable_cells;
	private String[] columnNames= {"Id","Brand","Model","Price","Quantity","Total Price"};
	
	public CheckoutTableModel(List<Item>db, ArrayList<Integer>quantity){

        this.editable_cells = new boolean[db.size()][6];
		checkoutItems= new ArrayList<>();
		for(int i = 0 ; i<db.size() ; i++)
		{
			checkoutItems.add(new CheckoutItem(db.get(i).getItemID(), db.get(i).getBrand(), db.get(i).getModel(), db.get(i).getPrice(), quantity.get(i)));
			editable_cells[i][5] = true; // set cell true/false
	        fireTableCellUpdated(i, 5);
			}

	}

	public void setTableModel(List<Item>db, ArrayList<Integer>quantity){
		for(int i = 0 ; i<db.size() ; i++)
		{
			checkoutItems.add(new CheckoutItem(db.get(i).getItemID(), db.get(i).getBrand(), db.get(i).getModel(), db.get(i).getPrice(), quantity.get(i)));

		}
	}
	public int getRowCount() {
		if(checkoutItems!=null)
			return checkoutItems.size();
		else
			return 0;
	}
	public int getColumnCount() {
		return 6;
	}
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public void setValueAt(List<CheckoutItem> value, int row, int col) {
	    checkoutItems = value;
	    fireTableCellUpdated(row, col);
	}


	@Override
public Object getValueAt(int row, int column) {
		
		CheckoutItem item =checkoutItems.get(row);
		
		switch(column)
		{
		case 0:
			return item.getItemID();	
		case 1:	
			return item.getBrand();
		case 2:
			return item.getModel();
		case 3:
			return item.getPrice();
		case 4:
			return item.getQuantity();
		case 5:	
			return item.getTotalPrice();
		}
		return null;
	}
}
