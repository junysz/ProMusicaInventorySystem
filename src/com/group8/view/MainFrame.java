package com.group8.view;



import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import com.group8.controller.LoginController;
import com.group8.model.MainModel;



@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{

	private JPanel contentPane;
	private LoginPanel loginPanel;
	private TabsPane tabbedPane;



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
		
		displayLoginView();
		
	
	}
	
	public TabsPane getTabsPane()
	{
		return tabbedPane;
	}
	
	//this will accept any object that implements CategoryListener 
		//this is going to be class form controller packet
	
	
		public void setCategoryListener(CategoryListener categoryListenr){
			
			tabbedPane.setCategoryListenr(categoryListenr);
			
		}

		public void displayMainView() {


			loginPanel.setVisible(false);
			tabbedPane.setVisible(true);
			tabbedPane.add("Maintenance",tabbedPane.getMaintainPanel());	
			tabbedPane.add("Reports",tabbedPane.getReportPanel());
			
		
			
		}
		public void displayMainViewStaff() {


			loginPanel.setVisible(false);
			tabbedPane.setVisible(true);
			tabbedPane.remove(tabbedPane.getReportPanel());	
			tabbedPane.remove(tabbedPane.getMaintainPanel());			
			
		}
		
		public void displayLoginView()
		{
			loginPanel.setVisible(true);
			tabbedPane.setVisible(false);
			loginPanel.setUserField("");
			loginPanel.setPassField("");
			loginPanel.getUsernameField().grabFocus();
			loginPanel.getUsernameField().selectAll();
		}
		 public void logout()
		 {
						
			displayLoginView();
			
		 }

		public String getUsernameLoginString() {
			return loginPanel.getUsernameFieldString();
		}

		public String getPasswordLoginString() {
			return loginPanel.getPasswordFieldString();
		}
		
		public void addLoginListener(LoginController l)
		{
			loginPanel.addLoginButtonListener(l);
			
			//Set enter key for login button
			loginPanel.getRootPane().setDefaultButton(loginPanel.getLoginBtn());
		}
		
		
}
