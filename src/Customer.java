import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer {
	private int user_id;
	private String name;
	private double annualIncome;
	private String jobTitle;
	private String creditRating;
	private LoanApplication loanApplication;
	private ArrayList<Loan> loans;
	
	/**
	 * initializing an existing customer
	 * @param customerData
	 */
	public Customer(HashMap<String, String> customerData) {
		// temp, to loan list of loans from storage.
		this.loans = new ArrayList<Loan>();
	}
	
	/**
	 * initializing a new customer.
	 */
	public Customer() {
		this.loans = new ArrayList<Loan>();
	}
	

	/**
	 * apply for new loan
	 */
	public void applyLoan() {
		UserInterface ui = new UserInterface();
		Scanner in = new Scanner(System.in);
		Storage storage = new Storage();
		
		System.out.println("Do you previously have loans? Y or N");
		String action = in.nextLine();
		
		if (action.equalsIgnoreCase("Y")) {
			HashMap<String, String> existingLoanDetail =  ui.promptExistingLoanDetail();
			Loan loan = new Loan(existingLoanDetail);
			this.loans.add(loan);
			storage.storeLoan(loan);
		} 
		
		
		HashMap loanApplicationDetail = ui.promptApplyLoan();
		this.loanApplication = new LoanApplication(loanApplicationDetail);
		storage.storeLoanApplication(loanApplication);
	}
	
	/**
	 * make monthly instalment for loan.
	 */
	public void makePayment() {
		
	}
	
	/**
	 * manage existing loan.
	 */
	public void manageLoan() {
		
	}

	
}
