package com.group8.view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;

public class centerTab extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 545801329319373101L;
	/**
	 * Create the panel.
	 */
	public centerTab(Component c) {
		setLayout(new BorderLayout(3, 3));
		add(c, BorderLayout.CENTER);
	}

}

