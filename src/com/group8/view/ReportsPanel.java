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
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Cursor;


@SuppressWarnings("serial")
public class ReportsPanel extends JPanel {
	private JTable tableReport;   
	private ReportTableModel 	ReportTableModel;
	private 	JButton btnReport;	
	JDateChooser date1;
	private JDateChooser date2;
	private JLabel date1Label,date2Label;
	private JButton logoutButton;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton CheckSelected;

	public ReportsPanel() {
		setAlignmentX(23.0f);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setLayout(new MigLayout("", "[95.00][24.00][][][grow][][][grow][][][][grow][][][grow][][]", "[][][][][][][][34.00][][][100px:250:400px,grow][90][1.00]"));

		date1Label = new JLabel("Start Date");
		date1Label.setFont(new Font("Cambria", Font.BOLD, 13));
		add(date1Label, "cell 2 6");

		date1 = new JDateChooser();
		add(date1, "cell 3 6,grow");

		date2Label = new JLabel("End Date");
		date2Label.setFont(new Font("Cambria", Font.BOLD, 13));
		add(date2Label, "cell 5 6");

		date2 = new JDateChooser();
		add(date2, "cell 6 6,grow");

		btnReport = new JButton("Get report");  //Button to generate the reports
		btnReport.setFont(new Font("Cambria", Font.BOLD, 13));
		add(btnReport, "cell 12 6");



		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(SystemColor.text);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 2, true));
		add(scrollPane, "cell 1 10 12 1,grow");

		ReportTableModel= new ReportTableModel();
		tableReport = new JTable();
		tableReport.setForeground(Color.BLACK);
		tableReport.setBackground(SystemColor.text);
		tableReport.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableReport.setModel(ReportTableModel);//set model for table

		scrollPane.setViewportView(tableReport);
		
		CheckSelected = new JButton("Check selected sale");
		CheckSelected.setFont(new Font("Cambria", Font.BOLD, 13));
		add(CheckSelected, "cell 13 10 4 1");
		
		btnNewButton_1 = new JButton("Print");
		btnNewButton_1.setFont(new Font("Cambria", Font.BOLD, 13));
		add(btnNewButton_1, "cell 5 11");
		
		btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 13));
		add(btnNewButton, "cell 6 11");

		logoutButton = new JButton("Logout");
		logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		logoutButton.setForeground(Color.BLACK);
		logoutButton.setFont(new Font("Cambria", Font.BOLD, 13));
		add(logoutButton, "cell 15 11,aligny bottom");



	}
	public void addTableListener(ActionListener listenForBtnReport)
	{
		btnReport.addActionListener(listenForBtnReport);
	}
   
	public void CheckSelectedListener	(ActionListener listenForBtnReport)
	{
		CheckSelected.addActionListener(listenForBtnReport);
	}

	/*
	 * I want to set Table Model
	 * This will come from Model
	 * Model doesn't know about view
	 * So the controller will set this. 
	 */
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setTableModel(List listFormController)
	{
		ReportTableModel.fireTableDataChanged();
		System.out.println("Setting table model...");
		ReportTableModel.setTableModel(listFormController);
		tableReport.setModel(ReportTableModel);
		

	}
	public int getIndexRow()
	{
		return tableReport.getSelectedRow();
	}
	public JButton getlogoutButton()
	{
		return logoutButton;
	}

	public Date getDate1()   //get first date from the JDateChooser
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

	public void logoutButtonListener(ActionListener listenFor)
	{
		logoutButton.addActionListener(listenFor);
	}
	public void warnDateNull(){

		JOptionPane.showMessageDialog(null,
				"Please make a selection for the dates.",
				"Warning",
				JOptionPane.WARNING_MESSAGE);

	}

	//method to warn the user if first date is after the second date
	public void warnDateAfter(){
		JOptionPane.showMessageDialog(null,
				"Your first date is after the second one!.",
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	public JButton getLogoutButton() {
		return logoutButton;
	}
}




