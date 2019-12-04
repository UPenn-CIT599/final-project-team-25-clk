package model;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LoanApplication implements Serializable {

	private static final long serialVersionUID = 6248986646073694934L;
	private int loanApplicationId;
	private double loanAmount;
	private int loanDuration;
	private Date applicationDate;
	
	private int pubRec;
	private double revol_bal;
	private double total_rev_hi_lim;
	private int mo_sin_old_rev_tl_op;
	private int inq_last_6mths;
	private int num_actv_bc_tl;
	private int num_actv_rev_tl;
	private int open_act_il;
	
	/**
	 * constructor for loan aplication.
	 */
	public LoanApplication(int loanApplicationId, double loanAmount, int loanDuration, int pubRec, double revol_bal ,
			double total_rev_hi_lim , int mo_sin_old_rev_tl_op ,int inq_last_6mths ,int num_actv_bc_tl, int num_actv_rev_tl,int open_act_il 
			  , Date today) {
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
	}

	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public int getPubRec() {
		return pubRec;
	}



	public double getRevol_bal() {
		return revol_bal;
	}



	public double getTotal_rev_hi_lim() {
		return total_rev_hi_lim;
	}



	public int getMo_sin_old_rev_tl_op() {
		return mo_sin_old_rev_tl_op;
	}



	public int getInq_last_6mths() {
		return inq_last_6mths;
	}



	public int getNum_actv_bc_tl() {
		return num_actv_bc_tl;
	}



	public int getNum_actv_rev_tl() {
		return num_actv_rev_tl;
	}



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
