package com.group8.view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.group8.controller.Controller;
import com.group8.model.Item;
import com.group8.model.ReservedItem;
import com.group8.model.Sale;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ReservationPanel extends JPanel 
{
	List<Item>dbItem= new ArrayList<>();
	

	private JTextField searchKeywordTF;
	private JTextField docketNoTF;
	private JTextField brandModelTF;
	private JTextField currentDepositTF;
	private JTextField totalPriceTF;
	private JTextField updateDepositTF;

	JTabbedPane tabbedPane;
	JPanel findReservationPanel;



	/*
	 * JPanel makeNewReservation components 
	 */
	private ItemTableModel 			itemTableModel;
	private JTable 					tableItemsRevervation;
	private CategoryComboBoxModel 	categoryComboBoxModel;
	private JComboBox<String> 		selectCategoryCBox;
	private SubCatComboBoxModel  	subCatComboBoxModel;
	private JComboBox<String> 		selectSubcategoryCBox;
	
	private  JList<String> list;
    private DefaultListModel<String> model;


	private JPanel makeNewReservationPanel;
	private JLabel lblNewLabel;
	private JLabel lblSelectCategory;


	private JLabel lblSlecetSubcategory;
	private JLabel lblSearch;
	private JButton btnFindItems;
	private JScrollPane scrollPaneReservTable;

	private JPanel southPanelReservation;
	private JLabel lblEnterDocketNo;
	private JTextField enterDocketNoTF;
	private JLabel lblDeposit;
	private JTextField depositTF;
	private JLabel euroLabel;
	private JButton btnReserveItem;
	private JButton btnEndReservation;
	private JButton btnNewButton;
	private JLabel makeNewErrorLabel;



	public ReservationPanel() {

		setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);

		findReservationPanel = new JPanel();
		tabbedPane.addTab("Find", null, findReservationPanel, null);
		findReservationPanel.setLayout(new MigLayout("", "[][grow][][grow][grow][grow][grow]", "[][grow][grow]"));
        

		model = new DefaultListModel<String>();	
	    list = new JList<String>(model);		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(new TitledBorder("Docket Numbers"));
		

		findReservationPanel.add(scrollPane, "cell 1 1 1 2,grow");

		JPanel reservationDetailsPanel = new JPanel();
		findReservationPanel.add(reservationDetailsPanel, "cell 3 1 3 1,grow");
		reservationDetailsPanel.setLayout(new MigLayout("", "[][][][grow][grow][][][grow]", "[][][][][][][][][][][]"));

		reservationDetailsPanel.setBorder(new TitledBorder("Reservation Details"));

		JLabel lblDocketNo = new JLabel("Docket No:");
		reservationDetailsPanel.add(lblDocketNo, "cell 1 1,alignx right");

		docketNoTF = new JTextField();
		docketNoTF.setEditable(false);
		reservationDetailsPanel.add(docketNoTF, "cell 3 1,growx");
		docketNoTF.setColumns(10);

		JLabel lblCurrentDeposti = new JLabel("Current Deposit");
		reservationDetailsPanel.add(lblCurrentDeposti, "cell 5 1,alignx left");

		JLabel lblgfgfgfgf = new JLabel("\u20AC");
		reservationDetailsPanel.add(lblgfgfgfgf, "cell 6 1,alignx trailing");

		currentDepositTF = new JTextField();
		currentDepositTF.setEditable(false);
		reservationDetailsPanel.add(currentDepositTF, "cell 7 1,growx");
		currentDepositTF.setColumns(10);

		JLabel lblItem = new JLabel("Item:");
		reservationDetailsPanel.add(lblItem, "cell 1 2,alignx right");

		brandModelTF = new JTextField();
		brandModelTF.setEditable(false);
		reservationDetailsPanel.add(brandModelTF, "cell 3 2,growx");
		brandModelTF.setColumns(10);

		JLabel lblTotalPrice = new JLabel("Total Price");
		reservationDetailsPanel.add(lblTotalPrice, "cell 5 2,alignx right");

		JLabel label_1 = new JLabel("\u20AC");
		reservationDetailsPanel.add(label_1, "cell 6 2,alignx trailing");

		totalPriceTF = new JTextField();
		
		reservationDetailsPanel.add(totalPriceTF, "cell 7 2,growx");
		totalPriceTF.setColumns(10);
		totalPriceTF.setEditable(false);
		JLabel lblUpdateDeposit = new JLabel("Update Deposit");
		reservationDetailsPanel.add(lblUpdateDeposit, "cell 1 4");

		updateDepositTF = new JTextField();
		reservationDetailsPanel.add(updateDepositTF, "cell 3 4,growx");
		updateDepositTF.setColumns(10);

		JLabel lblMakereservationtable = new JLabel("\u20AC");
		lblMakereservationtable.setForeground(Color.GRAY);
		reservationDetailsPanel.add(lblMakereservationtable, "cell 4 4");

		JLabel resErrorLabel = new JLabel("");
		resErrorLabel.setForeground(Color.RED);
		reservationDetailsPanel.add(resErrorLabel, "cell 3 5");

	    btnNewButton = new JButton("Update Reservation");
		reservationDetailsPanel.add(btnNewButton, "cell 3 6");
		btnNewButton.setEnabled(false);

	    btnEndReservation = new JButton("End Reservation");
		reservationDetailsPanel.add(btnEndReservation, "cell 3 8");
	    btnEndReservation.setEnabled(false);


		/*
		 * MakeNewReservationPanel STUFF
		 */
		makeNewReservationPanel = new JPanel();
		tabbedPane.addTab("Make New", null, makeNewReservationPanel, null);
		makeNewReservationPanel.setLayout(new MigLayout("", "[106.00,grow][grow][][grow][][grow][]", "[][][530.00,grow][530.00]"));

		lblNewLabel = new JLabel("Find One Item to Reserve");
		lblSelectCategory = new JLabel("Select Category");


		lblSearch = new JLabel("Search by Keyword ");
		lblSlecetSubcategory = new JLabel("Select Sub-Category");
		searchKeywordTF = new JTextField();
		btnFindItems = new JButton("Find Items");
		//btnFindItems.addTableActionEvetn(TableModelListener tableModelListener); 
		searchKeywordTF.setColumns(10);

		scrollPaneReservTable = new JScrollPane();
		//instantiate ItemTableModel
		itemTableModel= new ItemTableModel();
		tableItemsRevervation = new JTable();
		tableItemsRevervation.setModel(itemTableModel);
		tableItemsRevervation.getColumnModel().getColumn(0).setMinWidth(0);
		tableItemsRevervation.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPaneReservTable.setViewportView(tableItemsRevervation);

		makeNewReservationPanel.add(lblNewLabel, "cell 0 0 2 1");

		/* 
		 * Creating models for comboBoxes
		 * Model classes are passed as parameters to combo-boxes
		 */
		categoryComboBoxModel=new CategoryComboBoxModel(); 
		selectCategoryCBox = new JComboBox<String>();
		selectCategoryCBox.setModel(categoryComboBoxModel);

		subCatComboBoxModel= new SubCatComboBoxModel();
		selectSubcategoryCBox = new JComboBox<String>();
		selectSubcategoryCBox.setModel(subCatComboBoxModel);



		makeNewReservationPanel.add(selectCategoryCBox, "flowx,cell 0 1,growx");
		makeNewReservationPanel.add(lblSelectCategory, "cell 0 1,alignx left");	
		makeNewReservationPanel.add(lblSlecetSubcategory, "cell 2 1");
		makeNewReservationPanel.add(selectSubcategoryCBox, "cell 3 1,growx");
		makeNewReservationPanel.add(lblSearch, "cell 4 1");
		makeNewReservationPanel.add(searchKeywordTF, "cell 5 1,growx");
		makeNewReservationPanel.add(btnFindItems, "cell 6 1");
		makeNewReservationPanel.add(scrollPaneReservTable, "cell 0 2 6 1,grow");
		/*
		 * SOUTH PANEL
		 */
		southPanelReservation = new JPanel();
		southPanelReservation.setLayout(new MigLayout("", "[][grow][grow][grow][][grow][grow]", "[][][]"));
		makeNewReservationPanel.add(southPanelReservation, "cell 0 3 6 1,grow");

		lblEnterDocketNo = new JLabel("Enter Docket No.");
		enterDocketNoTF = new JTextField();
		enterDocketNoTF.setColumns(10);
		lblDeposit = new JLabel("Deposit");
		depositTF = new JTextField();					
		depositTF.setColumns(10);
		euroLabel = new JLabel("\u20AC");
		euroLabel.setForeground(Color.GRAY);
		btnReserveItem = new JButton("Reserve Item");	
		btnReserveItem.setEnabled(false);
		makeNewErrorLabel = new JLabel("");
		makeNewErrorLabel.setForeground(Color.RED);


		southPanelReservation.add(lblEnterDocketNo, "cell 0 1,alignx left");
		southPanelReservation.add(enterDocketNoTF, "cell 1 1,growx");
		southPanelReservation.add(lblDeposit, "cell 2 1,alignx trailing");
		southPanelReservation.add(depositTF, "cell 3 1,growx");
		southPanelReservation.add(euroLabel, "cell 4 1");
		southPanelReservation.add(btnReserveItem, "cell 6 1");
		southPanelReservation.add(makeNewErrorLabel, "cell 0 2");
	}

    public int getTableIndex()
    {
    	return tableItemsRevervation.getSelectedRow();
    }

	public int getItemID()
	{
		
		int row=getTableIndex();
		return  (int) tableItemsRevervation.getValueAt(row,0);
		
	}
	public int getAvailableStock()
	{		
		int row=getTableIndex();
		return  (int) tableItemsRevervation.getValueAt(row,4);		
	}
	public double getPrice()
	{		
		int row=getTableIndex();
		return  (double) tableItemsRevervation.getValueAt(row,5);		
	}
	
	
	public JComboBox<String> getSelectCategoryCBox() {
		return selectCategoryCBox;
	}
	public JComboBox<String> getSelectSubcategoryCBox() {
		return selectSubcategoryCBox;
	}
	
	public JTextField  getDocketNoTF () {
		return docketNoTF ;
	}
	public JTextField getBrandModelTF() {
		return brandModelTF;
	}                            						
	public JTextField getCurrentDepositTF() {
		return currentDepositTF;
	}
	public JTextField getTotalPriceTF() {
		return totalPriceTF;
	}
	public JTextField getupdateDepositTF()
	{
		return updateDepositTF;
	}
	public JList getList()
	{
		return list; 
	}
	public JButton getNewButton()
	{
		return btnNewButton;
	}
	
	public JButton getEndReservation()
	{
		return btnEndReservation;
	}
	
	public JButton getBtnReserveItem()
	{
		return btnReserveItem;
	}
	public JTextField getSearchKeywordTF()
	{
		return searchKeywordTF;
	}
	public JTextField getDocketTF()
	{
		return enterDocketNoTF;
	}
	public JTextField getDepositTF()
	{
		return depositTF;
	}
	
	public JTextField  getEnterDocketNoTF()
	{
		return enterDocketNoTF;
	}
	/*******************
	 * ADDING LISTENERS*
	 *******************/

	/*
	 * Provide the way to notify The Controller 
	 * whenever comboBox category is clicked
	 * This will be handled by The Controller
	 */
	public void addComboBoxCatListener(ActionListener listenComboCategory ){
		selectCategoryCBox.addActionListener(listenComboCategory);
	}
	/*
	 * Provide the way to notify The Controller 
	 * whenever comboBox subCategory is clicked
	 * This will be handled by The Controller
	 */
	public void addComboBoxSubCatListener(ActionListener listenComboSubCategory){
		selectSubcategoryCBox.addActionListener(listenComboSubCategory);
	}



	public void setComboBoxCategoryModel(List<String>comboBoxList){
		categoryComboBoxModel.setComboBoxList(comboBoxList);
	}
	public void setComboBoxSubCategoryModel(List<String>comboBoxList){
		subCatComboBoxModel.setComboBoxList(comboBoxList);
	}
	

	
	
	public void addListListener(ListSelectionListener liste ){
		list.addListSelectionListener(liste);
	}
   
	public void removeList()
	{
		model.removeAllElements();
	}
   

	/*
	 * Provide the way to notify The Controller 
	 * whenever button is clicked
	 * This will be handled by The Controller
	 */
	
	
	public void addUpdateListener(ActionListener listenFor)
	{
	        btnNewButton.addActionListener(listenFor);
	}
	
	public void addRemoveListener(ActionListener listenFor)
	{
		btnEndReservation.addActionListener(listenFor);
	}
	
	public void addReserveListener(ActionListener listenFor)
	{
		btnReserveItem.addActionListener(listenFor);
	}
	
	public void addTableListener(ActionListener listenForFindButton)
	{
		btnFindItems.addActionListener(listenForFindButton);
	}

	/*
	 * I want to set Table Model
	 * This will come from Model
	 * Model doesn't know about view
	 * So the controller will set this. 
	 */
	public void setTableModel(List<Item>listFormController)
	{
		System.out.println("I want to set table model");

		itemTableModel.setTableModel(listFormController);
		tableItemsRevervation.setModel(itemTableModel);
		itemTableModel.fireTableDataChanged();

	}
	public void setListModel(List<ReservedItem> list)
	{
		
   int size=list.size();
   for (int i=0;i<size;i++)
   {
	   model.addElement(list.get(i).getDocketNo());
   }
		
   	}
	
	public void warnUpdateNull(){

		JOptionPane.showMessageDialog(null,
				"Please enter a number for updating the deposit",
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}
	
	public void warnUpdate(){

		JOptionPane.showMessageDialog(null,
				"The deposit is too big",
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}
	public void warnDocketNull(){

		JOptionPane.showMessageDialog(null,
				"Please enter the Docket Number",
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}
	
	public void warnSubCategoryNull(){

		JOptionPane.showMessageDialog(null,
				"Please make a selection for the subcategory",
				"Warning",
				JOptionPane.WARNING_MESSAGE);

	}
	public void warnItemNull(){

		JOptionPane.showMessageDialog(null,
				"Please make a selection for an item",
				"Warning",
				JOptionPane.WARNING_MESSAGE);

	}
	public void success(){

		JOptionPane.showMessageDialog(null,
				"Reservation made with success",
				"Warning",
				JOptionPane.INFORMATION_MESSAGE);

	}
}
