
package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import com.group8.model.Item;
import com.group8.model.MainModel;
import com.group8.model.SubCategory;
import com.group8.view.MaintainPanel;

public class ItemsController {

	private Controller controller;	

	public ItemsController(Controller controller){
		this.controller=controller;
		getMaintainPanel().addCategoryListnerCreateItem(new MICategoryCB1());
		getMaintainPanel().addCreateItemBtn(new MICreateBTN1());
		getMaintainPanel().addEditCategoryComboBoxItem(new MICategoryCB2());
		getMaintainPanel().addEditSubCatComboBox(new MIsubCategoryCB2());
		getMaintainPanel().addSelectItemToEditComboBox(new MImoveSubCatCB2());
		getMaintainPanel().addConfirmItemChangesBtn(new MIconfirmBTN2());
	}

	class MICategoryCB1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String catSelected=getMaintainPanel().getNewItemCategoryComboBox().getSelectedItem().toString();
			getMaintainPanel().setSubCategoryModelItems(controller.getModel().getSubCategories(catSelected));
		}
	}

	//Inner Class that listens for the Create Item Button
	class MICreateBTN1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String subCatSelection=null;
			String itemBrand=null;
			String itemModel=null;
			double itemPrice=0.0;
			int stockLevel=0;//optional parameter from the view
			//read the values (subCat, brand, model, price, stockLevel) from the view
			try{
				subCatSelection=getMaintainPanel().getNewItemSubCatComboBox().getSelectedItem().toString();
				itemBrand= getMaintainPanel().getEnterBrandTF();
				itemModel= getMaintainPanel().getEnterModelTF();
				itemPrice= Double.parseDouble(getMaintainPanel().getEnterPriceTF());
				stockLevel = Integer.parseInt(getMaintainPanel().getEnterStockLevelTF());
				System.out.println("testing: "+subCatSelection+" "+itemBrand+" "+itemModel+" "+itemPrice+" "+stockLevel);
			}catch(Exception ex){
				System.out.println("Problem reading input fron Create Item Form");
			}
			//Now validate the data and add errors to errorMessages
			ArrayList<String> errorMessages = new ArrayList<String>();
			if(subCatSelection==null){
				errorMessages.add("SubCategory Selection");
			}
			if(itemBrand.isEmpty()){
				errorMessages.add("Brand Name");
			}
			if(itemModel.isEmpty()){
				errorMessages.add("Model Name");
			}
			if(!(itemPrice>0)){
				errorMessages.add("Price");
			}
			//If there is no errors then we can go ahead and make sub category
			if(errorMessages.isEmpty()){
				//first we get the SubCategory id based on the name that was selected
				int subCatID = getModel().getSubCatIdFromName(subCatSelection);
				//then we create the SubCategory Object to pass to the model
				SubCategory s=new SubCategory(subCatID, subCatSelection);
				Item i = new Item();
				i.setBrand(itemBrand);
				i.setModel(itemModel);
				i.setPrice(itemPrice);
				i.setStockLevel(stockLevel);
				i.setAvailableStockLevel(stockLevel);
				//send both object to the model to handle the database insert
				getModel().addNewItem(i,s);
				//Testing Prints
				System.out.println("Test if works: ItemName: "+ i.getBrand()+" " +i.getModel() +"\n Price: "+i.getPrice());
				//Now that data processing is complete, clear the GUI form
				getMaintainPanel().clearNewItemForm();
				//update sub-cat
				getMaintainPanel().clearEditItemForm();
				getMaintainPanel().setCategoryModels(getModel().getCategoryNames());	
				update();
				updteAccounts();
			}
			//Now handle the error Messages if there was any by sending the errors list to the view to be displayed
			else{
				getMaintainPanel().warnCreateItemFormErrors(errorMessages);
			}
		}
	}

	class MICategoryCB2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String catSelected=getMaintainPanel().getEditCategoryComboBox().getSelectedItem().toString();
			getMaintainPanel().setSubCategoryModelItems2(getModel().getSubCategories(catSelected));
		}
	}

	class MIsubCategoryCB2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String subCatSelected= getMaintainPanel().getItemToEditSubCatComboBox().getSelectedItem().toString();
			System.out.println("Testing subCat "+subCatSelected);
			List<Item> listItems= new ArrayList<>();
			listItems=getModel().getItemsInSubcategory(subCatSelected);
			List<String>listItemBrands= new ArrayList<>();
			String brandModel=null;
			for(int i=0;i<listItems.size();i++){
				brandModel=listItems.get(i).getBrand()+" "+listItems.get(i).getModel();
				listItemBrands.add(brandModel);
			}
			getMaintainPanel().setItemModle(listItemBrands);
		}
	}

	class MImoveSubCatCB2 implements ActionListener{
		//class is responsible for finding item selected by user for editing and displaying items's details in text fields
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedItem=getMaintainPanel().getItemToEditComboBox().getSelectedItem().toString();
			String selectedSubCategory= getMaintainPanel().getItemToEditSubCatComboBox().getSelectedItem().toString();

			System.out.println("testgin"+selectedItem);
			//extract model and brand from combo-box select item
			String getItem[]=selectedItem.split("\\s");
			String brand=getItem[0];
			String model=getItem[1];

			//create list with all items for selected sub-category
			List<Item>itemsInSubCat= new ArrayList<Item>();
			//use query itemsInSubCategory
			itemsInSubCat=getModel().getItemsInSubcategory(selectedSubCategory);

			for(int i=0;i<itemsInSubCat.size();i++){

				//iterate throughout the list and compare brand and model
				//get correct item and get price and all details	
				if((brand.equalsIgnoreCase(itemsInSubCat.get(i).getBrand())) && (model.equalsIgnoreCase(itemsInSubCat.get(i).getModel())))	
				{
					//populate comboBoxes
					getMaintainPanel().setEditBrandTF(itemsInSubCat.get(i).getBrand());
					getMaintainPanel().setEditModelTF(itemsInSubCat.get(i).getModel());
					getMaintainPanel().setEditPriceTF(String.valueOf(itemsInSubCat.get(i).getPrice()));
					getMaintainPanel().setEditStockLevelTF(String.valueOf((itemsInSubCat.get(i).getStockLevel())));
				}
			}	
		}
	}

	class MIconfirmBTN2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(getMaintainPanel().getBtnConfirmItemChanges())){
				System.out.println("I have recognized buttton");
				ArrayList<String> errorMessages = new ArrayList<String>();
				String selectedSubCategory=null;
				String selectedItem=null;

				String brand=null;
				String model=null;
				int stockLevel=0;
				double price=0;
				String[] getItem;
				String brandsplit = null;
				String modelsplit = null;
				int itemSubCatID;
				try{
					if(getMaintainPanel().getEditCategoryComboBox().getSelectedIndex()==-1){
						errorMessages.add("Category Selection");
					}
					if(getMaintainPanel().getItemToEditSubCatComboBox().getSelectedIndex()==-1){
						errorMessages.add("Sub-Cat Selection");
					}else{
						selectedSubCategory=getMaintainPanel().getItemToEditSubCatComboBox().getSelectedItem().toString();
					}
					brand= getMaintainPanel().getEditBrandTF();
					model= getMaintainPanel().getEditModelTF();
					price= Double.parseDouble(getMaintainPanel().getEditPriceTF());
					stockLevel= Integer.parseInt(getMaintainPanel().getEditStockLevelTF());
				}catch(Exception ex){
					System.out.println("Problem reading input fron Edit Existing Item Form");
				}
				//Now validate the data and add errors to errorMessages
				if(getMaintainPanel().getItemToEditComboBox().getSelectedIndex()==-1){
					errorMessages.add("Item Selection");
				}
				else{
					selectedItem=getMaintainPanel().getItemToEditComboBox().getSelectedItem().toString();
					getItem=selectedItem.split("\\s");
					brandsplit=getItem[0];
					modelsplit=getItem[1];
				}
				if(brand.isEmpty()){
					errorMessages.add("Brand Name");
				}
				if(model.isEmpty()){
					errorMessages.add("Model Name");
				}
				if(!(price>0)){
					errorMessages.add("Price");
				}
				if(getMaintainPanel().getEditStockLevelTF().isEmpty()){
					errorMessages.add("Stock Level");
				}
				//If there is no errors then we can go ahead and edit the item accordingly
				if(errorMessages.isEmpty()){
					//First we get the Item based on the string that was selected (contains "Brand Model")
					Item item = getModel().getItemByName(brandsplit, modelsplit);
					item.setBrand(brand);
					item.setModel(model);
					item.setPrice(price);

					int numReserved = item.getStockLevel() - item.getAvailableStockLevel();
					item.setStockLevel(stockLevel);
					item.setAvailableStockLevel(stockLevel-numReserved);
					//if move sub-cat is not selected use sub-category selected 
					if(getMaintainPanel().getItemMoveSubCatComboBox().getSelectedIndex()!=-1){
						selectedSubCategory=getMaintainPanel().getItemMoveSubCatComboBox().getSelectedItem().toString();
					}
					itemSubCatID = getModel().getSubCatIdFromName(selectedSubCategory);
					getModel().updateItem(item, itemSubCatID);
					//Now that data processing is complete, clear the GUI form
					getMaintainPanel().clearEditItemForm();
					getMaintainPanel().setCategoryModels(getModel().getCategoryNames());	
				}
				//Now handle the error Messages if there was any by sending the errors list to the view to be displayed
				else{
					getMaintainPanel().warnEditItemFormErrors(errorMessages);
				}
			}
			else if(e.getSource().equals(getMaintainPanel().getBtnRemoveItem())){
				System.out.println("Move here class class RemoveItemBtn implements ActionListener{");
			}
		}
	}

	public void updteAccounts(){
		ArrayList<String>accountNames= new ArrayList<String>();

		for(int i=0;i<getModel().getAllAccounts().size();i++){
			String accN=getModel().getAllAccounts().get(i).getAccountName();
			accountNames.add(accN);
			System.out.println(accN);
		}
		getMaintainPanel().setAccountModel(accountNames);
	}

	public void update() {
		//sets the model for all the category combo boxes in maintain panel
		getMaintainPanel().setCategoryModels(getModel().getCategoryNames());	
		getMaintainPanel().clearNewSubCatForm();
	}

	public MaintainPanel getMaintainPanel(){
		return controller.getView().getTabsPane().getMaintainPanel();
	}
	public MainModel getModel(){
		return controller.getModel();
	}
}
