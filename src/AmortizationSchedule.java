import java.util.ArrayList;
import java.util.HashMap;

public class AmortizationSchedule { 
	private ArrayList<Instalment> instalments;
	private double currentLoanOutstanding;
	private double startingLoan;
	
	/**
	 * create a amortization schedule
	 * @param existingLoanDetail
	 */
	public AmortizationSchedule(HashMap<String, String> existingLoanDetail) {
		
	}
	
	/**
	 * generate the next instalment payment.
	 */
	public Instalment generateNextInstalment() {
		return new Instalment();
	}
}
