package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class ApplicationResult {

	//File file = new File("loan_raw_sample.csv");
	ArrayList<String> columns = new ArrayList<>();
	ArrayList<LoanFile> loan = new ArrayList<>();

	public ArrayList<LoanFile> fileReader(String fileName) {

		File fw = new File(fileName);
		try {
			Scanner in = new Scanner(fw);
			String nextLine = in.nextLine();

			while(in.hasNextLine()) {
				if (columns.isEmpty()) {
					for (String s : nextLine.split(",")) {						
						columns.add(s);								
					}

				} else  { 
					LoanFile result = parseLoanFile(in.nextLine());
					if (result != null) {
						loan.add(result);
					}
				} 
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("the file cannot be read. Please double check.");
			e.printStackTrace();
			// USE EXCEPTION HANDLING HERE
		}	

		return loan;
	}

	public ArrayList<LoanFile> getAllLoanData() {
		return loan;
	}


	/***
	 * This is a helper method to make sure that only colon separated non-column data are
	 * added into an arraylist.
	 * @param each line it reads from flight.csv file after skipping the column.
	 * @return new instance created with the comma separated strings.
	 */
	public LoanFile parseLoanFile (String row) {
		String [] newRowArray = row.split(",");

		if (newRowArray.length > 0) {
			return new LoanFile(newRowArray[0], newRowArray[1], newRowArray[2], newRowArray[3], 
					newRowArray[4], newRowArray[5], newRowArray[6], newRowArray[7], newRowArray[8], 
					newRowArray[9], newRowArray[10]);
		}
		return null;
	}

	/***
	 * This method calculates the user's pennCLK score
	 * @param customerInformation: the user's information
	 * @return
	 */
	public static double userPennCLKScore (LoanApplication la) {
		double userPennCLKScore = Algorithm.calculatePennCLKscore(la.getIncome(), la.getLoanAmount(), 
				la.getPubRec(), la.getMo_sin_old_rev_tl_op(), la.getInq_last_6mths(), 
				la.getNum_actv_bc_tl(), la.getNum_actv_rev_tl(), 
				la.getOpen_act_il(), la.getRevol_bal(), la.getTotal_rev_hi_lim(), la.getJobStatus());
		
//		System.out.println("Income " + la.getIncome());
//		System.out.println("Loan " + la.getLoanAmount());
//		System.out.println("Inq 6 " +la.getInq_last_6mths());
//		System.out.println("getNumb_actv_bc " + la.getNum_actv_bc_tl());
//		System.out.println("revol " + la.getRevol_bal());
//		System.out.println("total " + la.getTotal_rev_hi_lim());
//		
//		System.out.println("INCOME SCORE " +  Algorithm.incomeScore(la.getIncome(), la.getLoanAmount()));
		
		return userPennCLKScore;
	}

	/***
	 * This method returns the credit grade of the user or the database
	 * @param userPennCLKScore
	 * @return
	 */
	public static String creditGradeforUser (double pennCLKScore) {
		String creditGrade = null;
		if (pennCLKScore > 700) {
			creditGrade = "A";
		} else if (pennCLKScore > 600 && pennCLKScore < 701){
			creditGrade = "B";
		} else if (pennCLKScore > 500 && pennCLKScore < 601){
			creditGrade = "C";
		} else if (pennCLKScore > 400 && pennCLKScore < 501){
			creditGrade = "D";
		} else if (pennCLKScore > 300 && pennCLKScore < 401){
			creditGrade = "E";
		} else {
			creditGrade = "NA";

		} 
		return creditGrade;
	}
	/***
	 * if the user's pennCLKscore is above the minimum conversion base of 350, then the user will be approved for its loan
	 * application
	 * @param customerInformation
	 * @return
	 */

	public static boolean isCustomerApprovedforLoan (double pennCLKScore) {
		boolean isCustomerApprovedforLoan = false;
		if (pennCLKScore > Algorithm.LOWESTCONVERSIONBASE) {
			isCustomerApprovedforLoan = true;
		} else {
			isCustomerApprovedforLoan = false;
		}
		return isCustomerApprovedforLoan;
	}

	/**
	 * calculate interest rates given credit scores
	 * @param pennCLKScore
	 * @return
	 */

	public static boolean isDataMissing (LoanFile file, ArrayList<LoanFile> loanList) {

		if (file.getIncomeAmount().isEmpty() || file.getLoanAmount().isEmpty() || file.getPubRec().isEmpty() ||
				file.getMo_sin_old_rev_tl_op().isEmpty() || file.getInq_last_6mths().isEmpty() || file.getNum_actv_bc_tl().isEmpty() ||
				file.getNum_actv_rev_tl().isEmpty() || file.getOpen_act_il().isEmpty() || file.getRevol_bal().isEmpty() || 
				file.getTotal_rev_hi_lim().isEmpty()) {

			return true;
		}

		return false;
	}


	public static double calculateInterestRates (ArrayList<LoanFile> loan, LoanApplication la) {

		int counterA = 0;
		int counterB = 0;
		int counterC = 0;
		int counterD = 0;
		int counterE = 0;
		double pennCLKScore;
		double sumAInterestRate = 0.0;
		double sumBInterestRate = 0.0;
		double sumCInterestRate = 0.0;
		double sumDInterestRate = 0.0;
		double sumEInterestRate = 0.0;
		double averageAInterestRate = 0.0;
		double averageBInterestRate = 0.0;
		double averageCInterestRate = 0.0;
		double averageDInterestRate = 0.0;
		double averageEInterestRate = 0.0;


		/***
		 * This is from the original datafile
		 */
		
		for (LoanFile lf : loan) {
			
			if (isDataMissing(lf, loan) == false) {
				
				pennCLKScore = Algorithm.calculatePennCLKscore(Double.parseDouble(lf.getIncomeAmount()), Double.parseDouble(lf.getLoanAmount()), Integer.parseInt(lf.getPubRec()), Integer.parseInt(lf.getMo_sin_old_rev_tl_op()),
						Integer.parseInt(lf.getInq_last_6mths()), Integer.parseInt(lf.getNum_actv_bc_tl()), Integer.parseInt(lf.getNum_actv_rev_tl()), 
						Integer.parseInt(lf.getOpen_act_il()), Double.parseDouble(lf.getRevol_bal()), Double.parseDouble(lf.getTotal_rev_hi_lim()), "Full Time");
//				System.out.println("Income " + Double.parseDouble(lf.getIncomeAmount()));
//				System.out.println("Loan " + Double.parseDouble(lf.getLoanAmount()));
//				System.out.println("Inq 6 " + Double.parseDouble(lf.getInq_last_6mths()));
//				System.out.println("getNumb_actv_bc " + Double.parseDouble(lf.getNum_actv_bc_tl()));
//				System.out.println("revol " + Double.parseDouble(lf.getRevol_bal()));
//				System.out.println("total " + Double.parseDouble(lf.getTotal_rev_hi_lim()));
				
				
				if (ApplicationResult.creditGradeforUser(pennCLKScore).equals("A")) {
					sumAInterestRate = sumAInterestRate + Double.parseDouble(lf.getInterestRate());
					counterA ++;
					averageAInterestRate = sumAInterestRate / counterA;
					//System.out.println("A rate " + averageAInterestRate);
					//System.out.println("pennCLKScore A " + pennCLKScore);

				} else if (ApplicationResult.creditGradeforUser(pennCLKScore).equals("B")) {
					sumBInterestRate = sumBInterestRate + Double.parseDouble(lf.getInterestRate());
					counterB ++;
					averageBInterestRate = sumBInterestRate / counterB;
					//System.out.println("B rate " + averageBInterestRate);
					//System.out.println("pennCLKScore B " + pennCLKScore);
					
				} else if (ApplicationResult.creditGradeforUser(pennCLKScore).equals("C")) {
					sumCInterestRate = sumCInterestRate + Double.parseDouble(lf.getInterestRate());
					counterC ++;
					averageCInterestRate = sumCInterestRate / counterC;
					//System.out.println("C rate " + averageCInterestRate);
					//System.out.println("pennCLKScore C " + pennCLKScore);
					
					
				} else if (ApplicationResult.creditGradeforUser(pennCLKScore).equals("D")) {
					sumDInterestRate = sumDInterestRate + Double.parseDouble(lf.getInterestRate());
					counterD ++;
					averageDInterestRate = sumDInterestRate / counterD;
					//System.out.println("D rate " + averageDInterestRate);
					//System.out.println("pennCLKScore D " + pennCLKScore);

				} else if (ApplicationResult.creditGradeforUser(pennCLKScore).equals("E")) {
					sumEInterestRate = sumEInterestRate + Double.parseDouble(lf.getInterestRate());
					counterE ++;
					averageEInterestRate = sumEInterestRate / counterE;
					//System.out.println("E rate " + averageEInterestRate);
					//System.out.println("pennCLKScore E " + pennCLKScore);
				} 
				//System.out.println("");
			}
		}
		
	

		if (ApplicationResult.creditGradeforUser(ApplicationResult.userPennCLKScore(la)).equals("A")) {
			double ArateUserRate = averageAInterestRate * 
					(701 / ApplicationResult.userPennCLKScore(la));
			return ArateUserRate;

		} else if (ApplicationResult.creditGradeforUser(ApplicationResult.userPennCLKScore(la)).equals("B")) {
			double BrateUserRate = averageBInterestRate * 
					(601 / ApplicationResult.userPennCLKScore(la));
			return BrateUserRate;

		} else if (ApplicationResult.creditGradeforUser(ApplicationResult.userPennCLKScore(la)).equals("C")) {
			double CrateUserRate = averageCInterestRate * 
					(501 / ApplicationResult.userPennCLKScore(la));
			return CrateUserRate;

		} else if (ApplicationResult.creditGradeforUser(ApplicationResult.userPennCLKScore(la)).equals("D")) {
			double DrateUserRate = averageDInterestRate * 
					(401 / ApplicationResult.userPennCLKScore(la));
			return DrateUserRate;

		} else {
			double ErateUserRate = averageEInterestRate * 
					(301 / ApplicationResult.userPennCLKScore(la));
			return ErateUserRate;
		}


	}

	/***
	 * approvedLoanperiod should be denoted in the number of "months" not "years"
	 * @param la
	 * @param result
	 * @return
	 */
	public double approvedLoanAmount (LoanApplication la, ApplicationResult result) {
		double income = la.getIncome() / 12;
		double interestRate = result.calculateInterestRates(loan, la) / 12;

		double repaymentAmount = (la.getLoanAmount() * interestRate * Math.pow((1 + interestRate), result.approvedLoanperiod(la))) 
				/ (Math.pow((1 + interestRate), result.approvedLoanperiod(la)) - 1) ; 

		double dti = repaymentAmount / income;


		if (dti > 0.5) {
			return Math.round( (income * 0.50 * 12) * 2) / 2;

		} else {
			return Math.round( (repaymentAmount * 12) * 2) / 2;
		}
	}

	public static int approvedLoanperiod (LoanApplication loanApplication) {
		if (loanApplication.getLoanDuration() < loanApplication.getLengthOfEmployment() * 12) {
			return loanApplication.getLoanDuration();
		} else if (loanApplication.getLengthOfEmployment() == 0 && loanApplication.getLoanDuration() < 120){
			return loanApplication.getLoanDuration();
		} else {
			return loanApplication.getLengthOfEmployment() * 12;	
		}
	}
}

