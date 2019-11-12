import java.util.ArrayList;

public class Algorithm {

	ArrayList <UserInterface> userInput;
	int pubRec; //instance variable for paymentHistory
	public static final double PAYMENTHISTORYWEIGHTING = 0.35;
	public static final double HIGHESTSCORE = 67.75;
	public static final double HIGHESTCONVERSIONBASE = 850;
	public static final double LOWESTCONVERSIONBASE = 300;
	
	/***
	 * Customer's information in ArrayListwill be the parameter for Algorithm constructor.
	 * @param userInput
	 */
//	public Algorithm (ArrayList<Customer> userInput) {
//		this.userInput = userInput;	
//	}
	
	/***
	 * non-parameter constructor will be used here fore testing.
	 */
	public Algorithm () {
		
	}

	/***
	 * First component of PennCLKscore - Payment History 35%
	 * Payment History represents the number of months since the most
	 * recent derogatory public record
	 * @param pubRec = public record -> should be replaced by userInput instance.
	 * @return paymentHistoryScore: score range 0 - 75.
	 */

	public static double paymentHistoryScore (int pubRec) {
		double paymentHistoryScore = 0;

		if (pubRec == 0) {
			paymentHistoryScore = 75;
		} else if (pubRec > 0 && pubRec < 6) {
			paymentHistoryScore = 10;
		} else if (pubRec > 5 && pubRec < 12) {
			paymentHistoryScore = 15;
		} else if (pubRec > 11 && pubRec < 24) {
			paymentHistoryScore = 25;
		} else if (pubRec > 23) {
			paymentHistoryScore = 55;
		}
		
		return paymentHistoryScore;
	}

	/***
	 * This is the second component of PennCLKscore - Amounts Owed 30%
	 * This represents the ratio of the user's revolving credit over his allocated
	 * maximum revolving limit.
	 * @param revol_bal: user's current maximum revolving credit -> should be replaced by userInput instance.
	 * @param total_rev_hi_lim: user's allocated revolving credit limit -> should be replaced by userInput instance.
	 * @return amountsOwedScore
	 */
	
	public static double amountsOwedScore (double revol_bal, double total_rev_hi_lim) {
		double amountsOwedScore = 0;
		double revolvingRatio = revol_bal / total_rev_hi_lim;
		
		if (revolvingRatio > 0.0 && revolvingRatio <= 0.20 ) {
			amountsOwedScore = 65;
		} else if (revolvingRatio > 0.20 && revolvingRatio <= 0.40) {
			amountsOwedScore = 50;
		} else if (revolvingRatio > 0.40 && revolvingRatio <= 0.60) {
			amountsOwedScore = 40;
		} else if (revolvingRatio > 0.60 && revolvingRatio <= 0.80) {
			amountsOwedScore = 25;
		} else if (revolvingRatio > 0.80) {
			amountsOwedScore = 15;
		} else if (total_rev_hi_lim == 0) {
			amountsOwedScore = 55;
		} else if (revolvingRatio == 0) {
			amountsOwedScore = 30;
		}
		
		return amountsOwedScore;
	}
	/***
	 * This is the third component of PennCLK Score model - Credit History 15%
	 * This represents the number of months it passed since the user created his first
	 * revolving credit account
	 * @param mo_sin_old_rev_tl_op: number of months since -> should be replaced by userInput instance.
	 * the user's creation of revolving credit 
	 * @return creditHistoryScore
	 */
	public static double creditHistoryScore (int mo_sin_old_rev_tl_op) {
		double creditHistoryScore = 0;
		if (mo_sin_old_rev_tl_op < 12) {
			creditHistoryScore = 12;
		} else if (mo_sin_old_rev_tl_op > 11 && mo_sin_old_rev_tl_op < 24) {
			creditHistoryScore = 35;
		} else if (mo_sin_old_rev_tl_op > 23 && mo_sin_old_rev_tl_op < 48) {
			creditHistoryScore = 60;
		} else if (mo_sin_old_rev_tl_op > 47) {
			creditHistoryScore = 75;
		}
		
		return creditHistoryScore;
	}
	
	/***
	 * This is the fourth component of PennCLK Score model 
	 * - Pursuit of New Credit 10%
	 * This represents how many inquiries the user has made for borrowing
	 * in the last 6 months
	 * @param inq_last_6mths
	 * @return 
	 */
	public static double pursuitofNewCreditScore (int inq_last_6mths) {
		double pursuitofNewCreditScore = 0;
		
		if (inq_last_6mths == 0) {
			pursuitofNewCreditScore = 70;
		} else if (inq_last_6mths == 1) {
			pursuitofNewCreditScore = 60;
		} else if (inq_last_6mths == 2) {
			pursuitofNewCreditScore = 45;
		} else if (inq_last_6mths == 3) {
			pursuitofNewCreditScore = 25;
		} else if (inq_last_6mths > 3) {
			pursuitofNewCreditScore = 20;
		}
		
		return pursuitofNewCreditScore;
	}
	
	/***
	 * This is the fifth component of PennCLK Score model - creditMixScore 10%
	 * The user will provide information about whether they have:
	 * 1) credit cards (=revolving credit)
	 * 2) check cards (= bank cards)
	 * 3) existing loan accounts (= installment loans)
	 * 
	 * If all they have it, then their creditmix score will increase
	 * 
	 * @return
	 */
	public static double creditMixScore (int num_actv_bc_tl, int num_actv_rev_tl, int open_act_il) {

		double creditMixScore = 0;
		double sumAccountNumbers = num_actv_bc_tl + num_actv_rev_tl + open_act_il;
		
		if (num_actv_bc_tl > 0 && num_actv_rev_tl > 0 && open_act_il > 0) {
			if (sumAccountNumbers == 3) {
				creditMixScore = 25;
			} else if (sumAccountNumbers == 4){
				creditMixScore = 50;
			} else if (sumAccountNumbers == 5){
				creditMixScore = 60;
			} else if (sumAccountNumbers > 5){
				creditMixScore = 50;
			}
		} else {
			creditMixScore = 15; //if any one of accounts does not exist.
		}
		
		return creditMixScore;
	}
	
	/***
	 * Example of how PennCLK Score is calculated
	 * @param all of the required information from the user and from the database.
	 * @return
	 */
	public static double calculatePennCLKscore (int pubRec, double revol_bal, double total_rev_hi_lim, 
			int mo_sin_old_rev_tl_op, int inq_last_6mths, int num_actv_bc_tl, int num_actv_rev_tl, int open_act_il) {
		//each parameter within the method should be re-written later on as userInput[i] 
		//where i is the index representing the original parameters of the methods as described in java doc above.
		
		double PennCLKScore = Algorithm.paymentHistoryScore(pubRec) * 0.35 + 
				Algorithm.amountsOwedScore(revol_bal, total_rev_hi_lim) * 0.30
		+ Algorithm.creditHistoryScore(mo_sin_old_rev_tl_op) * 0.15 + Algorithm.pursuitofNewCreditScore(inq_last_6mths) * 0.10
		+ Algorithm.creditMixScore(num_actv_bc_tl, num_actv_rev_tl, open_act_il) * 0.10;
		
		double finalPennCLKScore = (PennCLKScore / HIGHESTSCORE) * HIGHESTCONVERSIONBASE;
		if (finalPennCLKScore < LOWESTCONVERSIONBASE) {
			finalPennCLKScore = 300;
			return Math.round(finalPennCLKScore);
		} else {
			return Math.round(finalPennCLKScore);
		}
		
	}
	

}
