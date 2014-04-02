package com.group8.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

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

	private Connection mainConnection; //this holds the database connection when the class is created
	private DataInserts inserts;
	private DataQueries queries;
	private DataUpdates updates;

	//Constructor initializes the DatabaseConnection and sets up Database Interaction classes
	public MainModel()
	{
		try
		{
			//establish the connection when the MainModel is created
			mainConnection = DriverManager.getConnection("URL");
			
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
	/*
	 * Now place all the methods needed for queries using the private queries object in this class.
	 * All methods needed for on the fly queries
	 */
}

