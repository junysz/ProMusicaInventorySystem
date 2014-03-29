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
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.Color;

public class ReservationPanel extends JPanel {
	private JTextField searchKeywordTF;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField docketNoTF;
	private JTextField brandModelTF;
	private JTextField currentDepositTF;
	private JTextField totalPriceTF;
	private JTextField updateDepositTF;

	
	public ReservationPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JPanel FindReservationPanel = new JPanel();
		tabbedPane.addTab("Find", null, FindReservationPanel, null);
		FindReservationPanel.setLayout(new MigLayout("", "[][grow][][grow][grow][grow][grow]", "[][grow][grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder("Docket Numbers"));
		
		FindReservationPanel.add(scrollPane, "cell 1 1 1 2,grow");
		
		JPanel reservationDetailsPanel = new JPanel();
		FindReservationPanel.add(reservationDetailsPanel, "cell 3 1 3 1,grow");
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
		
		JPanel MakeNewReservationPanel = new JPanel();
		tabbedPane.addTab("Make New", null, MakeNewReservationPanel, null);
		MakeNewReservationPanel.setLayout(new MigLayout("", "[][106.00][grow][grow][][][grow][grow][][][grow][][grow]", "[][][][][530.00,grow][530.00][530.00][530.00][530.00][530.00][530.00][45.00][530.00,grow][]"));
		
		JLabel lblNewLabel = new JLabel("Find One Item to Reserve");
		MakeNewReservationPanel.add(lblNewLabel, "cell 1 0 2 1");
		
		JLabel lblSelectCategory = new JLabel("Select Category");
		MakeNewReservationPanel.add(lblSelectCategory, "cell 1 2,alignx left");
		
		JComboBox comboBox = new JComboBox();
		MakeNewReservationPanel.add(comboBox, "cell 2 2 2 1,growx");
		
		JLabel lblSlecetSubcategory = new JLabel("Select Sub-Category");
		MakeNewReservationPanel.add(lblSlecetSubcategory, "cell 5 2");
		
		JComboBox comboBox_1 = new JComboBox();
		MakeNewReservationPanel.add(comboBox_1, "cell 6 2 2 1,growx");
		
		JLabel lblSearch = new JLabel("Search by Keyword ");
		MakeNewReservationPanel.add(lblSearch, "cell 9 2");
		
		searchKeywordTF = new JTextField();
		MakeNewReservationPanel.add(searchKeywordTF, "cell 10 2,growx");
		searchKeywordTF.setColumns(10);
		
		JButton btnFindItems = new JButton("Find Items");
		MakeNewReservationPanel.add(btnFindItems, "cell 12 2");
		
		//scrolPane
		JScrollPane scrollPaneReservationPanel = new JScrollPane();
		MakeNewReservationPanel.add(scrollPaneReservationPanel, "cell 1 4 10 6,grow");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		table = new JTable();
		scrollPaneReservationPanel.setViewportView(table);
		
		JPanel panel = new JPanel();
		MakeNewReservationPanel.add(panel, "cell 1 10 10 3,grow");
		panel.setLayout(new MigLayout("", "[][grow][grow][grow][][grow][grow]", "[][][]"));
		
		JLabel lblEnterDocketNo = new JLabel("Enter Docket No.");
		panel.add(lblEnterDocketNo, "cell 0 1,alignx left");
		
		textField = new JTextField();
		panel.add(textField, "cell 1 1,growx");
		textField.setColumns(10);
		
		JLabel lblDeposti = new JLabel("Deposit");
		panel.add(lblDeposti, "cell 2 1,alignx trailing");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "cell 3 1,growx");
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("\u20AC");
		label.setForeground(Color.GRAY);
		panel.add(label, "cell 4 1");
		
		JButton btnReserveItem = new JButton("Reserve Item");
		panel.add(btnReserveItem, "cell 6 1");
		
		JLabel makeNewErrorLabel = new JLabel("");
		makeNewErrorLabel.setForeground(Color.RED);
		panel.add(makeNewErrorLabel, "cell 0 2");

	}

}
