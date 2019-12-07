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
	public Customer(int userId, String name, String occupation) {
		this.name = name;
		this.occupation = occupation;
		this.loanApplications = new ArrayList<LoanApplication>();
		this.loans = new ArrayList<Loan>();
		this.userId = userId + 1;
	
	}
	
	// STORAGE FOR LOAN APPLICATION.
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * adding the loan application into current customer.
	 * @param loanAmount
	 * @param loanDuration
	 * @param reason
	 */
	public LoanApplication addLoanApplication(double loanAmount, int loanDuration, int pubRec ,double revol_bal ,
			double total_rev_hi_lim , int mo_sin_old_rev_tl_op , int inq_last_6mths ,int num_actv_bc_tl,int num_actv_rev_tl, int open_act_il) {

		Date today = new Date();
		int loanApplicationId = loanApplications.size();
		
		LoanApplication loanApplication = new LoanApplication(loanApplicationId, loanAmount, 
				loanDuration, pubRec, revol_bal, total_rev_hi_lim, mo_sin_old_rev_tl_op, 
				inq_last_6mths, num_actv_bc_tl, num_actv_rev_tl, open_act_il,  today);
		
		loanApplications.add(loanApplication);
		
		return loanApplication;
	}
	
	/**
	 * get a specific loan application, by index.
	 * @param index
	 * @return
	 */
	public LoanApplication getLoanApplicationById(int index) {
		return loanApplications.get(index);
	}
	
	/**
	 * get the last loan application.
	 * @return
	 */
	public LoanApplication getLastLoanApplication() {
		return loanApplications.get(loanApplications.size() - 1);
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
	
	
	/**
	 * add a new loan to customer.
	 * @param loanInfo
	 */
	public Loan addLoan(double amount, double interestRates, int loanPeriod, String creditGrade ) {
		int loanId = loans.size();
		
		Loan loan = new Loan(loanId, amount, interestRates, loanPeriod, creditGrade);
		loans.add(loan);
		
		return loan;
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
}
