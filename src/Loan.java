
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Loan {
	private int loanId;
	private int customerId;
	private double interestRates;
	private double loanAmount;
	private String creditGrading;
	private Date startingDate;
	private int tenure;
	
	/**
	 * constructor
	 * @param existingLoanDetail
	 */
	public Loan(HashMap<String, String> existingLoanDetail) {

	}
	
	/**
	 * constructors for loans
	 * @param loanId
	 * @param customerId
	 * @param interestRates
	 * @param loanAmount
	 * @param tenure
	 * @param startingDate
	 */
	public Loan(int loanId, int customerId, double interestRates, double loanAmount, int tenure, String startingDate) {
		this.loanId = loanId; 
		this.customerId = customerId;
		this.interestRates = interestRates;
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		try {
			this.startingDate = new SimpleDateFormat("dd/MM/yyyy").parse(startingDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * calculate total interest over the life of the loan.
	 * @return
	 */
	public double calculateTotalInterest() {
		double totalInterest;
		totalInterest = loanAmount * (interestRates / 12) * tenure;
		return totalInterest;
	}
	
	/**
	 * months left until the loan matures
	 * @return
	 */
	public int monthsRemainingToMaturity() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar today = Calendar.getInstance();
	
		
		// we need tenure + startingDate to get end date.
		Calendar endingDate = Calendar.getInstance();
		endingDate.setTime(this.startingDate);
		endingDate.add(Calendar.MONTH, this.tenure);
		

	
		int daysBetween = (int) ChronoUnit.DAYS.between(today.toInstant(), endingDate.toInstant());
		int monthsBetween = (int) Math.round(daysBetween / 30 * 100.0 / 100.0);
		return monthsBetween;
	}

	/**
	 * get loan amount
	 * @return
	 */
	public double getLoanAmount() {
		return loanAmount;
	}

	/**
	 * get loan ID
	 * @return
	 */
	public int getLoanId() {
		return loanId;
	}

	/**
	 * get customer id
	 * @return
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * get interest rates
	 * @return
	 */
	public double getInterestRates() {
		return interestRates;
	}

	/**
	 * get credit grading.
	 * @return
	 */
	public String getCreditGrading() {
		return creditGrading;
	}

	/**
	 * get starting date
	 * @return
	 */
	public Date getStartingDate() {
		return startingDate;
	}

	/**
	 * get tenure in months.
	 * @return
	 */
	public int getTenure() {
		return tenure;
	}

} 
