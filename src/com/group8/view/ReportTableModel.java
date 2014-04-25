package com.group8.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.group8.model.Sale;

@SuppressWarnings("serial")
public class ReportTableModel extends AbstractTableModel{

	private List<Sale> db;
	private String[] titles= {"Sale  Date","Total Price","Account name"}; //set column headers


	public ReportTableModel()
	{	
		db=new ArrayList<Sale>(); //use arrayList in the constructor 
	}

	public void setTableModel(List<Sale>db){
		this.db=db;
	}

	@Override
	public int getColumnCount() { //number of columns

		return 3;
	}

	@Override
	public int getRowCount() {

		return db.size(); //number of rows equal with number of sale objects in list
	}
	public String getColumnName(int column) {
		return titles[column];      //set column headers names
	}

	@Override
	public Object getValueAt(int row, int column) {  //set values of cells

		Sale sale=db.get(row);

		switch(column)
		{
		case 0:	
			return sale.getDate();  //put date
		case 1:
			return sale.getTotalPrice(); //put price
		case 2:
			return sale.getName(); //put name
		}
		return null;
	}

}
