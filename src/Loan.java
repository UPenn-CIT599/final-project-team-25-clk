
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
	
	public double calculateTotalInterest() {
		double totalInterest;
		totalInterest = loanAmount * (interestRates / 12) * tenure;
		return totalInterest;
	}
	
	public int monthsRemainingToMaturity() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		
		// we have today's date
		System.out.println(dateFormat.format(cal.getTime()));
		
		
		// we need tenure + startingDate to get end date.
		Calendar endingCal = Calendar.getInstance();
		endingCal.setTime(this.startingDate);
		endingCal.add(Calendar.MONTH, this.tenure);
		
		
		
		System.out.println(dateFormat.format(endingCal.getTime()));
	
		int daysBetween = (int) ChronoUnit.DAYS.between(cal.toInstant(), endingCal.toInstant());
		System.out.println(daysBetween);
		int monthsBetween = (int) Math.round(daysBetween / 30 * 100.0 / 100.0);
		System.out.println(monthsBetween);
		return monthsBetween;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getInterestRates() {
		return interestRates;
	}

	public void setInterestRates(double interestRates) {
		this.interestRates = interestRates;
	}

	public String getCreditGrading() {
		return creditGrading;
	}

	public void setCreditGrading(String creditGrading) {
		this.creditGrading = creditGrading;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
} 
