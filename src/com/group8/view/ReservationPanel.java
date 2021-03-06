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
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Label;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ReservationPanel extends JPanel 
{
	@SuppressWarnings("rawtypes")
	List dbItem= new ArrayList<>();
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
	private CategoryComboBoxModel 	categoryComboBoxModel,subCatComboBoxModel;
	private JComboBox<String> 		selectCategoryCBox;
	private JComboBox<String> 		selectSubcategoryCBox;
	private  JList<String> list;
	private DefaultListModel<String> model;
	private JPanel makeNewReservationPanel;
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
	private Label label;
	private Label label_2;
	private JTextField DateTF;
	private JButton logoutButton;
	private JButton logoutButton2;

	public ReservationPanel() {
		setFont(new Font("Cambria", Font.BOLD, 14));
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		model = new DefaultListModel<String>();
		/*
		 * MakeNewReservationPanel STUFF
		 */
		makeNewReservationPanel = new JPanel();
		tabbedPane.addTab("Make New", null, makeNewReservationPanel, null);
		makeNewReservationPanel.setLayout(new MigLayout("", "[186.00,grow][219.00,grow][103.00][106.00][][grow][]", "[][][][][][][][][47.00][39.00][530.00,grow][][][][47.00][530.00,grow][530.00,grow][-53.00][][][]"));
		scrollPaneReservTable = new JScrollPane();
		scrollPaneReservTable.setFont(new Font("Cambria", Font.BOLD, 14));
		//instantiate ItemTableModel
		itemTableModel= new ItemTableModel();
		tableItemsRevervation = new JTable();
		tableItemsRevervation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableItemsRevervation.setModel(itemTableModel);
		tableItemsRevervation.getColumnModel().getColumn(0).setMinWidth(0);
		tableItemsRevervation.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPaneReservTable.setViewportView(tableItemsRevervation);
		scrollPaneReservTable.setViewportView(tableItemsRevervation); 
		selectCategoryCBox = new JComboBox<String>();
		subCatComboBoxModel= new CategoryComboBoxModel();
		lblSelectCategory = new JLabel("Select Category");
		lblSelectCategory.setFont(new Font("Cambria", Font.BOLD, 13));
		makeNewReservationPanel.add(lblSelectCategory, "flowx,cell 0 2,alignx left");	
		makeNewReservationPanel.add(selectCategoryCBox, "cell 0 2,growx");
		lblSlecetSubcategory = new JLabel("Select SubCategory");
		lblSlecetSubcategory.setFont(new Font("Cambria", Font.BOLD, 13));
		makeNewReservationPanel.add(lblSlecetSubcategory, "flowx,cell 1 2,alignx center");
		lblSearch = new JLabel(" Keyword ");
		lblSearch.setFont(new Font("Cambria", Font.BOLD, 13));
		makeNewReservationPanel.add(lblSearch, "cell 2 2,alignx right");
		searchKeywordTF = new JTextField();
		//btnFindItems.addTableActionEvetn(TableModelListener tableModelListener); 
		searchKeywordTF.setColumns(10);
		makeNewReservationPanel.add(searchKeywordTF, "cell 3 2,growx");
		btnFindItems = new JButton("Find items");
		btnFindItems.setIcon(new ImageIcon(ReservationPanel.class.getResource("/com/group8/images/Search.png")));
		makeNewReservationPanel.add(btnFindItems, "cell 5 2");
		btnFindItems.setFont(new Font("Cambria", Font.BOLD, 13));
		makeNewReservationPanel.add(scrollPaneReservTable, "cell 0 10 4 6,grow");
		/*
		 * SOUTH PANEL
		 */
		southPanelReservation = new JPanel();
		southPanelReservation.setLayout(new MigLayout("", "[][][grow][grow][][grow][][][][][grow][grow]", "[][][][][][][][][][][][][15.00][][][][][][]"));
		makeNewReservationPanel.add(southPanelReservation, "cell 0 16 6 1,growx,aligny center");
		makeNewErrorLabel = new JLabel("");
		makeNewErrorLabel.setForeground(Color.RED);
		southPanelReservation.add(makeNewErrorLabel, "cell 1 5");
		
				lblEnterDocketNo = new JLabel("Docket No.");
				lblEnterDocketNo.setFont(new Font("Cambria", Font.BOLD, 13));
				
				
						southPanelReservation.add(lblEnterDocketNo, "cell 0 15,alignx right");
		enterDocketNoTF = new JTextField();
		enterDocketNoTF.setColumns(10);
		southPanelReservation.add(enterDocketNoTF, "cell 1 15,alignx center");
		lblDeposit = new JLabel("Deposit");
		lblDeposit.setFont(new Font("Cambria", Font.BOLD, 13));
		southPanelReservation.add(lblDeposit, "cell 3 15,alignx trailing");
		depositTF = new JTextField();					
		depositTF.setColumns(10);
		southPanelReservation.add(depositTF, "cell 4 15,alignx center");
		euroLabel = new JLabel("\u20AC");
		euroLabel.setForeground(Color.GRAY);
		southPanelReservation.add(euroLabel, "cell 5 15");
		btnReserveItem = new JButton("Reserve item");	
		btnReserveItem.setIcon(new ImageIcon(ReservationPanel.class.getResource("/com/group8/images/reservation.png")));
		btnReserveItem.setFont(new Font("Cambria", Font.BOLD, 13));
		btnReserveItem.setEnabled(false);
		southPanelReservation.add(btnReserveItem, "cell 7 15,alignx right");
		selectSubcategoryCBox = new JComboBox<String>();
		makeNewReservationPanel.add(selectSubcategoryCBox, "cell 1 2,growx");
		logoutButton = new JButton("Logout");
		logoutButton.setIcon(new ImageIcon(ReservationPanel.class.getResource("/com/group8/images/logout.png")));
		logoutButton.setToolTipText("Logout");
		makeNewReservationPanel.add(logoutButton, "cell 5 19,alignx right");
		logoutButton.setForeground(Color.BLACK);
		logoutButton.setFont(new Font("Cambria", Font.BOLD, 13));

		findReservationPanel = new JPanel();
		tabbedPane.addTab("Find", null, findReservationPanel, null);
		findReservationPanel.setLayout(new MigLayout("", "[10.00][55.00,grow][39.00,grow][115.00,grow][176.00,grow,left][][grow][][][][][]", "[][269.00][79.00][51.00][98.00][48.00,grow]"));
		list = new JList<String>(model);
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setBackground(new Color(255, 255, 255));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Cambria", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Docket Number", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		findReservationPanel.add(scrollPane, "cell 0 1 2 5,grow");
		JPanel reservationDetailsPanel = new JPanel();
		findReservationPanel.add(reservationDetailsPanel, "cell 2 1 3 1,alignx right,growy");
		reservationDetailsPanel.setLayout(new MigLayout("", "[][][][grow][][][][][][][][][grow][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		reservationDetailsPanel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Reservation Details", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		JLabel lblDocketNo = new JLabel("Docket No:");
		lblDocketNo.setFont(new Font("Cambria", Font.BOLD, 13));
		reservationDetailsPanel.add(lblDocketNo, "cell 1 4,alignx right");

		docketNoTF = new JTextField();
		docketNoTF.setEditable(false);
		reservationDetailsPanel.add(docketNoTF, "cell 3 4 12 1,growx");
		docketNoTF.setColumns(10);

		JLabel lblCurrentDeposti = new JLabel("Current Deposit");
		lblCurrentDeposti.setFont(new Font("Cambria", Font.BOLD, 13));
		reservationDetailsPanel.add(lblCurrentDeposti, "cell 21 4,alignx left");

		currentDepositTF = new JTextField();
		currentDepositTF.setEditable(false);
		reservationDetailsPanel.add(currentDepositTF, "cell 23 4,growx");
		currentDepositTF.setColumns(10);

		label = new Label("\u20AC");
		reservationDetailsPanel.add(label, "cell 24 4,alignx left");

		JLabel resErrorLabel = new JLabel("");
		resErrorLabel.setForeground(Color.RED);
		reservationDetailsPanel.add(resErrorLabel, "cell 3 6");

		JLabel lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Cambria", Font.BOLD, 13));
		reservationDetailsPanel.add(lblItem, "cell 1 12,alignx right");

		brandModelTF = new JTextField();
		brandModelTF.setEditable(false);
		reservationDetailsPanel.add(brandModelTF, "cell 3 12 13 1,growx");
		brandModelTF.setColumns(10);

		JLabel lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setFont(new Font("Cambria", Font.BOLD, 13));
		reservationDetailsPanel.add(lblTotalPrice, "cell 21 12,alignx right");								

		totalPriceTF = new JTextField();

		reservationDetailsPanel.add(totalPriceTF, "cell 23 12,growx");
		totalPriceTF.setColumns(10);
		totalPriceTF.setEditable(false);																

		JLabel label_1 = new JLabel("\u20AC");
		reservationDetailsPanel.add(label_1, "cell 24 12,alignx left");
		JLabel lblUpdateDeposit = new JLabel("Update Deposit");
		lblUpdateDeposit.setFont(new Font("Cambria", Font.BOLD, 13));
		reservationDetailsPanel.add(lblUpdateDeposit, "cell 1 25");

		updateDepositTF = new JTextField();
		reservationDetailsPanel.add(updateDepositTF, "cell 3 25,growx");
		updateDepositTF.setColumns(10);

		JLabel lblMakereservationtable = new JLabel("\u20AC");
		lblMakereservationtable.setForeground(Color.GRAY);
		reservationDetailsPanel.add(lblMakereservationtable, "cell 4 25");

		label_2 = new Label("Start date");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Cambria", Font.BOLD, 13));
		reservationDetailsPanel.add(label_2, "cell 21 25,alignx right");

		DateTF = new JTextField();
		DateTF.setFont(new Font("Cambria", Font.BOLD, 13));
		DateTF.setEnabled(false);
		DateTF.setEditable(false);
		reservationDetailsPanel.add(DateTF, "cell 22 25 2 1,growx");
		DateTF.setColumns(10);

		btnNewButton = new JButton("Update Reservation");
		findReservationPanel.add(btnNewButton, "cell 2 2 2 1,alignx center");
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 13));
		btnNewButton.setEnabled(false);
		
				btnEndReservation = new JButton("End Reservation");
				findReservationPanel.add(btnEndReservation, "cell 4 2,alignx center");
				btnEndReservation.setFont(new Font("Cambria", Font.BOLD, 13));
				btnEndReservation.setEnabled(false);
		
				logoutButton2 = new JButton("Logout");
				logoutButton2.setIcon(new ImageIcon(ReservationPanel.class.getResource("/com/group8/images/logout.png")));
				logoutButton2.setFont(new Font("Cambria", Font.BOLD, 13));
				
						logoutButton2.setForeground(Color.BLACK);
						findReservationPanel.add(logoutButton2, "cell 5 5,alignx left,aligny bottom");																																		    	    	    	    
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
	public JTextField getDateTF()
	{
		return DateTF;
	}
	public JList<String> getList()
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

	public JButton getlogoutButton()
	{
		return logoutButton;
	}
	public JButton getlogoutButton2()
	{
		return logoutButton2;
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
	public void logoutButtonListener(ActionListener listenFor)
	{
		logoutButton.addActionListener(listenFor);
	}
	public void logoutButtonListener2(ActionListener listenFor)
	{
		logoutButton2.addActionListener(listenFor);
	}
	/*
	 * I want to set Table Model
	 * This will come from Model
	 * Model doesn't know about view
	 * So the controller will set this. 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setTableModel(List listFormController)
	{
		System.out.println("I want to set table model");
		itemTableModel.setTableModel(listFormController);
		tableItemsRevervation.setModel(itemTableModel);
		itemTableModel.fireTableDataChanged();
	}
	
  public DefaultListModel<String> getModel()
  { 
	  return model;
  }
  /*********************************************
   ******** Messages for controller*************
   ********************************************/
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
	public void warnNewReservationInput(ArrayList<String> errors){

		String message = "Fix:\n"; //errors message for JOptionPane
		//For each error in the list add the error to the message
		for(String e: errors)
		{
			message+=e + "\n";
		}
		JOptionPane.showMessageDialog(null,
				message,
				"Go away warning",
				JOptionPane.WARNING_MESSAGE);
	}

	public void warnSubCategoryNull(){

		JOptionPane.showMessageDialog(null,
				"Please make a selection for the subcategory",
				"Warning",
				JOptionPane.WARNING_MESSAGE);

	}
	public void success(){

		JOptionPane.showMessageDialog(null,
				"Reservation made with success",
				"Warning",
				JOptionPane.INFORMATION_MESSAGE);

	}
	public void successful(){

		JOptionPane.showMessageDialog(null,
				"Reservation updated",
				"Warning",
				JOptionPane.INFORMATION_MESSAGE);

	}
	public void successfuly(){

		JOptionPane.showMessageDialog(null,
				"Reservation terminated",
				" ",
				JOptionPane.INFORMATION_MESSAGE);

	}
	public void setSelectCategoryCBModel(CategoryComboBoxModel c, ArrayList<String> a)
	{
		c.setComboBoxList(a);
		selectCategoryCBox.setModel(c);
	}
	
	public void setComboBoxSubCategoryModel(CategoryComboBoxModel c, ArrayList<String> a)
	{
		c.setComboBoxList(a);
		selectSubcategoryCBox.setModel(c);
		
	}
	
}

