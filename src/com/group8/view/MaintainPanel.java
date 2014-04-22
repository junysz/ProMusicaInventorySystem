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
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Label;



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
	private CategoryListener categoryListenr;
	private JTextField editAccountPasswordTF;
	private JLabel lblPassword_1;

	CategoryComboBoxModel subCategoryCbM2;
	private CategoryComboBoxModel categoryComboBoxModel;
	private JComboBox<String> changeSubCatComboBox;
	private Label label;

	//all components
	void init(){

		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(11, 5, 11, 5));
		add(tabbedPane, BorderLayout.CENTER);

		//Create a Button Group for the Account status radio buttons
		statusGroup = new ButtonGroup();

		MaintainCategoriesPanel = new JPanel();
		MaintainCategoriesPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		tabbedPane.addTab("Maintain Categories", null, MaintainCategoriesPanel, null);
		MaintainCategoriesPanel.setLayout(new GridLayout(2, 2, 10, 10));

		createCategoryPanel = new JPanel();
		MaintainCategoriesPanel.add(createCategoryPanel);
		createCategoryPanel.setLayout(new MigLayout("", "[][][][grow]", "[60.00][][][][][][][][][]"));

		createCategoryPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Create New Category", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		errorLabel3 = new JLabel("");
		errorLabel3.setForeground(Color.RED);
		createCategoryPanel.add(errorLabel3, "cell 3 1,alignx right");

		lblEnterName = new JLabel("Enter Category Name");
		lblEnterName.setFont(new Font("Cambria", Font.BOLD, 13));
		createCategoryPanel.add(lblEnterName, "cell 1 5");

		enterCategoryNameTF = new JTextField();
		createCategoryPanel.add(enterCategoryNameTF, "cell 3 5,growx");
		enterCategoryNameTF.setColumns(10);

		btnCreateCategory = new JButton("Create Category");
		btnCreateCategory.setFont(new Font("Cambria", Font.BOLD, 13));

		createCategoryPanel.add(btnCreateCategory, "cell 3 8,aligny bottom");

		createSubCatPanel = new JPanel();
		MaintainCategoriesPanel.add(createSubCatPanel);

		createSubCatPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Create Sub-Category", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		createSubCatPanel.setLayout(new MigLayout("", "[][][][grow]", "[60][][][][][][][][][][][][][][][]"));

		errorLabel4 = new JLabel("");
		createSubCatPanel.add(errorLabel4, "flowx,cell 3 2");

		editCategoryPanel = new JPanel();
		MaintainCategoriesPanel.add(editCategoryPanel);
		editCategoryPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Edit Category", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		editCategoryPanel.setLayout(new MigLayout("", "[][][][grow]", "[62.00][][][][][][][][][]"));


		//SET MODEL FOR CATEGORY
		categoryComboBoxModel= new CategoryComboBoxModel();

		lblSelectCategory = new JLabel("Select Category");
		lblSelectCategory.setFont(new Font("Cambria", Font.BOLD, 13));
		createSubCatPanel.add(lblSelectCategory, "cell 1 7");

		selectCategoryForSubCatComboBox = new JComboBox<String>();
		createSubCatPanel.add(selectCategoryForSubCatComboBox, "cell 3 7,growx");
		selectCategoryForSubCatComboBox.setModel(categoryComboBoxModel);

		lblEnterSubcategoryName = new JLabel("Enter Sub-Category Name");
		lblEnterSubcategoryName.setFont(new Font("Cambria", Font.BOLD, 13));
		createSubCatPanel.add(lblEnterSubcategoryName, "cell 1 9");

		enterSubCatNameTF = new JTextField();
		createSubCatPanel.add(enterSubCatNameTF, "cell 3 9,growx");
		enterSubCatNameTF.setColumns(10);

		btnCreateSubcategory = new JButton("Create Sub-Category");
		btnCreateSubcategory.setFont(new Font("Cambria", Font.BOLD, 13));
		createSubCatPanel.add(btnCreateSubcategory, "cell 3 13");

		lblSelectCategory_1 = new JLabel("Select Category");
		lblSelectCategory_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editCategoryPanel.add(lblSelectCategory_1, "cell 1 2");
		selectCategoryToEditComboBox = new JComboBox<String>();


		//SET MODEL COR SUB-CATEGORY



		editCategoryPanel.add(selectCategoryToEditComboBox, "cell 3 2,growx");

		errorLabel5 = new JLabel("");
		errorLabel5.setForeground(Color.RED);
		editCategoryPanel.add(errorLabel5, "cell 3 4");

		lblEditName = new JLabel("Edit Name");
		lblEditName.setFont(new Font("Cambria", Font.BOLD, 13));
		editCategoryPanel.add(lblEditName, "cell 1 5");

		editCategoryNameTF = new JTextField();

		editCategoryPanel.add(editCategoryNameTF, "cell 3 5,growx");
		editCategoryNameTF.setColumns(10);

		btnConfirmChanges_2 = new JButton("Confirm Changes");
		btnConfirmChanges_2.setFont(new Font("Cambria", Font.BOLD, 13));
		editCategoryPanel.add(btnConfirmChanges_2, "cell 3 9");

		editSubCatPanel = new JPanel();
		MaintainCategoriesPanel.add(editSubCatPanel);

		editSubCatPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Edit Sub-Category", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		editSubCatPanel.setLayout(new MigLayout("", "[][][][grow]", "[50][][][][][][][][][][][]"));

		lblSelectCategory_2 = new JLabel("Select Category");
		lblSelectCategory_2.setFont(new Font("Cambria", Font.BOLD, 13));
		editSubCatPanel.add(lblSelectCategory_2, "cell 1 4");

		errorLabel6 = new JLabel("");
		errorLabel6.setForeground(Color.RED);
		editSubCatPanel.add(errorLabel6, "flowx,cell 3 4");

		lblSelectSubcategory_1 = new JLabel("Select Sub-Category");
		lblSelectSubcategory_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editSubCatPanel.add(lblSelectSubcategory_1, "cell 1 6");

		selectSubCatToEditComboBox = new JComboBox<String>();
		editSubCatPanel.add(selectSubCatToEditComboBox, "cell 3 6,growx");

		lblEditName_1 = new JLabel("Edit Name");
		lblEditName_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editSubCatPanel.add(lblEditName_1, "cell 1 8");

		editSubCatNameTF = new JTextField();
		editSubCatPanel.add(editSubCatNameTF, "cell 3 8,growx");
		editSubCatNameTF.setColumns(10);

		btnConfirmChanges_3 = new JButton("Confirm Changes");
		btnConfirmChanges_3.setFont(new Font("Cambria", Font.BOLD, 13));
		editSubCatPanel.add(btnConfirmChanges_3, "cell 3 11");

		findCatForSubCatToEditComboBox = new JComboBox<String>();
		editSubCatPanel.add(findCatForSubCatToEditComboBox, "cell 3 4,growx");

		MaintainItemPanel = new JPanel();
		MaintainItemPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		tabbedPane.addTab("Maintain Items", null, MaintainItemPanel, null);
		MaintainItemPanel.setLayout(new GridLayout(1, 2, 10, 10));

		createItemPanel = new JPanel();
		createItemPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Create New Item", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		MaintainItemPanel.add(createItemPanel);
		createItemPanel.setLayout(new MigLayout("", "[][][][131.00][grow]", "[60][20][][][][][][70][][][]"));

		lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Cambria", Font.BOLD, 13));
		createItemPanel.add(lblCategory, "cell 1 1");

		selectItemCategoryComboBox = new JComboBox<String>();
		createItemPanel.add(selectItemCategoryComboBox, "cell 3 1 2 1,growx");

		lblSubcategory = new JLabel("SubCategory");
		lblSubcategory.setFont(new Font("Cambria", Font.BOLD, 13));
		createItemPanel.add(lblSubcategory, "cell 1 2");

		selectItemSubCatComboBox = new JComboBox<String>();
		createItemPanel.add(selectItemSubCatComboBox, "cell 3 2 2 1,growx");

		lblBrand = new JLabel("Brand");
		lblBrand.setFont(new Font("Cambria", Font.BOLD, 13));
		createItemPanel.add(lblBrand, "cell 1 3");

		enterBrandTF = new JTextField();
		createItemPanel.add(enterBrandTF, "cell 3 3 2 1,growx");
		enterBrandTF.setColumns(10);

		lblModel = new JLabel("Model");
		lblModel.setFont(new Font("Cambria", Font.BOLD, 13));
		createItemPanel.add(lblModel, "cell 1 4");

		enterModelTF = new JTextField();
		createItemPanel.add(enterModelTF, "cell 3 4 2 1,growx");
		enterModelTF.setColumns(10);

		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Cambria", Font.BOLD, 13));
		createItemPanel.add(lblPrice, "cell 1 5");

		enterPriceTF = new JTextField();
		createItemPanel.add(enterPriceTF, "cell 3 5,growx");
		enterPriceTF.setColumns(10);

		lblEuro = new JLabel("\u20AC");
		lblEuro.setForeground(Color.GRAY);
		createItemPanel.add(lblEuro, "cell 4 5");

		lblStockLevel = new JLabel("Stock Level");
		lblStockLevel.setFont(new Font("Cambria", Font.BOLD, 13));
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
		btnCreateNewItem.setFont(new Font("Cambria", Font.BOLD, 13));
		createItemPanel.add(btnCreateNewItem, "cell 3 9");


		editItemPanel = new JPanel();
		editItemPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Edit Existing Item", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		MaintainItemPanel.add(editItemPanel);
		editItemPanel.setLayout(new MigLayout("", "[][][][136.00,grow][grow]", "[60][][][][][][][25][][][60][][][][][]"));

		lblCategory_1 = new JLabel("Category");
		lblCategory_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editItemPanel.add(lblCategory_1, "cell 1 1");

		editCategoryComboBox = new JComboBox<String>();
		editItemPanel.add(editCategoryComboBox, "cell 3 1 2 1,growx");

		lblSubcategory_1 = new JLabel("Sub-Category");
		lblSubcategory_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editItemPanel.add(lblSubcategory_1, "cell 1 2");

		editSubCatComboBox = new JComboBox<String>();
		editItemPanel.add(editSubCatComboBox, "cell 3 2 2 1,growx");

		lblSelectItem = new JLabel("Select Item");
		lblSelectItem.setFont(new Font("Cambria", Font.BOLD, 13));
		editItemPanel.add(lblSelectItem, "cell 1 3");

		selectItemToEditComboBox = new JComboBox<String>();
		editItemPanel.add(selectItemToEditComboBox, "cell 3 3 2 1,growx");

		lblBrand_1 = new JLabel("Brand");
		lblBrand_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editItemPanel.add(lblBrand_1, "cell 1 4");

		editBrandTF = new JTextField();
		editItemPanel.add(editBrandTF, "cell 3 4 2 1,growx");
		editBrandTF.setColumns(10);

		lblModel_1 = new JLabel("Model");
		lblModel_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editItemPanel.add(lblModel_1, "cell 1 5");

		editModelTF = new JTextField();
		editItemPanel.add(editModelTF, "cell 3 5 2 1,growx");
		editModelTF.setColumns(10);

		lblPrice_1 = new JLabel("Price");
		lblPrice_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editItemPanel.add(lblPrice_1, "cell 1 6");

		editPriceTF = new JTextField();
		editItemPanel.add(editPriceTF, "flowx,cell 3 6,alignx left");
		editPriceTF.setColumns(10);

		errorLabel8 = new JLabel("");
		errorLabel8.setForeground(Color.RED);
		editItemPanel.add(errorLabel8, "flowx,cell 3 8 2 1");

		label = new Label("Move SubCategory");
		label.setFont(new Font("Cambria", Font.BOLD, 13));
		editItemPanel.add(label, "cell 1 9");

		changeSubCatComboBox = new JComboBox<String>();
		editItemPanel.add(changeSubCatComboBox, "cell 3 9,growx");

		btnConfirmItemChanges = new JButton("Confirm Changes");
		btnConfirmItemChanges.setFont(new Font("Cambria", Font.BOLD, 13));
		editItemPanel.add(btnConfirmItemChanges, "cell 3 10");

		btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setForeground(new Color(255, 0, 0));
		btnRemoveItem.setFont(new Font("Cambria", Font.BOLD, 13));
		editItemPanel.add(btnRemoveItem, "cell 3 14,growx");

		lblNewLabel = new JLabel("\u20AC");
		lblNewLabel.setForeground(Color.GRAY);
		editItemPanel.add(lblNewLabel, "cell 3 6");

		MaintainAccountPanel = new JPanel();
		MaintainAccountPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		tabbedPane.addTab("Maintain Accounts", null, MaintainAccountPanel, null);
		MaintainAccountPanel.setLayout(new GridLayout(1, 2, 10, 10));

		newAccountPanel = new JPanel();
		MaintainAccountPanel.add(newAccountPanel);
		newAccountPanel.setLayout(new MigLayout("", "[][][][grow]", "[120][][][][][90][][]"));
		//SET A TITLE BORDER FOR THE PANEL
		newAccountPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Create New Account", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Cambria", Font.BOLD, 13));
		newAccountPanel.add(lblUserName, "cell 1 1");

		enterUsernameTF = new JTextField();
		newAccountPanel.add(enterUsernameTF, "cell 3 1,growx");
		enterUsernameTF.setColumns(10);

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Cambria", Font.BOLD, 13));
		newAccountPanel.add(lblPassword, "cell 1 2");

		enterPasswordTF = new JTextField();
		newAccountPanel.add(enterPasswordTF, "cell 3 2,growx");
		enterPasswordTF.setColumns(10);

		lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Cambria", Font.BOLD, 13));
		newAccountPanel.add(lblConfirmPassword, "cell 1 3");

		confirmPasswordTF = new JTextField();
		newAccountPanel.add(confirmPasswordTF, "cell 3 3,growx");
		confirmPasswordTF.setColumns(10);

		lblSelectType = new JLabel("Select Type");
		lblSelectType.setFont(new Font("Cambria", Font.BOLD, 13));
		newAccountPanel.add(lblSelectType, "cell 1 4");

		selectAccountTypeComboBox = new JComboBox<String>();
		newAccountPanel.add(selectAccountTypeComboBox, "cell 3 4,growx");

		errorLabel1 = new JLabel();
		errorLabel1.setForeground(Color.RED);
		newAccountPanel.add(errorLabel1, "cell 3 5");
		errorLabel1.setVisible(false);

		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setFont(new Font("Cambria", Font.BOLD, 13));
		newAccountPanel.add(btnCreateAccount, "cell 3 6");

		editAccountPanel = new JPanel();
		MaintainAccountPanel.add(editAccountPanel);
		editAccountPanel.setLayout(new MigLayout("", "[][][][120.00,grow][grow]", "[120][][][][][][][80][][]"));
		//SET A TITLE BORDER FOR THE PANEL
		editAccountPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Edit Existing Account", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		lblSelectAccount = new JLabel("Select Account");
		lblSelectAccount.setFont(new Font("Cambria", Font.BOLD, 13));
		editAccountPanel.add(lblSelectAccount, "cell 1 1");

		selectAccountToEditComboBox = new JComboBox<String>();
		editAccountPanel.add(selectAccountToEditComboBox, "cell 3 1 2 1,growx");

		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Cambria", Font.BOLD, 13));
		editAccountPanel.add(lblUsername, "cell 1 2");

		editUsernameTF = new JTextField();
		editAccountPanel.add(editUsernameTF, "cell 3 2 2 1,growx");
		editUsernameTF.setColumns(10);

		lblSelectType_1 = new JLabel("Select Type");
		lblSelectType_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editAccountPanel.add(lblSelectType_1, "cell 1 3");

		editAccountTypeComboBox = new JComboBox<String>();
		editAccountPanel.add(editAccountTypeComboBox, "cell 3 3 2 1,growx");

		lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Cambria", Font.BOLD, 13));
		editAccountPanel.add(lblPassword_1, "cell 1 4");

		editAccountPasswordTF = new JTextField();
		editAccountPanel.add(editAccountPasswordTF, "cell 3 4 2 1,growx");
		editAccountPasswordTF.setColumns(10);

		errorLabel2 = new JLabel("");
		errorLabel2.setForeground(Color.RED);
		editAccountPanel.add(errorLabel2, "cell 3 5 2 1");
		errorLabel2.setVisible(false);

		lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Cambria", Font.BOLD, 13));
		editAccountPanel.add(lblStatus, "cell 1 6");

		rdbtnEnableAccount = new JRadioButton("Enabled");

		rdbtnEnableAccount.setFont(new Font("Cambria", Font.BOLD, 12));
		editAccountPanel.add(rdbtnEnableAccount, "cell 3 6,alignx center");

		rdbtnEnableAccount.setSelected(true);
		editAccountPanel.add(rdbtnEnableAccount, "cell 3 6");

		statusGroup.add(rdbtnEnableAccount);

		rdbtnDisableAccount = new JRadioButton("Disabled");
		rdbtnDisableAccount.setFont(new Font("Cambria", Font.BOLD, 12));
		editAccountPanel.add(rdbtnDisableAccount, "cell 4 6");
		statusGroup.add(rdbtnDisableAccount);


		btnConfirmChanges = new JButton("Confirm Changes");
		btnConfirmChanges.setFont(new Font("Cambria", Font.BOLD, 13));
		editAccountPanel.add(btnConfirmChanges, "cell 3 8,alignx center");




	}


	public ButtonGroup getStatusGroup() {
		return statusGroup;
	}


	public JComboBox<String> getSelectAccountToEditComboBox() {
		return selectAccountToEditComboBox;
	}


	public JTextField getEditCategoryNameTF() {
		return editCategoryNameTF;
	}


	public void setEditCategoryNameTF(String editCategoryNameTF) {

		this.editCategoryNameTF.setText(editCategoryNameTF);
	}

	public JTextField getEditUsername() {
		return editUsernameTF;
	}

	public JComboBox<String>   getSelectAccountTypeCB() {
		return  selectAccountTypeComboBox;
	}

	public MaintainPanel() {

		init();
		addAccountTypeToComboBox();   

		//*********HERE PLACE FOR ADDING LISTENERS***********//		
		//I am getting category name and passing it to the controller as a CategoryFormEvent which is passed as 
		//a parameter to the CategoryListener Interface

		btnCreateCategory.addActionListener(this);
		//btnConfirmChanges.addActionListener(this);

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
	public JButton getBtnCreateNewItem() {
		return btnCreateNewItem;
	}

	public void addConfirmChangesAccount(ActionListener listen){
		btnConfirmChanges.addActionListener(listen);
	}
	//Create the Remove Item Button Listener
	/*public void addRemoveItemBtn(ActionListener listen){

	}*/
	public JButton getBtnRemoveItem() {
		return btnRemoveItem;
	}


	//Creates the Add New ACcount Button Listener
	public void addCreateAccountBtn(ActionListener listen){
		btnCreateAccount.addActionListener(listen);
	}

	public void addConfirmItemChangesBtn(ActionListener listen){
		btnConfirmItemChanges.addActionListener(listen);
	}


	public JButton getBtnConfirmItemChanges() {
		return btnConfirmItemChanges;
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
		changeSubCatComboBox.setModel(subCategoryCbM3);

		CategoryComboBoxModel subCategoryCbM4= new CategoryComboBoxModel();
		subCategoryCbM4.setComboBoxList(comboBoxList);
		changeSubCatComboBox.setModel(subCategoryCbM4);


	}

	//set list on Maintain Items for select item combo box
	public void setItemModle(List<String>comboBoxList){
		CategoryComboBoxModel selectItemModel= new CategoryComboBoxModel();
		selectItemModel.setComboBoxList(comboBoxList);
		selectItemToEditComboBox.setModel(selectItemModel);
	}

	public void setAccountModel(List<String> accountNames) {
		CategoryComboBoxModel selectItemModel= new CategoryComboBoxModel();
		selectItemModel.setComboBoxList(accountNames);
		selectAccountToEditComboBox.setModel(selectItemModel);
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



	//EDIT ITEM PANEL - Picks SubCat for Items to be display
	/*public void addFindItemsInSubCatListener( MouseListener listen){
		selectItemSubCatComboBox.addMouseListener(listen);

	}*/

	//Edit sub-category picks sub-category and set it on text area for editing 
	public void addEditSubCategory(ActionListener listen){

		selectSubCatToEditComboBox.addActionListener(listen);
	}



	//WIERD CATEGORY LISTENERS
	public void setCategoryListenr(CategoryListener categoryListenr) {

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


	/*
	 * *********************************************************************
	 * MAINTAIN Accounts
	 * *********************************************************************
	 */

	public void addSelectAccountToEditComboBox(ActionListener listen){
		selectAccountToEditComboBox.addActionListener(listen);
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
		editAccountTypeComboBox.addItem("Manager");
		editAccountTypeComboBox.addItem("Sales Staff");
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
	public void setEditBrandTF(String brand){
		editBrandTF.setText(brand);
	}
	public void setEditModelTF(String model){
		editModelTF.setText(model);
	}
	public void setEditPriceTF(String price){

		editPriceTF.setText(price);
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
		clearAllSub_CatComboBoxes();
		enterCategoryNameTF.setText("");
	}
	public void clearEditCategoryForm(){
		//selectCategoryToEditComboBox.setSelectedIndex(0);
		editCategoryNameTF.setText("");
	}

	public void clearAllSub_CatComboBoxes(){
		List<String>clearSubCat= new ArrayList<>();
		setSubCategoryModels(clearSubCat);
		setSubCategoryModelItems(clearSubCat);
		setSubCategoryModelItems2(clearSubCat);
	}

	public void clearNewSubCatForm(){
		//clear sub-Category comboBox
		clearAllSub_CatComboBoxes();
		enterSubCatNameTF.setText("");
		editSubCatNameTF.setText("");
	}
	public void clearEditSubCatForm(){
		clearAllSub_CatComboBoxes();
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
		List<String>clearSubCat= new ArrayList<>();
		setSubCategoryModelItems2(clearSubCat);
		//clear sub-Category comboBox
		setCategoryModels(clearSubCat);
		setItemModle(clearSubCat);

		enterBrandTF.setText("");
		enterModelTF.setText("");
		enterPriceTF.setText("");
		enterStockLevelTF.setText("");		
	}
	public void clearEditItemForm(){
		List<String>clearSubCat= new ArrayList<>();
		setSubCategoryModelItems2(clearSubCat);

		setItemModle(clearSubCat);
		editBrandTF.setText("");
		editModelTF.setText("");
		editPriceTF.setText("");
	}
	public void clearNewAccountForm(){
		enterUsernameTF.setText("");
		enterPasswordTF.setText("");
		confirmPasswordTF.setText("");

	}
	public void clearEditAccountForm(){
		//selectAccountToEditComboBox.setSelectedItem(0);
		editUsernameTF.setText("");
		//	editAccountTypeComboBox.setSelectedItem(0);
		editAccountPasswordTF.setText("");
		rdbtnEnableAccount.setSelected(false);
		rdbtnDisableAccount.setSelected(false);
	}


	public JRadioButton getRdbtnDisableAccount() {
		return rdbtnDisableAccount;
	}


	public JRadioButton getRdbtnEnableAccount() {
		return rdbtnEnableAccount;
	}


	public JTextField getEditAccountPasswordTF() {
		return editAccountPasswordTF;
	}
	public void setEdditPasswordTF(String passwd){
		this.editAccountPasswordTF.setText(passwd);
	}


	public JComboBox<String> getEditAccountTypeComboBox() {
		return editAccountTypeComboBox;
	}


	public JTextField getEditUsernameTF() {
		return editUsernameTF;
	}


	public void setEditUsernameTF(String editUsernameTF) {
		this.editUsernameTF.setText(editUsernameTF);
	}


	public void setSelectSubCatToEditComboBox(String subCategoryEdit) {
		editSubCatNameTF.setText(subCategoryEdit);

	}




}
