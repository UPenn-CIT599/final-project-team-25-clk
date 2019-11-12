import java.io.File;
import java.util.ArrayList;

public class ApplicationResult {


	Algorithm algo = new Algorithm();
	File file = new File("loan_raw_sample.csv");
	
	
	/***
	 * This method calculates the user's pennCLK score
	 * @param customerInformation: the user's information
	 * @return
	 */
	public double userPennCLKScore (ArrayList<Customer> customerInformation) {
		double userPennCLKScore = algo.calculatePennCLKscore(customerInformation[1]..);
		return userPennCLKScore;
	}
	
	/***
	 * This method returns the credit grade of the user or the database
	 * @param userPennCLKScore
	 * @return
	 */
	public String creditGrade (double pennCLKScore) {
		String creditGrade = null;
		
		if (pennCLKScore > 700) {
			creditGrade.equals("A");
		} else if (pennCLKScore > 600 && pennCLKScore < 701){
			creditGrade.equals("B");
			/***
			 * So on to userGrade equals to E.
			 */
		} 
		return userGrade;
	}
	/***
	 * if the user's pennCLKscore is above the minimum conversion base of 350, then the user will be approved for its loan
	 * application
	 * @param customerInformation
	 * @return
	 */
	public boolean isCustomerApprovedforLoan (ArrayList<Customer> customerInformation) {
		boolean isCustomerApprovedforLoan = false;
		if (this.userPennCLKScore < algo.LOWESTCONVERSIONBASE) {
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
	public double calculateInterestRates (double pennCLKScore) {
		double databaseInterestRates = 0.0;
		double averageDatabaseInterestRates = 0.0;
		double counter = 0;
		double userInterestRates = 0.0;
		
		if (this.creditGrade(pennCLKScore).equals("A")) {
			counter++;
			databaseInterestRates += file.interestRates;
			//find the column of "interest rate" in the original sample file and sum the interest rates
			//of the database users who have the credit grade of "A".
			averageDatabaseInterestRates = databaseInterestRates / counter;
			// then find the average interest rates. Continue this upto the grade "E".
		}
		if (this.isCustomerApprovedforLoan == true) {
			if (this.creditGrade.equals("A"))) {
				userInterestRates = averageDatabaseInterestRates;
				//same processes go through here again, but now we calculate the interest of the user by assigning
				//him/her the aveargeDatabaseInterestRates we calculated above.
				//this continues upto the grade "E".
			}
		}
		return userInterestRates;
	}
	
	
	
}
