package com.group8.model;
import java.sql.*;
import java.util.ArrayList;


public class DataQueries {

	Connection con;
	Statement statement;
	PreparedStatement pstmt, pstmt2;

	public DataQueries(Connection connection)
	{
		con = connection;
	}

	//prepared statements for queries on the fly to fill combo boxes and menu's


	//this method is responsible for returning an array list of sub-categories that are within a given category name
	public ArrayList<String> getSubCategories(String catName)
	{
		try {
			//query structure for requesting a CategoryID from any category name that is passed
			String query = "Select CategoryID From Category where categoryName = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,catName); //sets the categoryName for the statement query
			ResultSet rs = pstmt.executeQuery(); //executes query and puts the category ID into rs
			int catID = 0; //initialize the variable to hold the catID we get back from DB
			while( rs.next()) { 
				catID = rs.getInt("categoryID");	// sets the cat ID  
			}
			rs.close(); //close result set
			pstmt.close(); //close prepared statement


			//now we can run another query because we have the foreign key for SubCategory table
			String query2 = "Select subCatName From SubCategory where categoryID = " + catID + " ";
			statement = con.createStatement();
			ResultSet rs1 = statement.executeQuery(query2); //puts the list of subcat names into rs1
			con.commit();

			//ArrayList to temporarily store the SUbCatNames so we can pass them back to controller
			ArrayList<String> subCatNames = new ArrayList<String>();
			while(rs1.next())
			{
				//assigns each subCat name recieved from database into the array list
				subCatNames.add(rs1.getString(1)); 
			}
			statement.close();

			return subCatNames; //returns ArrayList


		}catch (Exception io) {
			return null;
		}  
	}
		
		
		
		public ArrayList<Integer> getItems(String subCatName)
	{
			int subCatID = 0; //initialize the variable to hold the subCatID we get back from DB
	
		try {
			//query structure for requesting a SubCategoryID from any subcategory name that is passed
			String query = "Select subCatID From SubCategory where subCatName = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,subCatName); //sets the subCategoryName for the statement query
			ResultSet rs = pstmt.executeQuery(); //executes query and puts the subcategory ID into rs
		
			while( rs.next()) { 
				subCatID = rs.getInt("subCatID");	// sets the subCat ID  
			}
			rs.close(); //close result set
			pstmt.close(); //close prepared statement


			//now we can run another query because we have the foreign key for Item table
			String query2 = "Select itemID From Item where subCatID = " + subCatID + " ";
			statement = con.createStatement();
			ResultSet rs1 = statement.executeQuery(query2); //puts the list of items ID's into rs1
			con.commit();

			//ArrayList to temporarily store the Items IDs so we can pass them back to controller
			ArrayList<Integer> itemsID = new ArrayList<Integer>();
			while(rs1.next())
			{
				//assigns each itemID received from database into the array list
				itemsID.add(rs1.getInt(1)); 
			}
			statement.close();

			return itemsID; //returns ArrayList


		}catch (Exception io) {
			return null;
		}  
	}
		public Item getItemAttributes(Integer itemID)
			{
			Item item=null;
		try {
			
		 	
             String query = "Select * from Item where itemID=?";  //create a new query 
             pstmt = con.prepareStatement(query);
			 pstmt.setInt(1,itemID);
             ResultSet rs =  pstmt.executeQuery(query);
             while (rs.next()) 
			 {
            	
             double price = rs.getDouble("itemPrice");
             
             String model = rs.getString("itemModel");
             String brand = rs.getString("itemBrand");
             item=new Item(model,brand,0,price);
             }      
			
		   	rs.close(); //close result set
			pstmt.close(); //close prepared statement
			return item;
     		}
			catch (Exception io) {
			return null;
		}  
		
	}
}
