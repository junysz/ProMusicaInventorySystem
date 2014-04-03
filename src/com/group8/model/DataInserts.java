package com.group8.model;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
/*
 * This class will receive an already established Database Connection from another class
 * This class is then responsible for inserting new tables to the Database
 */
public class DataInserts {

	Connection con;
	Statement statement;
	
	//New Constructor that accepts a connection already established in another class
	public DataInserts(Connection connection)
	{       
		con = connection;
	}
	
	//protected method to handle the MySQL insert query for Inserting A New Category
	//Accessed from MainModel class
	protected void insertNewCategory(Category c)
	{
		try
		{
			
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Category table
			String insert = "Insert into Category (categoryName) values ('" + c.getCategoryName() + "')";
			int res = statement.executeUpdate(insert); //writes to Category table
			//con.commit();
			statement.close();
		}
		catch(Exception e)
        {
			System.out.println("Insert Category Failed"); //testing with console
            e.printStackTrace();
        }
	}
	protected void insertNewSubCat(Category c, SubCategory s)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the SubCategory table
			String insert = "Insert into SubCategory (subCatName, catID ) values ('" + s.getSubCatName() + "', " + c.getCategoryID() + ")";
			int res = statement.executeUpdate(insert); //writes to SubCategory table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
			System.out.println("Insert SubCategory Failed"); //testing with console
            e.printStackTrace();
        }
	}
	protected void insertNewItem(Item i, SubCategory s)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Item table
			String insert = "Insert into Item (itemPrice, itemBrand, itemModel, stockLevel, availableStockLevel, subCatID, flag) values (" + i.getPrice() + ", '" + i.getBrand() + "','" + i.getModel() + "'," + i.getStockLevel() + "," + i.getAvailableStockLevel() + ", 1 )";
			int res = statement.executeUpdate(insert); //writes to Item table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
			System.out.println("Insert Item failed");
            e.printStackTrace();
        }
	}
	protected void insertNewSale(Sale s, Account a)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in Sale table
			String insert = "Insert into Sale (saleDate, totalSalePrice, saleAccountID) values ( '" + s.getDate() + "', " + s.getTotalPrice() + "," + a.getAccountID() + ")";
			int res = statement.executeUpdate(insert); //writes to Sale table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
			System.out.println("Insert Sale Failed");
            e.printStackTrace();
        }
	}
	protected void insertNewItemSold(Item i, Sale s, double itemSalePrice)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Item Sold table
			String insert = "Insert into ItemSold (itemSoldID, saleID, itemSalePrice) values (" + i.getItemID() + "," + s.getSaleID() + ", " + itemSalePrice + ")";
			int res = statement.executeUpdate(insert); //writes to Item Sold table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
			System.out.println("Insert ItemSold Failed");
            e.printStackTrace();
        }
	}
	protected void insertNewAccount(Account a)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Account table
			String insert = "Insert into Account (accountName, accountPassword, accountType, flag ) values ('" + a.getAccountName() + "','" + a.getPassword() + "','" + a.getType() + "', 1 )";
			int res = statement.executeUpdate(insert); //writes to Item SOld table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
			System.out.println("Insert Account Failed");
            e.printStackTrace();
        }
	}
	protected void insertNewReservation(Reservation r, Account a, Item i)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Reservation table
			String insert = "Insert into Reservation (docketNo, resDate, depositPlaced, accountID, itemID, flag) values (" + r.getDocketNo() + ",'" + r.getStartDate() + "'," + r.getDeposit() + "," + a.getAccountID() + "," + i.getItemID() + ", 1)";
			int res = statement.executeUpdate(insert); //writes to Reservation table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
			System.out.println("Insert Reservation Failed");
            e.printStackTrace();
        }
	}
	
}
