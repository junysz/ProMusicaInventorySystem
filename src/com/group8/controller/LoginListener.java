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
	
	private MainFrame mainView;
	private MainModel mainModel;
	private String usrName, usrPass;
	public LoginListener(MainFrame v, MainModel m)
	{
		mainView = v;
		mainModel = m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Performed...");
		usrName = mainView.getLoginPanel().getUsernameFieldString();
		usrPass = mainView.getLoginPanel().getPasswordFieldString();
		boolean valid = false;
		
		if(usrName.equals("") || usrName==null || usrPass.equals("") || usrPass==null)
		{
			JOptionPane.showMessageDialog(mainView, "One of th fields is empty! \nPlease try again.", "Error!", 0);
		}
		else
		{
			for(int i = 0 ; i < mainModel.getAllAccounts().size() ; i++){
				
			
				if(usrName.equals(mainModel.getAllAccounts().get(i).getAccountName())&&usrPass.equals(mainModel.getAllAccounts().get(i).getPassword()))
				{
	
					JOptionPane.showMessageDialog(mainView, "Login Succesful!", "Succes", 1);
					mainView.getLoginPanel().setVisible(false);
					mainView.getTabsPane().setVisible(true);
					valid = true;
					
				}
			}
			if(!valid)
			{
				JOptionPane.showMessageDialog(mainView, "One of the fields is invalid! \nPlease try again.", "Incorrect Information!", 0);
			}
		}

		mainView.getLoginPanel().setUserField(usrName);
		mainView.getLoginPanel().setPassField("");
		mainView.getLoginPanel().getUsernameField().selectAll();
		mainView.getLoginPanel().getUsernameField().grabFocus();
		
	}
	
}
