package model;
public class LoanFile {

	/***
	 * @author YEONG HUN LUKE LEE
	 */
	private String pubRec, mo_sin_old_rev_tl_op, inq_last_6mths, num_actv_bc_tl, 
	num_actv_rev_tl, open_act_il, revol_bal, total_rev_hi_lim, interestRate, incomeAmount, loanAmount;

	/***
	 * Creation of a constructor and instance variables.
	 */

	public LoanFile(String incomeAmount, String loanAmount,String pubRec, String mo_sin_old_rev_tl_op, String inq_last_6mths, String num_actv_bc_tl,
			String num_actv_rev_tl, String open_act_il, String revol_bal, String total_rev_hi_lim, String interestRate) {
		super();
		this.incomeAmount = incomeAmount;
		this.loanAmount = loanAmount;
		this.pubRec = pubRec;
		this.mo_sin_old_rev_tl_op = mo_sin_old_rev_tl_op;
		this.inq_last_6mths = inq_last_6mths;
		this.num_actv_bc_tl = num_actv_bc_tl;
		this.num_actv_rev_tl = num_actv_rev_tl;
		this.open_act_il = open_act_il;
		this.revol_bal = revol_bal;
		this.total_rev_hi_lim = total_rev_hi_lim;
		this.interestRate = interestRate;

	}


	/***
	 * Overriding toString method to display an arraylist with loan file object.
	 */

	@Override
	public String toString() {
		return "LoanFile [incomeAmount=" + incomeAmount + ", loanAmount=" + loanAmount + ", pubRec=" + pubRec
				+ ", mo_sin_old_rev_tl_op=" + mo_sin_old_rev_tl_op + ", inq_last_6mths=" + inq_last_6mths
				+ ", num_actv_bc_tl=" + num_actv_bc_tl + ", num_actv_rev_tl=" + num_actv_rev_tl + ", open_act_il="
				+ open_act_il + ", revol_bal=" + revol_bal + ", total_rev_hi_lim=" + total_rev_hi_lim
				+ ", interestRate=" + interestRate + "]";
	}

	/***
	 * getter method for public record
	 * @return public record
	 */
	
	public String getPubRec() {
		return pubRec;
	}

	/***
	 * getter method for number of months since
	 * the user's creation of revolving credit 
	 */
	
	public String getMo_sin_old_rev_tl_op() {
		return mo_sin_old_rev_tl_op;
	}

	/***
	 * getter method for the number of inquiries the
	 * user made for the last 6 months
	 */
	public String getInq_last_6mths() {
		return inq_last_6mths;
	}

	/***
	 * getter method for the number of active
	 * bank card accounts
	 */
	public String getNum_actv_bc_tl() {
		return num_actv_bc_tl;
	}

	/***
	 * getter method for the number of active
	 * credit card accounts
	 */
	public String getNum_actv_rev_tl() {
		return num_actv_rev_tl;
	}

	/***
	 * getter method for the number of existing loan
	 * accounts
	 */
	public String getOpen_act_il() {
		return open_act_il;
	}

	/***
	 * getter method for the current credit card usage per month
	 */
	public String getRevol_bal() {
		return revol_bal;
	}

	/***
	 * getter method for the total credit card limit per month
	 */
	public String getTotal_rev_hi_lim() {
		return total_rev_hi_lim;
	}


	/***
	 * getter method for the datafile's interest rates
	 */
	public String getInterestRate() {
		return interestRate;
	}

	/***
	 * getter method for the annual income of the datafile 
	 */
	public String getIncomeAmount() {
		return incomeAmount;
	}

	/***
	 * getter method for the loan amount that the user applied
	 * or the datafile's existing borrowing amount history.
	 */
	public String getLoanAmount() {
		return loanAmount;
	}


}


