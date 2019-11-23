package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Storage;

public class Customer implements Serializable {

	private static final long serialVersionUID = -3634930368104200041L;
	private int userId;
	private String name;
	private double annualIncome;
	private String occupation;
	private String creditGrading;
	//private LoanApplication loanApplication;
	//private ArrayList<Loan> loans;
	private int currentPaymentMonth;
	private int payOrDefault;
	private HashMap<Integer, String> monthlyDefaultStatus;

	
	public Customer(int userId, String name, String occupation, double annualIncome) {
		this.name = name;
		this.occupation = occupation;
		this.annualIncome = annualIncome;
		this.userId = userId + 1;
	
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public double getAnnualIncome() {
		return annualIncome;
	}


	public String getOccupation() {
		return occupation;
	}


	public String getCreditGrading() {
		return creditGrading;
	}


	public int getCurrentPaymentMonth() {
		return currentPaymentMonth;
	}


	public int getPayOrDefault() {
		return payOrDefault;
	}


	public HashMap<Integer, String> getMonthlyDefaultStatus() {
		return monthlyDefaultStatus;
	}


	public String getName() {
		return name;
	}


	public int getUserId() {
		return userId;
	}
	
	
//
//	/**
//	 * get user id
//	 * 
//	 * @return
//	 */
//	public int getUser_id() {
//		return user_id;
//	}
//
//	/**
//	 * get name of customer
//	 * 
//	 * @return
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * get annual income of customer
//	 * 
//	 * @return
//	 */
//	public double getAnnualIncome() {
//		return annualIncome;
//	}
//
//	/**
//	 * get job title of customer.
//	 * 
//	 * @return
//	 */
//	public String getOccupation() {
//		return occupation;
//	}
//
//	/**
//	 * get credit grading of customer
//	 * 
//	 * @return
//	 */
//	public String getCreditGrading() {
//		return creditGrading;
//	}
//
//	/**
//	 * get loan application associated with this customer.
//	 * 
//	 * @return
//	 */
//	public LoanApplication getLoanApplication() {
//		return loanApplication;
//	}
//
//	/**
//	 * get a list of loan associated with this customer.
//	 * 
//	 * @return
//	 */
//	public ArrayList<Loan> getLoans() {
//		return loans;
//	}
//
//	/**
//	 * apply for new loan
//	 */
//	public void applyLoan(String loanAmount, String loanDuration, String reasonForApplying,
//			String creditCardSpendingThisMonth, String lastDefaultDate, String allocatedCreditLimit,
//			String firstCreditCardDate, String inquireBorrowingEligibilityTimes) {
//
//		Storage storage = new Storage();
//
//		HashMap<String, String> loanApplicationDetail = new HashMap();
//		// for now will assume customer is applying for a 100,000 loan for a total of
//		// 36 months and other loan application details because UI is not implemented
//		// yet.
//
//		loanApplicationDetail.put("loanAmount", loanAmount);
//		loanApplicationDetail.put("loanDuration", loanDuration);
//		loanApplicationDetail.put("reasonForApplying", reasonForApplying);
//		loanApplicationDetail.put("creditCardSpendingThisMonth", creditCardSpendingThisMonth);
//		loanApplicationDetail.put("lastDefaultDate", lastDefaultDate);
//		loanApplicationDetail.put("allocatedCreditLimit", allocatedCreditLimit);
//		loanApplicationDetail.put("firstCreditCardDate", firstCreditCardDate);
//		loanApplicationDetail.put("inquireBorrowingEligibilityTimes", inquireBorrowingEligibilityTimes);
//
//		if (loanAmount.isEmpty() || loanDuration.isEmpty()) {
//			this.loanApplication = null;
//			return;
//		}
//		this.loanApplication = new LoanApplication(loanApplicationDetail, this.user_id);
//		storage.storeLoanApplication(loanApplication);
//	}
//
//	/**
//	 * make monthly instalment for loan.
//	 */
//	public void makePayment() {
//
//	}
//
//	// user chooses a month to pay. Note that if the user jumps to month 12, then
//	// assume that all the past payments for months 1-11 were paid back in full.
//	public int chooseMonth() {
//
//		return currentPaymentMonth;
//	}
//
//	// for this currentPaymentMonth, user must choose whether to pay back in
//	// full(input integer 1) or go default(input integer 0)
//	public int choosePayOrDefault(int currentPaymentMonth) {
//
//		return payOrDefault;
//	}
//
//	/**
//	 * manage existing loan.
//	 */
//	public void manageLoan() {
//
//	}
//	
//	/**
//	 * customer decides to pay instalment or not.
//	 * @return
//	 */
//	public boolean decidePayOrNot() {
//		return true;
//	}
//	
//	/**
//	 * customer decide how much to pay
//	 * @return
//	 */
//	public double howMuchToPay() {
//		return 1;
//	}

}
