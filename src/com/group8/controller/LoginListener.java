package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.group8.model.MainModel;
import com.group8.view.MainFrame;




public class LoginListener implements ActionListener {
/*
 * (non-Javadoc)
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 * 
 * 
 * This action listener is only for login button!
 */
	
	private MainFrame theView;
	private MainModel theModel;
	private String usrName=new String(""), usrPass=new String("");
	
	public LoginListener(MainFrame v, MainModel m)
	{
		theView = v;
		theModel = m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Performed...");
		usrName = theView.getUsernameLoginString();
		usrPass = theView.getPasswordLoginString();
		System.out.println("Logging in as: "+theView.getUsernameLoginString() + " with password: "+theView.getPasswordLoginString());
		boolean valid = false;
		
		if(usrName.equals("") || usrPass.equals(""))
		{
			JOptionPane.showMessageDialog(theView, "One of th fields is empty! \nPlease try again.", "Error!", 0);
			theView.displayLoginView(usrName);
		}
		else
		{
			for(int i = 0 ; i < theModel.getAllAccounts().size() ; i++){
				
			
				if(usrName.equals(theModel.getAllAccounts().get(i).getAccountName())&&usrPass.equals(theModel.getAllAccounts().get(i).getPassword()))
				{
	
					JOptionPane.showMessageDialog(theView, "Login Succesful!", "Succes", 1);
					theView.displayMainView();
					valid = true;
					
				}
			}
			if(!valid)
			{
				JOptionPane.showMessageDialog(theView, "One of the fields is invalid! \nPlease try again.", "Incorrect Information!", 0);
				theView.displayLoginView(usrName);
			}
		}

		
	}
	
}
