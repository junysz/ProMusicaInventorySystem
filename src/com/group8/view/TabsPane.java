package com.group8.view;

import javax.swing.JTabbedPane;
/*
 * 
 *  RELEVANT INFORMATION ON HOW TO USE TABBED PANES IN THE LINK BELOW:
 *   http://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
 */
@SuppressWarnings("serial")
public class TabsPane extends JTabbedPane {

	private ReservationPanel reservationPanel;
	private MaintainPanel maintainPanel;
	private MakeSalePanel makeSalePanel;
	private ReportPanel reportsPanel;

	public TabsPane() {
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		makeSalePanel = new MakeSalePanel();
		reservationPanel=new ReservationPanel();
		maintainPanel= new MaintainPanel();
		reportsPanel=new ReportPanel();
		
		add("Sale",makeSalePanel);
		add("Reservation",reservationPanel);
		add("Maintenance",maintainPanel);
		add("Reports",reportsPanel);
		

	}
	
	
	//pass ReservationPanel to main
	public ReservationPanel getReservationPanel()
	{	
		return reservationPanel;	
	}
	public MaintainPanel getMaintainPanel(){
		return maintainPanel;
	}


	public void setCategoryListenr(CategoryListener categoryListenr) {
		maintainPanel.setCategoryListenr(categoryListenr);
		
	}
	
	
	
	//Reference for the Reports Panel
	public ReportPanel getReportPanel(){
		
		return reportsPanel;
	}
	
	public MakeSalePanel getMakeSalePanel()
	{
		return makeSalePanel;
	}
	
}

