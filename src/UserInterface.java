import java.util.*;

public class UserInterface {
	private Scanner in;
	public UserInterface() {
		this.in = new Scanner(System.in);
	}
	
	/**
	 * get basic customer information and return a ArrayList
	 */
	public String promptLogin() {
		System.out.println("May I know your customer id?");
		String id = in.nextLine();
		return id;
	}
	
	/**
	 * Get customer details including name, job title, income etc
	 * @return hashmap of customer details , key = 'name' , value = 'jack'
	 */
	public HashMap<String, String> getCustomerDetail() {
		return new HashMap();
	}
	
	/**
	 * prompt for customer's instruction at menu screen.
	 * @return
	 */
	public String promptCustomerInstruction() {
		System.out.println("What do you want to do? Type the appropriate number. \n"
				+ "1. apply for a new loan \n"
				+ "2. manage existing loans \n"
				+ "3. quit");
		
		String action = in.nextLine();
		
		return action;
	}

	
	/**
	 * screen to ask for existing loan detail by filling a form.
	 */
	public HashMap<String, String> promptExistingLoanDetail() {
		return new HashMap();
	}
	
	
	/**
	 * prompt user for information about loan application by filling a form.
	 * @return
	 */
	public HashMap<String, String> promptApplyLoan() {
		return new HashMap();
	}
	
}
