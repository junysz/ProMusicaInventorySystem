package com.group8.view;



import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.group8.controller.LoginController;
import javax.swing.ListSelectionModel;



@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{

	private JPanel contentPane;
	private LoginPanel loginPanel;
	private TabsPane tabbedPane;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();



	public MainFrame() {
		setVisible(false);
		loginPanel = new LoginPanel();
		tabbedPane = new TabsPane();
		tabbedPane.getReportPanel().getRableReport().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.getMakeSalePanel().getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setBounds(100, 100, 650, 460);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout());
		setContentPane(contentPane);
		
		getContentPane().add(tabbedPane);
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
			setSize(450, 560);
    		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			
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
		public void initPanels()
		
		{
			tabbedPane.emptyInputs();
		}
		
		
}
