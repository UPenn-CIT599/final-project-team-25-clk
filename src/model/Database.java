package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import gui.MainFrame;

public class Database {
	
	/// these are the four things we need to save into file.
	private List<Customer> customers;
	private Customer currentCustomer;
	private Loan currentLoan;
	private LoanApplication currentLoanApplication;
	
	
	public LoanApplication getCurrentLoanApplication() {
		return currentLoanApplication;
	}

	public void setCurrentLoanApplication(LoanApplication currentLoanApplication) {
		this.currentLoanApplication = currentLoanApplication;
	}

	// star
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}
	
	/**
	 * Setting the current customer for this session.
	 * @param currentCustomer
	 */
	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}
	
	/**
	 * get current loan
	 * @return
	 */
	public Loan getCurrentLoan() {
		return currentLoan;
	}
		
	/**
	 * set current loan.
	 * @param currentLoan
	 */
	public void setCurrentLoan(Loan currentLoan) {
		this.currentLoan = currentLoan;
	}

	/**
	 * database constructor.
	 */
	public Database() {
		customers = new LinkedList<Customer>();
	}
	
	/////////////////// STORAGE FOR CUSTOMERS //////////////////////////
	
	/**
	 * Add a new customer into the database.
	 * @param name
	 * @param occupation
	 * @param annualIncome
	 * @return
	 */
	public Customer addCustomer(String name, String occupation) {
		int max = customers.size();
		Customer customer = new Customer(max, name, occupation);
		customers.add(customer);
		
		try {
			saveCustomerToFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	
	/**
	 * update customer object.
	 * @param customer
	 */
	public void updateCustomer(Customer customer) {
		int i = customers.indexOf(customer);
		customers.set(i, customer);
		try {
			saveCustomerToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get a list of customers.
	 * @return
	 */
	public List<Customer> getCustomers() {
		return customers;
	}
	
	/**
	 * get customer by id
	 * @param userId
	 * @return
	 */
	public Customer getCustomer(int userId) {
		Iterator i = customers.iterator();
		while (i.hasNext()) {
			Customer customer = (Customer) i.next();
			if (userId == customer.getUserId()) {
				return customer;
			}
		}
		return null;
	}
	
	
	//////// SAVING OF OBJECTS INTO FILE////////////
	
	/**
	 * Save list of customers into file.
	 * @throws IOException
	 */
	public void saveCustomerToFile() throws IOException {
		String desktopPath = System.getProperty("user.home") + "\\Desktop\\customer";
		
		FileOutputStream fos = new FileOutputStream(desktopPath);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Customer[] customersArr = customers.toArray(new Customer[customers.size()]) ;
		
		oos.writeObject(customersArr);
		oos.close();
	}
	
	
	/**
	 * load list of customers from file.
	 * @throws IOException
	 */
	public void loadCustomerFromFile() throws IOException {
		String desktopPath = System.getProperty("user.home") + "\\Desktop\\customer";
		
		FileInputStream fis = new FileInputStream(desktopPath);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Customer[] customersArr = (Customer[]) ois.readObject();
			customers.clear();
			customers.addAll(Arrays.asList(customersArr));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ois.close();
	}
}
