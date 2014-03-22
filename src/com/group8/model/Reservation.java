package com.group8.model;


/*The Concept of a Reservation is represented by this class.
 * The reservationID is the system ID used to identify Reservations
 * The resDate is the date the reservation was initially made
 * The depositPlaced is the deposit currently paid on the item
 * The docketNo is the number on the paper docket, the customer and the store keep a copy of this docket while the Reservation is still not paid off
 * */
public class Reservation {

	//Attributes of a Reservation
	private int reservationID;
	private String resDate; 
	private double depositPlaced;
	private int docketNo; 
	
	//Blank Constructor
	public Reservation()
	{
		
	}
	//Constructor that accepts the Date, Deposit, and DocketNo as arguments
	public Reservation(String date, double deposit, int docketNum)
	{
		setStartDate(date);
		setDeposit(deposit);
		setDocketNo(docketNum);
	}
	//Sets and Gets for the Reservation Attributes
	//setResID will set the reservationID for the Reservation
	//integer r is the number to be set
	public void setResID(int r)
	{
		this.reservationID = r;
	}
	//setStartDate will set the resDate for the Reservation
	//String d is the date to be set
	public void setStartDate(String d)
	{
		this.resDate = d;
	}
	//setDeposit will set the depositPlaced for the Reservation
	//double d is the deposit value to be set
	public void setDeposit(double d)
	{
		this.depositPlaced = d;
	}
	//setDocketNo will set the docketNo for the Reservation
	//integer d is the number to be set
	public void setDocketNo(int d)
	{
		this.docketNo = d;
	}
	//returns reservationID
	public int getResID()
	{
		return reservationID;
	}
	//returns resDate
	public String getStartDate()
	{
		return resDate;
	}
	//returns depositPlaced
	public double getDeposit()
	{
		return depositPlaced;
	}
	//returns docketNo
	public int getDocketNo()
	{
		return docketNo;
	}
}
