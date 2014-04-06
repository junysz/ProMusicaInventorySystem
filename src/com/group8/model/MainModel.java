package com.group8.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;

import java.util.List;


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
				// TODO Auto-generated catch block
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
	public void addNewReservation(Reservation r, Account a, Item i)
	{
		inserts.insertNewReservation(r, a, i);
	}
	public void addNewAccount(Account a)
	{
		inserts.insertNewAccount(a);
	}


	//update methods



	public void updateCategory(String name,String newName)
	{
		updates.updateCategory(name,newName);
	}



	public ArrayList<String> getListOfCategories(){

		return queries.getCategoryNames();
	}





	public List<Item> getMeSomeItems(){
		return someItems.getMeSomeItems();
	}
	public List<String>getMySomeCategories(){
		return someItems.getMeSomeCategories();
	}
	public List<String>getMeSomeSubCategories(){
		return someItems.getMeSomeSubCategories();
	}




	//queries methods

	public ArrayList<String>  getCategoryNames()
	{
		return queries.getCategoryNames();
	}

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
	//public ArrayList<Sale>  getSalesByDate(Date date1,Date date2)
	//{
	//return queries.getSalesByDate(date1, date2);

	//}

}


