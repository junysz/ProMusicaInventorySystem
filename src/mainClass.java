import java.awt.EventQueue;

import View.*;
//import Model.*;
//import Controller.*;

/*Test comment
 * can You see this
 */

/*
 * Test Number 2 ofcourse i can!
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
