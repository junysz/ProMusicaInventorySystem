package com.group8.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Label;
import javax.swing.JButton;


public class ReportPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ReportPanel() {
		setLayout(new MigLayout("", "[24.00][][][grow][][][grow][][][][grow][][][][][][][][][][][][][][][][][][][][][][][]", "[][][][][][][34.00][][][grow][]"));
		
		Label label = new Label("Start Date");
		add(label, "cell 1 5");
		
		JDateChooser dateChooser = new JDateChooser();
		add(dateChooser, "cell 2 5,grow");
		
		Label label_1 = new Label("End Date");
		add(label_1, "cell 4 5");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		add(dateChooser_1, "cell 5 5,grow");
		
		JButton btnNewButton = new JButton("Get report");
		add(btnNewButton, "cell 9 5");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 9 32 1,grow");
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
			
				

	}

}
