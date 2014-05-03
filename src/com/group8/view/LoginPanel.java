package com.group8.view;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import java.awt.Label;
import java.awt.Font;
import java.io.IOException;

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
	private Label musicaLabel;
	private Label labelGoup8;
	private Label label_2;

	/**
	 * Create the panel.
	 */
	public LoginPanel()	 {
		
		//set panel properties and add objects
		
		setLayout(new MigLayout("", "[][][99.00px][138.00][][][][][][76.00][261.00,fill]", "[160px][][][][35][][][][76.00][][][][][][][][][][][][][][][41.00][169.00][220]"));
		setSize(1048, 738);
		
		label = new Label("ProMusica Inventory System ");
		label.setFont(new Font("Cambria", Font.BOLD, 25));
		add(label, "cell 3 0 2 1");
		
		
		BufferedImage wPic = null;
		try {
			wPic = ImageIO.read(this.getClass().getResource("/logo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedImage Pic2 = null;
		try {
			Pic2 = ImageIO.read(this.getClass().getResource("/Musica.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel musicaLabel = new JLabel(new ImageIcon(Pic2) );
		add(musicaLabel, "cell 5 1 6 24");
		
		lblUsername = new JLabel("USERNAME : ");
		lblUsername.setFont(new Font("Cambria", Font.BOLD, 13));
		add(lblUsername, "flowx,cell 3 9,alignx left");
		
		lblPassword = new JLabel("PASSWORD: ");
		lblPassword.setFont(new Font("Cambria", Font.BOLD, 13));
		add(lblPassword, "flowx,cell 3 13,alignx left");
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Cambria", Font.BOLD, 14));
		add(btnLogin, "cell 3 18,alignx center");
		
		
		
		label_2 = new Label("Powered by");
		label_2.setFont(new Font("Monotype Corsiva", Font.BOLD, 18));
		add(label_2, "cell 2 24");
		JLabel labelGroup8 = new JLabel(new ImageIcon(wPic));
		add(labelGroup8, "cell 3 24");
		
		usrPassPF = new JPasswordField();
		usrPassPF.setColumns(15);
		add(usrPassPF, "cell 3 13,alignx center");
		
		usrNameTF = new JTextField();
		add(usrNameTF, "cell 3 9,alignx center");
		usrNameTF.setColumns(15);
		
		
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
