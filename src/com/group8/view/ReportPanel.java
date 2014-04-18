package com.group8.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import com.group8.model.Item;
import com.group8.model.Sale;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;


public class ReportPanel extends JPanel {
	private JTable tableReport;
	private ReportTableModel 	ReportTableModel;
	private 	JButton btnReport;	
	JDateChooser date1;
	private JDateChooser date2;
	private JLabel date1Label,date2Label;
	
	public ReportPanel() {
		setLayout(new MigLayout("", "[24.00][][][grow][][][grow][][][][grow][][][][][][][][][][][][][][][][][][][][][][][]", "[][][][][][][34.00][][][grow][]"));
		
	    date1Label = new JLabel("Start Date");
		add(date1Label, "cell 1 5");
		
	    date1 = new JDateChooser();
		add(date1, "cell 2 5,grow");
		
		date2Label = new JLabel("End Date");
		add(date2Label, "cell 4 5");
		
		 date2 = new JDateChooser();
		add(date2, "cell 5 5,grow");
		
	    btnReport = new JButton("Get report");
		add(btnReport, "cell 9 5");
	
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 9 32 1,grow");
		
		ReportTableModel= new ReportTableModel();
		tableReport = new JTable();		
		tableReport.setModel(ReportTableModel);
		
		scrollPane.setViewportView(tableReport);		
			
				

	}
	public void addTableListener(ActionListener listenForBtnReport)
	{
		btnReport.addActionListener(listenForBtnReport);
	}

	/*
	 * I want to set Table Model
	 * This will come from Model
	 * Model doesn't know about view
	 * So the controller will set this. 
	 */
	public void setTableModel(List<Sale>listFormController)
	{
		

		ReportTableModel.setTableModel(listFormController);
		tableReport.setModel(ReportTableModel);
		ReportTableModel.fireTableDataChanged();

	}
        
	public Date getDate1()
	{
		try 
		{
		java.util.Date tempDate= date1.getDate();
		@SuppressWarnings("deprecation")
		java.sql.Date sqlDate = new java.sql.Date(tempDate.getTime()); 		
	    return  sqlDate;	
		}
		catch (Exception io) {
			return null;
		}  
	}
	
	public Date  getDate2()
	{
	try
	       {
		java.util.Date tempDate= date2.getDate();
	    java.sql.Date sqlDate = new java.sql.Date(tempDate.getTime()); 		
	    return  sqlDate;	
	 }
	catch (Exception io) {
		return null;
	}  
	}
	
	public boolean getValid()
	{
	if (getDate1()==null || getDate2() ==null)
	{return false;}
	else return true;
	}
	
}
