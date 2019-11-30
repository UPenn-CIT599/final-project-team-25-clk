package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import eventobject.ExistingUserListener;
import eventobject.LoanSelectionListener;
import eventobject.NewUserListener;
import model.Customer;
import model.Database;
import model.Loan;
import model.LoanApplication;

public class MainFrame extends JFrame {
	// panels
	private NewUserPane newUserPane;
	private ExistingUserPane existingUserPane;
	private LoanApplicationPane loanApplicationPane;
	private ManageLoanPane manageLoanPane;
	private LoanPane loanPane;
	private JLabel welcome;
	private JTabbedPane tabbedPane;
	
	
	// database and model.
	private Database database;
	private Customer currentCustomer;
	
	public MainFrame() {
		super();
		this.database = new Database();
		
		// load a list of customers to from file.
		
		try {
			database.loadCustomerFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		
		// add these panels to tab.
		welcome = new JLabel("Welcome to Loan Simulator");
		newUserPane = new NewUserPane(database);
		existingUserPane = new ExistingUserPane(database);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.add("Welcome", welcome);
		tabbedPane.add("New User", newUserPane);
		tabbedPane.add("Select User", existingUserPane);
		
		newUserPane.setFormListener(new NewUserListener() {
			public void newUserCreatedOccured() {
				showLoanTab();
			}
		});
		
		existingUserPane.setFormListener(new ExistingUserListener() {
			public void existingUserSelected() {
				showLoanTab();
			}
		});

		add(tabbedPane);
		
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * show additional loan panels.
	 */
	public void showLoanTab() {
		// creating a new loan application tab.
		tabbedPane.remove(loanApplicationPane);
		loanApplicationPane = new LoanApplicationPane(database);
		tabbedPane.add("Loan Application", loanApplicationPane);
		
		// creating a new loan tab
		tabbedPane.remove(loanPane);
		loanPane = new LoanPane(database);
		tabbedPane.add("Select Loan", loanPane);
		
		loanPane.setFormListener(new LoanSelectionListener() {
			public void loanSelectionOccured() {				
				tabbedPane.remove(manageLoanPane);
				manageLoanPane = new ManageLoanPane();
				
				manageLoanPane.setData(database.getCurrentLoan().getPayments());
				tabbedPane.add("Manage Loan", manageLoanPane);
			}
			
		});
	}
	
}
