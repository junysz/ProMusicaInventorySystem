package com.group8.view;



import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import com.group8.model.Item;

public class MakeSalePanel extends JPanel {
	private static final long serialVersionUID = -4601644025125134230L;
	private JTextField searchTF;
	private JTable table;
	private JPanel filterByPanel;
	private JLabel lblSelectCategory;
	private JComboBox<String> selectCategoryCB;
	private JLabel lblSelectSubcategory;
	private JComboBox<String> selectSubCategoryCB;
	private JLabel lblSearchWord;
	private JButton btnCheckout;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnAddToCart;
	private JButton btnClearCart;
	/**
	 * Create the panel.
	 */
	public MakeSalePanel() {
		setLayout(new MigLayout("", "[grow]", "[][51.00][grow][grow]"));
		
		filterByPanel = new JPanel();
		filterByPanel.setBorder(new TitledBorder(null, "Filter Results By:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(filterByPanel, "cell 0 1,grow");
		filterByPanel.setLayout(new MigLayout("", "[grow][grow][][]", "[][][]"));
		
		lblSelectCategory = new JLabel("Select Category:");
		filterByPanel.add(lblSelectCategory, "flowy,cell 0 0,alignx trailing");
		
		selectCategoryCB = new JComboBox<String>(new CategoryComboBoxModel());
		filterByPanel.add(selectCategoryCB, "cell 0 0,growx");
		
		lblSelectSubcategory = new JLabel("Select SubCategory:");
		filterByPanel.add(lblSelectSubcategory, "flowy,cell 1 0");
		
		selectSubCategoryCB = new JComboBox<String>(new CategoryComboBoxModel());
		filterByPanel.add(selectSubCategoryCB, "cell 1 0,growx");
		
		lblSearchWord = new JLabel("Search Word:");
		filterByPanel.add(lblSearchWord, "flowy,cell 2 0");
		
		searchTF = new JTextField();
		filterByPanel.add(searchTF, "cell 2 0");
		searchTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchTF.setColumns(15);
		
		btnCheckout = new JButton("Checkout");
		filterByPanel.add(btnCheckout, "cell 3 0,grow");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 2,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		add(panel, "cell 0 3,alignx right,growy");
		
		btnAddToCart = new JButton("Add to Cart");
		panel.add(btnAddToCart);
		
		btnClearCart = new JButton("Clear Cart");
		panel.add(btnClearCart);

	}
	public void addCheckoutButtonListener(ActionListener checkoutButtonListener)
	{
		btnCheckout.addActionListener(checkoutButtonListener);
	}
	
	public void addClearCartButtonListener(ActionListener clearCartButtonListener)
	{
		btnClearCart.addActionListener(clearCartButtonListener);
	}
	
	public void addAddToCartButtonListener(ActionListener addToCartButtonListener)
	{
		btnAddToCart.addActionListener(addToCartButtonListener);
	}
	public void addCategoryBoxListener(ActionListener categoryBoxListener)
	{
		selectCategoryCB.addActionListener(categoryBoxListener);
	}
	public void addSubCategoryListener(ActionListener subCatListener)
	{
		selectSubCategoryCB.addActionListener(subCatListener);
	}
	public JTextField getSearchTF() {
		return searchTF;
	}
	public void setSearchTF(JTextField searchTF) {
		this.searchTF = searchTF;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JComboBox<String> getSelectCategoryCB() {
		return selectCategoryCB;
	}
	public void setSelectCategoryCB(JComboBox<String> categoryCB) {
		this.selectCategoryCB = categoryCB;
	}
	public JComboBox<String> getSelectSubCategoryCB() {
		return selectSubCategoryCB;
	}
	public void setSelectSubCategoryCB(JComboBox<String> selectSubCategoryCB) {
		this.selectSubCategoryCB = selectSubCategoryCB;
	}
	public JButton getBtnCheckout() {
		return btnCheckout;
	}
	public void setBtnCheckout(JButton btnCheckout) {
		this.btnCheckout = btnCheckout;
	}
	public JButton getBtnAddToCart() {
		return btnAddToCart;
	}
	public void setBtnAddToCart(JButton btnAddToCart) {
		this.btnAddToCart = btnAddToCart;
	}
	public JButton getBtnClearCart() {
		return btnClearCart;
	}
	public void setBtnClearCart(JButton btnClearCart) {
		this.btnClearCart = btnClearCart;
	}
	public void setSelectCategoryCBModel(CategoryComboBoxModel c, ArrayList<String> a)
	{
		c.setComboBoxList(a);
		selectCategoryCB.setModel(c);
	}
	public boolean setSelectSubCategoryCBModel(CategoryComboBoxModel c, ArrayList<String> a)
	{
		c.setComboBoxList(a);
		selectSubCategoryCB.setModel(c);
		return true;
	}
	public void setTableModel(ItemTableModel iTM, List<Item> db)
	{
		iTM.setTableModel(db);;
		table.setModel(iTM);
	}
	public void addKeyListenerToSerchTextBox(KeyListener k)
	{
		searchTF.addKeyListener(k);
	}
}
