package com.group8.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

public class PopupSaleDialog extends JDialog {

	private static final long serialVersionUID = -1595401629009100854L;
	private JTable saleItemsTable = new JTable();
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
		scrollPane.scrollRectToVisible(getBounds());
		scrollPane.setBounds(10, 119, 975, 300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);
        
        saleItemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(saleItemsTable);
		getContentPane().add(scrollPane, "cell 0 0,grow");
		setSize(440, 440);
		
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
		btnGoBack.setIcon(new ImageIcon(PopupSaleDialog.class.getResource("/com/group8/images/goBack.png")));
		btnGoBack.setToolTipText("Go Back");
		panel.add(btnGoBack);
		
		btnCompleteSale = new JButton("Complete Sale");
		btnCompleteSale.setIcon(new ImageIcon(PopupSaleDialog.class.getResource("/com/group8/images/ok.png")));
		btnCompleteSale.setToolTipText("Complete Sale");
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
	public void addTableModelListener(PropertyChangeListener tml)
	{
		saleItemsTable.addPropertyChangeListener(tml);;
	}
	public void setTotal(double total)
	{
        DecimalFormat df = new DecimalFormat("#.##");
		totalTF.setText(""+df.format(total));
		
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
