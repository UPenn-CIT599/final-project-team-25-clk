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
import eventobject.LoanApplicationListener;
import eventobject.LoanAnalysisListener;
import eventobject.NewUserListener;
import model.Customer;
import model.Database;
import model.Loan;
import model.LoanApplication;

/**
 * Main containing frame for java swing.
 *
 */
public class MainFrame extends JFrame {
	// panels
	private NewUserPane newUserPane;
	private ExistingUserPane existingUserPane;
	private LoanApplicationPane loanApplicationPane;
	private LoanPane loanPane;
	private JLabel welcome;
	private JTabbedPane tabbedPane;
		
	// database and model.
	private Database database;
	
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
				tabbedPane.remove(existingUserPane);
				existingUserPane = new ExistingUserPane(database);
				tabbedPane.add("Select User", existingUserPane);
				
				existingUserPane.setFormListener(new ExistingUserListener() {
					public void existingUserSelected() {
						tabbedPane.remove(loanApplicationPane);
						tabbedPane.remove(loanPane);
						
						loanApplicationPane = new LoanApplicationPane(database);
						loanPane = new LoanPane(database);
						
						tabbedPane.add("Loan Application", loanApplicationPane);
						tabbedPane.add("Select Loan", loanPane);
						
						loanApplicationPane.setFormListener(new LoanApplicationListener() {
							public void loanApplicationOccured() {
								tabbedPane.remove(loanPane);
								loanPane = new LoanPane(database);
								tabbedPane.add("Select Loan", loanPane);
							}
						});
					}
				});
			}
		});
		
		existingUserPane.setFormListener(new ExistingUserListener() {
			public void existingUserSelected() {
				tabbedPane.remove(loanApplicationPane);
				tabbedPane.remove(loanPane);
				
				loanApplicationPane = new LoanApplicationPane(database);
				loanPane = new LoanPane(database);
				
				tabbedPane.add("Loan Application", loanApplicationPane);
				tabbedPane.add("Select Loan", loanPane);
				
				loanApplicationPane.setFormListener(new LoanApplicationListener() {
					public void loanApplicationOccured() {
						tabbedPane.remove(loanPane);
						loanPane = new LoanPane(database);
						tabbedPane.add("Select Loan", loanPane);
					}
				});
			}
		});

		add(tabbedPane);
		setSize(1000,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
