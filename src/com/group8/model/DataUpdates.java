package com.group8.model;


import java.sql.Connection;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


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

			java.sql.PreparedStatement preparedStatement = con.prepareStatement("UPDATE Category SET categoryName=? WHERE categoryName = ?");

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

	protected void updateItem(Item i, int subCatID)
	{

	}
}






