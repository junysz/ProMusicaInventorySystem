package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.group8.model.Account;
import com.group8.model.MainModel;

import com.group8.view.MaintainPanel;

public class AccountsController {

	private Controller controller; 	
	


	public AccountsController(Controller controller){
		this.controller=controller;
	
		getMaintainPanel().addCreateAccountBtn(new MAcreateBTN1());
		getMaintainPanel().addConfirmChangesAccount(new MAconfirmChangeBTN2());
		getMaintainPanel().addSelectAccountToEditComboBox(new MAselectAccCB());
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
				username=getMaintainPanel().getEnterUsernameTF();
				password1=getMaintainPanel().getEnterPasswordTF();
				password2= getMaintainPanel().getConfirmPasswordTF();
				accountTypeSelection=getMaintainPanel().getSelectAccountTypeComboBox().getSelectedItem().toString();
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
				getModel().addNewAccount(username,password1,accountTypeSelection);
				//Now that data processing is complete, clear the GUI form
				getMaintainPanel().clearNewAccountForm();
				updteAccounts();
			}
			else{
				getMaintainPanel().warnCreateAccountFormErrors(errorMessages);
				getMaintainPanel().clearNewAccountForm();
			}
		}

		void checkIfAccountExitst(){
			for(int i=0;i<getModel().getAllAccounts().size();i++){

				String accN=getModel().getAllAccounts().get(i).getAccountName();

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

				userName=getMaintainPanel().getEditUsernameTF().getText();
				accType= getMaintainPanel().getEditAccountTypeComboBox().getSelectedItem().toString();
				passwd=getMaintainPanel().getEditAccountPasswordTF().getText();
				enabled=getMaintainPanel().getRdbtnEnableAccount().isSelected();
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
			if(errorMessages.isEmpty())
			{
				if  (enabled) flag=0;	  
				String accountName=getMaintainPanel().getSelectAccountToEditComboBox().getSelectedItem().toString();
				Account a=getModel().getAccount(accountName);
				int accountID=a.getAccountID();
				getModel().updateAccount(accountID, userName, passwd, accType, flag);
				//Now that data processing is complete, clear the GUI form
				getMaintainPanel().clearNewAccountForm();
				getMaintainPanel().getEditUsername().setText(null);
				getMaintainPanel().getEditAccountPasswordTF().setText(null);
				getMaintainPanel().getEditAccountTypeComboBox().setSelectedIndex(-1);			
				updteAccounts();
			}
			else{
				getMaintainPanel().warnCreateAccountFormErrors(errorMessages);
				getMaintainPanel().clearNewAccountForm();
			}
		}	
	}

	class  MAselectAccCB implements ActionListener {
		String accountName=null;
		Account a;
		int i;
		@Override
		public void actionPerformed(ActionEvent e) {

			accountName=getMaintainPanel().getSelectAccountToEditComboBox().getSelectedItem().toString();
			a=getModel().getAccount(accountName);

			getMaintainPanel().setEditUsernameTF(a.getAccountName());
			getMaintainPanel().getEditAccountTypeComboBox().setSelectedItem(a.getType());
			getMaintainPanel().setEdditPasswordTF(a.getPassword());
		}
	}



	public void updteAccounts(){
		ArrayList<String>accountNames= new ArrayList<String>();

		for(int i=0;i<getModel().getAllAccounts().size();i++){

			String accN=getModel().getAllAccounts().get(i).getAccountName();
			accountNames.add(accN);
			System.out.println(accN);
		}

		getMaintainPanel().setAccountModel(accountNames);
	}
	public MaintainPanel getMaintainPanel(){
		return controller.getView().getTabsPane().getMaintainPanel();
	}
	public MainModel getModel(){
		return controller.getModel();
	}
}
