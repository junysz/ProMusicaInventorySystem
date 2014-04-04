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

public void updateCategory(String name,String newName)
	{ int ID=-1;
		try
		{
		
			
			
			String query1 = "Select * From Account Where accountName = "+name+" ";	    

			PreparedStatement pstmt = con.prepareStatement(query1);
			ResultSet rs1 =  pstmt.executeQuery(query1);

			while (rs1.next()) 
			{
				ID   =rs1.getInt("categoryID");	

			}      

			rs1.close(); //close result set 1
			pstmt.close(); //close prepared statemen
		}
		catch(Exception f)
		{
			f.printStackTrace();
		}
			
			statement = con.createStatement();
			//Structure for updating the category in the table
			String update = "Update Category Set categoryName="+ (newName) +" Where categoryID = "+ID+" ";
			if (ID>-1) 
			{
		int    res = statement.executeUpdate(update); //updates name for category
			statement.close();
		   }
		
		   }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}




	

