package com.group8.view;



import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;

@SuppressWarnings("serial")
public class SalesPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public SalesPanel() {
		setLayout(new MigLayout("", "[grow]", "[][51.00][grow]"));
		
		Label label = new Label("Under Construction ");
		label.setForeground(Color.RED);
		label.setFont(new Font("Cambria", Font.BOLD, 19));
		add(label, "flowy,cell 0 2,alignx center");
		
		table = new JTable();
		add(table, "cell 0 2,grow");

	}

}
