package com.group8.model;



/*The Concept of an Item is represented by this class.
 * Items on our system will be linked to SubCategories which they are listed under
 * The item ID is for system use only.
 * The item Price will be the retail value of the item
 * The item Brand will be the company that manufactures/produces the item
 * The Item Model will be the name of the item that varies from Brand to Brand and uniquely identifies the item
 * The Stock Level indicates how many copies of the item are in store at any given time
 * The Available Stock Level indicates how many copies of the item are available to be sold to Customers whodo not have Reservations
 * */
public class Item {

	//Attributes of an Item
	private int itemID;
	private double itemPrice;
	private String itemBrand, itemModel;
	private int stockLevel, availableStockLevel;
	
	//Blank Constructor
	public Item()
	{
		
	}
	//Constructor that accepts the Item Brand and Item Model as arguments
	public Item(String b, String m)
	{
		setBrand(b);
		setModel(m);
	}
	//Constructor that accepts the Item Brand, Item Model, and Stock Level as arguments
	public Item(String b, String m, int s)
	{
		setBrand(b);
		setModel(m);
		setStockLevel(s);
	}
	//Constructor that accepts the Item Brand, Item Model, Stock Level, and Retail Price as arguments
	public Item(String b, String m, int s, double c,int a,int x,boolean f)
	{
		setBrand(b);
		setModel(m);
		setStockLevel(s);
		setPrice(c);
		setAvailabelStockLevel(a);
		setSubcatID(x);
		setItemFlag(f);
	}
	//Set and Get Methods for Item Attributes
	//setItemID will set the itemID of the item
	//integer i is the ID to be set
	public void setItemID(int i)
	{
		this.itemID = i;
	}
	//setPrice will set the retail price for the item
	//double p is the price to be set
	public void setPrice(double p)
	{
		this.itemPrice = p;
	}
	//setBrand will set the Brand name of the item
	//String b is the name to be set
	public void setBrand(String b)
	{
		this.itemBrand = b;
	}
	//setModel will set the Model name of the item
	//String m is the name to be set
	public void setModel(String m)
	{
		this.itemModel = m;
	}
	//setStockLevel will set the number of copies in store
	//integer s is the number of copies to be set
	public void setStockLevel(int s)
	{
		this.stockLevel = s;
	}
	//setAvailableStockLevel will set the number of copies available to be sold
	//integer s is the number available to be set
	public void setAvailableStockLevel(int s)
	{
		this.availableStockLevel = s;
	}
	//returns the itemID
	public int getItemID()
	{
		return itemID;
	}
	//returns the itemPrice
	public double getPrice()
	{
		return itemPrice;
	}
	//return the itemBrand
	public String getBrand()
	{
		return itemBrand;
	}
	//returns the itemModel
	public String getModel()
	{
		return itemModel;
	}
	//returns the stockLevel
	public int getStockLevel()
	{
		return stockLevel;
	}
	//returns the availableStockLevel
	public int getAvailableStockLevel()
	{
		return availableStockLevel;
	}
}
