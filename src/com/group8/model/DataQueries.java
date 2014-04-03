package com.group8.model;
import java.sql.*;
import java.util.ArrayList;


public class DataQueries {

	Connection con;
	Statement statement;
	PreparedStatement pstmt;

	public DataQueries(Connection connection)
	{
		con = connection;
	}




	//This method is getting a LIST  of  the NAMES of all the Categories
	public ArrayList<String>  getCategoryNames()
	{

		try {

			String query = "Select * from Category";  //create a new query ,getting all fields
			pstmt = con.prepareStatement(query);

			ResultSet rs =  pstmt.executeQuery(query); //create a new result set
			ArrayList<String> catNames = new ArrayList<String>();  //declaring an array list of type String
			while (rs.next()) 
			{
				String name = rs.getString("categoryName");   //get name from result set for the category
				catNames.add(name);                           //add name to the list     
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

			ArrayList<Item> listItems = new ArrayList<Item>(); // new arraylist of type Item

			//create a new query based on subcategory Name
			//query structure for requesting a subCategoryID from any subcategory name that is passed
			String query = "Select SubCategoryID From SubCategory where subCatName = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,subCatName); //sets the SubcategoryName for the statement query
			ResultSet rs = pstmt.executeQuery(); //executes query and puts the subcategory ID into rs
			int subCatID = 0; //initialize the variable to hold the catID we get back from DB
			while( rs.next()) { 
				subCatID = rs.getInt("subCatID");	// sets the cat ID  
			}
			rs.close(); //close result set
			pstmt.close(); //close prepared statement


			//now we can run another query because we have the foreign key for Item table
			String query2 = "Select * From Item where SubCatID = "+subCatID+" ";	    

			pstmt = con.prepareStatement(query2);
			ResultSet rs2 =  pstmt.executeQuery(query2);

			while (rs2.next()) 
			{
				int id       =rs2.getInt("subCatID");	
				double price = rs2.getDouble("itemPrice");                    
				String brand = rs2.getString("itemBrand");
				String model = rs2.getString("itemModel");   //get attributes for the item from result set 2
				int level    = rs2.getInt("stockLevel");
				int level2   = rs2.getInt("availableStockLevel");
				boolean flag=rs2.getBoolean("itemFlag");

				item=new Item(brand,model,level,price,level2);//creating a new object with some of the attributes
				item.setFlag(flag);   //set flag for the item
				item.setItemID(id);    //set itemID for the item

				listItems.add(item);   //add newly created object item to the list to be returned

			}      

			rs2.close(); //close result set 2
			pstmt.close(); //close prepared statement
			return listItems;
		}
		catch (Exception io) {
			return null;
		}  
	}

	// Method to get Objects Items by a keyword			
	public ArrayList<Item>  getItemsByKeyword(String keyword)
	{
		Item item;
		try {

			ArrayList<Item> listItems = new ArrayList<Item>();  //create an array list of type Item
			//this query will return all from items where the brand or model contains the keyword
			String query = "Select * From Item Where itemBrand like '%" + keyword +"%' or itemModel like '%" + keyword +"%' "; //create a query 
			pstmt = con.prepareStatement(query);
			ResultSet rs =  pstmt.executeQuery(query);

			while (rs.next()) 
			{
				//getting attributes from  the Item Table 
				int id       =rs.getInt("itemID");	
				double price = rs.getDouble("itemPrice");                    
				String brand = rs.getString("itemBrand");
				String model = rs.getString("itemModel"); 
				int level    = rs.getInt("stockLevel");
				int level2   = rs.getInt("availableStockLevel");
				boolean flag=rs.getBoolean("flag");

				item=new Item(id,price,brand,model,level,level2, flag); //create new object item

				listItems.add(item);       //add the item to a list
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

			ArrayList<Account> listAccounts = new ArrayList<Account>(); //create a new arraylist type account
			String query = "Select * from Account ";  //create a new query 
			pstmt = con.prepareStatement(query);
			ResultSet rs =  pstmt.executeQuery(query);
			while (rs.next()) 
			{
				Integer    id   = rs.getInt("accountID");	
				String username = rs.getString("accountName");         
				String password = rs.getString("accountPassword");  //get attributes from Account table
				String type     = rs.getString("accountType");
				boolean flag   =rs.getBoolean("accountFlag"); 

				account=new Account(type,username,password);//create a new object account
				account.setAccountID(id);
				account.setFlag(flag);  //set attributes for account
				listAccounts.add(account);  //add object to the list

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


