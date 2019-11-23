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

import eventobject.LoanApplicationForm;
import eventobject.NewUserForm;
import gui.MainFrame;

public class Database {
	private List<Customer> customers;
	private List<LoanApplication> loanApplications;
	private List<Loan> loans;
	
	public Database() {
		customers = new LinkedList<Customer>();
		loanApplications = new LinkedList<LoanApplication>();
		loans = new LinkedList<Loan>();
	}
	
	/////////////////// STORAGE FOR CUSTOMERS //////////////////////////
	public void addCustomer(NewUserForm ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		double annualIncome = Double.parseDouble(ev.getAnnualIncome());
		int max = customers.size();
		Customer customer = new Customer(max, name, occupation, annualIncome);
		
		customers.add(customer);
		try {
			saveCustomerToFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	

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
	
	/////////////////// STORAGE FOR LOAN APPLICATION //////////////////////////
	
	public void addLoanApplication(LoanApplicationForm ev, Customer customer) {
		double loanAmount = Double.parseDouble(ev.getLoanApplicationAmount()); 
		int loanDuration = Integer.parseInt(ev.getLoanApplicationDuration());
		String reasonForApplying = ev.getReason();
		Date today = new Date();
		int max = loanApplications.size();
		
		LoanApplication loanApplication = new LoanApplication(max, loanAmount, loanDuration, reasonForApplying, today, customer.getUserId());
		
		loanApplications.add(loanApplication);
		
		try {
			saveLoanApplicationToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public LoanApplication getLoanApplication(int loanApplicationID) {
		Iterator i = loanApplications.iterator();
		while (i.hasNext()) {
			LoanApplication loanApplication = (LoanApplication) i.next();
			if (loanApplicationID == loanApplication.getLoanApplicationId()) {
				return loanApplication;
			}
		}
		return null;
	}
	
	public List<LoanApplication> getLoanApplicationByCustomerId(int customerId) {
		List<LoanApplication> result = new LinkedList();
		Iterator i = loanApplications.iterator();
		while (i.hasNext()) {
			LoanApplication loanApplication = (LoanApplication) i.next();
			if (customerId == loanApplication.getCustomerId()) {
				result.add(loanApplication);
			}
		}
		return result;
	}
	
	/////////////////// STORAGE FOR LOAN //////////////////////////
	
	public void addLoan(HashMap<String, String>loanInfo, Customer customer ) {
		int max = loans.size();
//		loanInfo.get()
//		
//		
//		double loanAmount = Double.parseDouble(ev.getLoanApplicationAmount()); 
//		int loanDuration = Integer.parseInt(ev.getLoanApplicationDuration());
//		String reasonForApplying = ev.getReason();
//		Date today = new Date();
//		
//		LoanApplication loanApplication = new LoanApplication(loanAmount, loanDuration, reasonForApplying, today, customer.getUserId());
//		
//		loanApplications.add(loanApplication);
//		
//		try {
//			saveLoanApplicationToFile();
//			loadLoanApplicationFromFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public Loan getLoan(int loanID) {
		Iterator i = loans.iterator();
		while (i.hasNext()) {
			Loan loan = (Loan) i.next();
			if (loanID == loan.getLoanId()) {
				return loan;
			}
		}
		return null;
	}
	
	public List<Loan> getLoanByCustomerId(int customerId) {
		List<Loan> result = new LinkedList();
		Iterator i = loans.iterator();
		while (i.hasNext()) {
			Loan loan = (Loan) i.next();
			if (customerId == loan.getCustomerId()) {
				result.add(loan);
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	//////// SAVING OF OBJECTS INTO FILE////////////
	
	
	public void saveCustomerToFile() throws IOException {
		FileOutputStream fos = new FileOutputStream("C:\\Users\\chian\\Documents\\customer");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Customer[] customersArr = customers.toArray(new Customer[customers.size()]) ;
		
		oos.writeObject(customersArr);
		oos.close();
	}
	
	public void loadCustomerFromFile() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\chian\\Documents\\customer");
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
	
	public void saveLoanApplicationToFile() throws IOException {
		FileOutputStream fos2 = new FileOutputStream("C:\\Users\\chian\\Documents\\loanApplication");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		
		LoanApplication[] loanApplicationArr = loanApplications.toArray(new LoanApplication[loanApplications.size()]) ;
		
		oos2.writeObject(loanApplicationArr);
		oos2.close();
	}
	
	public void loadLoanApplicationFromFile() throws IOException {
		FileInputStream fis2 = new FileInputStream("C:\\Users\\chian\\Documents\\loanApplication");
		
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		
		try {
			LoanApplication[] loanApplicationArr = (LoanApplication[]) ois2.readObject();
			for (LoanApplication c : loanApplicationArr) {
				System.out.println(c.getLoanAmount());
			}
			loanApplications.clear();
			loanApplications.addAll(Arrays.asList(loanApplicationArr));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ois2.close();
	}
	
	public void saveLoanToFile() throws IOException {
		FileOutputStream fos3 = new FileOutputStream("C:\\Users\\chian\\Documents\\loan");
		ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
		
		Loan[] loanArr = loans.toArray(new Loan[loans.size()]) ;
		
		oos3.writeObject(loanArr);
		oos3.close();
	}
	
	public void loadLoanFromFile() throws IOException {
		FileInputStream fis3 = new FileInputStream("C:\\Users\\chian\\Documents\\loan");
		ObjectInputStream ois3 = new ObjectInputStream(fis3);
		
		try {
			Loan[] loanArr = (Loan[]) ois3.readObject();
			loans.clear();
			loans.addAll(Arrays.asList(loanArr));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ois3.close();
	}
}
