package com.group8.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;



import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;



public class MaintainPanel extends JPanel implements ActionListener {
	private JTextField enterUsernameTF, enterPasswordTF1, enterPasswordTF2, editUsernameTF;
	private JPanel MaintainAccountPanel, newAccountPanel, editAccountPanel, MaintainCategoriesPanel, MaintainItemPanel;
	private JLabel lblUserName, lblPassword, lblConfirmPassword, lblSelectType, errorLabel1,errorLabel2, lblSelectAccount, lblUsername, lblSelectType_1, lblStatus;
	private JComboBox<String> selectAccountTypeComboBox, selectAccountComboBox, selectAccountTypeEditComboBox;
	private JRadioButton rdbtnEnableAccount, rdbtnDisableAccount;
	private JButton btnCreateAccount, btnConfirmChanges;
	private JPanel createItemPanel;
	private JPanel editItemPanel;
	private JLabel lblCategory;
	private JComboBox createCategoryComboBox;
	private JLabel lblSubcategory;
	private JComboBox createSubCatComboBox;
	private JLabel lblBrand;
	private JLabel lblModel;
	private JTextField enterModelTF;
	private JLabel lblPrice;
	private JTextField enterPriceTF;
	private JLabel lblStockLevel;
	private JTextField enterStockLevelTF;
	private JLabel errorLabel7;
	private JButton btnCreate;
	private JLabel lbloptional;
	private JLabel lblEuro;
	private JLabel lblCategory_1;
	private JComboBox editCategoryComboBox;
	private JLabel lblSubcategory_1;
	private JComboBox editSubCatComboBox;
	private JLabel lblSelectItem;
	private JComboBox selectItemComboBox;
	private JLabel lblBrand_1;
	private JTextField editBrandTF;
	private JLabel lblModel_1;
	private JTextField editModelTF;
	private JLabel lblPrice_1;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel errorLabel8;
	private JButton btnConfirmChanges_1;
	private JButton btnRemoveItem;
	private JPanel createCategoryPanel;
	private JPanel createSubCatPanel;
	private JPanel editCategoryPanel;
	private JPanel editSubCatPanel;
	private JLabel lblEnterName;
	private JTextField enterCategoryNameTF;
	private JLabel errorLabel3;
	private JButton btnCreateCategory;
	private JLabel lblSelectCategory;
	private JComboBox<String> selectCategorycomboBox;
	private JLabel lblEnterSubcategoryName;
	private JTextField enterSubCatNameTF;
	
	private JLabel errorLabel4;
	private JButton btnCreateSubcategory;
	private JLabel lblSelectCategory_1;
	private JComboBox<String> selectCategoryToEditcomboBox;
	private JLabel lblEditName;
	private JTextField editCategoryNameTF;
	private JLabel errorLabel5;
	private JButton btnConfirmChanges_2;
	private JLabel lblSelectCategory_2;
	private JComboBox<String> findCategoryComboBox;
	private JLabel lblSelectSubcategory_1;
	private JComboBox selectSubCatToEditComboBox;
	private JLabel lblEditName_1;
	private JTextField editSubCatNameTF;
	private JLabel errorLabel6;
	private JButton btnConfirmChanges_3;
	private JLabel lblNewLabel_1;
	private JComboBox changeSubCatComboBox;
	private CategoryListener categoryListenr;
	private AccountListner accountListener;
	private JTextField editAccountPasswordTF;
	private JLabel lblPassword_1;


	private CategoryComboBoxModel categoryComboBoxModel;

	//all components
	void init(){

		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(tabbedPane, BorderLayout.CENTER);

		MaintainAccountPanel = new JPanel();
		tabbedPane.addTab("Maintan Accounts", null, MaintainAccountPanel, null);
		MaintainAccountPanel.setLayout(new GridLayout(1, 2, 10, 10));

		newAccountPanel = new JPanel();
		MaintainAccountPanel.add(newAccountPanel);
		newAccountPanel.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][]"));
		//SET A TITLE BORDER FOR THE PANEL
		newAccountPanel.setBorder(new TitledBorder("Create New Account"));

		lblUserName = new JLabel("User Name");
		newAccountPanel.add(lblUserName, "cell 1 1");

		enterUsernameTF = new JTextField();
		newAccountPanel.add(enterUsernameTF, "cell 3 1,growx");
		enterUsernameTF.setColumns(10);

		lblPassword = new JLabel("Password");
		newAccountPanel.add(lblPassword, "cell 1 2");

		enterPasswordTF1 = new JTextField();
		newAccountPanel.add(enterPasswordTF1, "cell 3 2,growx");
		enterPasswordTF1.setColumns(10);

		lblConfirmPassword = new JLabel("Confirm Password");
		newAccountPanel.add(lblConfirmPassword, "cell 1 3");

		enterPasswordTF2 = new JTextField();
		newAccountPanel.add(enterPasswordTF2, "cell 3 3,growx");
		enterPasswordTF2.setColumns(10);

		lblSelectType = new JLabel("Select Type");
		newAccountPanel.add(lblSelectType, "cell 1 4");

		selectAccountTypeComboBox = new JComboBox();
		newAccountPanel.add(selectAccountTypeComboBox, "cell 3 4,growx");

		errorLabel1 = new JLabel();
		errorLabel1.setForeground(Color.RED);
		newAccountPanel.add(errorLabel1, "cell 3 5");
		errorLabel1.setVisible(false);

		btnCreateAccount = new JButton("Create Account");
		newAccountPanel.add(btnCreateAccount, "cell 3 6");

		editAccountPanel = new JPanel();
		MaintainAccountPanel.add(editAccountPanel);
		editAccountPanel.setLayout(new MigLayout("", "[][][][120.00,grow][grow]", "[][][][][][][][][][]"));
		//SET A TITLE BORDER FOR THE PANEL
		editAccountPanel.setBorder(new TitledBorder("Edit Existing Account"));

		lblSelectAccount = new JLabel("Select Account");
		editAccountPanel.add(lblSelectAccount, "cell 1 1");

		selectAccountComboBox = new JComboBox();
		editAccountPanel.add(selectAccountComboBox, "cell 3 1 2 1,growx");

		lblUsername = new JLabel("Username");
		editAccountPanel.add(lblUsername, "cell 1 2");

		editUsernameTF = new JTextField();
		editAccountPanel.add(editUsernameTF, "cell 3 2 2 1,growx");
		editUsernameTF.setColumns(10);

		lblSelectType_1 = new JLabel("Select Type");
		editAccountPanel.add(lblSelectType_1, "cell 1 3");

		selectAccountTypeEditComboBox = new JComboBox();
		editAccountPanel.add(selectAccountTypeEditComboBox, "cell 3 3 2 1,growx");

		//Create a Button Group for the Account status radio buttons
		ButtonGroup statusGroup = new ButtonGroup();

		lblPassword_1 = new JLabel("Password");
		editAccountPanel.add(lblPassword_1, "cell 1 4");

		editAccountPasswordTF = new JTextField();
		editAccountPanel.add(editAccountPasswordTF, "cell 3 4 2 1,growx");
		editAccountPasswordTF.setColumns(10);

		errorLabel2 = new JLabel("");
		errorLabel2.setForeground(Color.RED);
		editAccountPanel.add(errorLabel2, "cell 3 5 2 1");
		errorLabel2.setVisible(false);

		lblStatus = new JLabel("Status");
		editAccountPanel.add(lblStatus, "cell 1 6");

		rdbtnEnableAccount = new JRadioButton("Enabled");
		editAccountPanel.add(rdbtnEnableAccount, "cell 3 6");
		statusGroup.add(rdbtnEnableAccount);

		rdbtnDisableAccount = new JRadioButton("Disabled");
		editAccountPanel.add(rdbtnDisableAccount, "cell 4 6");
		statusGroup.add(rdbtnDisableAccount);


		btnConfirmChanges = new JButton("Confirm Changes");
		editAccountPanel.add(btnConfirmChanges, "cell 3 8");

		MaintainCategoriesPanel = new JPanel();
		tabbedPane.addTab("Maintain Categories", null, MaintainCategoriesPanel, null);
		MaintainCategoriesPanel.setLayout(new GridLayout(2, 2, 10, 10));

		createCategoryPanel = new JPanel();
		MaintainCategoriesPanel.add(createCategoryPanel);
		createCategoryPanel.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][]"));

		createCategoryPanel.setBorder(new TitledBorder("Create New Category"));

		lblEnterName = new JLabel("Enter Category Name");
		createCategoryPanel.add(lblEnterName, "cell 1 0");

		enterCategoryNameTF = new JTextField();
		createCategoryPanel.add(enterCategoryNameTF, "cell 3 0,growx");
		enterCategoryNameTF.setColumns(10);

		errorLabel3 = new JLabel("");
		errorLabel3.setForeground(Color.RED);
		createCategoryPanel.add(errorLabel3, "cell 3 1,alignx right");

		btnCreateCategory = new JButton("Create Category");

		createCategoryPanel.add(btnCreateCategory, "cell 3 5");

		createSubCatPanel = new JPanel();
		MaintainCategoriesPanel.add(createSubCatPanel);

		createSubCatPanel.setBorder(new TitledBorder("Create Sub-Category"));
		createSubCatPanel.setLayout(new MigLayout("", "[][][][grow]", "[][][][]"));

		lblSelectCategory = new JLabel("Select Category");
		createSubCatPanel.add(lblSelectCategory, "cell 1 0");

		selectCategorycomboBox = new JComboBox();
		createSubCatPanel.add(selectCategorycomboBox, "cell 3 0,growx");

		lblEnterSubcategoryName = new JLabel("Enter Sub-Category Name");
		createSubCatPanel.add(lblEnterSubcategoryName, "cell 1 1");

		enterSubCatNameTF = new JTextField();
		createSubCatPanel.add(enterSubCatNameTF, "cell 3 1,growx");
		enterSubCatNameTF.setColumns(10);

		errorLabel4 = new JLabel("");
		createSubCatPanel.add(errorLabel4, "cell 3 2");

		btnCreateSubcategory = new JButton("Create Sub-Category");
		createSubCatPanel.add(btnCreateSubcategory, "cell 3 3");

		editCategoryPanel = new JPanel();
		MaintainCategoriesPanel.add(editCategoryPanel);
		editCategoryPanel.setBorder(new TitledBorder("Edit Category"));
		editCategoryPanel.setLayout(new MigLayout("", "[][][][grow]", "[][][][][]"));

		lblSelectCategory_1 = new JLabel("Select Category");
		editCategoryPanel.add(lblSelectCategory_1, "cell 1 0");



		categoryComboBoxModel= new CategoryComboBoxModel();
		selectCategoryToEditcomboBox = new JComboBox<String>();
		selectCategorycomboBox.setModel(categoryComboBoxModel);


		editCategoryPanel.add(selectCategoryToEditcomboBox, "cell 3 0,growx");

		lblEditName = new JLabel("Edit Name");
		editCategoryPanel.add(lblEditName, "cell 1 1");

		editCategoryNameTF = new JTextField();
		
		editCategoryPanel.add(editCategoryNameTF, "cell 3 1,growx");
		editCategoryNameTF.setColumns(10);

		errorLabel5 = new JLabel("");
		errorLabel5.setForeground(Color.RED);
		editCategoryPanel.add(errorLabel5, "cell 3 2");

		btnConfirmChanges_2 = new JButton("Confirm Changes");
		editCategoryPanel.add(btnConfirmChanges_2, "cell 3 3");

		editSubCatPanel = new JPanel();
		MaintainCategoriesPanel.add(editSubCatPanel);

		editSubCatPanel.setBorder(new TitledBorder("Edit Sub-Category"));

		editSubCatPanel.setLayout(new MigLayout("", "[][][][grow]", "[][][][][]"));

		lblSelectCategory_2 = new JLabel("Select Category");
		editSubCatPanel.add(lblSelectCategory_2, "cell 1 0");

		findCategoryComboBox = new JComboBox();
		editSubCatPanel.add(findCategoryComboBox, "cell 3 0,growx");

		lblSelectSubcategory_1 = new JLabel("Select Sub-Category");
		editSubCatPanel.add(lblSelectSubcategory_1, "cell 1 1");

		selectSubCatToEditComboBox = new JComboBox();
		editSubCatPanel.add(selectSubCatToEditComboBox, "cell 3 1,growx");

		lblEditName_1 = new JLabel("Edit Name");
		editSubCatPanel.add(lblEditName_1, "cell 1 2");

		editSubCatNameTF = new JTextField();
		editSubCatPanel.add(editSubCatNameTF, "cell 3 2,growx");
		editSubCatNameTF.setColumns(10);

		errorLabel6 = new JLabel("");
		errorLabel6.setForeground(Color.RED);
		editSubCatPanel.add(errorLabel6, "cell 3 3");

		btnConfirmChanges_3 = new JButton("Confirm Changes");
		editSubCatPanel.add(btnConfirmChanges_3, "cell 3 4");

		MaintainItemPanel = new JPanel();
		tabbedPane.addTab("Maintain Items", null, MaintainItemPanel, null);
		MaintainItemPanel.setLayout(new GridLayout(1, 2, 10, 10));

		createItemPanel = new JPanel();
		createItemPanel.setBorder(new TitledBorder("Create New Item"));
		MaintainItemPanel.add(createItemPanel);
		createItemPanel.setLayout(new MigLayout("", "[][][][131.00][grow]", "[][][][][][][][][][][]"));

		lblCategory = new JLabel("Category");
		createItemPanel.add(lblCategory, "cell 1 1");

		createCategoryComboBox = new JComboBox();
		createItemPanel.add(createCategoryComboBox, "cell 3 1 2 1,growx");

		lblSubcategory = new JLabel("Sub-Category");
		createItemPanel.add(lblSubcategory, "cell 1 2");

		createSubCatComboBox = new JComboBox();
		createItemPanel.add(createSubCatComboBox, "cell 3 2 2 1,growx");

		lblBrand = new JLabel("Brand");
		createItemPanel.add(lblBrand, "cell 1 3");

		JTextField enterBrandTF = new JTextField();
		createItemPanel.add(enterBrandTF, "cell 3 3 2 1,growx");
		enterBrandTF.setColumns(10);

		lblModel = new JLabel("Model");
		createItemPanel.add(lblModel, "cell 1 4");

		enterModelTF = new JTextField();
		createItemPanel.add(enterModelTF, "cell 3 4 2 1,growx");
		enterModelTF.setColumns(10);

		lblPrice = new JLabel("Price");
		createItemPanel.add(lblPrice, "cell 1 5");

		enterPriceTF = new JTextField();
		createItemPanel.add(enterPriceTF, "cell 3 5,growx");
		enterPriceTF.setColumns(10);

		lblEuro = new JLabel("\u20AC");
		lblEuro.setForeground(Color.GRAY);
		createItemPanel.add(lblEuro, "cell 4 5");

		lblStockLevel = new JLabel("Stock Level");
		createItemPanel.add(lblStockLevel, "cell 1 6");

		enterStockLevelTF = new JTextField();
		createItemPanel.add(enterStockLevelTF, "cell 3 6,growx");
		enterStockLevelTF.setColumns(10);

		lbloptional = new JLabel("*optional");
		lbloptional.setForeground(Color.GRAY);
		createItemPanel.add(lbloptional, "cell 4 6");

		errorLabel7 = new JLabel("");
		errorLabel7.setForeground(Color.RED);
		createItemPanel.add(errorLabel7, "cell 3 7 2 1");

		btnCreate = new JButton("Create Item");
		createItemPanel.add(btnCreate, "cell 3 8 2 1");


		editItemPanel = new JPanel();
		editItemPanel.setBorder(new TitledBorder("Edit Existing Item"));
		MaintainItemPanel.add(editItemPanel);
		editItemPanel.setLayout(new MigLayout("", "[][][][136.00,grow][grow]", "[][][][][][][][][][][]"));

		lblCategory_1 = new JLabel("Category");
		editItemPanel.add(lblCategory_1, "cell 1 1");

		editCategoryComboBox = new JComboBox();
		editItemPanel.add(editCategoryComboBox, "cell 3 1 2 1,growx");

		lblSubcategory_1 = new JLabel("Sub-Category");
		editItemPanel.add(lblSubcategory_1, "cell 1 2");

		editSubCatComboBox = new JComboBox();
		editItemPanel.add(editSubCatComboBox, "cell 3 2 2 1,growx");

		lblSelectItem = new JLabel("Select Item");
		editItemPanel.add(lblSelectItem, "cell 1 3");

		selectItemComboBox = new JComboBox();
		editItemPanel.add(selectItemComboBox, "cell 3 3 2 1,growx");

		lblBrand_1 = new JLabel("Brand");
		editItemPanel.add(lblBrand_1, "cell 1 4");

		editBrandTF = new JTextField();
		editItemPanel.add(editBrandTF, "cell 3 4 2 1,growx");
		editBrandTF.setColumns(10);

		lblModel_1 = new JLabel("Model");
		editItemPanel.add(lblModel_1, "cell 1 5");

		editModelTF = new JTextField();
		editItemPanel.add(editModelTF, "cell 3 5 2 1,growx");
		editModelTF.setColumns(10);

		lblPrice_1 = new JLabel("Price");
		editItemPanel.add(lblPrice_1, "cell 1 6");

		textField = new JTextField();
		editItemPanel.add(textField, "cell 3 6,alignx left");
		textField.setColumns(10);

		lblNewLabel = new JLabel("\u20AC");
		lblNewLabel.setForeground(Color.GRAY);
		editItemPanel.add(lblNewLabel, "cell 4 6");

		lblNewLabel_1 = new JLabel("Move Sub-Cat");
		editItemPanel.add(lblNewLabel_1, "cell 1 7");

		changeSubCatComboBox = new JComboBox();
		editItemPanel.add(changeSubCatComboBox, "cell 3 7 2 1,growx");

		errorLabel8 = new JLabel("");
		errorLabel8.setForeground(Color.RED);
		editItemPanel.add(errorLabel8, "cell 3 8 2 1");

		btnConfirmChanges_1 = new JButton("Confirm Changes");
		editItemPanel.add(btnConfirmChanges_1, "cell 3 9");

		btnRemoveItem = new JButton("Remove Item");
		editItemPanel.add(btnRemoveItem, "cell 3 10,growx");




	}


	public JTextField getEditCategoryNameTF() {
		return editCategoryNameTF;
	}


	public void setEditCategoryNameTF(String editCategoryNameTF) {
		
		this.editCategoryNameTF.setText(editCategoryNameTF);
	}


	public MaintainPanel() {

		init();
		addAccountTypeToComboBox();   

		//*********HERE PLACE FOR ADDING LISTENERS***********//		
		//I am getting category name and passing it to the controller as a CategoryFormEvent which is passed as 
		//a parameter to the CategoryListener Interface

		btnCreateAccount.addActionListener(this);
		btnCreateCategory.addActionListener(this);
		btnConfirmChanges.addActionListener(this);
		btnCreateSubcategory.addActionListener(this);
		//btnConfirmChanges_2.addActionListener(this);
		btnConfirmChanges_3.addActionListener(this);
		btnCreate.addActionListener(this);
		btnConfirmChanges_1.addActionListener(this);
		btnRemoveItem.addActionListener(this);





	}



	// Event detected on the create category button 
	public void actionPerformed(ActionEvent e) 
	{

		if(e.getSource().equals(btnCreateAccount))
		{
			System.out.println("Create New Account button clicked\n");
			if(accountListener!=null){
				int getIndexFromCombobox=selectAccountTypeComboBox.getSelectedIndex();
				String accountType=null;
				if(getIndexFromCombobox == 0){
					accountType="manager";
				}
				else{
					accountType="user";
				}
				accountListener.accountAddedPerformed(new AccountFormEvent(enterUsernameTF.getText(), enterPasswordTF1.getText(), enterPasswordTF2.getText(),accountType));
			}
		}
		else if(e.getSource().equals(btnConfirmChanges))		
		{
			System.out.println("Edit existing Account  button clicked");
		}








		else if(e.getSource().equals(btnCreateCategory))
		{
			System.out.println("Create new category button clicked");
			System.out.println("I have to invoke categoryAddedPerformed which takes in category name");

			if(categoryListenr !=null){
				categoryListenr.categoryAddedPerformed(new CategoryFormEvent(enterCategoryNameTF.getText()));
			}
		}
		





		else if(e.getSource().equals(btnConfirmChanges_2))
		{
			System.out.println("Edit Category button clicked");
		}
		else if(e.getSource().equals(btnConfirmChanges_3))
		{
			System.out.println("Edit Sub-Category button clicked");
		}
		else if (e.getSource().equals(btnCreate))
		{
			System.out.println("Create New Item button clicked");
		}
		else if(e.getSource().equals(btnConfirmChanges_1))
		{
			System.out.println("Edit Item button clicked");
		}
		else if(e.getSource().equals(btnRemoveItem))
		{
			System.out.println("Remove Item button clicked");		
		}


	}





	/***************************START COMBO-BOXES*****************************/
	//BUTTONS:
	public void addCreateSubCategoryBtn(ActionListener listen){
		btnCreateSubcategory.addActionListener(listen);
	}
	
	
	/*
	 * Populates ALL Category combo-boxes 
	 * Sets new model for 3 combo-boxes
	 * This method is also used to update all category combo-boxes
	 * When new category is created
	 */
	public void setNewModel(List<String>comboBoxList){
		categoryComboBoxModel= new CategoryComboBoxModel();
		
		categoryComboBoxModel.setComboBoxList(comboBoxList);

		selectCategorycomboBox.setModel(categoryComboBoxModel);
		selectCategoryToEditcomboBox.setModel(categoryComboBoxModel);
		findCategoryComboBox.setModel(categoryComboBoxModel);
	}
	/*
	 * I want to get strings form selected comboBoxes
	 * This will tell me when combo-boxes has been clicked and 
	 * Give me String form particular combo-box
	 */
	public void addselectCategorycomboBoxListener(ActionListener listen){
		selectCategorycomboBox.addActionListener(listen);
	}
	public void addselectCategoryToEditcomboBoxListener(ActionListener listen){
		selectCategoryToEditcomboBox.addActionListener(listen);
	}
	public void addfindCategoryComboBoxListener(ActionListener listen){
		findCategoryComboBox.addActionListener(listen);
	}

	
	public void addbtnConfirmChanges_2Listener(ActionListener listen){
		btnConfirmChanges_2.addActionListener(listen);
	}




	public void setCategoryListenr(CategoryListener categoryListenr) {
		System.out.println("I will accept any object that implemnts CategoryListener Class: MaintainPanel");
		this.categoryListenr = categoryListenr;
	}
	//JoptionPanel to inform that create category field is empty
	public void warnCategoryFieldEmpty(){

		JOptionPane.showMessageDialog(null,
				"Category field empty.",
				"Go away warning",
				JOptionPane.WARNING_MESSAGE);
	}
	public void warnCategoryExist() {
		JOptionPane.showMessageDialog(null,
				"Category Already Exist.",
				"Go away warning",
				JOptionPane.WARNING_MESSAGE);
		
	}
	public void warnSubCategoryFieldEmpty(){
		JOptionPane.showMessageDialog(null,
				"Sub-Category field empty.",
				"Go away warning",
				JOptionPane.WARNING_MESSAGE);
	}
	public void warnCategoryNull(){
		JOptionPane.showMessageDialog(null,
				"Select Category field.",
				"Go away warning",
				JOptionPane.WARNING_MESSAGE);
	}
	

	/***************************END COMBO-BOXES*****************************/






	//adds two strings to account panel to the combo-box
	public void addAccountTypeToComboBox(){
		selectAccountTypeComboBox.addItem("Manager");
		selectAccountTypeComboBox.addItem("User");
	}
	//sets listener passed from TestingMainPanel from Mian_Test


	public JComboBox<String> getSelectCategorycomboBox() {
		return selectCategorycomboBox;
	}


	public JComboBox<String> getSelectCategoryToEditcomboBox() {
		return selectCategoryToEditcomboBox;
	}


	public JComboBox<String> getFindCategoryComboBox() {
		return findCategoryComboBox;
	}


	public void setAccountListener(AccountListner accountListener) {
		System.out.println("I will accept any object that implemnts AccountListener Class: MaintainPanel");	 
		this.accountListener= accountListener;	
	}


	public void clearCategoryTF() {
		enterCategoryNameTF.setText("");
		
	}

	public String getEnterSubCatNameTF() {
		return enterSubCatNameTF.getText();
	}


	public void setEnterSubCatNameTF(JTextField enterSubCatNameTF) {
		this.enterSubCatNameTF = enterSubCatNameTF;
	}


	

}
