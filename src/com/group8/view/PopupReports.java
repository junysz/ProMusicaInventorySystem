package com.group8.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PopupReports extends JFrame {

	private static final long serialVersionUID = -1595401629009100854L;
	private JTable saleItemsTable;
	private JPanel panel;
	private JLabel lblTotalSaleCost;
	private JTextField totalTF;
	ReportPopupTableModel	PopupTableModel;
	public PopupReports() {

		getContentPane().setLayout(new MigLayout("", "[][grow]", "[grow][grow]"));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.scrollRectToVisible(getBounds());
		getContentPane().add(scrollPane, "cell 1 0,grow");
		setSize(500,400);
		
		saleItemsTable = new JTable();
		saleItemsTable.setCellSelectionEnabled(true);
		saleItemsTable.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(saleItemsTable);
		
		panel = new JPanel();
		getContentPane().add(panel, "cell 1 1,grow");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblTotalSaleCost = new JLabel("Total  Sale: \u20AC");
		panel.add(lblTotalSaleCost);
		lblTotalSaleCost.setFont(new Font("Cambria", Font.BOLD, 13));
		
		totalTF = new JTextField();
		panel.add(totalTF);
		totalTF.setText("00.00");
		totalTF.setEditable(false);
		totalTF.setColumns(8);
		PopupTableModel= new ReportPopupTableModel();
		// TODO Auto-generated constructor stub
	}
	public void setSaleTableModel(TableModel m)
	{
		saleItemsTable.setModel(m);
		
	}
	public void setTableModel(List list1,List list2)
	{

		PopupTableModel.setTableModel(list1,list2);
		saleItemsTable.setModel(PopupTableModel);
		PopupTableModel.fireTableDataChanged();

	}
	public void addTableModelListener(TableModelListener tml)
	{
		saleItemsTable.getModel().addTableModelListener(tml);
	}
	public void setTotal(double total)
	{
		totalTF.setText(""+total);
		
	}
	

}