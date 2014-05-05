package com.group8.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
	//list used for populating the table in popup 
	private ArrayList<Item> itemsSold = new ArrayList<Item>();	
	//list to hold quantities for items sold
	private ArrayList<Integer> listq = new ArrayList<Integer>();
	int index=-1; //index of the row 
	int index1; //index of sale id from the list retrieved from table
	double total=0; //total of a sale
	String name=""; //name is used to hold the account name 
	java.sql.Date date1,date2;   //used to hold he two dates for sales

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

    /*************************************
     * 
     * INNER CLASSES for LISTENERS
     *
     ************************************/
	
	
	//listener class for generating the table in Reports tab
	class PopulateTable2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
            //enable the buttons 
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
				
				if (date1.getTime()>=date2.getTime()-86000000)
				{
					getView().getTabsPane().getReportPanel().warnDateAfter();//if first date after second warn user
				}
				else if(date1.getTime()+86400000>Calendar.getInstance().getTimeInMillis())
				{
					JOptionPane.showMessageDialog(getView().getTabsPane().getReportPanel(), "You can't select dates from the future!", "Wrong dates selected!", 2);
				}
			}
			else 
			{  getView().getTabsPane().getReportPanel().warnDateNull(); //if any date null warn user
			}
			getView().getTabsPane().getReportPanel().clearDates();	
		}
	}

	 //listener class for loggin out in Reports tab
	class logoutButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				//method logout from the view
				getView().logout();

			}catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}
	
	//listener class for closing the popup in Reports tab
	class okButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				//dispose of the popup dialog
				getView().getTabsPane().getReportPanel().getPopup().dispose();

			}catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}
	
	//listener class for viewing the items of a sale
	class CheckSelectedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
									
					listq.clear();//empty list for quantities
					calculateItems(); //calculate what should be put in the table for popup
					if (index>-1)   //if a row is selected in the table in report panel
					{
					//set table model for popup with the two  lists								
					getView().getTabsPane().getReportPanel().getPopup().setTableModel(itemsSold,listq);
					//set the total textfield in popup
					getView().getTabsPane().getReportPanel().getPopup().setTotal(total);
					getView().getTabsPane().getReportPanel().getPopup().setAlwaysOnTop(true);
					// make popup visible
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
	System.out.println("Selected index is: " + index);
	//Getting the total for the specified sale
	if (index>-1)   {
		               // get total to be displayed
		               total=saleList.get(index).getTotalPrice();
                       //a temporary list to hold two lists: item sold IDs and quantities related
		               ArrayList<ArrayList<Integer>> tempList2=new ArrayList<ArrayList<Integer>>();
		               //list to hold the item ID-s
		               ArrayList<Integer> tempList=new ArrayList<Integer>();
		               
	                    index1=saleList.get(index).getSaleID();  //index1 holds the saleId of the sale selected in the table	
	                    
                        tempList2=getModel().getItemsSold(index1);// //get the two lists  from database
                        tempList=tempList2.get(0); // get the array of item id-s 
                        listq=tempList2.get(1);      //get the quantitities                                   												 
		           					    	
	
						//Clear the list of items to be displayed before constructing another model 
					     itemsSold.clear();
					   
						//Adding items to the list to be put in the table model
						   for (int i=0;i<tempList.size();i++)
						    {
								int ID=tempList.get(i);
							    Item item=getModel().getItemByID(ID);
								itemsSold.add(item);
					    						   }
	  	  	}
	  }
		
    // Listener for saving the reports 
	class saveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
          			
			        try {
			        	//declare new file to write to
			            File file = new File("Report from "+date1.toString()+" to "+date2.toString());
			            //declaring printer to file
			            PrintWriter os = new PrintWriter(file);
			            os.println("");
			            os.println("");
			            //get table column names and print them to file
			            for (int col = 0; col < getView().getTabsPane().getReportPanel().getRableReport().getColumnCount(); col++)
			            {
			                os.print(getView().getTabsPane().getReportPanel().getRableReport().getColumnName(col) + "   \t\t");			               
			            }
			            os.println("");
			            os.println("");
                       //write values to file
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
			        	 JOptionPane.showMessageDialog(null,
				    				"Failed to save to  file ",
				    				"Warning",
				    				JOptionPane.WARNING_MESSAGE);   			           
			}
		}
	}
	
	
		public MainFrame getView()
		{
		return controller.getView();
	    }
		
    	public MainModel getModel()
	    {
		return controller.getModel();
	   }
	
		
	//class for listening the print button in Report tab
	public class buttonPrinter implements ActionListener {
				 
	    public void actionPerformed(ActionEvent e) {
	    	 //declaring header to be printed
	    	 MessageFormat header = new MessageFormat("Sales from: "+date1.toString()+" to "+date2.toString() );
	    	 //declare footer
	    	 MessageFormat footer = new MessageFormat("Page - {0}");
	        
	    	try {
	    		 
    	    //print the table from the Report tab 
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
       //listener class for printer button inside the popup in report tab	
	    public class buttonItemsPrinter implements ActionListener {
			 
		    public void actionPerformed(ActionEvent e) {
		    	
		    	//get the row selected in table in report tab	    	
		    	int row=getView().getTabsPane().getReportPanel().getRableReport().getSelectedRow();
		    	 total=saleList.get(row).getTotalPrice();
		    	 getView().getTabsPane().getReportPanel().getPopup().setTotal(total);	
		    	//dispose of the popup
		    	 getView().getTabsPane().getReportPanel().getPopup().dispose();
		    	 //declare the header
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
	
		


