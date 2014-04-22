package com.group8.view;

import javax.swing.JPanel;


import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import java.awt.Label;
import java.awt.Font;

public class LoginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3568249935233716447L;
	private JTextField usrNameTF;
	private JButton btnLogin;
	private JPasswordField usrPassPF;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private Label label;
	private Label label_1;

	/**
	 * Create the panel.
	 */
	public LoginPanel()	 {
		
		//set panel properties and add objects
		
		setLayout(new MigLayout("", "[320px][][]", "[160px][][][][35][][76.00][][][][][][][][][][220]"));
		setSize(1048, 738);
		
		label = new Label("ProMusica Inventory System ");
		label.setFont(new Font("Cambria", Font.BOLD, 25));
		add(label, "cell 1 0 2 1");
		
		lblUsername = new JLabel("USERNAME : ");
		lblUsername.setFont(new Font("Cambria", Font.BOLD, 13));
		add(lblUsername, "flowx,cell 1 3,alignx trailing");
		
		usrNameTF = new JTextField();
		add(usrNameTF, "cell 1 3,alignx center");
		usrNameTF.setColumns(15);
		
		lblPassword = new JLabel("PASSWORD: ");
		lblPassword.setFont(new Font("Cambria", Font.BOLD, 13));
		add(lblPassword, "flowx,cell 1 5,alignx trailing");
		
		usrPassPF = new JPasswordField();
		usrPassPF.setColumns(15);
		add(usrPassPF, "cell 1 5,alignx center");
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Cambria", Font.BOLD, 14));
		add(btnLogin, "cell 1 7,alignx right");
		
		label_1 = new Label("Developed by Group8");
		label_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		add(label_1, "cell 0 16,alignx center");

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
