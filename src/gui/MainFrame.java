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
import eventobject.LoanPaymentListener;
import eventobject.LoanSelectionListener;
import eventobject.NewUserForm;
import eventobject.NewUserFormListener;
import model.Customer;
import model.Database;
import model.Loan;
import model.LoanApplication;
import model.Payment;

public class MainFrame extends JFrame {
	// panels
	private NewUserPane newUserPane;
	private ExistingUserPane existingUserPane;
	private LoanApplicationPane loanApplicationPane;
	private ManageLoanPane manageLoanPane;
	private LoanPane loanPane;
	
	// database and model.
	private Database database;
	private Customer currentCustomer;
	private Loan currentLoan;
	private LoanApplication currentloanApplication;
	
	public MainFrame() {
		super();
		
		Database database = new Database();
		
		// load all loan, loan application and customer on to database.
		
		try {
			database.loadCustomerFromFile();
			database.loadLoanApplicationFromFile();
			database.loadLoanFromFile();
			database.loadPaymentFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		setLayout(new BorderLayout());
		
		newUserPane = new NewUserPane();
		existingUserPane = new ExistingUserPane(database);
		JLabel welcome = new JLabel("Welcome to Loan Simulator");
		loanApplicationPane = new LoanApplicationPane();
		loanPane = new LoanPane(database);
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		
		
		
		tabbedPane.add("Welcome", welcome);
		tabbedPane.add("New User", newUserPane);
		tabbedPane.add("Select User", existingUserPane);
		tabbedPane.add("Loan Application", loanApplicationPane);
		tabbedPane.add("Select Loan", loanPane);
		

		newUserPane.setFormListener(new NewUserFormListener() {
			public void formEventOccurred(NewUserForm ev) {
				database.addCustomer(ev);
			}
		});
		
		existingUserPane.setFormListener(new ExistingUserListener() {
			public void userSelectionOccured(int userId) {
				currentCustomer  = database.getCustomer(userId);
				existingUserPane.repaintWithCurrentCustomer(currentCustomer);
				
				
				// mock approved loan. For now , it is created when we select a random user. It creates a 150k loan for them.
				HashMap<String, String> loanInfo = new HashMap();
				loanInfo.put("principal", "150000");
				loanInfo.put("rate", "12");
				loanInfo.put("loanPeriod", "24");
				loanInfo.put("monthlyPayment", "2500");
				loanInfo.put("customerId", Integer.toString(currentCustomer.getUserId()));
				database.addLoan(loanInfo);
				
			}
		});
		
		loanApplicationPane.setFormListener(new LoanApplicationListener() {
			public void formEventOccurred(LoanApplicationForm ev) {
				//System.out.println(currentCustomer.getName());
				database.addLoanApplication(ev, currentCustomer);
			
			}
		});
		
		loanPane.setFormListener(new LoanSelectionListener() {
			public void loanSelectionOccured(int loanId) {
				currentLoan = database.getLoan(loanId);
				loanPane.repaintWithCurrentLoan(currentLoan);
				
				List<Payment> payments = database.getPaymentsByLoanId(1);
				for (Payment p : payments) {
					System.out.println(p.getMonthlyPaymentForInterest());
				}
				manageLoanPane = new ManageLoanPane();
				manageLoanPane.setData(database.getPaymentsByLoanId(1));
				tabbedPane.add("Manage Loan", manageLoanPane);
				
				/// NOW mocked list of payments.
//				for (int i = 0; i < 40; i++) {
//					HashMap<String, String> paymentInfo = new HashMap();
//		
//					paymentInfo.put("monthlyPaymentForPrincipal", "1000");
//					paymentInfo.put("monthlyPaymentForInterest", "100");
//					paymentInfo.put("monthlyPaymentTotal", "1100");
//					paymentInfo.put("payOrDefault", "true");
//					paymentInfo.put("paymentMadeForEachMonth", "1100");
//					paymentInfo.put("loanId", "1");
//					
//					database.addPayment(paymentInfo);
//					
//				}
			}
			
		});
		
		
		loanPane.setPaymentListener(new LoanPaymentListener() {
			public void loanPaymentOccured(double amount) {
				currentLoan.payInstalment(amount);
			}
		});
			

		
		
		add(tabbedPane);
		
		
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
