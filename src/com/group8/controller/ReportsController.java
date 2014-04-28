package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.group8.controller.ReservationController.logoutButtonListener;
import com.group8.controller.SalesController.QuantityChangeListener;
import com.group8.model.Item;
import com.group8.model.MainModel;
import com.group8.model.Sale;
import com.group8.view.CheckoutTableModel;
import com.group8.view.MainFrame;
import com.group8.view.PopupReports;
import com.group8.view.PopupSaleDialog;
import com.group8.view.ReportPopupTableModel;

public class ReportsController {
	
	private Controller controller;  
	private PopupReports popup;
	ArrayList<Sale> saleList=new ArrayList <Sale>();	
	private ArrayList<Item> saleItems = new ArrayList<Item>();
	private ArrayList<Integer> listq = new ArrayList<Integer>();
	int index=-1; double total=0; String name="";

	public ReportsController(Controller controller){
		this.controller=controller;

		getView().getTabsPane().getReportPanel().addTableListener(new PopulateTable2Listener());
		getView().getTabsPane().getReportPanel().logoutButtonListener(new logoutButtonListener());
		getView().getTabsPane().getReportPanel().CheckSelectedListener(new CheckSelectedListener());
		popup = new PopupReports();
		
	}


	class PopulateTable2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {


				        		    

			java.sql.Date date1=  getView().getTabsPane().getReportPanel().getDate1();//get first date from the ReportPanel
			java.sql.Date date2=	getView().getTabsPane().getReportPanel().getDate2();	//get the second date			
			saleList=getModel().getSalesByDate(date1,date2); //query database for Sales between the two dates
			if (date1!=null && date2!=null)
			{
				getView().getTabsPane().getReportPanel().setTableModel(saleList);	//set the table if dates are not null
				
				if (date1.after(date2))
				{
					getView().getTabsPane().getReportPanel().warnDateAfter();//if first date after second warn user
				}
			}
			else 
			{  getView().getTabsPane().getReportPanel().warnDateNull(); //if any date null warn user
			}
		}
	}

	class logoutButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				getView().logout();

			}catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}
	
	class CheckSelectedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				
				{
					/*
					 * Make popup visible if it's not, and refresh table with the items
					 */
					if(!popup.isVisible())
					{
					popup.setAlwaysOnTop(true);
					popup.setVisible(true);
					
					}
					calculateItems();
					if (index>-1)
					{
					ReportPopupTableModel model=new ReportPopupTableModel();
					popup.setTableModel(saleItems,listq);
					popup.setTotal(total);
					popup.setName(name);
					
					}		
					else JOptionPane.showMessageDialog(null,
							"Please select a row of table first",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
					}

			}catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}
	
	public void calculateItems()
	{
	index=getView().getTabsPane().getReportPanel().getIndexRow();
	
	index=saleList.get(index).getSaleID();
	total=saleList.get(index).getTotalPrice();

	ArrayList<Integer> tempList=new ArrayList<Integer>();
     tempList=getModel().getItemsSold(index);
	ArrayList<Integer> tempList2=new ArrayList<Integer>();
	 
	
	for( int i=0;i<tempList.size();i++)
	    {
		int counter=1;
		for (int j=i+1;j<tempList.size();j++)
			if (tempList.get(j)==tempList.get(i))				
												{  
													counter++;
													tempList.remove(j);				
												}
	 	tempList2.add(tempList.get(i));
		 listq.add(counter);
		 
		}
	
	
	 
	 
	for (int i=0;i<tempList2.size();i++)
	{
	int ID=tempList2.get(i);
	System.out.println("EDD:  " +ID);
	Item item=getModel().getItemByID(ID);
	saleItems.add(item);
    
	}
	  	  	}
		
	public MainFrame getView(){
		return controller.getView();
	}
	public MainModel getModel(){
		return controller.getModel();
	}

}
