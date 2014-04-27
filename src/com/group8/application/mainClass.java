package com.group8.application;
import java.awt.EventQueue;

import com.group8.controller.Controller;
import com.group8.model.MainModel;
import com.group8.view.*;

public class MainClass {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame theView= new MainFrame();
					MainModel theModel= new MainModel();
					Controller theController= new Controller(theView,theModel);		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
