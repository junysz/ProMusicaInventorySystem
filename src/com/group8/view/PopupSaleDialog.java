package com.group8.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class PopupSaleDialog extends JFrame {

	private static final long serialVersionUID = -1595401629009100854L;
	private JTable saleItemsTable;
	private JPanel panel;
	private JButton btnGoBack;
	private JButton btnCompleteSale;
	private JLabel lblTotalSaleCost;
	private JTextField totalTF;

	public PopupSaleDialog() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.scrollRectToVisible(getBounds());
		getContentPane().add(scrollPane, "cell 0 0,grow");
		setSize(400, 400);
		
		saleItemsTable = new JTable();
		saleItemsTable.setCellSelectionEnabled(true);
		saleItemsTable.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(saleItemsTable);
		
		lblTotalSaleCost = new JLabel("Total sale cost: \u20AC");
		getContentPane().add(lblTotalSaleCost, "flowx,cell 0 1,alignx right");
		
		totalTF = new JTextField();
		totalTF.setText("00.00");
		totalTF.setEditable(false);
		getContentPane().add(totalTF, "cell 0 1,alignx right");
		totalTF.setColumns(8);
		
		panel = new JPanel();
		getContentPane().add(panel, "cell 0 1,alignx right,growy");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnGoBack = new JButton("Go Back");
		panel.add(btnGoBack);
		
		btnCompleteSale = new JButton("Complete Sale");
		getContentPane().add(btnCompleteSale, "cell 0 1");
		// TODO Auto-generated constructor stub
	}
	public void setSaleTableModel(TableModel m)
	{
		saleItemsTable.setModel(m);
		
	}
	public void addPopupButtonsListener(ActionListener a)
	{
		btnGoBack.addActionListener(a);
		btnCompleteSale.addActionListener(a);
	}
	public void addTableModelListener(TableModelListener tml)
	{
		saleItemsTable.getModel().addTableModelListener(tml);
	}
	public void setTotal(double total)
	{
		totalTF.setText(""+total);
		
	}
	public JButton getGoBckButton()
	{
		return btnGoBack;
	}
	public JButton getCompleteSaleButton()
	{
		return btnCompleteSale;
	}
	public JTable getCheckoutTable()
	{
		return saleItemsTable;
	}
	

}
