package com.group8.model;
import java.sql.*;
import java.util.ArrayList;

import java.util.Collections;


public class DataQueries {

	Connection con;
	Statement statement;
	PreparedStatement pstmt,pstmt2;

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

			Collections.sort (catNames);

			return catNames;
		}
		catch (Exception io) {
			return null;
		}  
	}
	//This method is responsible for getting all the SubCategory NAMES into a list
	public ArrayList<String>  getSubCategoryNames()
	{

		try {

			String query = "Select * from SubCategory";  //create a new query ,getting all fields
			pstmt = con.prepareStatement(query);

			ResultSet rs =  pstmt.executeQuery(query); //create a new result set
			ArrayList<String> subCatNames = new ArrayList<String>();  //declaring an array list of type String
			while (rs.next()) 
			{
				String name = rs.getString("subCatName");   //get name from result set for the category
				subCatNames.add(name);                           //add name to the list     
			}      

			rs.close(); //close result set
			pstmt.close(); //close prepared statement
			Collections.sort(subCatNames);
			return subCatNames;
		}
		catch (Exception io) {
			return null;
		}  
	}

	//this method is responsible for returning an array LIST of NAMES sub-categories that are within a given Category name
	public ArrayList<String> getSubCategories(String catName)
	{        ArrayList<String> listNames = new ArrayList<String>();  //declaring an array list of type String
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
			String query2 = "Select subCatName From SubCategory where categoryID = ? ";
			pstmt2 = con.prepareStatement(query2);
			pstmt2.setInt(1,catID); //sets the categoryID for the statement query
			ResultSet rs2 =  pstmt2.executeQuery();

			while (rs2.next()) 
			{
				String name = rs2.getString("subCatName");
				listNames.add(name);   //add newly created object item to the list to be returned

			}      

			rs2.close(); //close result set 2
			pstmt2.close(); //close prepared statement

			Collections.sort (listNames);

			return listNames;
		}
		catch (Exception io) {
			return null;
		}  
	}

	// Method to get Objects Items in a given subCategory 				
	public ArrayList<Item>  getItemsInSubcategory(String subCatName)
	{
		Item item;
		try {

			ArrayList<Item> listItems = new ArrayList<Item>(); // new arrayList of type Item

			//create a new query based on subCategory Name
			//query structure for requesting a subCategoryID from any subCategory name that is passed
			String query = "Select SubCatID From SubCategory where subCatName = ? ";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1,subCatName); //sets the SubcategoryName for the statement query
			
			ResultSet rs = pstmt.executeQuery(); //executes query and puts the subCategory ID into rs
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
				int id       =rs2.getInt("itemID");	
				double price = rs2.getDouble("itemPrice");                    
				String brand = rs2.getString("itemBrand");
				String model = rs2.getString("itemModel");   //get attributes for the item from result set 2
				int level    = rs2.getInt("stockLevel");
				int level2   = rs2.getInt("availableStockLevel");
				boolean flag=rs2.getBoolean("flag");

				item=new Item(brand,model,level,price,level2);//creating a new object with some of the attributes
				item.setItemID(id);
				item.setFlag(flag);   //set flag for the item
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
				String password = rs.getString("password");  //get attributes from Account table
				String type     = rs.getString("accountType");
				boolean flag   =rs.getBoolean("flag"); 

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

	public ArrayList<Sale>  getSalesByDate(Date date1,Date date2)
	{
		Sale sale;
		try {

			ArrayList<Sale> listSales = new ArrayList<Sale>();  //create an array list of type Sale
			//this query will return all sales between the two given dates
			String query = "SELECT *  FROM Sale ";			
			pstmt = con.prepareStatement(query);
			ResultSet rs =  pstmt.executeQuery(query);
			ArrayList<Account> Accounts=new ArrayList<Account>();
			Accounts=getAllAccounts();
			int size=Accounts.size();
			

			while (rs.next()) 
			{
				//getting attributes from  the Sale Table 
				int SaleID=rs.getInt("saleID");
				Date saleDate  =rs.getDate("saleDate");	
				double price = rs.getDouble("totalSalePrice");                    
				int accountID =rs.getInt("accountID");
				
				
				sale=new Sale(SaleID,saleDate,price,accountID,"GoPlanet"); //create new object sale
				if (sale.getDate().after(date1) && sale.getDate().before(date2))
				{
				for (int i=0;i<size;i++)
				{
					if (sale.getAccountID()==Accounts.get(i).getAccountID()) 
					{sale.setName(Accounts.get(i).getAccountName());}
				}
				listSales.add(sale);       //add the Sale to a list
			   }    
			}
			rs.close(); //close result set
			pstmt.close(); //close prepared statement
			return listSales;
		}
		catch (Exception io) {
			return null;
		}  
	}

	//pass the category name to get back the category id
	protected int getCategoryIdByName(String catName)
	{
		try{
			String query = "Select categoryID From Category where categoryName = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,catName); //sets the categoryName for the statement query
			ResultSet rs = pstmt.executeQuery(); //executes query and puts the category ID into rs
			int catID = 0; //initialize the variable to hold the catID we get back from DB
			while( rs.next()) { 
				catID = rs.getInt("categoryID");	// sets the cat ID  
			}
			rs.close(); //close result set
			pstmt.close(); //close prepared statement
			return catID;
		}
		catch(Exception e)
		{
			return (Integer) null;
		}
	}
	//pass the subCat name to get back the subCatID
	protected int getSubCatIdByName(String subCatName) {

		try{
			String query = "Select subCatID From subcategory where subCatName = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,subCatName); //sets the subCategoryName for the statement query
			ResultSet rs = pstmt.executeQuery(); //executes query and puts the SubCategory ID into rs
			int subCatID = 0; //initialize the variable to hold the catID we get back from DB
			while( rs.next()) { 
				subCatID = rs.getInt("subCatID");	// sets the subCat ID  
			}
			rs.close(); //close result set
			pstmt.close(); //close prepared statement
			return subCatID;
		}
		catch(Exception e)
		{
			return (Integer) null;
		}
	}

	//pass this method the brand and model strings to get back the item
	protected Item getItemByName(String brand, String model)
	{
		try{
			String query = "Select * From item where itemBrand = ? and itemModel = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,brand); //sets the itemBrand for the statement query
			pstmt.setString(2,model); //sets the itemModel for the statement query
			ResultSet rs = pstmt.executeQuery(); //executes query and puts the Item Table Data into rs
			Item item = new Item();
			while( rs.next()) { 
				int id   = rs.getInt("itemID");	
				double price = rs.getDouble("itemPrice");         
				String itemBrand = rs.getString("itemBrand");  //get attributes from Account table
				String itemModel = rs.getString("itemModel");
				int level = rs.getInt("stockLevel");
				int level2 = rs.getInt("availableStockLevel");
				boolean flag   =rs.getBoolean("flag"); 

				item=new Item(id,price,itemBrand,itemModel,level,level2, flag); //create new object item
			}
			rs.close(); //close result set
			pstmt.close(); //close prepared statement
			return item;
		}
		catch(Exception e)
		{
			return null;
		}
	}
  protected ArrayList<ReservedItem >getReservedItems()
  {
	  try {

			ArrayList<ReservedItem> list = new ArrayList<ReservedItem>(); //create a new arraylist type account
			String query = "Select * from ReservedItem  where flag>0";  //create a new query 
			pstmt = con.prepareStatement(query);
			ResultSet rs =  pstmt.executeQuery(query);
			while (rs.next()) 
			{
				int   accountID   = rs.getInt("accountID");	  
				String docketNo = rs.getString("docketNo");  //get attributes from ReservedItem Table
				Date date     = rs.getDate("reservationDate");
				Double deposit=rs.getDouble("depositPlaced");
				int itemID=rs.getInt("itemID");
				boolean flag   =rs.getBoolean("flag"); 

				ReservedItem reservedItem=new ReservedItem(date,deposit,docketNo,itemID,accountID,flag);//create a new object reservedItem
				list.add(reservedItem);  //add object to the list

			}  

			rs.close(); //close result set
			pstmt.close(); //close prepared statement
			return list;
		}
		catch (Exception io) {
			return null;
		}  
	  
	  
  }

}


