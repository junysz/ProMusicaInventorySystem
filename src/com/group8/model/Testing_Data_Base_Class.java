package com.group8.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;


public class Testing_Data_Base_Class {
	private Connection con;
	

	private ArrayList<Category> categoryList;


	public Testing_Data_Base_Class()
	{
		categoryList = new ArrayList<>();
	}




	public void connect() throws Exception{

		if(con!=null) return;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			throw new Exception("Driver not found");
		}
		String conURL="jdbc:mysql://localhost:8889/testGroup8";
		con = (Connection) DriverManager.getConnection(conURL,"root","root");
		System.out.println("Connected "+con);
	}

	public void disconnect(){

		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Canat close connection");
			}
		}

	}




public void save() throws SQLException{
	
	String checkSql ="select count(*) as count from testGroup8.Category where categoryID=?";
	PreparedStatement checkStmt= (PreparedStatement) con.prepareStatement(checkSql);
	
	
	for(Category category: categoryList){
		
		
		int catID=category.getCategoryID();
		
		checkStmt.setInt(1,catID );
		ResultSet checkResult= (ResultSet) checkStmt.executeQuery();
		checkResult.next();
		
		int count= checkResult.getInt(1);
		
				
		
		System.out.println("this form db category ID "+catID+"  is  "+count);
	
	}
	
	checkStmt.close();
	
}



	



	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}




	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}


	public void printCategory(){

		for(int i=0;i<categoryList.size();i++)
		{
			categoryList.get(i).print();
		}
	}





public void initializeCBox(){
	String queryString= "SELECT categoryName FROM Category ORDER BY categoryName";
	
	try {
		PreparedStatement checkStmt=   (PreparedStatement) con.prepareStatement(queryString);
		ResultSet checkResult= (ResultSet) checkStmt.executeQuery();
	
		while(checkResult.next())
		{
			
			categoryList.add(new Category(checkResult.getString(1)));
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
}

}



