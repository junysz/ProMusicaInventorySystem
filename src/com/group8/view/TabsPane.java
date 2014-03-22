package com.group8.view;

import javax.swing.JPanel;


import javax.swing.JTabbedPane;
/*
 * 
 *  RELEVANT INFORMATION ON HOW TO USE TABBED PANES IN THE LINK BELOW:
 *   http://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
 */
public class TabsPane extends JTabbedPane {
	private static final long serialVersionUID = -1093584118198122580L;

	/**
	 * Create the panel.
	 */
	public TabsPane() {
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

	}

}
