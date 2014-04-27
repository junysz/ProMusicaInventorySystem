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
	private List<Integer> id;
	private boolean[][] editable_cells;
	private String[] columnNames= {"Brand","Model","Price","Quantity","Total Price"};
	
	public CheckoutTableModel(List<Item>db, ArrayList<Integer>quantity){
		id = new ArrayList<>();
        this.editable_cells = new boolean[db.size()][6];
		checkoutItems= new ArrayList<>();
		for(int i = 0 ; i<db.size() ; i++)
		{
			id.add(i, db.get(i).getItemID());
			checkoutItems.add(new CheckoutItem(db.get(i).getBrand(), db.get(i).getModel(), db.get(i).getPrice(), quantity.get(i)));
			editable_cells[i][2] = true; // set cell true/false
	        fireTableCellUpdated(i, 2);
			editable_cells[i][3] = true; // set cell true/false
	        fireTableCellUpdated(i, 3);
			}

	}

	public void setTableModel(List<Item>db, ArrayList<Integer>quantity){
		for(int i = 0 ; i<db.size() ; i++)
		{
			checkoutItems.add(new CheckoutItem(db.get(i).getBrand(), db.get(i).getModel(), db.get(i).getPrice(), quantity.get(i)));

		}
	}
	public int getRowCount() {
		if(checkoutItems!=null)
			return checkoutItems.size();
		else
			return 0;
	}
	public int getColumnCount() {
		return 5;
	}
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	
	//this will update tableModel with new data
	/*@Override
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, row, column);
	}*/
	
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
			return item.getBrand();
		case 1:	
			return item.getModel();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getTotalPrice();
		}
		return null;
	}
}
