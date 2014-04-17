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
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;



@SuppressWarnings("serial")
public class MaintainPanel extends JPanel implements ActionListener {
	private JTextField enterUsernameTF, enterPasswordTF, confirmPasswordTF, editUsernameTF;
	private JPanel MaintainAccountPanel, newAccountPanel, editAccountPanel, MaintainCategoriesPanel, MaintainItemPanel;
	private JLabel lblUserName, lblPassword, lblConfirmPassword, lblSelectType, errorLabel1,errorLabel2, lblSelectAccount, lblUsername, lblSelectType_1, lblStatus;
	private JComboBox<String> selectAccountTypeComboBox, selectAccountToEditComboBox, editAccountTypeComboBox;
	private ButtonGroup statusGroup;
	private JRadioButton rdbtnEnableAccount, rdbtnDisableAccount;
	private JButton btnCreateAccount, btnConfirmChanges;
	private JPanel createItemPanel;
	private JPanel editItemPanel;
	private JLabel lblCategory;
	private JComboBox<String> selectItemCategoryComboBox;
	private JLabel lblSubcategory;
	private JComboBox<String> selectItemSubCatComboBox;
	private JLabel lblBrand;
	private JTextField enterBrandTF;
	private JLabel lblModel;
	private JTextField enterModelTF;
	private JLabel lblPrice;
	private JTextField enterPriceTF;
	private JLabel lblStockLevel;
	private JTextField enterStockLevelTF;
	private JLabel errorLabel7;
	private JButton btnCreateNewItem;
	private JLabel lbloptional;
	private JLabel lblEuro;
	private JLabel lblCategory_1;
	private JComboBox<String> editCategoryComboBox;
	private JLabel lblSubcategory_1;
	private JComboBox<String> editSubCatComboBox;
	private JLabel lblSelectItem;
	private JComboBox<String> selectItemToEditComboBox;
	private JLabel lblBrand_1;
	private JTextField editBrandTF;
	private JLabel lblModel_1;
	private JTextField editModelTF;
	private JLabel lblPrice_1;
	private JTextField editPriceTF;
	private JLabel lblNewLabel;
	private JLabel errorLabel8;
	private JButton btnConfirmItemChanges;
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
	private JComboBox<String> selectCategoryForSubCatComboBox;
	private JLabel lblEnterSubcategoryName;
	private JTextField enterSubCatNameTF;

	private JLabel errorLabel4;
	private JButton btnCreateSubcategory;
	private JLabel lblSelectCategory_1;
	private JComboBox<String> selectCategoryToEditComboBox;
	private JLabel lblEditName;
	private JTextField editCategoryNameTF;
	private JLabel errorLabel5;
	private JButton btnConfirmChanges_2;
	private JLabel lblSelectCategory_2;
	private JComboBox<String> findCatForSubCatToEditComboBox;
	private JLabel lblSelectSubcategory_1;
	private JComboBox<String> selectSubCatToEditComboBox;
	private JLabel lblEditName_1;
	private JTextField editSubCatNameTF;
	private JLabel errorLabel6;
	private JButton btnConfirmChanges_3;
	private JLabel lblNewLabel_1;
	private JComboBox<String> changeSubCatComboBox;
	private CategoryListener categoryListenr;
	private JTextField editAccountPasswordTF;
	private JLabel lblPassword_1;

	CategoryComboBoxModel subCategoryCbM2;
	private CategoryComboBoxModel categoryComboBoxModel;

	//all components
	void init(){

		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(tabbedPane, BorderLayout.CENTER);

		//Create a Button Group for the Account status radio buttons
		statusGroup = new ButtonGroup();

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

		selectCategoryForSubCatComboBox = new JComboBox<String>();
		createSubCatPanel.add(selectCategoryForSubCatComboBox, "cell 3 0,growx");

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


		//SET MODEL FOR CATEGORY
		categoryComboBoxModel= new CategoryComboBoxModel();
		selectCategoryToEditComboBox = new JComboBox<String>();
		selectCategoryForSubCatComboBox.setModel(categoryComboBoxModel);
		
		
		//SET MODEL COR SUB-CATEGORY
		


		editCategoryPanel.add(selectCategoryToEditComboBox, "cell 3 0,growx");

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

		findCatForSubCatToEditComboBox = new JComboBox<String>();
		editSubCatPanel.add(findCatForSubCatToEditComboBox, "cell 3 0,growx");

		lblSelectSubcategory_1 = new JLabel("Select Sub-Category");
		editSubCatPanel.add(lblSelectSubcategory_1, "cell 1 1");

		selectSubCatToEditComboBox = new JComboBox<String>();
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

		selectItemCategoryComboBox = new JComboBox<String>();
		createItemPanel.add(selectItemCategoryComboBox, "cell 3 1 2 1,growx");

		lblSubcategory = new JLabel("Sub-Category");
		createItemPanel.add(lblSubcategory, "cell 1 2");

		selectItemSubCatComboBox = new JComboBox<String>();
		createItemPanel.add(selectItemSubCatComboBox, "cell 3 2 2 1,growx");

		lblBrand = new JLabel("Brand");
		createItemPanel.add(lblBrand, "cell 1 3");

		enterBrandTF = new JTextField();
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

		btnCreateNewItem = new JButton("Create Item");
		createItemPanel.add(btnCreateNewItem, "cell 3 8 2 1");


		editItemPanel = new JPanel();
		editItemPanel.setBorder(new TitledBorder("Edit Existing Item"));
		MaintainItemPanel.add(editItemPanel);
		editItemPanel.setLayout(new MigLayout("", "[][][][136.00,grow][grow]", "[][][][][][][][][][][]"));

		lblCategory_1 = new JLabel("Category");
		editItemPanel.add(lblCategory_1, "cell 1 1");

		editCategoryComboBox = new JComboBox<String>();
		editItemPanel.add(editCategoryComboBox, "cell 3 1 2 1,growx");

		lblSubcategory_1 = new JLabel("Sub-Category");
		editItemPanel.add(lblSubcategory_1, "cell 1 2");

		editSubCatComboBox = new JComboBox<String>();
		editItemPanel.add(editSubCatComboBox, "cell 3 2 2 1,growx");

		lblSelectItem = new JLabel("Select Item");
		editItemPanel.add(lblSelectItem, "cell 1 3");

		selectItemToEditComboBox = new JComboBox<String>();
		editItemPanel.add(selectItemToEditComboBox, "cell 3 3 2 1,growx");

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

		editPriceTF = new JTextField();
		editItemPanel.add(editPriceTF, "cell 3 6,alignx left");
		editPriceTF.setColumns(10);

		lblNewLabel = new JLabel("\u20AC");
		lblNewLabel.setForeground(Color.GRAY);
		editItemPanel.add(lblNewLabel, "cell 4 6");

		lblNewLabel_1 = new JLabel("Move Sub-Cat");
		editItemPanel.add(lblNewLabel_1, "cell 1 7");

		changeSubCatComboBox = new JComboBox<String>();
		editItemPanel.add(changeSubCatComboBox, "cell 3 7 2 1,growx");

		errorLabel8 = new JLabel("");
		errorLabel8.setForeground(Color.RED);
		editItemPanel.add(errorLabel8, "cell 3 8 2 1");

		btnConfirmItemChanges = new JButton("Confirm Changes");
		editItemPanel.add(btnConfirmItemChanges, "cell 3 9");

		btnRemoveItem = new JButton("Remove Item");
		editItemPanel.add(btnRemoveItem, "cell 3 10,growx");

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

		enterPasswordTF = new JTextField();
		newAccountPanel.add(enterPasswordTF, "cell 3 2,growx");
		enterPasswordTF.setColumns(10);

		lblConfirmPassword = new JLabel("Confirm Password");
		newAccountPanel.add(lblConfirmPassword, "cell 1 3");

		confirmPasswordTF = new JTextField();
		newAccountPanel.add(confirmPasswordTF, "cell 3 3,growx");
		confirmPasswordTF.setColumns(10);

		lblSelectType = new JLabel("Select Type");
		newAccountPanel.add(lblSelectType, "cell 1 4");

		selectAccountTypeComboBox = new JComboBox<String>();
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

		selectAccountToEditComboBox = new JComboBox<String>();
		editAccountPanel.add(selectAccountToEditComboBox, "cell 3 1 2 1,growx");

		lblUsername = new JLabel("Username");
		editAccountPanel.add(lblUsername, "cell 1 2");

		editUsernameTF = new JTextField();
		editAccountPanel.add(editUsernameTF, "cell 3 2 2 1,growx");
		editUsernameTF.setColumns(10);

		lblSelectType_1 = new JLabel("Select Type");
		editAccountPanel.add(lblSelectType_1, "cell 1 3");

		editAccountTypeComboBox = new JComboBox<String>();
		editAccountPanel.add(editAccountTypeComboBox, "cell 3 3 2 1,growx");

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




	}


	public JTextField getEditCategoryNameTF() {
		return editCategoryNameTF;
	}


	public void setEditCategoryNameTF(String editCategoryNameTF) {

		this.editCategoryNameTF.setText(editCategoryNameTF);
	}


	public MaintainPanel() {

		init();
		addAccountTypeToComboBox();  //adds the Strings Manager and Sales Staff to the type selection combo box 

		//*********HERE PLACE FOR ADDING LISTENERS***********//		
		//I am getting category name and passing it to the controller as a CategoryFormEvent which is passed as 
		//a parameter to the CategoryListener Interface

		btnCreateCategory.addActionListener(this);
		btnConfirmChanges.addActionListener(this);
		//btnConfirmChanges_3.addActionListener(this);

	}



	// Event detected on the create category button 
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(btnCreateCategory))
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
	}


	//Creating the BUTTON LISTENERS:
	
	public void addSubmitSubCategoryBtn(ActionListener listen){
		btnConfirmChanges_3.addActionListener(listen);
		
	}
	public void addEditCategoryBtn(ActionListener listen){
		btnConfirmChanges_2.addActionListener(listen);
	}
	
	//Creates the Add New SubCategory Button Listener
	public void addCreateSubCategoryBtn(ActionListener listen){
		btnCreateSubcategory.addActionListener(listen);
	}
	//Creates the Add New Item Button Listener
	public void addCreateItemBtn(ActionListener listen){
		btnCreateNewItem.addActionListener(listen);
	}
	//Create the Edit Item Button Listener
	public void addEditItemBtn(ActionListener listen){
		btnConfirmChanges.addActionListener(listen);
	}
	//Create the Remove Item Button Listener
	public void addRemoveItemBtn(ActionListener listen){
		btnRemoveItem.addActionListener(listen);
	}
	//Creates the Add New ACcount Button Listener
	public void addCreateAccountBtn(ActionListener listen){
		btnCreateAccount.addActionListener(listen);
	}




	/* METHOD: setCategoryModels is for Category Combo BOxes
	 * Populates ALL Category combo-boxes in the Maintenance Tab
	 * When new category is created this method is used to update all the Category Combo Boxes in the Maintenance Tab
	 */
	public void setCategoryModels(List<String>comboBoxList){

		categoryComboBoxModel= new CategoryComboBoxModel();
		CategoryComboBoxModel categoryComboBoxModel= new CategoryComboBoxModel();
		CategoryComboBoxModel categoryComboBoxModel2= new CategoryComboBoxModel();
		CategoryComboBoxModel categoryComboBoxModel3= new CategoryComboBoxModel();
		CategoryComboBoxModel categoryComboBoxModel4= new CategoryComboBoxModel();
		CategoryComboBoxModel categoryComboBoxModel5= new CategoryComboBoxModel();
		
		categoryComboBoxModel.setComboBoxList(comboBoxList);
		categoryComboBoxModel2.setComboBoxList(comboBoxList);
		categoryComboBoxModel3.setComboBoxList(comboBoxList);
		categoryComboBoxModel4.setComboBoxList(comboBoxList);
		categoryComboBoxModel5.setComboBoxList(comboBoxList);
		//5 CATEGORY COMBO BOXES IN THE MAINTAIN PANEL
		selectCategoryForSubCatComboBox.setModel(categoryComboBoxModel);
		selectCategoryToEditComboBox.setModel(categoryComboBoxModel2);
		findCatForSubCatToEditComboBox.setModel(categoryComboBoxModel3);
		selectItemCategoryComboBox.setModel(categoryComboBoxModel4);
		editCategoryComboBox.setModel(categoryComboBoxModel5);
			}
	
	
	//list of sub-categories for MaintianCategries and MaintainItems Panel
	public void setSubCategoryModels(List<String>comboBoxList){
		CategoryComboBoxModel subCategoryCbM= new CategoryComboBoxModel();
		subCategoryCbM.setComboBoxList(comboBoxList);
		selectSubCatToEditComboBox.setModel(subCategoryCbM);
	}
	
	//sets list on Maintain Item sub-category(create new item) COMBOB0X
	public void setSubCategoryModelItems(List<String>comboBoxList){
		 subCategoryCbM2= new CategoryComboBoxModel();
		subCategoryCbM2.setComboBoxList(comboBoxList);
		selectItemSubCatComboBox.setModel(subCategoryCbM2);
		
	}
	
	//sets list on Maintain Items sub-category (edit existing item) COMBOBOX
	public void setSubCategoryModelItems2(List<String>comboBoxList){
		
		CategoryComboBoxModel subCategoryCbM3= new CategoryComboBoxModel();
		subCategoryCbM3.setComboBoxList(comboBoxList);
		editSubCatComboBox.setModel(subCategoryCbM3);
	}
	
	//set list on Maintain Items for select item combo box
	public void setItemModle(List<String>comboBoxList){
		CategoryComboBoxModel selectItemModel= new CategoryComboBoxModel();
		selectItemModel.setComboBoxList(comboBoxList);
		selectItemToEditComboBox.setModel(selectItemModel);
	}

	
	
	//COMBO BOX LISTENERS FOR MAINTAIN PANEL
	//Create SUBCAT Panel
	public void addSelectCategoryForSubCatComboBoxListener(ActionListener listen){
		selectCategoryForSubCatComboBox.addActionListener(listen);
	}
	//EDIT CATEGORY PANEL
	public void addselectCategoryToEditcomboBoxListener(ActionListener listen){
		selectCategoryToEditComboBox.addActionListener(listen);
	}
	//CREATE SUBCAT PANEL
	public void addfindCatForSubCatToEditComboBoxListener(ActionListener listen){
		findCatForSubCatToEditComboBox.addActionListener(listen);
	}
<<<<<<< HEAD
	public void addbtnConfirmCategoryChangesListener(ActionListener listen){
		btnConfirmChanges_2.addActionListener(listen);
=======
	
	
	
	//EDIT ITEM PANEL - Picks SubCat for Items to be display
	public void addFindItemsInSubCatListener( MouseListener listen){
		selectItemSubCatComboBox.addMouseListener(listen);
		
	}
	
	//Edit sub-category picks sub-category and set it on text area for editing 
	public void addEditSubCategory(ActionListener listen){
		
		selectSubCatToEditComboBox.addActionListener(listen);
>>>>>>> 41a9cf09b4def1488732f87b1a5b41f9ea866371
	}

	
	
	//WIERD CATEGORY LISTENERS
	public void setCategoryListenr(CategoryListener categoryListenr) {
		System.out.println("I will accept any object that implemnts CategoryListener Class: MaintainPanel");
		this.categoryListenr = categoryListenr;
	}
	
	
	/*
	 * *********************************************************************
	 * MAINTAIN ITEMS
	 * *********************************************************************
	 */
	//COMBOBOX LEFT PANEL  
	public void addCategoryListnerCreateItem(ActionListener listen){
		selectItemCategoryComboBox.addActionListener(listen);
	}
	//COMBOBOX RIGHT PANEL  
	public void addEditCategoryComboBoxItem(ActionListener listen){
		editCategoryComboBox.addActionListener(listen);
	}
	//COMBOBOX RIGHT PANEL SUB-CATEGORY get and disply all items
	public void addEditSubCatComboBox(ActionListener listen){
		editSubCatComboBox.addActionListener(listen);
	}
	
	//COMBOBOX	RIGHT PANEL get item and populate fields
	public void addSelectItemToEditComboBox(ActionListener listen){
		selectItemToEditComboBox.addActionListener(listen);
	}

	
	
	
	
	
	
	
	

	public JComboBox<String> getEditCategoryComboBox() {
		return editCategoryComboBox;
	}

	/*
	 * ****************** WARNING MESSAGES HANDLERS **********************
	 * NEEDED BY CONTROLLER TO WARN THE USER WHEN THEY HAVE INPUT BAD DATA
	 * ********** ALL WARNING OUTPUT METHODS SHOULD GO HERE **************
	 */
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
	/*
	 * WARNING MESSAGES FOR THE CREATE SUBCATEGORY FORM
	 */
	public void warnCreateSubCatFormErrors(ArrayList<String> errors){

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
	/*
	 * WARNING MESSAGES FOR THE CREATE ITEM FORM
	 */
	public void warnCreateItemFormErrors(ArrayList<String> errors){

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
	/*
	 * WARNING MESSAGES FOR THE EDIT ITEM FORM
	 */
	public void warnEditItemFormErrors(ArrayList<String> errors){

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
	/*
	 * WARNING MESSAGES FOR THE CREATE ACCOUNT FORM
	 */
	public void warnCreateAccountFormErrors(ArrayList<String> errors){

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


	/************************WORKER METHODS**********************************/
	/********************** SETS   &   GETS *********************************/

	//This method sets the values for the Account Type Combo Box on the Create Account Panel
	public void addAccountTypeToComboBox(){
		selectAccountTypeComboBox.addItem("Manager");
		selectAccountTypeComboBox.addItem("Sales Staff");
	}

	/*
	 * ***************METHODS FOR GETTING GUI ITEMS********************
	 * NEEDED BY THE CONTROLLER TO ACCESS THE DATA ENTERED BY THE USER
	 * *********ALL GETTER METHODS SHOULD GO IN HERE ******************
	 */

	//EDIT CATEGORY GETTER METHODS
	public JComboBox<String> getFindCategoryComboBox() {
		return findCatForSubCatToEditComboBox;
	}
	public JComboBox<String> getSelectCategoryToEditcomboBox() {
		return selectCategoryToEditComboBox;
	}

	//CREATE NEW SUBCATEGORY GETTER METHODS
	public JComboBox<String> getSelectCategorycomboBox() {
		return selectCategoryForSubCatComboBox;
	}
	public String getEnterSubCatNameTF() {
		return enterSubCatNameTF.getText();
	}


	//CREATE NEW ITEM PANEL GETTER METHODS
	public JComboBox<String> getNewItemCategoryComboBox() {
		return selectItemCategoryComboBox;
	}
	public JComboBox<String> getNewItemSubCatComboBox() {
		return selectItemSubCatComboBox;
	}
	public String getEnterBrandTF() {
		return enterBrandTF.getText().toString();
	}
	public String getEnterModelTF() {
		return enterModelTF.getText().toString();
	}
	public String getEnterPriceTF() {
		return enterPriceTF.getText().toString();
	}
	public String getEnterStockLevelTF() {
		return enterStockLevelTF.getText().toString();
	}
	//EDIT EXISTING ITEM PANEL GETTER METHODS
	public JComboBox<String> getItemToEditSubCatComboBox() {
		return editSubCatComboBox;
	}
	public JComboBox<String> getItemToEditComboBox() {
		return selectItemToEditComboBox;
	}
	public String getEditBrandTF() {
		return editBrandTF.getText();
	}
	public String getEditModelTF() {
		return editModelTF.getText();
	}
	public String getEditPriceTF() {
		return editPriceTF.getText();
	}
	public JComboBox<String> getItemMoveSubCatComboBox() {
		return changeSubCatComboBox;
	}
	//CREATE ACCOUNT PANEL GETTER METHODS
	public String getEnterUsernameTF() {
		return enterUsernameTF.getText();
	}
	public String getEnterPasswordTF() {
		return enterPasswordTF.getText();
	}
	public String getConfirmPasswordTF() {
		return confirmPasswordTF.getText();
	}
	public JComboBox<String> getSelectAccountTypeComboBox() {
		return selectAccountTypeComboBox;
	}

	/*
	 * ************** METHODS FOR CLEARING FORMS *************************
	 *  THERE IS A METHOD TO CLEAR GUI ITEMS FOR EVERY PANEL IN THIS CLASS
	 *  ******* USED AT END OF EVERY BUTTON ACTION LISTENER **************
	 *  ******************************************************************
	 */
	public void clearNewCategoryForm() {
		enterCategoryNameTF.setText("");
	}
	public void clearEditCategoryForm(){
		//selectCategoryToEditComboBox.setSelectedIndex(0);
		editCategoryNameTF.setText("");
	}
	
	public void clearNewSubCatForm(){
		//selectCategoryForSubCatComboBox.setSelectedIndex(0);
		enterSubCatNameTF.setText("");
	}
	public void clearEditSubCatForm(){
		selectSubCatToEditComboBox.setSelectedItem("");
		editSubCatNameTF.setText("");
	}
	public JTextField getEditSubCatNameTF() {
		return editSubCatNameTF;
	}


	public void getEditSubCatNameTF(String subCatEdit) {
		 editSubCatNameTF.setText(subCatEdit);
	}


	public JComboBox<String> getSelectSubCatToEditComboBox() {
		return selectSubCatToEditComboBox;
	}

	public void clearNewItemForm(){
	//	selectItemCategoryComboBox.setSelectedItem(0);
		
	//	selectItemSubCatComboBox.setSelectedItem(0);
		enterBrandTF.setText("");
		enterModelTF.setText("");
		enterPriceTF.setText("");
		enterStockLevelTF.setText("");		
	}
	public void clearEditItemForm(){
		//editSubCatComboBox.setSelectedItem(0);
		//selectItemToEditComboBox.setSelectedItem(0);
		editBrandTF.setText("");
		editModelTF.setText("");
		editPriceTF.setText("");
		//changeSubCatComboBox.setSelectedItem(0);
	}
	public void clearNewAccountForm(){
		enterUsernameTF.setText("");
		enterPasswordTF.setText("");
		confirmPasswordTF.setText("");
		//selectAccountTypeComboBox.setSelectedItem(0);
	}
	public void clearEditAccountForm(){
		selectAccountToEditComboBox.setSelectedItem(0);
		editUsernameTF.setText("");
		editAccountTypeComboBox.setSelectedItem(0);
		editAccountPasswordTF.setText("");
		rdbtnEnableAccount.setSelected(false);
		rdbtnDisableAccount.setSelected(false);
	}


	public void setSelectSubCatToEditComboBox(String subCategoryEdit) {
		editSubCatNameTF.setText(subCategoryEdit);
		
	}


	public void setEditSubCatComboBox() {
		//editSubCatComboBox.setSelectedItem(0);
	}
	
	
	
	
}
