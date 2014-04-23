package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import com.group8.model.Account;
import com.group8.model.MainModel;
import com.group8.view.MainFrame;

public class MaintainAccounts {

	private MainFrame theView;  	
	private MainModel theModel;	


	public MaintainAccounts(MainFrame theView, MainModel theModel){
		this.theView=theView;
		this.theModel=theModel;

		theView.getTabsPane().getMaintainPanel().addCreateAccountBtn(new MAcreateBTN1());
		theView.getTabsPane().getMaintainPanel().addConfirmChangesAccount(new MAconfirmChangeBTN2());
		theView.getTabsPane().getMaintainPanel().addSelectAccountToEditComboBox(new MAselectAccCB());


	}


	//Inner Class that listens for the Create Account Button
	class MAcreateBTN1 implements ActionListener{

		String username=null;
		String password1=null;
		String password2=null;
		String accountTypeSelection=null;
		ArrayList<String> errorMessages;

		@Override
		public void actionPerformed(ActionEvent e) {

			errorMessages=new ArrayList<String>();
			//read the values (username, enterPassword, confirmPassword, accountType) from the view
			try{
				username=theView.getTabsPane().getMaintainPanel().getEnterUsernameTF();
				password1=theView.getTabsPane().getMaintainPanel().getEnterPasswordTF();
				password2= theView.getTabsPane().getMaintainPanel().getConfirmPasswordTF();
				accountTypeSelection=theView.getTabsPane().getMaintainPanel().getSelectAccountTypeComboBox().getSelectedItem().toString();
				checkIfAccountExitst();
			}catch(Exception ex){
				System.out.println("Problem reading input fron Create New Account Form");
			}
			//Now validate the data and add errors to errorMessages

			if(username.isEmpty()){
				errorMessages.add("Username");
			}
			if(password1.isEmpty() || password2.isEmpty()){
				errorMessages.add("Enter Password Twice");
			}
			if(!(password1.equals(password2))){
				errorMessages.add("Passwords Do Not Match");
			}
			if(accountTypeSelection==null){
				errorMessages.add("Account Type");
			}

			if(errorMessages.isEmpty()){
				theModel.addNewAccount(username,password1,accountTypeSelection);
				//Now that data processing is complete, clear the GUI form
				theView.getTabsPane().getMaintainPanel().clearNewAccountForm();

				updteAccounts();
			}
			else{
				theView.getTabsPane().getMaintainPanel().warnCreateAccountFormErrors(errorMessages);
				theView.getTabsPane().getMaintainPanel().clearNewAccountForm();
			}



		}















		void checkIfAccountExitst(){
			for(int i=0;i<theModel.getAllAccounts().size();i++){

				String accN=theModel.getAllAccounts().get(i).getAccountName();

				if(accN.equalsIgnoreCase(username)){
					errorMessages.add("Account Already Exitst");
					System.out.println(accN +"="+ username);
					break;	
				}
			}
		}
	}



	class MAconfirmChangeBTN2 implements ActionListener {

		String account=null;
		String userName=null;
		String accType=null;
		String passwd=null;
		boolean enabled;
		boolean disabled;
		int flag=0;
		ArrayList<String> errorMessages;
		@Override
		public void actionPerformed(ActionEvent e) {


			errorMessages=new ArrayList<String>();
			//read the values (username, enterPassword, confirmPassword, accountType) from the view
			try{

				userName=theView.getTabsPane().getMaintainPanel().getEditUsernameTF().getText();
				accType= theView.getTabsPane().getMaintainPanel().getEditAccountTypeComboBox().getSelectedItem().toString();
				passwd=theView.getTabsPane().getMaintainPanel().getEditAccountPasswordTF().getText();
				enabled=theView.getTabsPane().getMaintainPanel().getRdbtnEnableAccount().isSelected();
				//disabled=theView.getTabsPane().getMaintainPanel().getRdbtnDisableAccount().isSelected();
				if (enabled) flag=1;

			}catch(NullPointerException ex){
				errorMessages.add("Select Account");
			}

			if(userName.isEmpty()){
				errorMessages.add("Username");
			}
			if(passwd.isEmpty()){
				errorMessages.add("Enter New Password");
			}
			if(errorMessages.isEmpty()){


				if  (enabled) flag=0;	  
				String accountName=theView.getTabsPane().getMaintainPanel().getSelectAccountToEditComboBox().getSelectedItem().toString();
				Account a=theModel.getAccount(accountName);
				int accountID=a.getAccountID();

				theModel.updateAccount(accountID, userName, passwd, accType, flag);




				//Now that data processing is complete, clear the GUI form
				theView.getTabsPane().getMaintainPanel().clearNewAccountForm();
				theView.getTabsPane().getMaintainPanel().getEditUsername().setText(null);
				theView.getTabsPane().getMaintainPanel().getEditAccountPasswordTF().setText(null);
				theView.getTabsPane().getMaintainPanel().getEditAccountTypeComboBox().setSelectedIndex(-1);			

				updteAccounts();
			}
			else{
				theView.getTabsPane().getMaintainPanel().warnCreateAccountFormErrors(errorMessages);
				theView.getTabsPane().getMaintainPanel().clearNewAccountForm();
			}

		}	
	}



	class  MAselectAccCB implements ActionListener {
		String accountName=null;Account a;int i;
		@Override
		public void actionPerformed(ActionEvent e) {

			accountName=theView.getTabsPane().getMaintainPanel().getSelectAccountToEditComboBox().getSelectedItem().toString();

			Account a=theModel.getAccount(accountName);



			theView.getTabsPane().getMaintainPanel().setEditUsernameTF(a.getAccountName());
			theView.getTabsPane().getMaintainPanel().getEditAccountTypeComboBox().setSelectedItem(a.getType());

			theView.getTabsPane().getMaintainPanel().setEdditPasswordTF(a.getPassword());




		}
	}



	public void updteAccounts(){
		ArrayList<String>accountNames= new ArrayList<String>();

		for(int i=0;i<theModel.getAllAccounts().size();i++){

			String accN=theModel.getAllAccounts().get(i).getAccountName();
			accountNames.add(accN);
			System.out.println(accN);
		}

		theView.getTabsPane().getMaintainPanel().setAccountModel(accountNames);
	}


}
