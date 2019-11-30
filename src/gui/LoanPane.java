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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eventobject.LoanSelectionListener;
import model.Customer;
import model.Database;
import model.Loan;

public class LoanPane extends JPanel implements ActionListener {
	private JComboBox<Item> loanSelection;
	private LoanSelectionListener selectionListener;
	private JTextField amount;
	private JButton payBtn;
	private Customer currentCustomer;
	private Database database;
	
	
	public LoanPane(Database database) {		
		setLayout(new BorderLayout());
		this.database = database;
		
		currentCustomer = database.getCurrentCustomer();
		ArrayList<Loan> loans = currentCustomer.getLoans();
		loanSelection = new JComboBox<Item>();
		
		DefaultComboBoxModel<Item> loanSelectionModel = new DefaultComboBoxModel<Item>();
		
		for (Loan loan : loans) {
			loanSelectionModel.addElement(new Item(loan.getPrincipal() + " loan to " + currentCustomer.getName(), loan.getLoanId()));
		}
		loanSelection.setModel(loanSelectionModel);
		loanSelection.addActionListener(this);
		loanSelection.setActionCommand("loanSelection");
		
		add(loanSelection, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("loanSelection")) {
			JComboBox cb = (JComboBox)e.getSource();
			Item _loan = (Item) cb.getSelectedItem();
			int loanId = _loan.getValue();
			
			Loan currentLoan = currentCustomer.getLoan(loanId);
			database.setCurrentLoan(currentLoan);
			repaintWithCurrentLoan(currentLoan);
			
			
			// mock payment.
			for (int i = 0; i < 40; i++) {
				HashMap<String, String> paymentInfo = new HashMap();
				paymentInfo.put("monthlyPaymentForPrincipal", "1000");
				paymentInfo.put("monthlyPaymentForInterest", "100");
				paymentInfo.put("monthlyPaymentTotal", "1100");
				paymentInfo.put("payOrDefault", "true");
				paymentInfo.put("paymentMadeForEachMonth", "1100");
				currentLoan.addPayment(paymentInfo);
			}
			
			database.updateCustomer(currentCustomer);
			
			if (selectionListener != null) {
				selectionListener.loanSelectionOccured();
			}
		} else if (e.getActionCommand().equals("loanPayment")) {
			double amount = Double.parseDouble(this.amount.getText());
			database.getCurrentLoan().updatePaymentForTheMonth(amount);
			database.updateCustomer(currentCustomer);
		}
		
		
	}
	
	public void repaintWithCurrentLoan(Loan currentLoan) {

		JPanel loanInfo = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		loanInfo.setLayout(gbl);

		GridBagConstraints gc = new GridBagConstraints();

		//////////// next row /////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("Loan ID :     "), gc);

		// coordinate (1,0)
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JTextField(Integer.toString(currentLoan.getLoanId()), 20), gc);
		
		///////////// next row /////////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("Principal :     "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JTextField(Double.toString(currentLoan.getPrincipal()), 20), gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("Interest rates:      "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JTextField(Double.toString(currentLoan.getRate()), 20), gc);
		
		
		///////////// next row /////////////////
		gc.gridy++;
	
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("Monthly payment :      "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JTextField(Double.toString(currentLoan.getMonthlyPayment()), 20), gc);
		
		///////////// next row /////////////////
		gc.gridy++;
	
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		
		loanInfo.add(new JLabel("Payment :      "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		amount = new JTextField(20);
		loanInfo.add(amount, gc);
		
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		payBtn = new JButton("Pay");
		
		payBtn.addActionListener(this);
		payBtn.setActionCommand("loanPayment");
		loanInfo.add(payBtn, gc);
		
		///////////// next row /////////////////
		
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel(""), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JLabel(""), gc);
		
		add(loanInfo, BorderLayout.CENTER);
		
		
	}
	
	public void setFormListener(LoanSelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}
}
