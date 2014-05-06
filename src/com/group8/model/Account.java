package com.group8.model;



/*The Concept of an Account is represented by this class.
 * The userID is the system ID that identifies each Account on the system
 * The accountType is either Administrator, Manager, or Sales Staff
 * The userName is the name used for each user to log in
 * The password is a secret string that each staff member will use to log in to their account
 * */
public class Account {

	//Attributes of an Account
	private int accountID;
	private boolean flag;
	private String accountType, accountName, password;

	//Blank Constructor
	public Account()
	{ 
		setFlag(true);
	}
	//Constructor that accepts Type, User name, and Password as arguments
	public Account(String t, String u, String p)
	{
		setType(t);
		setAccountName(u);
		setPassword(p);
		setFlag(true);
	}
	public Account(int id,String name,String passwd,String type,int flag){
		accountID=id;
		accountName=name;
		password=passwd;
		accountType=type;

	}
	//Sets and Gets for Account Attributes
	//setUserID will set the userID for the Account
	//integer u is the ID to be set
	public void setAccountID(int u)
	{
		this.accountID = u;
	}
	//setType will set the accountType of the Account
	//integer t will be the type to be set
	public void setType(String t)
	{
		this.accountType = t;
	}
	//setUsername will set the username of the Account
	//String name is the username to be set
	public void setAccountName(String name)
	{
		this.accountName = name;
	}
	//setPassword will set the password of the account
	//String pass is the password to be set
	public void setPassword(String pass)
	{
		this.password = pass;
	}
	//setFlag will set the flag to the account
	public void setFlag(boolean flag )
	{
		this.flag = flag;
	}

	//returns userID
	public int getAccountID()
	{
		return accountID;
	}
	//returns accountType
	public String getType()
	{
		return accountType;
	}
	//returns userName
	public String getAccountName()
	{
		return accountName;
	}
	//returns password
	public String getPassword()
	{
		return password;
	}
	//returns flag
	public boolean getFlag()
	{
		return flag;
	}
}
