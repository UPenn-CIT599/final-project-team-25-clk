package model;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Customer implements Serializable {

	private static final long serialVersionUID = -3634930368104200041L;
	private int userId;
	private String name;
	private double annualIncome;
	private String occupation;
	private String creditGrading;
	private ArrayList<LoanApplication> loanApplications;
	private ArrayList<Loan> loans;
	private int currentPaymentMonth;
	private int payOrDefault;
	private HashMap<Integer, String> monthlyDefaultStatus;

	/**
	 * constructor for customer.
	 * @param userId
	 * @param name
	 * @param occupation
	 * @param annualIncome
	 */
	public Customer(int userId, String name, String occupation, double annualIncome) {
		this.name = name;
		this.occupation = occupation;
		this.annualIncome = annualIncome;
		this.loanApplications = new ArrayList<LoanApplication>();
		this.loans = new ArrayList<Loan>();
		this.userId = userId + 1;
	
	}
	
	// STORAGE FOR LOAN APPLICATION.
	
	/**
	 * adding the loan application into current customer.
	 * @param loanAmount
	 * @param loanDuration
	 * @param reason
	 */
	public void addLoanApplication(String loanAmount, String loanDuration, String reason) {

		Date today = new Date();
		int loanApplicationId = loanApplications.size();
		
		LoanApplication loanApplication = new LoanApplication(loanApplicationId, Double.parseDouble(loanAmount), 
				Integer.parseInt(loanDuration), reason, today);
		
		loanApplications.add(loanApplication);
	}
	
	/**
	 * get the loan application by id.
	 * @param loanApplicationID
	 * @return
	 */
	public LoanApplication getLoanApplication(int loanApplicationID) {
		Iterator i = loanApplications.iterator();
		while (i.hasNext()) {
			LoanApplication loanApplication = (LoanApplication) i.next();
			if (loanApplicationID == loanApplication.getLoanApplicationId()) {
				return loanApplication;
			}
		}
		return null;
	}
	
	/**
	 * get loan applications list.
	 * @return
	 */
	public ArrayList<LoanApplication> getLoanApplications() {
		return loanApplications;
	}
	
	// STORAGE FOR LOAN.
	
	/**
	 * add a new loan to customer.
	 * @param loanInfo
	 */
	public void addLoan(HashMap<String, String> loanInfo) {
		int loanId = loans.size();
		double principal = Double.parseDouble(loanInfo.get("principal"));
		double rate = Double.parseDouble(loanInfo.get("rate"));
		int loanPeriod = Integer.parseInt(loanInfo.get("loanPeriod"));
		double monthlyPayment = Double.parseDouble(loanInfo.get("monthlyPayment"));
		
		
		Loan loan = new Loan(loanId, principal, rate, loanPeriod, monthlyPayment);
		loans.add(loan);
	}
	
	/**
	 * get loan by id.
	 * @param loanId
	 * @return
	 */
	public Loan getLoan(int loanId) {
		Iterator i = loans.iterator();
		while (i.hasNext()) {
			Loan loan = (Loan) i.next();
			if (loanId == loan.getLoanId()) {
				return loan;
			}
		}
		return null;
	}
	
	/**
	 * get a list of loans.
	 * @return
	 */
	public ArrayList<Loan> getLoans() {
		return loans;
	}
	
	/// Miscellaneous
	

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
