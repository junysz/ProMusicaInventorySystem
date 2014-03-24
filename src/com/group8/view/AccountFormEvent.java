package com.group8.view;

public class AccountFormEvent {
	
	private String userName;
	private String password;
	private String confirmPassword;
	private String type;
	
	
	public AccountFormEvent(String userName,String password,String confirmPassword,String type){
		this.userName=userName;
		this.password=password;
		this.confirmPassword=confirmPassword;
		this.type=type;
		
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

}
