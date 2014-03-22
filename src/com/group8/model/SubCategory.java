package com.group8.model;


/*The Concept of a Sub Category is represented by this class.
 * Sub Categories on our system will be linked to Categories which they are listed under
 * Items will also be linked to different Sub Categories.
 * The SubCat ID is for system use only.
 * The SubCat Name is chosen by the user when implementing the software
 * */
public class SubCategory {
	
	//Attributes of a Sub Category
	private int subCatID;
	private String subCatName;
	
	//Blank Constructor
	public SubCategory()
	{
		
	}
	//Constructor that accepts the name as an argument
	public SubCategory(String n)
	{
		setSubCatName(n);		
	}
	//Set and Get methods for access to the attributes
	//setSubCatID sets the system ID used for tracking SubCategories 
	//integer x is the ID being set
	public void setSubCatID(int x)
	{
		this.subCatID = x;
	}
	//setSubCatName sets the name that will be displayed to the user to identify the SubCategory 
	//String s is the name being set
	public void setSubCatName(String s)
	{
		this.subCatName = s;
	}
	//returns the SubCategry ID
	public int getSubCatID()
	{
		return subCatID;
	}
	//returns the SubCategoryName
	public String getSubCatName()
	{
		return subCatName;
	}
}
