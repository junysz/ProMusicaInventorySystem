package com.group8.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;



/*
 * This class is responsible for the entire model package. 
 * This class is used so that the Controller can with the Database.
 * The methods in this class use 3 other classes in the same Model Package:
 * 		-DataInserts
 * 		-DataQueries
 * 		-DataUpdates
 * Each of these classes is represented by one instance used here in this class.
 * Each of these instances use a reference to the same connection established here in the Constructor.
 */
public class MainModel {
	TemporaryDataBaseClass someItems = new TemporaryDataBaseClass();
	private Connection mainConnection; //this holds the database connection when the class is created
	private DataInserts inserts;
	private DataQueries queries;
	private DataUpdates updates;

	//Constructor initializes the DatabaseConnection and sets up Database Interaction classes
	public MainModel()
	{
		try
		{ 

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Driver not found");
			}
			String conURL="jdbc:mysql://localhost:8889/mydb";
			//establish the connection when the MainModel is created
			mainConnection = DriverManager.getConnection(conURL,"root","root");

			inserts = new DataInserts(mainConnection);
			queries = new DataQueries(mainConnection);
			updates = new DataUpdates(mainConnection);

		}

		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Connection Failed on Creation Of Main Model");
		}
	}
	/*
	 * This block of "Add" methods are responsible for inserting new rows into the Tables
	 * They make use of the private inserts object here in this class
	 * Each add method needs the relevant objects needed for the insert 
	 */
	public void addNewCategory(Category c)
	{
		inserts.insertNewCategory(c);
	}
	public void addNewSubCategory(Category c, SubCategory s)
	{
		inserts.insertNewSubCat(c, s);
	}
	public void addNewItem(Item i, SubCategory s)
	{
		inserts.insertNewItem(i, s);
	}
	public void addNewSale(Sale s, Account a)
	{
		inserts.insertNewSale(s, a);
	}
	public void addNewItemSold(Item i, Sale s, double itemSalePrice)
	{
		inserts.insertNewItemSold(i, s, itemSalePrice);
	}
	public void insertNewReservation(int accountID,String docketNo,Date reservationDate,double deposit,int itemID)
	{
		inserts.insertNewReservation( accountID,docketNo,reservationDate,deposit, itemID);
	}
	public void addNewAccount(String username,String password1,String accountTypeSelection)
	{
		inserts.insertNewAccount( username, password1, accountTypeSelection);
	}


	//update methods
	public void updateCategory(String name,String newName)
	{
		updates.updateCategory(name,newName);
	}
	public void updateSubCategory(String name,String newName){
		updates.updateSubCategory(name, newName);
	}
	public void updateItem(Item i, int subCatID)
	{
		updates.updateItem(i, subCatID);
	}
	public void removeItem(Item i){
		updates.removeItem(i);
	}
   
	//method to update the deposit associated with the docket number
	public void updateReservedItem(String docket,double deposit)
	{
	updates.updateReservedItem(docket,deposit);
    }
	//method to "remove" the deposit associated with the docket number
	public void removeReservedItem(String docket)
	{
		updates.removeReservedItem(docket);
	}

	public  void updateItemAvailableStock(int ItemID,int Stock)
	{
		updates.updateItem(ItemID, Stock);
	}
	
	
	/*
	//queries methods
	public List<Item> getMeSomeItems(){
		return someItems.getMeSomeItems();
	}
	public List<String>getMySomeCategories(){
		return someItems.getMeSomeCategories();
	}
	public List<String>getMeSomeSubCategories(){
		return someItems.getMeSomeSubCategories();
	}
	*/

	public ArrayList<String>  getCategoryNames()
	{
		return queries.getCategoryNames();
	}
	public ArrayList<String>  getSubCategoryNames()
	{
		return queries.getSubCategoryNames();
	}
	
	//get me all sub-cat for category chosen 
	public   ArrayList<String> getSubCategories(String catName)
	{
		return queries.getSubCategories(catName);

	}
	public ArrayList<Item>  getItemsInSubcategory(String subCatName)
	{
		return queries.getItemsInSubcategory(subCatName);
	}

	public ArrayList<Item>  getItemsByKeyword(String keyword)
	{ 
		return queries.getItemsByKeyword(keyword);
	}
	public ArrayList<Account>  getAllAccounts()
	{ return queries.getAllAccounts();

	}
	
	
	
	
	public int getCategoryIdFromName(String name)
	{
		int id = queries.getCategoryIdByName(name);
		return id;
	}
	public int getSubCatIdFromName(String name)
	{
		int id = queries.getSubCatIdByName(name);
		return id;
	}
	//This method will return an item using a string containing the brand and model separated by a space
	/*public Item getItemByName(String itemName)
	{
		int space =itemName.indexOf(" ");
		String brand = itemName.substring(0, space); //gets brand from the first part of the string up to 1 before the space
		String model = itemName.substring(space).replace(" ", ""); //gets the model from the space to the end of the string and strips off the space after
		Item i = queries.getItemByName(brand, model); //gets item based on brand and model
		return i; //returns the item
		
	}*/
	//the method will return all Sales objects between two dates
	
	public Item getItemByName(String brand, String model){
		return queries.getItemByName(brand, model);
	}
	
	public ArrayList<Sale>  getSalesByDate(Date date1,Date date2)
	{
	return queries.getSalesByDate(date1,date2);

	}
	//method that returns all the ReservedItems
	 public ArrayList<ReservedItem >getReservedItems()
	 {
	 return queries.getReservedItems();
	 }
	 //method that will return an item based on the itemID
	 public Item getItemByID(int ID)
	 {
		 ArrayList<Item> myList=queries.getItemsByKeyword("");
		 for (int i=0;i<myList.size();i++)
			 if (ID==myList.get(i).getItemID()) return myList.get(i);
		 return null;
	 }
	 
	 
	
	 
	 
	 
	 public Account getAccount(String accName){
			int i;
			Account a = null;
			 ArrayList<Account> Accounts=getAllAccounts();
				for ( i=0;i<Accounts.size();i++)
					if (Accounts.get(i).getAccountName().equals(accName))
			       a=Accounts.get(i);
			 return a;
		 }
	 
}



