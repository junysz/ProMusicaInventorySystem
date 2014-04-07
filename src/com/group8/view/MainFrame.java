package com.group8.view;



import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.group8.controller.LoginListener;



@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{

	private JPanel contentPane;
	private LoginPanel loginPanel;

	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private TabsPane tabbedPane;
	//private StockBrowserPanel stockBrowsingPanel;
	private JMenu mnManageStock;
	private JMenuItem mntmLogOut;
	private JMenuItem mntmExit;
	private JMenuItem mntmCorrectStockLevel;
	private JMenuItem mntmMarkIncorrectItem;
	private JMenu mnManageAccount;
	private JMenuItem mntmAddNewAccount;
	private JMenuItem mntmChangeUsername;
	private JMenuItem mntmChangePassword;
	private JMenuItem mntmHelp;
	private JMenuItem mntmAbout;
	private JMenuItem mntmAddNewItem;



	public MainFrame() {
		
		loginPanel = new LoginPanel();
		tabbedPane = new TabsPane();
		setDefaultCloseOperation(JOptionPane.CANCEL_OPTION);
		setBounds(100, 100, 650, 460);
		setSize(1250, 700);
		setVisible(true);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmLogOut = new JMenuItem("Log Out...");
		mnFile.add(mntmLogOut);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		mnManageStock = new JMenu("Manage Stock");
		menuBar.add(mnManageStock);
		
		mntmCorrectStockLevel = new JMenuItem("Correct Stock Level");
		mnManageStock.add(mntmCorrectStockLevel);
		
		mntmMarkIncorrectItem = new JMenuItem("Mark Incorrect Item");
		mnManageStock.add(mntmMarkIncorrectItem);
		
		mntmAddNewItem = new JMenuItem("Add New Item");
		mnManageStock.add(mntmAddNewItem);
		
		mnManageAccount = new JMenu("Manage Account");
		menuBar.add(mnManageAccount);
		
		mntmAddNewAccount = new JMenuItem("Add New Account");
		mnManageAccount.add(mntmAddNewAccount);
		
		mntmChangeUsername = new JMenuItem("Change Username");
		mnManageAccount.add(mntmChangeUsername);
		
		mntmChangePassword = new JMenuItem("Change Password");
		mnManageAccount.add(mntmChangePassword);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);
		
		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
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
