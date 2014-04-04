package com.group8.model;

import java.sql.Connection;
import java.sql.Statement;


public class DataUpdates {

	Connection con;
	Statement statement;
	
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
			statement = con.createStatement();
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
	
	
	
	public void updateCategory(String name)

	{

	try

	{

	statement = con.createStatement();

	//Structure for updating the category in the table
	String update = "Update Category Set categoryName="+ name +" "; 

	int res = statement.executeUpdate(update); //updates name for category

	statement.close();

	}

	catch(Exception e)

	{

	e.printStackTrace();

	}

	}
	
	
	
	
}
