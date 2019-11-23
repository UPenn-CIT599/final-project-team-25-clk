package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import eventobject.ExistingUserListener;
import eventobject.LoanApplicationForm;
import eventobject.LoanApplicationListener;
import eventobject.NewUserForm;
import eventobject.NewUserFormListener;
import model.Customer;
import model.Database;
import model.LoanApplication;

public class MainFrame extends JFrame {
	// panels
	private NewUserPane newUserPane;
	private ExistingUserPane existingUserPane;
	private LoanApplicationPane loanApplicationPane;
	private ManageLoanPane manageLoanPane;
	
	// controllers and model.
	private Database database;
	private Customer currentCustomer;
	private LoanApplication currentloanApplication;
	
	public MainFrame() {
		super();
		
		Database database = new Database();
		
		
		
		
		// load all loan, loan application and customer on to database.
		
		try {
			database.loadCustomerFromFile();
			
			//database.loadLoanFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			database.loadLoanApplicationFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		
		newUserPane = new NewUserPane();
		existingUserPane = new ExistingUserPane(database.getCustomers());
		JLabel welcome = new JLabel("Welcome to Loan Simulator");
		JTabbedPane tabbedPane = new JTabbedPane();
		loanApplicationPane = new LoanApplicationPane();
		
		
		
		tabbedPane.add("Welcome", welcome);
		tabbedPane.add("New User", newUserPane);
		tabbedPane.add("Select User", existingUserPane);
		tabbedPane.add("Loan Application", loanApplicationPane);
		//tabbedPane.add("Select Loan", loanPane);
		//tabbedPane.add("Manage Loan", manageLoanPane);

		newUserPane.setFormListener(new NewUserFormListener() {
			public void formEventOccurred(NewUserForm ev) {
				database.addCustomer(ev);
			}
		});
		
		existingUserPane.setFormListener(new ExistingUserListener() {
			public void userSelectionOccured(int userId) {
				currentCustomer  = database.getCustomer(userId);
				existingUserPane.repaintWithCurrentCustomer(currentCustomer);
				
				// mock approved loan.
				HashMap<String, String> loanInfo = new HashMap();
				loanInfo.put("principal", "150000");
				loanInfo.put("rate", "12");
				loanInfo.put("loanPeriod", "24");
				loanInfo.put("monthlyPayment", "2500");
				loanInfo.put("customerId", "6");
				database.addLoan(loanInfo, currentCustomer);
			}
		});
		
		loanApplicationPane.setFormListener(new LoanApplicationListener() {
			public void formEventOccurred(LoanApplicationForm ev) {
				System.out.println(currentCustomer.getName());
				database.addLoanApplication(ev, currentCustomer);
			
			}
		});

		
		
		add(tabbedPane);
		
		
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
