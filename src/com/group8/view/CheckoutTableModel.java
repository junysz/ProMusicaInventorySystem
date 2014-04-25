package com.group8.view;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
<<<<<<< HEAD

=======
import javax.swing.table.DefaultTableModel;

import com.group8.model.CheckoutItem;
>>>>>>> 11a4d58f2e333db85011611addf9e951bc1f2dae
import com.group8.model.Item;
/*
 * This is wrapper class that sets model  the table form REserveationPanel class 
 * 
 */
@SuppressWarnings("serial")
<<<<<<< HEAD
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
=======
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
>>>>>>> 11a4d58f2e333db85011611addf9e951bc1f2dae
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
	
<<<<<<< HEAD
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
		
=======
	public void setValueAt(List<CheckoutItem> value, int row, int col) {
	    checkoutItems = value;
	    fireTableCellUpdated(row, col);
>>>>>>> 11a4d58f2e333db85011611addf9e951bc1f2dae
	}


	@Override
<<<<<<< HEAD
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
=======
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
>>>>>>> 11a4d58f2e333db85011611addf9e951bc1f2dae
		return null;
	}
}
