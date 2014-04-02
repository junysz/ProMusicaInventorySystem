package com.group8.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.group8.view.LoginPanel;
import com.group8.view.MainFrame;
import com.group8.view.MaintainPanel;
import com.group8.view.ReservationPanel;
import com.group8.view.StockBrowserPanel;




public class LoginListener implements ActionListener {
/*
 * (non-Javadoc)
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 * 
 * 
 * This action listener is only for login button!
 */
	
	private MainFrame mainView;
	private String usrName, usrPass;
	public LoginListener(MainFrame m)
	{
		mainView = m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		usrName = mainView.getLoginPanel().getUsernameFieldString();
		usrPass = mainView.getLoginPanel().getPasswordFieldString();
		if(usrName.equals("") || usrName==null || usrPass.equals("") || usrPass==null)
		{
			JOptionPane.showMessageDialog(mainView, "One of th fields is empty! \nPlease try again.", "Error!", 0);
		}
		else
		{
		//	if(usrName.equals("user")&&usrPass.equals("password"))
			if (2>1)
			{

				JOptionPane.showMessageDialog(mainView, "Login Succesful!", "Succes", 1);
				mainView.getLoginPanel().setVisible(false);
				
				
				
			}
			else
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
