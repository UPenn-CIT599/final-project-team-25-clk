import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer {
	private int user_id;
	private String name;
	private double annualIncome;
	private String jobTitle;
	private String creditGrading;
	private LoanApplication loanApplication;
	private ArrayList<Loan> loans;
	
	/**
	 * initializing an existing customer
	 * @param customerData
	 */
	public Customer(HashMap<String, String> customerData) {
		// temp, to loan list of loans from storage.
		this.loans = new ArrayList<Loan>();
	}
	
	/**
	 * initializing a new customer.
	 */
	public Customer() {
		this.user_id = 1;
		this.loans = new ArrayList<Loan>();
	}
	
	/**
	 * get user id
	 * @return
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * get name of customer
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * get annual income of customer
	 * @return
	 */
	public double getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * get job title of customer.
	 * @return
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * get credit grading of customer
	 * @return
	 */
	public String getCreditGrading() {
		return creditGrading;
	}

	/**
	 * get loan application associated with this customer.
	 * @return
	 */
	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	/**
	 * get a list of loan associated with this customer.
	 * @return
	 */
	public ArrayList<Loan> getLoans() {
		return loans;
	}

	/**
	 * apply for new loan
	 */
	public void applyLoan(String loanAmount, String loanDuration, String reasonForApplying, String creditCardSpendingThisMonth, String lastDefaultDate,
			String allocatedCreditLimit, String firstCreditCardDate, String inquireBorrowingEligibilityTimes) {
		
		Storage storage = new Storage();
		
		HashMap<String, String> loanApplicationDetail = new HashMap();
		// for now will assume customer is applying for a 100,000 loan for a total of 
		// 36 months and other loan application details because UI is not implemented yet.
		
		loanApplicationDetail.put("loanAmount", loanAmount);
		loanApplicationDetail.put("loanDuration", loanDuration);
		loanApplicationDetail.put("reasonForApplying", reasonForApplying);
		loanApplicationDetail.put("creditCardSpendingThisMonth", creditCardSpendingThisMonth);
		loanApplicationDetail.put("lastDefaultDate", lastDefaultDate);
		loanApplicationDetail.put("allocatedCreditLimit", allocatedCreditLimit);
		loanApplicationDetail.put("firstCreditCardDate", firstCreditCardDate);
		loanApplicationDetail.put("inquireBorrowingEligibilityTimes", inquireBorrowingEligibilityTimes);
		
		if (loanAmount.isEmpty() || loanDuration.isEmpty()) {
			this.loanApplication = null;
			return;
		}
		this.loanApplication = new LoanApplication(loanApplicationDetail, this.user_id);
		storage.storeLoanApplication(loanApplication);
	}
	
	/**
	 * make monthly instalment for loan.
	 */
	public void makePayment() {
		
	}
	
	/**
	 * manage existing loan.
	 */
	public void manageLoan() {
		
	}

	
}
