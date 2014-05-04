package com.group8.view;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class ReportsPanel extends JPanel {
	private JTable tableReport;   
	private ReportTableModel 	ReportTableModel;
	private 	JButton btnReport;	
	JDateChooser date1;
	private JDateChooser date2;
	private JLabel date1Label,date2Label;
	private JButton logoutButton;
	private JButton printButton;
	private JButton CheckSelected;;  
	private PopupReports popup;
	private JButton saveButton;

	public ReportsPanel() {
		
		
		popup = new PopupReports();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		popup.setLocation(dim.width/2-popup.getSize().width/2, dim.height/2-popup.getSize().height/2);
		popup.setModal(true);
		setAlignmentX(23.0f);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setLayout(new MigLayout("", "[95.00][24.00][][][][grow][][][][grow][][][][][][][][][][][47.00][grow][][][grow][][]", "[][][][][][][][34.00][][][100px:250:400px,grow][90][1.00]"));

		date1Label = new JLabel("Start Date");
		date1Label.setFont(new Font("Cambria", Font.BOLD, 13));
		add(date1Label, "cell 2 6");

		date1 = new JDateChooser();
		add(date1, "cell 3 6,grow");

		date2Label = new JLabel("End Date");
		date2Label.setFont(new Font("Cambria", Font.BOLD, 13));
		add(date2Label, "cell 6 6");

		date2 = new JDateChooser();
		add(date2, "cell 7 6,grow");

		btnReport = new JButton("Get sales");  //Button to generate the reports
		btnReport.setIcon(new ImageIcon("/Users/pawel/Copy/iMacProjectEclipse/ProMusicaInventorySystem/resources/Search.png"));
		btnReport.setFont(new Font("Cambria", Font.BOLD, 13));
		add(btnReport, "cell 20 6 2 1,growx");
		



		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(SystemColor.text);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 2, true));
		add(scrollPane, "cell 1 10 22 1,grow");

		ReportTableModel= new ReportTableModel();
		tableReport = new JTable();
		tableReport.getTableHeader().setFont( new Font( "Cambria" , Font.BOLD, 14 ));
		tableReport.setForeground(Color.BLACK);
		tableReport.setBackground(SystemColor.text);
		tableReport.setFont(new Font("Cambria", Font.BOLD, 13));
		tableReport.setModel(ReportTableModel);//set model for table

		scrollPane.setViewportView(tableReport);
		
	
		printButton = new JButton("Print report");
		printButton.setFont(new Font("Cambria", Font.BOLD, 13));
		printButton.setEnabled(false);
		add(printButton, "cell 3 11");
		
		CheckSelected = new JButton("View sale items");
		CheckSelected.setFont(new Font("Cambria", Font.BOLD, 13));
		add(CheckSelected, "cell 7 11");
		CheckSelected.setEnabled(false);
		
		saveButton = new JButton("Save report");
		saveButton.setFont(new Font("Cambria", Font.BOLD, 13));
		saveButton.setEnabled(false);
		add(saveButton, "cell 19 11 2 1");

		logoutButton = new JButton("Logout");
		logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		logoutButton.setForeground(Color.BLACK);
		logoutButton.setFont(new Font("Cambria", Font.BOLD, 13));
		add(logoutButton, "cell 25 11,aligny bottom");
        
       
       
		
	}
	    /* ************************************************
	     *     ADD LISTENERS
	     **************************************************/
	public void logoutButtonListener(ActionListener listenFor)
	{
		logoutButton.addActionListener(listenFor); //logout button listener
	}
	 
	public void addTableListener(ActionListener listenForBtnReport)
	{
		btnReport.addActionListener(listenForBtnReport); //Report buton listener
	}
	public void printListener(ActionListener listen)
	{
		printButton.addActionListener(listen);  //print button listener
	}
	public void saveListener(ActionListener listen)
	{
		saveButton.addActionListener(listen);  //save button listener
	}
	public void CheckSelectedListener	(ActionListener listenForBtnReport)
	{
		CheckSelected.addActionListener(listenForBtnReport);  //check selected sale button listener
	}

	
	/*********************
	 *    GETTERS
	 *********************/
	public JButton getLogoutButton() {
		return logoutButton;
	}
	public JButton getCheckReport() {
		return CheckSelected;
	}
	public JButton getprintButton() {
		return printButton;
	}
	public JButton getsaveButton() {
		return saveButton;
	}
	public PopupReports getPopup() {
		return popup;
	}
	public void setPopup(PopupReports popup) {
		this.popup = popup;
	}
	
	 public JTable getRableReport()
	 {
		 return tableReport;
	 }			
	
	public int getIndexRow()
	{
		return tableReport.getSelectedRow();
	}
	public JButton getlogoutButton()
	{
		return logoutButton;
	}
	 //get first date from the JDateChooser
	public Date getDate1()  
	{
		try 
		{
			java.util.Date tempDate= date1.getDate();  
			//need to convert from java.util to java.sql	
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
			//need to convert from java.util to java.sql	
			java.sql.Date sqlDate = new java.sql.Date(tempDate.getTime()); 		
			return  sqlDate;	
		}
		catch (Exception io) {
			return null;
		}  
	}

  //set table model 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setTableModel(List listFormController)
	{
		ReportTableModel.fireTableDataChanged();
		System.out.println("Setting table model...");
		ReportTableModel.setTableModel(listFormController);
		tableReport.setModel(ReportTableModel);
		

	}
	//method to warn the user if first date is after the second date
	public void warnDateAfter(){
		JOptionPane.showMessageDialog(null,
				"Your first date is after the second one!.",
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}
	//warn if a date is not selected
	public void warnDateNull(){

		JOptionPane.showMessageDialog(null,
				"Please make a selection for the dates.",
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}
	
	//set dates to null after a report generated
	public void clearDates()
	{
		
		 date1.setDate(null);
		 date2.setDate(null);
	}
}
























