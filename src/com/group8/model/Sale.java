package com.group8.model;


/*The Concept of a Sale is represented by this class.
 * The saleID is for System use only
 * The saleDate is the Date the sale took place
 * The totalSalePrice is the total cost of the all the items that were sold in the single transaction
 * */
public class Sale {

	//Attributes of a Sale
	private int saleID;
	private String saleDate;
	private double totalSalePrice;
	
	//Blank Constructor
	public Sale()
	{
		
	}
	//Constructor that accepts the Date and Total as arguments
	public Sale(String d, double t)
	{	
		setDate(d);
		setTotalPrice(t);
	}
	//Sets and Gets for Sale Attributes
	//setSaleID will set the system ID for each Sale
	//integer i is the ID to be set
	public void setSaleID(int i)
	{
		this.saleID = i;
	}
	//setDate will set the Date the Sale took place
	//String d is the date to be set
	public void setDate(String d)
	{
		this.saleDate = d;
	}
	//setTotalPrice will set the total price of all items that were sold
	//double p is the price to be set
	public void setTotalPrice(double p)
	{
		this.totalSalePrice = p;
	}
	//returns the saleID
	public int getSaleID()
	{
		return saleID;
	}
	//returns the saleDate
	public String getDate()
	{
		return saleDate;
	}
	//returns the totalSalePrice
	public double getTotalPrice()
	{
		return totalSalePrice;
	}
	
}
