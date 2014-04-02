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

		
	

	//This method is getting a LIST  of  the NAMES of all the Categories
	public ArrayList<String>  getCategoryNames()
	{
		
       try {
	 	
     String query = "Select * from Category";  //create a new query 
     pstmt = con.prepareStatement(query);

     ResultSet rs =  pstmt.executeQuery(query);
     ArrayList<String> catNames = new ArrayList<String>();
     while (rs.next()) 
	 {
     String name = rs.getString("categoryName"); 
     catNames.add(name);          
     }      
	
   	rs.close(); //close result set
	pstmt.close(); //close prepared statement
	return catNames;
		}
	catch (Exception io) {
	return null;
     }  
	}
	

	//this method is responsible for returning an array LIST of NAMES sub-categories that are within a given Category name
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
		
		
		// Method to get Objects Items in a given subcategory 				
		public ArrayList<Item>  getItemsInSubcategory(String subCatName)
			{
			Item item;
		try {
			
			ArrayList<Item> listItems = new ArrayList<Item>();
			String query = "Select subCatID From SubCategory where subCatName = ? ";
             pstmt = con.prepareStatement(query);
			 pstmt.setString(1,subCatName);
             ResultSet rs =  pstmt.executeQuery(query);
             
             while (rs.next()) 
			 {
             int id       =rs.getInt("subCatID");	
             double price = rs.getDouble("itemPrice");                    
             String brand = rs.getString("itemBrand");
             String model = rs.getString("itemModel");
             int level    = rs.getInt("stockLevel");
             int level2   = rs.getInt("availableStockLevel");
             boolean flag=rs.getBoolean("itemFlag");
             
             item=new Item(brand,model,level,price,level2);
             item.setFlag(flag);
             item.setItemID(id);
             
             listItems.add(item);
             
             }      
			
		   	rs.close(); //close result set
			pstmt.close(); //close prepared statement
			return listItems;
     		}
			catch (Exception io) {
			return null;
		}  
			}
		
		//Method to get ALL objects  ACCOUNTS in the Database
		public ArrayList<Account>  getAllAccounts()
		{
	    Account account;
	     try {
		
	   	 ArrayList<Account> listAccounts = new ArrayList<Account>();
         String query = "Select * from Account ";  //create a new query 
         pstmt = con.prepareStatement(query);
		 ResultSet rs =  pstmt.executeQuery(query);
         while (rs.next()) 
		 {
         Integer    id   = rs.getInt("accountID");	
         String username = rs.getString("username");         
         String password = rs.getString("password");
         String type     = rs.getString("accountType");
         boolean flag   =rs.getBoolean("accountFlag"); 
         
         account=new Account(type,username,password);
         account.setUserID(id);
         account.setFlag(flag);
         listAccounts.add(account);
                
         }      
		
	   	rs.close(); //close result set
		pstmt.close(); //close prepared statement
		return listAccounts;
 		}
		catch (Exception io) {
		return null;
	}  
		}
		
		
		
	}

