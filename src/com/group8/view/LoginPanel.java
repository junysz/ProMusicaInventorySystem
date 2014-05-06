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
	private static final long serialVersionUID = 3568249935233716447L;
	private JTextField usrNameTF;
	private JButton btnLogin;
	private JPasswordField usrPassPF;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private Label label;
	private JLabel lblPoweredBy;

	/**
	 * Create the panel.
	 */
	public LoginPanel()	 {

		//set panel properties and add objects
		setLayout(new MigLayout("", "[190.00][][]", "[170.00px][20.00][][][35][][][][76.00][][][][][][][][][][][][][][][][41.00][]"));
		setSize(250, 370);

		label = new Label("Inventory System ");
		label.setFont(new Font("Cambria", Font.BOLD, 25));
		add(label, "cell 0 8,alignx center");
		lblUsername = new JLabel("USERNAME : ");
		lblUsername.setFont(new Font("Cambria", Font.BOLD, 13));
		add(lblUsername, "flowx,cell 0 9,alignx right");
		lblPassword = new JLabel("PASSWORD: ");
		lblPassword.setFont(new Font("Cambria", Font.BOLD, 13));
		add(lblPassword, "flowx,cell 0 13,alignx right");
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Cambria", Font.BOLD, 14));
		btnLogin.setSize(80, 15);
		add(btnLogin, "cell 0 18,alignx center");
		usrNameTF = new JTextField();
		add(usrNameTF, "cell 0 9,alignx right");
		usrNameTF.setColumns(15);
		usrPassPF = new JPasswordField();
		usrPassPF.setColumns(15);
		add(usrPassPF, "cell 0 13,alignx right");
		ImagePanel proMusicaLogo = new ImagePanel("/com/group8/images/logo.png");
		add(proMusicaLogo, "cell 0 0 1 2,grow");
		lblPoweredBy = new JLabel("Powered by:");
		lblPoweredBy.setFont(new Font("Cambria", Font.PLAIN, 11));
		add(lblPoweredBy, "flowx,cell 0 25,alignx right");
		ImagePanel group8Logo = new ImagePanel("/com/group8/images/logo.jpg");
		add(group8Logo, "cell 0 25,grow");
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
