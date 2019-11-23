import java.util.ArrayList;

import model.Customer;
import model.Loan;
import model.LoanApplication;

public class Storage {
	
	/**
	 * Turn customer object to csv file
	 * @param customerData customer data in HashMap data structure.
	 */
	public void storeCustomer(Customer customer) {
		
	}
	
	/**
	 * Read customer data from csv file and return a customer object
	 * @param id unique customer id
	 * @return Customer object
	 */
	public Customer getCustomer(String id) {
		return new Customer();
	}
	
	/**
	 * get the newest customer.
	 * @return customer obj
	 */
	public Customer getLastCustomer() {
		return new Customer();
	}
	
	/**
	 * store loan application into csv with a reference to customer id
	 * @param loanApplication
	 */
	public void storeLoanApplication(LoanApplication loanApplication) {
		
	}
	
	/**
	 * get loan application with customer id
	 * @return
	 */
	public LoanApplication getLoanApplication(String customerId) {
		return new LoanApplication();
	}
	
	/**
	 * Store loan into csv with reference to customer id
	 * @param loan
	 */
	public void storeLoan(Loan loan) {
		
	}
	
	/**
	 * Get a list of loans with customer id
	 * @param customerId
	 * @return
	 */
	public ArrayList<Loan> getLoans(String customerId) {
		return new ArrayList<Loan>();
	}
	
	

}
