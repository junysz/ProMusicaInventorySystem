package com.group8.view;



import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import com.group8.controller.LoginListener;



@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{

	private JPanel contentPane;
	private LoginPanel loginPanel;
	private TabsPane tabbedPane;
	//private StockBrowserPanel stockBrowsingPanel;



	public MainFrame() {
		
		loginPanel = new LoginPanel();
		tabbedPane = new TabsPane();
		setDefaultCloseOperation(JOptionPane.CANCEL_OPTION);
		setBounds(100, 100, 650, 460);
		setSize(1250, 700);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout());
		setContentPane(contentPane);
		
		add(tabbedPane);
		contentPane.add(tabbedPane);
		tabbedPane.setVisible(false);
		contentPane.add(loginPanel);
		
		displayLoginView("");
		
	
	}
	
	public TabsPane getTabsPane()
	{
		return tabbedPane;
	}
	
	//this will accept any object that implements CategoryListener 
		//this is going to be class form controller packet
	
	
		public void setCategoryListener(CategoryListener categoryListenr){
			System.out.println("I will accept any object that implemnts CategoryListener Class:Testing_MaintainPanel");
			tabbedPane.setCategoryListenr(categoryListenr);
			
		}

		public void displayMainView() {


			loginPanel.setVisible(false);
			tabbedPane.setVisible(true);
			
		}
		
		public void displayLoginView(String usrName)
		{
			loginPanel.setVisible(true);
			tabbedPane.setVisible(false);
			loginPanel.setUserField(usrName);
			loginPanel.setPassField("");
			loginPanel.getUsernameField().grabFocus();
			loginPanel.getUsernameField().selectAll();
		}

		public String getUsernameLoginString() {
			return loginPanel.getUsernameFieldString();
		}

		public String getPasswordLoginString() {
			return loginPanel.getPasswordFieldString();
		}
		
		public void addLoginListener(LoginListener l)
		{
			loginPanel.addLoginButtonListener(l);
			
			//Set enter key for login button
			loginPanel.getRootPane().setDefaultButton(loginPanel.getLoginBtn());
		}
		
		
}
