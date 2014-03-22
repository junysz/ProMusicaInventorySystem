package com.group8.model;



/*The Concept of a Category is represented by this class.
 * Category on our system will be linked to Sub Categories which will be linked
 * to different Items on the System.
 * The Category ID is for system use only.
 * The Category Name is chosen by the user when implementing the software
 * */
public class Category {
	
	//Attributes of a Category
	private int categoryID;
	private String categoryName;
	
	//Blank Constructor
	public Category()
	{
		
	}
	//Constructor that accepts the category name as an argument
	public Category(String n)
	{
		setCategoryName(n);		
	}
	//Set and Get methods for access to the attributes
	//setCategoryID sets the system ID used for tracking categories 
	//integer x is the ID being set
	public void setCategoryID(int x)
	{
		this.categoryID = x;
	}
	//setCategoryName sets the name that will be displayed to the user to identify the category 
	//String s is the name being set
	public void setCategoryName(String s)
	{
		this.categoryName = s;
	}
	//returns the categoryID
	public int getCategoryID()
	{
		return categoryID;
	}
	//returns the CategoryName
	public String getCategoryName()
	{
		return categoryName;
	}

}
