package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.group8.model.Item;
import com.group8.model.MainModel;
import com.group8.model.Sale;
import com.group8.view.MainFrame;


public class ReportsController {
	
	private Controller controller;
	ArrayList<Sale> saleList=new ArrayList <Sale>();	
	private ArrayList<Item> itemsSold = new ArrayList<Item>();
	private ArrayList<Integer> listq = new ArrayList<Integer>();
	int index=-1; double total=0; String name="";

	public ReportsController(Controller controller){
		this.controller=controller;

		getView().getTabsPane().getReportPanel().addTableListener(new PopulateTable2Listener());
		getView().getTabsPane().getReportPanel().logoutButtonListener(new logoutButtonListener());
		getView().getTabsPane().getReportPanel().CheckSelectedListener(new CheckSelectedListener());
		
	}


	class PopulateTable2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {


				        		    
			System.out.println("Updating the table...");
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
				
					
					listq.clear();
					calculateItems();
					if (index>-1)
					{
					System.out.println("Number of elements of list is:  "+listq.size());
			
					getView().getTabsPane().getReportPanel().getPopup().setTableModel(itemsSold,listq);
					getView().getTabsPane().getReportPanel().getPopup().setTotal(total);
					getView().getTabsPane().getReportPanel().getPopup().setAlwaysOnTop(true);
					getView().getTabsPane().getReportPanel().getPopup().setVisible(true);
					
					
					}		
					else JOptionPane.showMessageDialog(null,
							"Please select a row of table first",
							"Warning",
							JOptionPane.WARNING_MESSAGE);			
		           }
	          }
	
	//Calculate what items should be placed in the popup table
	public void calculateItems()
	{//getting the index of the selected row in the table of sales
	index=getView().getTabsPane().getReportPanel().getIndexRow();
	//Getting the total for the specified sale
	total=saleList.get(index).getTotalPrice();
  //a temporary list to hold all the Id-s of the sold items
	ArrayList<Integer> tempList=new ArrayList<Integer>();
	int index1=saleList.get(index).getSaleID();  //index1 holds the saleId of the sale selected in the table
	
     tempList=getModel().getItemsSold(index1);//get the array of id-s from quering the Sale table
    
     //a second temporary list to extract the unique Id-s of items
	ArrayList<Integer> tempList2=new ArrayList<Integer>();
	 
	
	for( int i=0;i<tempList.size();i++)
	    {
		int counter=1;
		tempList2.add(tempList.get(i));
		for (int j=i+1;j<tempList.size();j++)
			if (tempList.get(j)==tempList.get(i))				
												{  
													counter++;
													 tempList.remove(j);				
												}
	 
		 listq.add(counter);
		 
		}
	
	//Clear the list of items to be displayed before constructing another model 
     itemsSold.clear();
   
	//Adding items to the list to be put in the table model
	for (int i=0;i<tempList2.size();i++)
	{
	int ID=tempList2.get(i);
	Item item=getModel().getItemByID(ID);
	itemsSold.add(item);
    
	}
	  	  	}
	
		
	public MainFrame getView(){
		return controller.getView();
	}
	public MainModel getModel(){
		return controller.getModel();
	}

}
