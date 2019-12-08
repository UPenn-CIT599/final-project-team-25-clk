package model;
public class LoanFile {

	private String pubRec, mo_sin_old_rev_tl_op, inq_last_6mths, num_actv_bc_tl, 
	num_actv_rev_tl, open_act_il, revol_bal, total_rev_hi_lim, interestRate, incomeAmount, loanAmount;

	/*
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

	/*
	 * Overriding toString method to display an arraylist with loan file object.
	 */

	public String getIncomeAmount() {
		return incomeAmount;
	}

	public String getLoanAmount() {
		return loanAmount;
	}


	public String getPubRec() {
		return pubRec;
	}


	

	@Override
	public String toString() {
		return "LoanFile [incomeAmount=" + incomeAmount + ", loanAmount=" + loanAmount + ", pubRec=" + pubRec
				+ ", mo_sin_old_rev_tl_op=" + mo_sin_old_rev_tl_op + ", inq_last_6mths=" + inq_last_6mths
				+ ", num_actv_bc_tl=" + num_actv_bc_tl + ", num_actv_rev_tl=" + num_actv_rev_tl + ", open_act_il="
				+ open_act_il + ", revol_bal=" + revol_bal + ", total_rev_hi_lim=" + total_rev_hi_lim
				+ ", interestRate=" + interestRate + "]";
	}

	public void setPubRec(String pubRec) {
		this.pubRec = pubRec;
	}

	public String getMo_sin_old_rev_tl_op() {
		return mo_sin_old_rev_tl_op;
	}

	public void setMo_sin_old_rev_tl_op(String mo_sin_old_rev_tl_op) {
		this.mo_sin_old_rev_tl_op = mo_sin_old_rev_tl_op;
	}

	public String getInq_last_6mths() {
		return inq_last_6mths;
	}

	public void setInq_last_6mths(String inq_last_6mths) {
		this.inq_last_6mths = inq_last_6mths;
	}

	public String getNum_actv_bc_tl() {
		return num_actv_bc_tl;
	}

	public void setNum_actv_bc_tl(String num_actv_bc_tl) {
		this.num_actv_bc_tl = num_actv_bc_tl;
	}

	public String getNum_actv_rev_tl() {
		return num_actv_rev_tl;
	}

	public void setNum_actv_rev_tl(String num_actv_rev_tl) {
		this.num_actv_rev_tl = num_actv_rev_tl;
	}

	public String getOpen_act_il() {
		return open_act_il;
	}

	public void setOpen_act_il(String open_act_il) {
		this.open_act_il = open_act_il;
	}

	public String getRevol_bal() {
		return revol_bal;
	}

	public void setRevol_bal(String revol_bal) {
		this.revol_bal = revol_bal;
	}

	public String getTotal_rev_hi_lim() {
		return total_rev_hi_lim;
	}

	public void setTotal_rev_hi_lim(String total_rev_hi_lim) {
		this.total_rev_hi_lim = total_rev_hi_lim;
	}
	
	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
}

