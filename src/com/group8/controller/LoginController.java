package com.group8.controller;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.group8.model.MainModel;
import com.group8.view.MainFrame;


public class LoginController implements ActionListener {
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * This action listener is only for login button!
	 */
	private MainFrame theView;
	private MainModel theModel;
	private String name,pass;
	private String name1="" ,pass1="",type="";
	boolean valid=false;
	boolean flag=true;
	int id;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	GraphicsDevice myDevice = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	public LoginController(MainFrame v, MainModel m)
	{
		theView = v;
		theModel = m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		name = theView.getUsernameLoginString();
		pass = theView.getPasswordLoginString();		

		for(int i = 0 ; i < theModel.getAllAccounts().size() ; i++)				
		{
			name1=theModel.getAllAccounts().get(i).getAccountName();
			if (name.equals(name1)) 
			{   valid=true;
			pass1=theModel.getAllAccounts().get(i).getPassword();	
			flag=theModel.getAllAccounts().get(i).getFlag();
			type=theModel.getAllAccounts().get(i).getType();
			id=theModel.getAllAccounts().get(i).getAccountID();
			}
		}
		if (name.equals("")) {	
			JOptionPane.showMessageDialog(null,	"Please enter an username","Warning",JOptionPane.WARNING_MESSAGE);
			theView.displayLoginView();
		}
		else if (pass.equals("")) {	
			JOptionPane.showMessageDialog(null,	"Please enter a password","Warning",JOptionPane.WARNING_MESSAGE);
			theView.displayLoginView();
		}
		else	if (!(valid))   {	
			JOptionPane.showMessageDialog(null,	"Username not found in the system","Warning",JOptionPane.WARNING_MESSAGE);
			theView.displayLoginView();
		}
		else if (!(pass.equals(pass1))) {	
			JOptionPane.showMessageDialog(null,"Password not matching account","Warning",JOptionPane.WARNING_MESSAGE);
			theView.displayLoginView();
		}
		else if (!flag) 
		{	
			JOptionPane.showMessageDialog(null,"Your account has been disabled","Warning",JOptionPane.WARNING_MESSAGE);
			theView.displayLoginView();
		}

		else  if (type.equals("Sales Staff")) 
		{
			theView.setTitle("ProMusica Inventory System");
			theView.displayMainViewStaff();
			theView.setResizable(true);
			theView.setSize(1250, 700);
			theView.setLocation(dim.width/2-theView.getSize().width/2, dim.height/2-theView.getSize().height/2);
		}
		else   
		{
			theView.setTitle("ProMusica Inventory System");
			theView.setResizable(true);
			theModel.setLoggedID(id);
			theModel.setLoggedName(name1);
			theView.initPanels();
			theView.displayMainView();
			theView.setSize(1250, 700);
			theView.setLocation(dim.width/2-theView.getSize().width/2, dim.height/2-theView.getSize().height/2);
		}
	}
}


