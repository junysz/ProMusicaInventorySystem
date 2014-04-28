package com.group8.view;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.group8.model.Item;
/*
 * 
 * 
 */
@SuppressWarnings("serial")
public class ReportPopupTableModel extends AbstractTableModel {
	
	private List<Item> db;
	private List<Integer> db2;
	private String[] columnNames= {"Brand","Model","Quantity sold",};
	
	public ReportPopupTableModel()
	{	
		db=new ArrayList<>();
		db2=new ArrayList<>();		
	}
	//method used by ItemTableModel in the ReservationPanel class
	public void setTableModel(List<Item>db,List<Integer> db2){
		this.db=db;
		this.db2=db2;
	}
	public int getColumnCount() {
		return 3;
	}
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public Object getValueAt(int row, int column) {
		
		Item item =db.get(row);
		int i=db2.get(row);
		
		switch(column)
		{
			case 0:	
			return item.getBrand();
		case 1:
			return item.getModel();
		case 2:
			return i;
		}
		return null;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return db.size();
	}
	
}
