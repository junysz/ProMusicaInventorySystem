package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.group8.model.MainModel;
import com.group8.model.Sale;
import com.group8.view.MainFrame;

public class ReportController {
	private MainFrame theView;  	
	private MainModel theModel;	

	public ReportController(MainFrame theView, MainModel theModel){
		this.theView=theView;
		this.theModel=theModel;
		theView.getTabsPane().getReportPanel().addTableListener(new PopulateTable2Listener());
		
    }
	
	class PopulateTable2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {



			ArrayList<Sale> saleList=new ArrayList <Sale>();		        		    

			java.sql.Date date1=  theView.getTabsPane().getReportPanel().getDate1();//get first date from the ReportPanel
			java.sql.Date date2=	theView.getTabsPane().getReportPanel().getDate2();	//get the second date			
			saleList=theModel.getSalesByDate(date1,date2); //query database for Sales between the two dates
			if (date1!=null && date2!=null)
			{
				theView.getTabsPane().getReportPanel().setTableModel(saleList);	//set the table if dates are not null
				if (date1.after(date2))
				{
					theView.getTabsPane().getReportPanel().warnDateAfter();//if first date after second warn user
				}
			}
			else 
			{  theView.getTabsPane().getReportPanel().warnDateNull(); //if any date null warn user
			}
		}
	}
	
	
}
