package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import eventobject.ExistingUserListener;
import model.Customer;
import model.Database;
import model.Loan;

public class ExistingUserPane extends JPanel implements ActionListener {
	private JComboBox<Item> userSelection;
	private Database database;
	private ExistingUserListener existingUserListener;
	
	public ExistingUserPane(Database database) {
		setLayout(new BorderLayout());
		
		this.database = database;
		List<Customer> customers = database.getCustomers();
		userSelection = new JComboBox<Item>();
		DefaultComboBoxModel<Item> userSelectionModel = new DefaultComboBoxModel<Item>();
		
		for (Customer customer : customers) {
			userSelectionModel.addElement(new Item(customer.getName(), customer.getUserId()));
		}
		userSelection.setModel(userSelectionModel);
		userSelection.addActionListener(this);
		
		add(userSelection, BorderLayout.NORTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		JComboBox<Item> cb = (JComboBox<Item>)e.getSource();
		Item user = (Item) cb.getSelectedItem();
		int userId = user.getValue();
		
		Customer currentCustomer  = database.getCustomer(userId);
		database.setCurrentCustomer(currentCustomer);

		// mock approved loan. For now , it is created when we select a random user. It creates a 150k loan for them.
		HashMap<String, String> loanInfo = new HashMap();
		loanInfo.put("principal", "150000");
		loanInfo.put("rate", "12");
		loanInfo.put("loanPeriod", "24");
		loanInfo.put("monthlyPayment", "2500");
		
		currentCustomer.addLoan(loanInfo);
		database.updateCustomer(currentCustomer);
		this.repaintWithCurrentCustomer(currentCustomer);
		
		if (existingUserListener != null) {
			existingUserListener.existingUserSelected();
		}
		
		
	}
	
	public void repaintWithCurrentCustomer(Customer currentCustomer) {
		JPanel userInfo = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		userInfo.setLayout(gbl);

		GridBagConstraints gc = new GridBagConstraints();

		//////////// next row /////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		userInfo.add(new JLabel("name:     "), gc);

		// coordinate (1,0)
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		userInfo.add(new JTextField(currentCustomer.getName(), 20), gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		userInfo.add(new JLabel("Occupation:     "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		userInfo.add(new JTextField(currentCustomer.getOccupation(), 20), gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		userInfo.add(new JLabel("Annual Income:      "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		userInfo.add(new JTextField(Double.toString(currentCustomer.getAnnualIncome()), 20), gc);
		
		///////////// next row /////////////////
		
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.anchor = GridBagConstraints.LINE_END;
		userInfo.add(new JLabel(""), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		userInfo.add(new JLabel(""), gc);
		
		add(userInfo, BorderLayout.CENTER);
		
		
	}
	
	public void setFormListener(ExistingUserListener existingUserListener) {
		this.existingUserListener = existingUserListener;
	}
}








