package com.group8.model;


import java.sql.Connection;
import java.sql.Statement;

import java.sql.PreparedStatement;


public class DataUpdates {

	Connection con;


	public DataUpdates(Connection connection)
	{
		con = connection;
	}
	public void updateItemStockLevels(Item i, int level)
	{
		updateStockLevels(i, level);
	}
	//newItems represents how many more items are being added (i.e if there is already 4, and we add 3 more, newItems = 3)
	private void updateStockLevels(Item i, int newItems)
	{
		try
		{
			Statement statement = con.createStatement();
			//Structure for updating the stock level and available stock level of an item
			String update = "Update Item Set stockLevel=" + (i.getStockLevel() +newItems) + ", availableStockLevel=" + (i.getAvailableStockLevel()+newItems) + " Where itemID=" + i.getItemID() + "";
			int res = statement.executeUpdate(update); //updates stock level and available stock level on Item
			con.commit();
			statement.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void updateCategory(String name,String newName)
	{ 
		try
		{

			PreparedStatement preparedStatement = con.prepareStatement("UPDATE Category SET categoryName=? WHERE categoryName = ?");

			preparedStatement.setString(1,newName);

			preparedStatement.setString(2,name);			   		


			int    res = preparedStatement.executeUpdate(); //updates name for category
			preparedStatement.close();


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

	protected void updateSubCategory(String name,String newName)
	{ 
		try
		{

			PreparedStatement preparedStatement = con.prepareStatement("UPDATE SubCategory SET subCatName=? WHERE subCatName = ?");

			preparedStatement.setString(1,newName);

			preparedStatement.setString(2,name);			   		


			int    res = preparedStatement.executeUpdate(); //updates name for category
			preparedStatement.close();


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//This method updates an Items' StockAvailableLevel only
	protected void updateItem(int ItemID,int Stock)
	{
		try
		{

			PreparedStatement preparedStatement = con.prepareStatement("UPDATE Item SET availableStockLevel=? where itemID = ?");

			//Set the variables in the prepared statement
			preparedStatement.setInt(1,Stock);
			preparedStatement.setInt(2,ItemID);			
			int    res = preparedStatement.executeUpdate(); 
			preparedStatement.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	//This method updates item after Edit Item button is triggered
	protected void updateItem(Item i, int subCatID)
	{
		try
		{

			PreparedStatement preparedStatement = con.prepareStatement("UPDATE Item SET itemBrand=?,itemModel=?,itemPrice=?,subCatID=? WHERE itemID = ?");

			//Set the variables in the prepared statement
			preparedStatement.setString(1,i.getBrand());
			preparedStatement.setString(2,i.getModel());
			preparedStatement.setDouble(3,i.getPrice());
			preparedStatement.setInt(4,subCatID);
			preparedStatement.setInt(5,i.getItemID());

			int    res = preparedStatement.executeUpdate(); //updates name for category
			preparedStatement.close();


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//Remove Item method accepts an item and uses its id to set its flag to false on the database
	protected void removeItem(Item i)
	{
		try
		{

			PreparedStatement preparedStatement = con.prepareStatement("UPDATE Item SET flag=0 WHERE itemID = ?");

			//Set the variables in the prepared statement
			preparedStatement.setInt(1,i.getItemID());

			int res = preparedStatement.executeUpdate(); //Sets the boolean flag to false so that the item will no longer appear
			preparedStatement.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	
	protected void updateReservedItem(String docket,double deposit)
	{
		try
		{

			PreparedStatement preparedStatement = con.prepareStatement("UPDATE ReservedItem SET depositPlaced=?  WHERE docketNo = ?");

			//Set the variables in the prepared statement
			preparedStatement.setDouble(1,deposit);
	
			preparedStatement.setString(2,docket );

			int    res = preparedStatement.executeUpdate(); 
			preparedStatement.close();


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void removeReservedItem(String docket)
	{
		try
		{

			PreparedStatement preparedStatement = con.prepareStatement("UPDATE ReservedItem SET flag=0  WHERE docketNo = ?");
			
			
			preparedStatement.setString(1,docket);
			int    res = preparedStatement.executeUpdate(); 
			preparedStatement.close();
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//This method updates item after Edit Item button is triggered
	protected void updateAccount(int ID,String name,String pass,String type,int flag)
	{
		try
		{

			PreparedStatement preparedStatement = con.prepareStatement("UPDATE Account SET accountName=?,password=?,accountType=?,flag=? WHERE accountID = ?");

			//Set the variables in the prepared statement
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,pass);
			preparedStatement.setString(3,type);
			preparedStatement.setInt(4,flag);
			preparedStatement.setInt(5,ID);
			
			int    res = preparedStatement.executeUpdate(); //updates name for category
			preparedStatement.close();


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}






