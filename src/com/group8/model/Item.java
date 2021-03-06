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
	private int id;
	private double price;
	private String brand, model;
	private int stockLevel, availableStockLevel;
	private boolean flag;
	
	public Item()
	{
		
	}
	
	public Item(int id,double price,String brand,String model,int stockLevel, int availableStockLevel, boolean flag)
	{
		setItemID(id);
		setPrice(price);
		setBrand(brand);
		setModel(model);
		setStockLevel(stockLevel);
		setAvailableStockLevel(availableStockLevel);
		setFlag(flag);
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
	//Constructor that accepts the Item Brand, Item Model, Stock Level,Retail Price,Available stock level  as arguments
	public Item(String b, String m, int s, double c,int a)
	{
		setBrand(b);
		setModel(m);
		setStockLevel(s);
		setPrice(c);
		setAvailableStockLevel(a);
		
	}
	//Set and Get Methods for Item Attributes
	//setItemID will set the itemID of the item
	//integer i is the ID to be set
	public void setItemID(int i)
	{
		this.id = i;
	}
	//setPrice will set the retail price for the item
	//double p is the price to be set
	public void setPrice(double p)
	{
		this.price = p;
	}
	//setBrand will set the Brand name of the item
	//String b is the name to be set
	public void setBrand(String b)
	{
		this.brand = b;
	}
	//setModel will set the Model name of the item
	//String m is the name to be set
	public void setModel(String m)
	{
		this.model = m;
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
	
	public void setFlag(boolean f)
	{
		this.flag = f;
	}
	//returns the itemID
	public int getItemID()
	{
		return id;
	}
	//returns the itemPrice
	public double getPrice()
	{
		return price;
	}
	//return the itemBrand
	public String getBrand()
	{
		return brand;
	}
	//returns the itemModel
	public String getModel()
	{
		return model;
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
	public boolean getFlag()
	{
		return flag;
	}
}
