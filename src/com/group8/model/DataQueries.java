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
             String brand = rs.getString("itemBrand");
             String model = rs.getString("itemModel");
             int level    = rs.getInt("stockLevel");
             int level2   = rs.getInt("availableStockLevel");
             int subCatID = rs.getInt("subCatID");
             boolean flag=rs.getBoolean("itemFlag");
             
             item=new Item(brand,model,level,price,level2,subCatID,flag);
             item.setItemID(itemID);
             }      
			
		   	rs.close(); //close result set
			pstmt.close(); //close prepared statement
			return item;
     		}
			catch (Exception io) {
			return null;
		}  
			}
		
		
		public Account getAccount(Integer userID)
		{
	Account account=null;
	try {
		
	 	
         String query = "Select * from Account where userID=?";  //create a new query 
         pstmt = con.prepareStatement(query);
		 pstmt.setInt(1,userID);
         ResultSet rs =  pstmt.executeQuery(query);
         while (rs.next()) 
		 {
        	
         String username = rs.getString("username");         
         String password = rs.getString("password");
         String type = rs.getString("accountType");
         account=new Account(type,username,password);
         boolean flag2=rs.getBoolean("accountFlag");
         
         account=new Account(type,username,password);
         account.setUserID(userID);
         account.setAccountFlag(flag);
                
         }      
		
	   	rs.close(); //close result set
		pstmt.close(); //close prepared statement
		return account;
 		}
		catch (Exception io) {
		return null;
	}  
		}
		
		
		
	}

