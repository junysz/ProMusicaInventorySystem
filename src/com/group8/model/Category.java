package com.group8.model;

import java.util.ArrayList;



/*The Concept of a Category is represented by this class.
 * Category on our system will be linked to Sub Categories which will be linked
 * to different Items on the System.
 * The Category ID is for system use only.
 * The Category Name is chosen by the user when implementing the software
 * */
public class Category {
	
	//Attributes of a Category
	private int categoryID;
	static int id=1;
	private String categoryName;
	private ArrayList<SubCategory> subCategory;
	
	
	//Constructor that accepts the category name as an argument
	public Category(String n)
	{
		setCategoryName(n);	
		categoryID=id;
		id++;
		
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
	
	
	
	
	public ArrayList<SubCategory> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(ArrayList<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}
	public void addSubcategory(SubCategory subCategory){
		this.subCategory.add(subCategory);
	}
	public void print() {
		System.out.println("Category ID is: "+getCategoryID());
		System.out.println("Category Name is: "+getCategoryName());
		
	}

}
