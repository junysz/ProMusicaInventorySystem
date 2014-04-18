package com.group8.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.group8.model.Item;
import com.group8.model.Sale;

public class ReportTableModel extends AbstractTableModel{

	private List<Sale> db;
	private String[] titles= {"SaleID","Sale  Date","Total Price","Account name"};
	
	
	
	
	
	public ReportTableModel()
	{	
		db=new ArrayList<Sale>();
	}
	
	public void setTableModel(List<Sale>db){
		this.db=db;
	}

	@Override
	public int getColumnCount() {
		
		return 4;
	}

	@Override
	public int getRowCount() {
	
		return db.size();
	}
	 public String getColumnName(int column) {
	        return titles[column];
	    }

	@Override
public Object getValueAt(int row, int column) {
		
		Sale sale=db.get(row);
		
		switch(column)
		{
		case 0:
			return sale.getSaleID();	
		case 1:	
			return sale.getDate();
		case 2:
			return sale.getTotalPrice();
		case 3:
			return sale.getAccountID();
		
		}
		return null;
	}
  
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, row, column);
	
	}	
	
}
