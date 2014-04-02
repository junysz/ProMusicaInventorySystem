package com.group8.view;

import javax.swing.JPanel;


import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class LoginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3568249935233716447L;
	private JTextField usrNameTF;
	JButton btnLogin;
	private JPasswordField usrPassPF;
	private JLabel lblUsername;
	private JLabel lblPassword;

	/**
	 * Create the panel.
	 */
	public LoginPanel()	 {
		
		//set panel properties and add objects
		
		setLayout(new MigLayout("", "[200px][]", "[100px][][][]"));
		setSize(309, 226);
		
		lblUsername = new JLabel("USERNAME : ");
		add(lblUsername, "cell 0 1,alignx trailing");
		
		usrNameTF = new JTextField();
		add(usrNameTF, "cell 1 1,alignx left");
		usrNameTF.setColumns(15);
		
		lblPassword = new JLabel("PASSWORD: ");
		add(lblPassword, "cell 0 2,alignx trailing");
		
		usrPassPF = new JPasswordField();
		usrPassPF.setColumns(15);
		add(usrPassPF, "cell 1 2,alignx left");
		
		btnLogin = new JButton("Login");
		add(btnLogin, "cell 1 3,growx");

	}
	/*
	*  Setters and Getters for login panel
	*/
	public JButton getLoginBtn()
	{
		return btnLogin;
	}
	public void addLoginButtonListener(ActionListener aListener)
	{
		btnLogin.addActionListener(aListener);
	}
	public String getUsernameFieldString()
	{
		return usrNameTF.getText();
	}
	public String getPasswordFieldString()
	{
		return new String(usrPassPF.getPassword());
	}
	public void setUserField(String usrName)
	{
		usrNameTF.setText(usrName);
	}
	public void setPassField(String usrPass)
	{
		usrPassPF.setText(usrPass);
	}
	public JPasswordField getPasswordField()
	{
		return usrPassPF;
	}
	public JTextField getUsernameField()
	{
		return usrNameTF;
	}

}
