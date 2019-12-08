package model;
import java.util.ArrayList;

import gui.LoanApplicationPane;


public class Algorithm {

	/***
	 * The maximum pennCLK score the user can acquire is 850
	 * The minimum pennCLK score the user can acquire is 300
	 */
	public static final double HIGHESTCONVERSIONBASE = 850;
	public static final double LOWESTCONVERSIONBASE = 300;


	/***
	 * non-parameter constructor will be used here fore testing.
	 */
	
	public Algorithm () {

	}


	/***
	 * This is the first component of PennCLKscore
	 * Payment History represents the number of months since the most
	 * recent derogatory public record
	 * @param pubRec = public record
	 * @return paymentHistoryScore
	 */

	public static double paymentHistoryScore (int pubRec) {
		double paymentHistoryScore = 0;

		if (pubRec == 0) {
			paymentHistoryScore = 900;
		} else if (pubRec > 0 && pubRec < 6) {
			paymentHistoryScore = 0;
		} else if (pubRec > 5 && pubRec < 12) {
			paymentHistoryScore = 300;
		} else if (pubRec > 11 && pubRec < 24) {
			paymentHistoryScore = 500;
		} else if (pubRec > 23) {
			paymentHistoryScore = 700;
		}

		return paymentHistoryScore;
	}

	/***
	 * This is the second component of PennCLKscore.
	 * This represents the ratio of the user's revolving credit over his allocated
	 * maximum revolving limit.
	 * @param revol_bal: user's current maximum revolving credit
	 * @param total_rev_hi_lim: user's allocated revolving credit limit
	 * @return amountsOwedScore
	 */

	public static double amountsOwedScore (double revol_bal, double total_rev_hi_lim) {
		double amountsOwedScore = 0;
		double revolvingRatio = revol_bal / total_rev_hi_lim;

		if (revolvingRatio > 0.0 && revolvingRatio <= 0.20 ) {
			amountsOwedScore = 900;
		} else if (revolvingRatio > 0.20 && revolvingRatio <= 0.40) {
			amountsOwedScore = 700;
		} else if (revolvingRatio > 0.40 && revolvingRatio <= 0.60) {
			amountsOwedScore = 300;
		} else if (revolvingRatio > 0.60 && revolvingRatio <= 0.80) {
			amountsOwedScore = 100;
		} else if (revolvingRatio > 0.80) {
			amountsOwedScore = -10000000;
		} else if (total_rev_hi_lim == 0) {
			amountsOwedScore = -10000000;
		} else if (revolvingRatio == 0) {
			amountsOwedScore = 500;
		}

		return amountsOwedScore;
	}
	/***
	 * This is the third component of PennCLK Score model
	 * This represents the number of months it passed since the user created his first
	 * revolving credit account
	 * @param mo_sin_old_rev_tl_op: number of months since
	 * the user's creation of revolving credit 
	 * @return creditHistoryScore
	 */
	public static double creditHistoryScore (int mo_sin_old_rev_tl_op) {
		double creditHistoryScore = 0;
		if (mo_sin_old_rev_tl_op < 12) {
			creditHistoryScore = 200;
		} else if (mo_sin_old_rev_tl_op > 11 && mo_sin_old_rev_tl_op < 24) {
			creditHistoryScore = 400;
		} else if (mo_sin_old_rev_tl_op > 23 && mo_sin_old_rev_tl_op < 48) {
			creditHistoryScore = 600;
		} else if (mo_sin_old_rev_tl_op > 47) {
			creditHistoryScore = 800;
		}

		return creditHistoryScore;
	}

	/***
	 * This is the fourth component of PennCLK Score model 
	 * This represents how many inquiries the user has made for borrowing
	 * in the last 6 months
	 * @param inq_last_6mths
	 * @return 
	 */
	public static double pursuitofNewCreditScore (int inq_last_6mths) {
		double pursuitofNewCreditScore = 0;

		if (inq_last_6mths == 0) {
			pursuitofNewCreditScore = 800;
		} else if (inq_last_6mths == 1) {
			pursuitofNewCreditScore = 600;
		} else if (inq_last_6mths == 2) {
			pursuitofNewCreditScore = 400;
		} else if (inq_last_6mths == 3) {
			pursuitofNewCreditScore = 200;
		} else if (inq_last_6mths > 3) {
			pursuitofNewCreditScore = 0;
		}

		return pursuitofNewCreditScore;
	}

	/***
	 * This is the fifth component of PennCLK Score model
	 * The user will provide information about whether they have:
	 * 1) check cards (= bank cards)
	 * 2) credit cards (=revolving credit)
	 * 3) existing loan accounts (= installment loans)
	 * 
	 * Depending on the number of accounts they have, then their creditmix score will vary
	 * @param num_actv_bc_tl: number of active bank card accounts
	 * num_actv_rev_tl: number of active revolving accounts
	 * open_act_il: number of active existing loan accounts
	 * @return open_act_il
	 */
	public static double creditMixScore (int num_actv_bc_tl, int num_actv_rev_tl, int open_act_il) {

		double creditMixScore = 0;
		double sumAccountNumbers = num_actv_bc_tl + num_actv_rev_tl + open_act_il;

		if (num_actv_bc_tl > 0 && num_actv_rev_tl > 0 && open_act_il > 0) {
			if (sumAccountNumbers == 3) {
				creditMixScore = 100;
			} else if (sumAccountNumbers == 7){
				creditMixScore = 500;
			} else if (sumAccountNumbers == 10){
				creditMixScore = 850;
			} else if (sumAccountNumbers > 10){
				creditMixScore = 200;
			}
		} else {
			creditMixScore = 0; //if any one of accounts does not exist.
		}

		return creditMixScore;
	}

	/***
	 * This is the sixth component of PennCLK Score model
	 * The user will provide information whether he/she has FT, PT or unemployed
	 * If the user has FT job, he/she will be assigned the highest score.
	 * @param jobStatus of the user
	 * @return jobScore
	 * 
	 * Note that the database will be automatically assumed as "Full Time" as no such
	 * information was provided from the raw file in the first place.
	 */
	public static double jobScore (String jobStatus) {
		int jobScore = 0;
		if (jobStatus.equals("Full Time")) {
			jobScore = 800;
		} else if (jobStatus.equals("Part Time")) {
			jobScore = 500;
		} else {
			jobScore = 300;
		}

		return jobScore;
	}
	

	/***
	 * This method calculates PennCLK Scores
	 * @param all of the required information from the user and from the database.
	 * @return pennCLK score which becomes the ground for assigning the final credit grade.
	 */
	public static double calculatePennCLKscore (double annualIncome, double loanAmount, int pubRec, int mo_sin_old_rev_tl_op,  
			 int inq_last_6mths, int num_actv_bc_tl, int num_actv_rev_tl,int open_act_il,
			 double revol_bal, double total_rev_hi_lim, String jobStatus ) {
		//each parameter within the method should be re-written later on as userInput[i] 
		//where i is the index representing the original parameters of the methods as described in java doc above.
		
		double PennCLKScore = 
				Algorithm.paymentHistoryScore(pubRec) * 0.20 + Algorithm.amountsOwedScore(revol_bal, total_rev_hi_lim) * 0.25
				+ Algorithm.creditHistoryScore(mo_sin_old_rev_tl_op) * 0.10 
				+ Algorithm.pursuitofNewCreditScore(inq_last_6mths) * 0.15
				+ Algorithm.creditMixScore(num_actv_bc_tl, num_actv_rev_tl, open_act_il) * 0.10 
				+ Algorithm.jobScore(jobStatus) * 0.20;


		if (PennCLKScore < LOWESTCONVERSIONBASE) {
			PennCLKScore = 300;
			return Math.round(PennCLKScore);
		} else if (PennCLKScore > HIGHESTCONVERSIONBASE){
			PennCLKScore = 850;
			return Math.round(PennCLKScore);
		} else {
			return Math.round(PennCLKScore);
		}

	}
}
