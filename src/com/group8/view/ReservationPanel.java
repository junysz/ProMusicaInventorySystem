package com.group8.view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.group8.controller.Controller;
import com.group8.model.Item;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ReservationPanel extends JPanel 
{
	List<Item>dbItem= new ArrayList<>();
	List<String>category= new ArrayList<>();

	private JTextField searchKeywordTF;
	private JTextField docketNoTF;
	private JTextField brandModelTF;
	private JTextField currentDepositTF;
	private JTextField totalPriceTF;
	private JTextField updateDepositTF;

	JTabbedPane tabbedPane;
	JPanel findReservationPanel;

	private ItemTableModel itemTableModel;
	private JTable tableItemsRevervation;
	private CategoryComboBoxModel categoryComboBoxModel;

	/*
	 * JPanel makeNewReservation components 
	 */
	private JPanel makeNewReservationPanel;
	private JLabel lblNewLabel;
	private JLabel lblSelectCategory;
	private JComboBox<String> selectCategoryCBox;
	private JComboBox slecetSubcategoryCBox;
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
	private JLabel makeNewErrorLabel;



	public ReservationPanel() {

		setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);

		findReservationPanel = new JPanel();
		tabbedPane.addTab("Find", null, findReservationPanel, null);
		findReservationPanel.setLayout(new MigLayout("", "[][grow][][grow][grow][grow][grow]", "[][grow][grow]"));

		JScrollPane scrollPane = new JScrollPane();
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
		totalPriceTF.setEditable(false);
		reservationDetailsPanel.add(totalPriceTF, "cell 7 2,growx");
		totalPriceTF.setColumns(10);

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

		JButton btnNewButton = new JButton("Update Reservation");
		reservationDetailsPanel.add(btnNewButton, "cell 3 6");

		JButton btnEndReservation = new JButton("End Reservation");
		reservationDetailsPanel.add(btnEndReservation, "cell 3 8");








		/*
		 * makeReservationPanel STUFF
		 */
		makeNewReservationPanel = new JPanel();
		tabbedPane.addTab("Make New", null, makeNewReservationPanel, null);
		makeNewReservationPanel.setLayout(new MigLayout("", "[106.00,grow][grow][][grow][][grow][]", "[][][530.00,grow][530.00]"));

		lblNewLabel = new JLabel("Find One Item to Reserve");
		lblSelectCategory = new JLabel("Select Category");
		slecetSubcategoryCBox = new JComboBox();
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
		scrollPaneReservTable.setViewportView(tableItemsRevervation);

		makeNewReservationPanel.add(lblNewLabel, "cell 0 0 2 1");

		categoryComboBoxModel=new CategoryComboBoxModel(category);


		selectCategoryCBox = new JComboBox<String>();
		selectCategoryCBox.setModel(categoryComboBoxModel);


		makeNewReservationPanel.add(selectCategoryCBox, "flowx,cell 0 1,growx");
		makeNewReservationPanel.add(lblSelectCategory, "cell 0 1,alignx left");	
		makeNewReservationPanel.add(lblSlecetSubcategory, "cell 2 1");
		makeNewReservationPanel.add(slecetSubcategoryCBox, "cell 3 1,growx");
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



	public JComboBox<String> getSelectCategoryCBox() {
		return selectCategoryCBox;
	}



	/*******************
	 * ADDING LISTENERS*
	 *******************/

	/*
	 * Provide the way to notify The Controller 
	 * whenever comboBoc category is clicked
	 * This will be handled by The Controller
	 */
	public void addComboBoxCatListener(ActionListener listenComboCategory ){
		selectCategoryCBox.addActionListener(listenComboCategory);
	}








	public void setComboBoxCategoryModel(List<String>comboBoxList){
		categoryComboBoxModel.setComboBoxList(comboBoxList);
	}

	/*
	 * Provide the way to notify The Controller 
	 * whenever button is clicked
	 * This will be handled by The Controller
	 */
	public void addTableListener(ActionListener listenForFindButton)
	{
		btnFindItems.addActionListener(listenForFindButton);
	}

	/*
	 * I want to set Table Model
	 * This will come form Model
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


}
