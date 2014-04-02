package com.group8.model;

import java.sql.Connection;
import java.sql.Statement;
/*
 * This class will receive an already established Database Connection from another class
 * This class is then responsible for inserting new tables to the Database
 */
public class DataInsert {

	Connection con;
	Statement statement;
	
	//New Constructor that accepts a connection already established in another class
	public DataInsert(Connection connection)
	{       
		con = connection;
	}
	
	//public access to this method for adding a new Category to the database
	public void addNewCategory(Category c)
	{
		insertNewCategory(c);
	}
	//private method to handle the MySQL insert query for Inserting A New Category
	private void insertNewCategory(Category c)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Category table
			String insert = "Insert into Category values (" + c.getCategoryID() + ", '" + c.getCategoryName() + "')";
			int res = statement.executeUpdate(insert); //writes to Category table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	public void addNewSubCategory(Category c, SubCategory s)
	{
		insertNewSubCat(c, s);
	}
	private void insertNewSubCat(Category c, SubCategory s)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the SubCategory table
			String insert = "Insert into SubCategory values (" + s.getSubCatID() + ", '" + s.getSubCatName() + "', " + c.getCategoryID() + ")";
			int res = statement.executeUpdate(insert); //writes to SubCategory table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	public void addNewItem(Item i, SubCategory s)
	{
		insertNewItem(i, s);
	}
	private void insertNewItem(Item i, SubCategory s)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Item table
			String insert = "Insert into Item values (" + i.getItemID() + "," + i.getPrice() + ", '" + i.getBrand() + "','" + i.getModel() + "'," + i.getStockLevel() + "," + i.getAvailableStockLevel() + ", " + s.getSubCatID() + ")";
			int res = statement.executeUpdate(insert); //writes to Item table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	public void addNewSale(Sale s, Account a)
	{
		insertNewSale(s, a);
	}
	private void insertNewSale(Sale s, Account a)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in Sale table
			String insert = "Insert into Sale values (" + s.getSaleID() + ", '" + s.getDate() + "', " + s.getTotalPrice() + "," + s.getSaleID() + ")";
			int res = statement.executeUpdate(insert); //writes to Sale table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	public void addNewItemSold(Item i, Sale s, double itemSalePrice)
	{
		insertNewItemSold(i, s, itemSalePrice);
	}
	private void insertNewItemSold(Item i, Sale s, double itemSalePrice)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Item Sold table
			String insert = "Insert into ItemSold values (" + i.getItemID() + "," + s.getSaleID() + ", " + itemSalePrice + ")";
			int res = statement.executeUpdate(insert); //writes to Item Sold table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	public void addNewAccount(Account a)
	{
		insertNewAccount(a);
	}
	private void insertNewAccount(Account a)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Account table
			String insert = "Insert into Account values (" + a.getUserID() + ",'" + a.getUsername() + "','" + a.getPassword() + "','" + a.getType() + "')";
			int res = statement.executeUpdate(insert); //writes to Item SOld table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	public void addNewReservation(Reservation r, Account a, Item i)
	{
		insertNewReservation(r, a, i);
	}
	private void insertNewReservation(Reservation r, Account a, Item i)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Reservation table
			String insert = "Insert into Reservation values (" + r.getResID() + "," + a.getUserID() + "," + r.getDocketNo() + ",'" + r.getStartDate() + "'," + r.getDeposit() + "," + i.getItemID() + ")";
			int res = statement.executeUpdate(insert); //writes to Reservation table
			con.commit();
			statement.close();
		}
		catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	
}
