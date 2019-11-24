package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eventobject.ExistingUserListener;
import eventobject.LoanPaymentListener;
import eventobject.LoanSelectionListener;
import model.Customer;
import model.Database;
import model.Loan;

public class LoanPane extends JPanel implements ActionListener {
	private JComboBox<Item> loanSelection;
	private LoanSelectionListener selectionListener;
	private LoanPaymentListener paymentListener;
	private JTextField amount;
	private JButton payBtn;
	
	
	public LoanPane(Database database) {
		// add filter current customer . for now just display all loans.
		
		setLayout(new BorderLayout());
		List<Loan> loans = database.getLoans();
		loanSelection = new JComboBox<Item>();
		
		DefaultComboBoxModel<Item> loanSelectionModel = new DefaultComboBoxModel<Item>();
		
		for (Loan loan : loans) {
			Customer customer = database.getCustomer(loan.getCustomerId());
			loanSelectionModel.addElement(new Item(loan.getPrincipal() + " loan to " + customer.getName(), loan.getLoanId()));
		}
		loanSelection.setModel(loanSelectionModel);
		loanSelection.addActionListener(this);
		loanSelection.setActionCommand("loanSelection");
		
		add(loanSelection, BorderLayout.NORTH);
	}



	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("loanSelection")) {
			JComboBox cb = (JComboBox)e.getSource();
			Item loan = (Item) cb.getSelectedItem();
			int loanId = loan.getValue();
			if (selectionListener != null) {
				selectionListener.loanSelectionOccured(loanId);;
			}
		} else if (e.getActionCommand().equals("loanPayment")) {
			double amount = Double.parseDouble(this.amount.getText());
			if (paymentListener != null) {
				paymentListener.loanPaymentOccured(amount);
			}
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
	
	public void setPaymentListener(LoanPaymentListener paymentListener) {
		this.paymentListener = paymentListener;
	}
}
