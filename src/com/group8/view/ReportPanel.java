package com.group8.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ReportPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ReportPanel() {
		setLayout(new MigLayout("", "[][grow][][grow][][][][][][][][][][93.00][][42.00][111.00][][][][][][][][][][][][][][][][][][][][]", "[][][][49.00][][][][][][grow][]"));
		
		JLabel lblNewLabel = new JLabel("Start Date");
		add(lblNewLabel, "cell 3 6");
		
		JDateChooser dateChooser = new JDateChooser();
		add(dateChooser, "cell 5 6");
		
		JLabel lblNewLabel_1 = new JLabel("End date");
		add(lblNewLabel_1, "cell 7 6,aligny baseline");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		add(dateChooser_1, "cell 9 6,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 9 23 1,grow");
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
			
		
		
		

	}

}
