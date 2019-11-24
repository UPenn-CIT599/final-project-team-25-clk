package model;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LoanApplication implements Serializable {

	private static final long serialVersionUID = 6248986646073694934L;
	private int loanApplicationId;
	private int customerId;
	private double loanAmount;
	private int loanDuration;
	private String reasonForApplying;
	private Date applicationDate;
	private double creditCardSpendingThisMonth;
	private Date lastDefaultDate;
	private double allocatedCreditLimit;
	private Date firstCreditCardDate;
	private int inquireBorrowingEligibilityTimes;
	
	/**
	 * constructor.
	 */
	public LoanApplication(int loanApplicationId, double loanAmount, int loanDuration, String reasonForApplying, Date today, int customerId) {
		this.loanApplicationId = loanApplicationId + 1;
		this.loanAmount = loanAmount;
		this.loanDuration = loanDuration;
		this.reasonForApplying = reasonForApplying;
		this.applicationDate = today;
		this.customerId = customerId;
	}

	

	/**
	 * get loan application id
	 * 
	 * @return
	 */
	public int getLoanApplicationId() {
		return loanApplicationId;
	}

	/**
	 * get customer id associated with this loan aplication.
	 * 
	 * @return
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * get loan amount
	 * 
	 * @return
	 */
	public double getLoanAmount() {
		return loanAmount;
	}

	/**
	 * get loan duration
	 * 
	 * @return
	 */
	public int getLoanDuration() {
		return loanDuration;
	}

	/**
	 * get reason for applying.
	 * 
	 * @return
	 */
	public String getReasonForApplying() {
		return reasonForApplying;
	}

	/**
	 * get application date.
	 * 
	 * @return
	 */
	public Date getApplicationDate() {
		return applicationDate;
	}

	/**
	 * get credit card spending of this month. Used in credit scoring.
	 * 
	 * @return
	 */
	public double getCreditCardSpendingThisMonth() {
		return creditCardSpendingThisMonth;
	}

	/**
	 * get last default date of customer.
	 * 
	 * @return
	 */
	public Date getLastDefaultDate() {
		return lastDefaultDate;
	}

	/**
	 * get allocated credit limit of the credit card.
	 * 
	 * @return
	 */
	public double getAllocatedCreditLimit() {
		return allocatedCreditLimit;
	}

	/**
	 * get first date of getting credit card.
	 * 
	 * @return
	 */
	public Date getFirstCreditCardDate() {
		return firstCreditCardDate;
	}

	/**
	 * get how many times has customer enquire about eligibility to obtain a loan.
	 * 
	 * @return
	 */
	public int getInquireBorrowingEligibilityTimes() {
		return inquireBorrowingEligibilityTimes;
	}

}
