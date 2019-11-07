import java.util.Date;
import java.util.HashMap;

public class Loan {
	private int loanId;
	private int customerId;
	private double interestRates;
	private String creditGrading;
	private Date startingDate;
	private int tenure;
	private AmortizationSchedule amortization;
	
	public Loan(HashMap<String, String> existingLoanDetail) {
		
		this.amortization = new AmortizationSchedule(existingLoanDetail);
	}
} 
