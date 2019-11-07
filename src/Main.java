import java.util.*;

public class Main {

	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		Storage storage = new Storage();
		Scanner in = new Scanner(System.in);
		Customer currentCustomer;
		System.out.println("Welcome to Loan Simulator");
		System.out.println("Are you a new user ? Press y for yes and n for no");

		String action = in.nextLine();
		if (action.equalsIgnoreCase("y")) {
			HashMap<String, String> customerDetail = ui.getCustomerDetail();
			currentCustomer = new Customer(customerDetail);
			storage.storeCustomer(currentCustomer);
		} else {
			
			String id = ui.promptLogin();
			currentCustomer = storage.getCustomer(id);
		}
		
		boolean quit = false;
		
		while (!quit) {
			action = ui.promptCustomerInstruction();
			
			if (action.equals("1")) {
				currentCustomer.applyLoan();
			} else if (action.equals("2")) {
				currentCustomer.manageLoan();
			} else if (action.equals("3")) {
				System.out.println("See you");
				quit = true;
			} else {
				System.out.println("Unrecognised action.");
			}
			
		}
		
		

	}

}
