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
public class CheckoutTableModel extends AbstractTableModel {
	
	private ArrayList<CheckoutItem> checkoutItems;
	
	private String[] columnNames= {"Id","Brand","Model","Price","Quantity","Total Price"};
	
	public CheckoutTableModel()
	{	
		checkoutItems= new ArrayList<CheckoutItem>();
	}
	//method used by ItemTableModel in the ReservationPanel class
	public void setTableModel(List<Item>db, ArrayList<Integer>quantity){
		for(int i = 0 ; i<db.size() ; i++)
		{
			checkoutItems.add(new CheckoutItem().init(db.get(i).getItemID(), db.get(i).getBrand(), db.get(i).getModel(), db.get(i).getPrice(), quantity.get(i)));
		}
	}
	public int getRowCount() {
		return checkoutItems.size();
	}
	public int getColumnCount() {
		return 6;
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
	
	/*public void setValueAt(List<Item> value, int row, int col) {
	    db= value;
	    fireTableCellUpdated(row, col);
	}*/
	class CheckoutItem{
		String  brand, model;
		double price;
		double totalprice;
		int id, quantity;
		public CheckoutItem init(int id, String brand, String model, double price, int quantity)
		{
			this.id=id;
			this.brand=brand;
			this.model=model;
			this.price=price;
			this.quantity=quantity;
			totalprice = price*((double) quantity);
			return this;
		}
		
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
