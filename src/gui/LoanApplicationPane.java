package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eventobject.LoanApplicationListener;
import eventobject.LoanAnalysisListener;
import model.Customer;
import model.Database;
import model.Loan;
import model.LoanApplication;
import model.Algorithm;
import model.ApplicationResult;

public class LoanApplicationPane extends JPanel {
	private JLabel loanAmountLabel;
	private JLabel loanDurationLabel;
	private JLabel paymentHistoryLabel;
	private JLabel creditCardLimitLabel;
	private JLabel creditCardUsageLabel;
	private JLabel monthsSinceFirstCreditCardLabel;
	private JLabel timesInquirityToBankLabel;
	private JLabel numOfCreditCardAccountLabel;
	private JLabel numOfCheckAccountLabel;
	private JLabel numOfActiveBorrowingAccountLabel;
	

	private JTextField loanAmountField;
	private JComboBox<Integer> loanDurationField;
	private JComboBox<Integer> paymentHistoryField;
	private JTextField creditCardLimitField;
	private JTextField creditCardUsageField;
	private JComboBox<Integer> monthsSinceFirstCreditCardField;
	private JComboBox<Integer> timesInquirityToBankField;
	private JComboBox<Integer> numOfCreditCardAccountField;
	private JComboBox<Integer> numOfCheckAccountField;
	private JComboBox<Integer> numOfActiveBorrowingAccountField;
	private JButton submit;
	
	private LoanApplicationListener applicationListener;

	

	public LoanApplicationPane(Database database) {
		Container loanApplicationPanel = this;
		loanAmountLabel = new JLabel("Loan Application Amount: (USD)");
		loanDurationLabel = new JLabel("Loan Duration: (In Months)"); // 12,24,36,48,60, 72
		paymentHistoryLabel = new JLabel("How many months ago since you last failed to pay your financial obligation? (In Months)"); // 1-120
		creditCardUsageLabel = new JLabel("How much do you use your credit card per month on average? (USD)");
		creditCardLimitLabel = new JLabel("What is your credit card limit? (USD)");
		monthsSinceFirstCreditCardLabel = new JLabel("How many months ago since your first credit card was approved? (In Months)"); // 1-120
		timesInquirityToBankLabel = new JLabel("How many times you inquiry the bank about loan application in the last 6 months? (# of times)"); // 0-20
		numOfCreditCardAccountLabel = new JLabel("How many credit card do you have? (# of cards)"); // 0-10
		numOfCheckAccountLabel = new JLabel("How many checking account you have? (# of accounts)"); // 0-10
		numOfActiveBorrowingAccountLabel = new JLabel("How many active borrowing account do you have? (# of accounts)"); // 0-10
		
		Integer[] loadDurationChoice = {12,24,36, 48, 60, 72,84,96,108,120};
		Integer[] months1To120 = new Integer[120];
		for (int i = 1; i <= 120; i++) {
			months1To120[i - 1] = i;
		}
		
		Integer[] times0To25 = new Integer[26];
		for (int i = 0; i <= 25; i++) {
			times0To25[i] = i;
		}
		
		loanAmountField = new JTextField(10);
		loanDurationField = new JComboBox<Integer>(loadDurationChoice);
		paymentHistoryField =new JComboBox<Integer>(months1To120);
		creditCardUsageField = new JTextField(10);
		creditCardLimitField = new JTextField(10);
		monthsSinceFirstCreditCardField = new JComboBox<Integer>(months1To120);
		timesInquirityToBankField = new JComboBox<Integer>(times0To25);
		numOfCreditCardAccountField = new JComboBox<Integer>(times0To25);
		numOfCheckAccountField = new JComboBox<Integer>(times0To25);
		numOfActiveBorrowingAccountField = new JComboBox<Integer>(times0To25);

		submit = new JButton("Apply");

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double loanAmount = Double.parseDouble(loanAmountField.getText());
				int loanDuration = (int) loanDurationField.getSelectedItem();
				int pubRec = (int) paymentHistoryField.getSelectedItem();
				double revol_bal = Double.parseDouble(creditCardUsageField.getText());
				double total_rev_hi_lim = Double.parseDouble(creditCardLimitField.getText());
				int mo_sin_old_rev_tl_op =  (int) monthsSinceFirstCreditCardField.getSelectedItem();
				int inq_last_6mths = (int) timesInquirityToBankField.getSelectedItem();
				int num_actv_bc_tl = (int) numOfCreditCardAccountField.getSelectedItem();
				int num_actv_rev_tl = (int) numOfCheckAccountField.getSelectedItem();
				int open_act_il = (int) numOfActiveBorrowingAccountField.getSelectedItem();
				
				Customer currentCustomer = database.getCurrentCustomer();
				LoanApplication currentLoanApplication = currentCustomer.addLoanApplication(loanAmount, loanDuration, pubRec, revol_bal ,
						total_rev_hi_lim ,mo_sin_old_rev_tl_op ,inq_last_6mths , num_actv_bc_tl, num_actv_rev_tl, open_act_il);
				database.setCurrentLoanApplication(currentLoanApplication);
				database.updateCustomer(currentCustomer);
				
				
				/// LUKE'S INTEGRATION ////
				/*
				ApplicationResult applicationResult = new ApplicationResult();
				applicationResult.userPennCLKScore(currentLoanApplication);
				
				
				String creditGrade = applicationResult.creditGrade();
				Boolean isLoanApproved = applicationResult.isCustomerApprovedforLoan();
				double interestRates = applicationResult.calculateInterestRates();
				double amount = currentLoanApplication.getLoanAmount();
				int loanPeriod = currentLoanApplication.getLoanDuration()
				*/
				/////// END ///////
				Boolean isLoanApproved = true;
				String creditGrade = "A";
				double interestRates = 12;
				double amount = currentLoanApplication.getLoanAmount();
				int loanPeriod = currentLoanApplication.getLoanDuration();
				
				if (isLoanApproved) {
					// mock data.
					String message = "The loan of " + amount + " has been approved\n";
					message += "Your credit grading is " + creditGrade + "\n";
					message += "Your interest rates is " + interestRates + "\n";

					
					JOptionPane.showMessageDialog(loanApplicationPanel, message );
					
					// convert the loan application into a new loan.
					
					Loan currentLoan = currentCustomer.addLoan(amount, interestRates, loanPeriod, creditGrade );
					database.setCurrentLoan(currentLoan);
					database.updateCustomer(currentCustomer);
					
					if (applicationListener != null) {
						applicationListener.loanApplicationOccured();
					}
				} else {
					String message = "The loan of " + amount + " has been rejected\n";
					JOptionPane.showMessageDialog(loanApplicationPanel, message );
				}

			}

		});
		
		layoutComponent();
	}

	public void layoutComponent() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		//////////// next row /////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(loanAmountLabel, gc);

		// coordinate (1,0)
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(loanAmountField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(loanDurationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(loanDurationField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(paymentHistoryLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(paymentHistoryField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(creditCardUsageLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(creditCardUsageField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(creditCardLimitLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(creditCardLimitField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(monthsSinceFirstCreditCardLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(monthsSinceFirstCreditCardField, gc);
		
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(timesInquirityToBankLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(timesInquirityToBankField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(numOfCreditCardAccountLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(numOfCreditCardAccountField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(numOfCheckAccountLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(numOfCheckAccountField, gc);
		
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(numOfActiveBorrowingAccountLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(numOfActiveBorrowingAccountField, gc);

		/////////// next row //////////////////
		
		gc.weightx = 1;
		gc.weighty = 2.0; 
		
		gc.gridy++;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(submit, gc);		
		
		
	}
	public void setFormListener(LoanApplicationListener applicationListener) {
		this.applicationListener = applicationListener;
	}
}
