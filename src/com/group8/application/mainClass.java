package com.group8.application;
import java.awt.EventQueue;

import com.group8.view.*;


/*Test comment
 * can You see this
 */
//////
/*
 * Saturday update 22.03.14
 */


public class mainClass {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
