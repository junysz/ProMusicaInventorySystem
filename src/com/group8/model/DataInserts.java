package com.group8.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
			String insert = "Insert into SubCategory (subCatName, categoryID ) values ('" + s.getSubCatName() + "', " + c.getCategoryID() + ")";
			int res = statement.executeUpdate(insert); //writes to SubCategory table
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
			String insert = "Insert into Item (itemPrice, itemBrand, itemModel, stockLevel, availableStockLevel,flag,subCatID) values (" + i.getPrice() + ", '" + i.getBrand() + "','" + i.getModel() + "'," + i.getStockLevel() + "," + i.getAvailableStockLevel() + ", 1,"+s.getSubCatID()+")";
			int res = statement.executeUpdate(insert); //writes to Item table
			
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
	
			statement.close();
		}
		catch(Exception e)
        {
			System.out.println("Insert ItemSold Failed");
            e.printStackTrace();
        }
	}
	protected void insertNewAccount(String username,String password1,String accountTypeSelection)
	{
		try
		{
			/*statement = con.createStatement();
			//Structure for inserting a new tuple in the Account table
			String insert = "Insert into Account (accountName, password, accountType, flag ) values ('" + a.getAccountName() + "','" + a.getPassword() + "','" + a.getType()+",1)";
			int res = statement.executeUpdate(insert); //writes to Item SOld table
			con.commit();
			statement.close();*/
			
			String sql= "Insert into Account(accountName,password,accountType) values(?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password1);
			preparedStatement.setString(3, accountTypeSelection);
			
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			
		}
		catch(Exception e)
        {
			System.out.println("Insert Account Failed");
            e.printStackTrace();
        }
	}
	/*protected void insertNewReservation(int accountID,String docketNo,Date reservationDe,double deposit,int itemID)
	{
		try
		{
			statement = con.createStatement();
			//Structure for inserting a new tuple in the Reservation table
			String insert = "Insert into ReservedItem (accountID,docketNo, reservationDate, depositPlaced,flag,itemID) values ?,?,?,?,?)";
			int res = statement.executeUpdate(insert); //writes to Reservation table
	
			statement.close();
		}
		catch(Exception e)
        {
			System.out.println("Insert Reservation Failed");
            e.printStackTrace();
        }
	}
	*/
	
	
	
	protected void insertNewReservation(int accountID,String docketNo,Date reservationDate,double deposit,int itemID)
	{ 
		try
		{

			PreparedStatement preparedStatement = con.prepareStatement("Insert into ReservedItem (accountID,docketNo, reservationDate, depositPlaced,flag,itemID) values (?,?,?,?,?,?)");

			preparedStatement.setInt(1,accountID);

			preparedStatement.setString(2,docketNo);			
			
			preparedStatement.setDate(3,reservationDate);

			preparedStatement.setDouble(4,deposit);	
			
			preparedStatement.setInt(5,1);
			
			preparedStatement.setInt(6,itemID);

		

			int    res = preparedStatement.executeUpdate(); //updates name for category
			preparedStatement.close();


		}
		catch(Exception e)
		{
			System.out.println("Insert Reservation Failed");
			 e.printStackTrace();
		}
	}
	
	
	
}
