package com.group8.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.group8.model.Item;

public class ReportTableModel extends AbstractTableModel{

	private List<Item> db;
	private String[] columnNames= {"Id","Brand","Model","Stock Level","Available Stock Level","Price",};
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
