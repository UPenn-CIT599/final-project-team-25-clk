package model;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Loan application class.
 *
 */
public class LoanApplication implements Serializable {

	private static final long serialVersionUID = 6248986646073694934L;
	private int loanApplicationId;
	private double loanAmount;
	private int loanDuration;
	private Date applicationDate;
	private boolean approved;
	private int pubRec;
	private double revol_bal;
	private double total_rev_hi_lim;
	private int mo_sin_old_rev_tl_op;
	private int inq_last_6mths;
	private int num_actv_bc_tl;
	private int num_actv_rev_tl;
	private int open_act_il;	
	private String jobStatus;
	private double income;
	private int lengthOfEmployment;
	
	/**
	 * constructor for loan application.
	 */
	public LoanApplication(int loanApplicationId, double loanAmount, int loanDuration, int pubRec, double revol_bal ,
			double total_rev_hi_lim , int mo_sin_old_rev_tl_op ,int inq_last_6mths ,int num_actv_bc_tl, int num_actv_rev_tl,int open_act_il 
			  , Date today, String jobStatus, double income, int lengthOfEmployment) {
		this.loanApplicationId = loanApplicationId;
		this.loanAmount = loanAmount;
		this.loanDuration = loanDuration;
		this.pubRec = pubRec;
		this.revol_bal = revol_bal;
		this.total_rev_hi_lim = total_rev_hi_lim;
		this.mo_sin_old_rev_tl_op = mo_sin_old_rev_tl_op;
		this.inq_last_6mths = inq_last_6mths;
		this.num_actv_bc_tl = num_actv_bc_tl;
		this.num_actv_rev_tl = num_actv_rev_tl;
		this.open_act_il = open_act_il;
		this.applicationDate = today;
		this.jobStatus = jobStatus;
		this.income = income;
		this.lengthOfEmployment = lengthOfEmployment;
	}


	public int getLengthOfEmployment() {
		return lengthOfEmployment;
	}

	/**
	 * return the boolean of whether loan is approved or not.
	 * @return
	 */
	public boolean isApproved() {
		return approved;
	}


	/**
	 * setting approval state of loan application.
	 * @param approved
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	/**
	 * get the job status of applicant.
	 * @return
	 */
	public String getJobStatus() {
		return jobStatus;
	}


	/**
	 * get income level of job application.
	 * @return
	 */
	public double getIncome() {
		return income;
	}

	/**
	 * get public record.
	 * @return
	 */
	public int getPubRec() {
		return pubRec;
	}


	/**
	 * get revolving balance.
	 * @return
	 */
	public double getRevol_bal() {
		return revol_bal;
	}


	/**
	 * get total revolving balance limit.
	 * @return
	 */
	public double getTotal_rev_hi_lim() {
		return total_rev_hi_lim;
	}


	/**
	 * get month since old revolving limit breach.
	 * @return
	 */
	public int getMo_sin_old_rev_tl_op() {
		return mo_sin_old_rev_tl_op;
	}


	/**
	 * get inquiry to bank in the last 6 months.
	 * @return
	 */
	public int getInq_last_6mths() {
		return inq_last_6mths;
	}


	/**
	 * get number of active bank account.
	 * @return
	 */
	public int getNum_actv_bc_tl() {
		return num_actv_bc_tl;
	}


	/**
	 * get number of active credit cards.
	 * @return
	 */
	public int getNum_actv_rev_tl() {
		return num_actv_rev_tl;
	}


	/**
	 * get active current account.
	 * @return
	 */
	public int getOpen_act_il() {
		return open_act_il;
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
	 * get application date.
	 * 
	 * @return
	 */
	public Date getApplicationDate() {
		return applicationDate;
	}

	

}
