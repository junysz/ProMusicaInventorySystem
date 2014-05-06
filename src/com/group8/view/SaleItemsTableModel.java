package com.group8.view;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.group8.model.Item;
/*
 * This is wrapper class that sets model  the table form PopupSaleDialog class 
 * 
 */
public class SaleItemsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -264997902854670078L;
	private List<Item> db;
	private String[] columnNames= {"ID","Brand","Model","Quantity","Price",};
	
	public SaleItemsTableModel()
	{	
		db=new ArrayList<>();
	}
	/*
	 * method used by ItemTableModel in the ReservationPanel class
	 */
	public void setTableModel(List<Item>db){
		this.db=db;
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 * returns number of rows in the table excluding headers
	 */
	public int getRowCount() {
		return db.size();
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 * returns number of columns
	 */
	public int getColumnCount() {
		return 4;
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 * returns column name at index "column"
	 */
	public String getColumnName(int column) {
		return columnNames[column];
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 * returns value of specific cell at location (row, column)
	 */
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
	
	}
