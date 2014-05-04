package com.group8.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.JDialog;
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

public class PopupReports extends JDialog {

	private static final long serialVersionUID = -1595401629009100854L;
	private JTable saleItemsTable;
	private JPanel panel;
	private JLabel lblTotalSaleCost;
	private JTextField totalTF;
	ReportPopupTableModel	PopupTableModel;
	private JButton okButton;
	private JButton printItemsButton;
	public PopupReports() {

		getContentPane().setLayout(new MigLayout("", "[][grow][]", "[grow][][grow]"));
		setSize(500,400);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.scrollRectToVisible(getBounds());
		getContentPane().add(scrollPane, "cell 1 1,grow");
		
		saleItemsTable = new JTable();
		saleItemsTable.getTableHeader().setFont( new Font( "Cambria" , Font.BOLD, 14 ));
		saleItemsTable.setFont(new Font("Cambria", Font.BOLD, 13));
		saleItemsTable.setCellSelectionEnabled(true);
		saleItemsTable.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(saleItemsTable);
		
		lblTotalSaleCost = new JLabel("Total \u20AC");
		getContentPane().add(lblTotalSaleCost, "flowx,cell 1 2");
		lblTotalSaleCost.setFont(new Font("Cambria", Font.BOLD, 13));
		
		totalTF = new JTextField();
		getContentPane().add(totalTF, "cell 1 2");
		totalTF.setText("00.00");
		totalTF.setEditable(false);
		totalTF.setColumns(5);
		
		panel = new JPanel();
		getContentPane().add(panel, "cell 1 2,growx,aligny center");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		printItemsButton = new JButton("Print items");
		panel.add(printItemsButton);
		
		okButton = new JButton("OK");
		getContentPane().add(okButton, "cell 1 2");
		okButton.setFont(new Font("Cambria", Font.BOLD, 13));
		PopupTableModel= new ReportPopupTableModel();
		// TODO Auto-generated constructor stub
	}
	//set the model for table in popup
	 public void setTableModel(List list1,List list2)
	   {   		
		PopupTableModel.setTableModel(list1,list2);
		saleItemsTable.setModel(PopupTableModel);
		PopupTableModel.fireTableDataChanged();
		}
	
	public void okButtonListener(ActionListener listenFor)
	{
		okButton.addActionListener(listenFor);
	}
	public void printListener2(ActionListener listen)
	{
		printItemsButton.addActionListener(listen);
	}
	
	public void setTotal(double total)
	{
		totalTF.setText(""+total);
		
	}
	public JTable getTable()
	{
		return saleItemsTable;
	}

}