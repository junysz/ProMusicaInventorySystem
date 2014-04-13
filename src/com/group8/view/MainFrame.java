package com.group8.view;



import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;



@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{

	private JPanel contentPane;
	private LoginPanel loginPanel;
	private TabsPane tabbedPane;
	//private StockBrowserPanel stockBrowsingPanel;



	public MainFrame() {
		
		loginPanel = new LoginPanel();
		setDefaultCloseOperation(JOptionPane.CANCEL_OPTION);
		setBounds(100, 100, 650, 460);
		setSize(1250, 700);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout());
		setContentPane(contentPane);
		
		tabbedPane = new TabsPane();
		add(tabbedPane);
		contentPane.add(tabbedPane);
		tabbedPane.setVisible(false);
		contentPane.add(loginPanel);
		
	
	}
	
	public TabsPane getTabsPane()
	{
		return tabbedPane;
	}
	
	
	
	public LoginPanel getLoginPanel()
	{
		return loginPanel;
	}
	
	


	
	
	
	
	
	
	
	//this will accept any object that implements CategoryListener 
		//this is going to be class form controller packet
		public void setCategoryListener(CategoryListener categoryListenr){
			System.out.println("I will accept any object that implemnts CategoryListener Class:Testing_MaintainPanel");
			tabbedPane.setCategoryListenr(categoryListenr);
			
		}
		
		
		
}
