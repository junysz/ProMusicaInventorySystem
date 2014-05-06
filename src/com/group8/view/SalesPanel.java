package com.group8.view;



import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import com.group8.model.Item;

import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.ImageIcon;

public class SalesPanel extends JPanel {
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
	private JButton logoutButton;
	private PopupSaleDialog pSale;
	/**
	 * Create the panel and initialize the panel.
	 */
	public SalesPanel() {
		setLayout(new MigLayout("", "[grow][]", "[][51.00][grow][grow]"));
		setpSale(new PopupSaleDialog());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		pSale.setLocation(dim.width/2-pSale.getSize().width/2, dim.height/2-pSale.getSize().height/2);
		pSale.setModal(true);
		filterByPanel = new JPanel();
		filterByPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Filter Results By:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		add(filterByPanel, "cell 0 1,grow");
		filterByPanel.setLayout(new MigLayout("", "[grow][grow][][]", "[][][]"));
		
		lblSelectCategory = new JLabel("Select Category");
		lblSelectCategory.setFont(new Font("Cambria", Font.BOLD, 13));
		filterByPanel.add(lblSelectCategory, "flowy,cell 0 0,alignx left,growy");
		
		selectCategoryCB = new JComboBox<String>(new CategoryComboBoxModel());
		filterByPanel.add(selectCategoryCB, "cell 0 0,growx");
		
		lblSelectSubcategory = new JLabel("Select SubCategory");
		lblSelectSubcategory.setFont(new Font("Cambria", Font.BOLD, 13));
		filterByPanel.add(lblSelectSubcategory, "flowy,cell 1 0");
		
		selectSubCategoryCB = new JComboBox<String>(new CategoryComboBoxModel());
		filterByPanel.add(selectSubCategoryCB, "cell 1 0,growx");
		
		lblSearchWord = new JLabel("or Search by keyword");
		lblSearchWord.setFont(new Font("Cambria", Font.BOLD, 13));
		filterByPanel.add(lblSearchWord, "flowy,cell 2 0");
		
		searchTF = new JTextField();
		filterByPanel.add(searchTF, "cell 2 0");
		searchTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchTF.setColumns(15);
		
		btnCheckout = new JButton("");
		btnCheckout.setIcon(new ImageIcon(SalesPanel.class.getResource("/com/group8/images/checkout.png")));
		btnCheckout.setToolTipText("Checkout");
		btnCheckout.setFont(new Font("Cambria", Font.BOLD, 13));
		filterByPanel.add(btnCheckout, "flowx,cell 3 0,grow");
		
		btnAddToCart = new JButton("");
		filterByPanel.add(btnAddToCart, "cell 3 0,grow");
		btnAddToCart.setIcon(new ImageIcon(SalesPanel.class.getResource("/com/group8/images/addToCart.png")));
		btnAddToCart.setToolTipText("Add to Cart");
		btnAddToCart.setFont(new Font("Cambria", Font.BOLD, 13));
		
		btnClearCart = new JButton("");
		filterByPanel.add(btnClearCart, "cell 3 0,grow");
		btnClearCart.setIcon(new ImageIcon(SalesPanel.class.getResource("/com/group8/images/clearCart.png")));
		btnClearCart.setToolTipText("Clear Cart");
		btnClearCart.setFont(new Font("Cambria", Font.BOLD, 13));
		btnClearCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 2,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		add(panel, "flowx,cell 0 3,alignx right,growy");
		
		logoutButton = new JButton("");
		logoutButton.setIcon(new ImageIcon(SalesPanel.class.getResource("/com/group8/images/logout.png")));
		logoutButton.setToolTipText("Logout");
		logoutButton.setFont(new Font("Cambria", Font.BOLD, 13));
		add(logoutButton, "cell 1 3,alignx right");
		disableCartButtons();

	}
	
	
	/*
	 * Methods for adding listeners to the objets
	 */
	public void addLogoutButtonListener(ActionListener checkoutButtonListener)
	{
		logoutButton.addActionListener(checkoutButtonListener);
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
	/*
	 * THe method below returns string from searchTF text box
	 */
	public JTextField getSearchTF() {
		return searchTF;
	}
	/*
	 * This method sets the text in the search text box
	 */
	public void setSearchTF(JTextField searchTF) {
		this.searchTF = searchTF;
	}
	/*
	 * Setter and getter for the table
	 */
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	/*
	 * setter and getter for sales panel combo boxes
	 */
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
	/*
	 * Getters and setters for the buttons
	 */
	public JButton getBtnCheckout() {
		return btnCheckout;
	}
	public JButton getLogoutButton() {
		return logoutButton;
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
	/*
	 * Methods for setting models on table and combo boxes
	 */
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
	public PopupSaleDialog getpSale() {
		return pSale;
	}
	public void setpSale(PopupSaleDialog pSale) {
		this.pSale = pSale;
	}
	public void disableCartButtons()
	{
		btnCheckout.setEnabled(false);
		btnClearCart.setEnabled(false);
	}
	public void enableCartButtons()
	{
		btnCheckout.setEnabled(true);
		btnClearCart.setEnabled(true);
	}
}
