package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable.PrintMode;
import com.group8.model.Item;
import com.group8.model.MainModel;
import com.group8.model.Sale;
import com.group8.view.MainFrame;


public class ReportsController {
	
	private Controller controller;
	ArrayList<Sale> saleList=new ArrayList <Sale>();	
	private ArrayList<Item> itemsSold = new ArrayList<Item>();
	private ArrayList<Integer> listq = new ArrayList<Integer>();
	int index=-1,index1; double total=0; String name="";
	java.sql.Date date1,date2;

	public ReportsController(Controller controller){
		this.controller=controller;
		getView().getTabsPane().getReportPanel().addTableListener(new PopulateTable2Listener());
		getView().getTabsPane().getReportPanel().logoutButtonListener(new logoutButtonListener());
		getView().getTabsPane().getReportPanel().CheckSelectedListener(new CheckSelectedListener());
		getView().getTabsPane().getReportPanel().printListener(new buttonPrinter());
		getView().getTabsPane().getReportPanel().getPopup().printListener2(new buttonItemsPrinter());
		getView().getTabsPane().getReportPanel().getPopup().okButtonListener(new okButtonListener());
		getView().getTabsPane().getReportPanel().saveListener(new saveButtonListener());
	}


	class PopulateTable2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			getView().getTabsPane().getReportPanel().getCheckReport().setEnabled(true);	 
			getView().getTabsPane().getReportPanel().getsaveButton().setEnabled(true);	
			getView().getTabsPane().getReportPanel().getprintButton().setEnabled(true);
			
			System.out.println("Updating the table...");
			 date1=  getView().getTabsPane().getReportPanel().getDate1();//get first date from the ReportPanel
			 date2=	getView().getTabsPane().getReportPanel().getDate2();	//get the second date			
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
			getView().getTabsPane().getReportPanel().clearDates();	
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
	
	class okButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				getView().getTabsPane().getReportPanel().getPopup().dispose();

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
	if (index>-1)   {
		               total=saleList.get(index).getTotalPrice();
                       //a temporary list to hold all the Id-s of the sold items
	                   ArrayList<Integer> tempList=new ArrayList<Integer>();
	                    index1=saleList.get(index).getSaleID();  //index1 holds the saleId of the sale selected in the table	
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
	  }
		

	class saveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			getView().getTabsPane().getReportPanel().getRableReport();
			        try {
			            File file = new File("Report from "+date1.toString()+" to "+date2.toString());
			            PrintWriter os = new PrintWriter(file);
			            os.println("");
			            for (int col = 0; col < getView().getTabsPane().getReportPanel().getRableReport().getColumnCount(); col++) {
			                os.print(getView().getTabsPane().getReportPanel().getRableReport().getColumnName(col) + "   \t\t");
			               
			            }

			            os.println("");
			            os.println("");

			            for (int i = 0; i <getView().getTabsPane().getReportPanel().getRableReport().getRowCount(); i++) {
			                for (int j = 0; j < getView().getTabsPane().getReportPanel().getRableReport().getColumnCount(); j++) {
			                    os.print(getView().getTabsPane().getReportPanel().getRableReport().getValueAt(i, j).toString() + "\t\t\t");

			                }
			                os.println("");
			            }
			            os.close();
			            JOptionPane.showMessageDialog(null,
			    				"Report  saved to file ",
			    				"Done",
			    				JOptionPane.WARNING_MESSAGE);
			        } catch (IOException f) {
			            // TODO Auto-generated catch block
			           
			}
		}
	}
	
	
		public MainFrame getView(){
		return controller.getView();
	}
	public MainModel getModel(){
		return controller.getModel();
	}
	
	
	
	
	public class buttonPrinter implements ActionListener {
				 
	    public void actionPerformed(ActionEvent e) {
	    	
	    	 MessageFormat header = new MessageFormat("Sales from: "+date1.toString()+" to "+date2.toString() );
	    	 MessageFormat footer = new MessageFormat("Page - {0}");
	        
	    	try {
	    		 
    	
	    	    boolean complete = getView().getTabsPane().getReportPanel().getRableReport().print(PrintMode.FIT_WIDTH,header,footer);
	    	    if (complete) {
	    	       
	    	       
	    	    } else {
	    	    	JOptionPane.showMessageDialog(null,
		    				"Printing aborted by user",
		    				"Warning",
		    				JOptionPane.WARNING_MESSAGE);
	    	     
	    	    }
	    	} catch (PrinterException pe) {
	    		JOptionPane.showMessageDialog(null,
	    				"Printing failed check if printer is connected.",
	    				"Warning",
	    				JOptionPane.WARNING_MESSAGE);
	    	 
	    	}
	    }
		
	} 
	    public class buttonItemsPrinter implements ActionListener {
			 
		    public void actionPerformed(ActionEvent e) {
		    	
		    		    	
		    	int row=getView().getTabsPane().getReportPanel().getRableReport().getSelectedRow();
		    	 total=saleList.get(row).getTotalPrice();
		    	 getView().getTabsPane().getReportPanel().getPopup().setTotal(total);	
		    	
		    	 getView().getTabsPane().getReportPanel().getPopup().dispose();
		    	 MessageFormat header = new MessageFormat("Sale made by: "+saleList.get(row).getName()+"   Date: "+saleList.get(row).getDate().toString());   	 
		    	 MessageFormat footer = new MessageFormat("Page - {0}");
		    	try {
		    	   	
		    	    boolean complete = getView().getTabsPane().getReportPanel().getPopup().getTable().print(PrintMode.FIT_WIDTH,header,footer);
		    	    if (complete) {
		    	       
		    	       
		    	    } else {
		    	    	JOptionPane.showMessageDialog(null,
			    				"Printing aborted by user",
			    				"Warning",
			    				JOptionPane.WARNING_MESSAGE); 
		    	     
		    	    }
		    	} catch (PrinterException pe) {
		    		JOptionPane.showMessageDialog(null,
		    				"Printing failed check if printer is connected.",
		    				"Warning",
		    				JOptionPane.WARNING_MESSAGE);
		    	 
		    	}
		    }
		}
	}
	
		


