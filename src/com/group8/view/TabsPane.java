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
	private StockBrowserPanel stockBrowsingPanel;

	public TabsPane() {
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		stockBrowsingPanel = new StockBrowserPanel();
		reservationPanel=new ReservationPanel();
		maintainPanel= new MaintainPanel();
		
		add("Stock Browsing",stockBrowsingPanel);
		add("Reservation",reservationPanel);
		add("Maintenance",maintainPanel);
		

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
}

