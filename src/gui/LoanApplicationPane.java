package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eventobject.LoanApplicationListener;
import eventobject.IntegerVerifier;
import model.Customer;
import model.Database;
import model.Loan;
import model.LoanApplication;
import model.LoanFile;
import model.Algorithm;
import model.ApplicationResult;

/**
 * this is the panel for applying for loan.
 *
 */
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
	private JLabel jobStatusLabel;
	private JLabel incomeLabel;
	private JLabel lengthOfEmploymentLabel;
	

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
	private JComboBox<String> jobStatusField;
	private JComboBox<Integer> lengthOfEmploymentField;
	private JTextField incomeField;
	private JButton submit;
	
	private LoanApplicationListener applicationListener;

	/**
	 * constructor for loan application panel.
	 * @param database
	 */
	public LoanApplicationPane(Database database) {
		Container loanApplicationPanel = this;
		loanAmountLabel = new JLabel("Loan Amount: (USD)");
		loanDurationLabel = new JLabel("Loan Duration: (In Months)"); // 12,24,36,48,60, 72
		incomeLabel = new JLabel("What is your annual income? (USD)"); // 0-10
		paymentHistoryLabel = new JLabel("How many months ago since you last failed to pay your financial obligation? (In Months)"); // 1-120
		creditCardUsageLabel = new JLabel("How much do you use your credit card per month on average? (USD)");
		creditCardLimitLabel = new JLabel("What is your monthly credit card limit? (USD)");
		monthsSinceFirstCreditCardLabel = new JLabel("How many months ago since your first credit card was approved? (In Months)"); // 1-120
		timesInquirityToBankLabel = new JLabel("How many times you inquiry the bank about loan application in the last 6 months? (# of times)"); // 0-20
		numOfCreditCardAccountLabel = new JLabel("How many credit card do you have? (# of cards)"); // 0-10
		numOfCheckAccountLabel = new JLabel("How many checking account you have? (# of accounts)"); // 0-10
		numOfActiveBorrowingAccountLabel = new JLabel("How many active borrowing account do you have? (# of accounts)"); // 0-10
		jobStatusLabel = new JLabel("What is your job status?"); // 0-10
		lengthOfEmploymentLabel = new JLabel("What is your length of employment? (in Years)"); // 0-10
		
		
		Integer[] loadDurationChoice = {12,24,36, 48, 60, 72,84,96,108,120};
		Integer[] months1To120 = new Integer[121];
		for (int i = 0; i <= 120; i++) {
			months1To120[i] = i;
		}
		
		Integer[] times0To25 = new Integer[26];
		for (int i = 0; i <= 25; i++) {
			times0To25[i] = i;
		}
		
		String[] jobStatus = {"Full Time", "Part Time", "Unemployed"};
		
		Integer[] zeroTo100 = new Integer[101];
		for (int i = 0; i <= 100; i++) {
			zeroTo100[i] = i;
		}
		
		
		loanAmountField = new JTextField(10);
		loanAmountField.setInputVerifier(new IntegerVerifier());
		loanDurationField = new JComboBox<Integer>(loadDurationChoice);
		incomeField = new JTextField(10);
		incomeField.setInputVerifier(new IntegerVerifier());
		paymentHistoryField =new JComboBox<Integer>(months1To120);
		creditCardUsageField = new JTextField(10);
		creditCardUsageField.setInputVerifier(new IntegerVerifier());
		creditCardLimitField = new JTextField(10);
		creditCardLimitField.setInputVerifier(new IntegerVerifier());
		monthsSinceFirstCreditCardField = new JComboBox<Integer>(months1To120);
		timesInquirityToBankField = new JComboBox<Integer>(times0To25);
		numOfCreditCardAccountField = new JComboBox<Integer>(times0To25);
		numOfCheckAccountField = new JComboBox<Integer>(times0To25);
		numOfActiveBorrowingAccountField = new JComboBox<Integer>(times0To25);
		jobStatusField = new JComboBox<String>(jobStatus);
		
		lengthOfEmploymentField = new JComboBox<Integer>(zeroTo100);
		
		
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
				String jobStatus = (String) jobStatusField.getSelectedItem();
				double income = Double.parseDouble(incomeField.getText());
				int lengthOfEmployment = (int) lengthOfEmploymentField.getSelectedItem();
				
				Customer currentCustomer = database.getCurrentCustomer();
				LoanApplication currentLoanApplication = currentCustomer.addLoanApplication(loanAmount, loanDuration, pubRec, revol_bal ,
						total_rev_hi_lim ,mo_sin_old_rev_tl_op ,inq_last_6mths , num_actv_bc_tl, num_actv_rev_tl, open_act_il,
						jobStatus, income, lengthOfEmployment);
				
				database.setCurrentLoanApplication(currentLoanApplication);
				database.updateCustomer(currentCustomer);
				
				ApplicationResult applicationResult = new ApplicationResult();
				ArrayList<LoanFile> loan = applicationResult.fileReader("loan_original.csv");
				
				DecimalFormat df = new DecimalFormat("#.00");
				double pennCLKScore = ApplicationResult.userPennCLKScore(currentLoanApplication);
				String creditGrade = ApplicationResult.creditGradeforUser(pennCLKScore);
				boolean isLoanApproved = ApplicationResult.isCustomerApprovedforLoan(pennCLKScore);
				double interestRates = ApplicationResult.calculateInterestRates(loan, currentLoanApplication);
				double loanPrincipal = applicationResult.approvedLoanAmount(currentLoanApplication, applicationResult);
				int loanPeriod = ApplicationResult.approvedLoanperiod(currentLoanApplication);
				
				// rounded to two decimals.
				String loanPrincipalStr = df.format(loanPrincipal);
				String interestRatesStr = df.format(interestRates);
				
				loanPrincipal = Double.parseDouble(loanPrincipalStr);
				interestRates = Double.parseDouble(interestRatesStr);

				
				if (isLoanApproved) {
					String message = "The loan application has been approved with details below:\n";
					message += "---------------APPLICATION SUMMARY---------------\n";
					message += "Your requested loan amount is " + loanAmount + "\n";
					message += "Your credit grading is " + creditGrade + "\n";
					message += "Your interest rates is " + interestRatesStr + "\n";
					message += "Your loan period is " + loanPeriod + " months \n";
					message += "---------------EXTRA INFORMATION---------------\n";
					message += "The maximum loan amount you can request is " + loanPrincipalStr + "\n";
					

					currentLoanApplication.setApproved(true);
					JOptionPane.showMessageDialog(loanApplicationPanel, message );
					
					Loan currentLoan = currentCustomer.addLoan(loanAmount , interestRates, loanPeriod, creditGrade );
					database.setCurrentLoan(currentLoan);
					database.updateCustomer(currentCustomer);
					
					if (applicationListener != null) {
						applicationListener.loanApplicationOccured();
					}
				} else {
					String message = "The loan application has been rejected with details below:\n";
					message += "------------DETAILS -------------\n";
					message += "Your requested loan amount is " + loanAmount + "\n";
					message += "Your credit grading is " + creditGrade + "\n";
					message += "Your interest rates is " + interestRates + "\n";
					message += "Your loan period is " + loanPeriod + " months\n";
					message += "------------END-------------------\n";
					JOptionPane.showMessageDialog(loanApplicationPanel, message );
				}
			}
		});
		
		layoutComponent();
	}
	
	/**
	 * layout details for this screen.
	 */
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
		add(incomeLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(incomeField, gc);
		
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
		
		///////////// next row /////////////////
		gc.gridy++;
	
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(jobStatusLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(jobStatusField, gc);
	

		
		///////////// next row /////////////////
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lengthOfEmploymentLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lengthOfEmploymentField, gc);
		
		/////////// next row //////////////////
		
		gc.weightx = 1;
		gc.weighty = 2.0; 
		
		gc.gridy++;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(submit, gc);		
		
		
	}
	
	/**
	 * setting form listener.
	 * @param applicationListener
	 */
	public void setFormListener(LoanApplicationListener applicationListener) {
		this.applicationListener = applicationListener;
	}
}
