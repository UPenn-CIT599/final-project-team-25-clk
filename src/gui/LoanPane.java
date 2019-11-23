package gui;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Database;

public class LoanPane extends JPanel implements ActionListener {
	private JComboBox<Item> loanSelection;
	private Database database;
	public LoanPane() {
		
	}
}
