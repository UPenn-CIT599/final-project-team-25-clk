package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import eventobject.LoanAnalysisListener;
import model.Customer;
import model.Database;
import model.Loan;

/**
 * a panel for the approved loan.
 *
 */
public class LoanPane extends JPanel implements ActionListener {
	private JComboBox<Item> loanSelection;
	private LoanAnalysisListener selectionListener;
	private JComboBox<String> year;
	private JComboBox<String> month;
	private JTextField amount;
	private JButton analyseBtn;
	private Customer currentCustomer;
	private Database database;
	private Loan currentLoan;
	private Container loanPanel;
	
	
	public LoanPane(Database database) {		
		setLayout(new BorderLayout());
		this.database = database;
		
		loanPanel = this;
		currentCustomer = database.getCurrentCustomer();
		ArrayList<Loan> loans = currentCustomer.getLoans();
		loanSelection = new JComboBox<Item>();
		
		DefaultComboBoxModel<Item> loanSelectionModel = new DefaultComboBoxModel<Item>();
		
		for (Loan loan : loans) {
			loanSelectionModel.addElement(new Item("Loan Id: " + loan.getLoanId() + ", loan principal: " 
		+ loan.getPrincipal(), loan.getLoanId()));
		}
		loanSelection.setModel(loanSelectionModel);
		loanSelection.addActionListener(this);
		loanSelection.setActionCommand("loanSelection");
		
		add(loanSelection, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("loanSelection")) {
			JComboBox cb = (JComboBox)e.getSource();
			Item _loan = (Item) cb.getSelectedItem();
			int loanId = _loan.getValue();
			
			this.currentLoan = currentCustomer.getLoan(loanId);

			database.setCurrentLoan(currentLoan);
			repaintWithCurrentLoan(currentLoan);
			
			
		} else if (e.getActionCommand().equals("Analyse")) {
			
			// forms 
			double amount = Double.parseDouble(this.amount.getText());
			String year = (String) this.year.getSelectedItem();
			String month = (String) this.month.getSelectedItem();
			
			
			/// LOAN SELECTED
			/// principal of 100,000
			// interest of 12%.
			// loan peirod of 12 months.
			double monthlyPayment = currentLoan.monthlyPayment();
			
			HashMap<Integer, Double> loanSchedule = currentLoan.mapMonthToPaymentDue();
			int installmentNum = currentLoan.calcInstallNumFrDateSelected(year + month);
			System.out.println(loanSchedule);
			
			String futurePrincipalDue = currentLoan.amountDue(installmentNum, amount);
			
			JOptionPane.showMessageDialog(loanPanel, futurePrincipalDue );
			
			/////// DAVID PART ////////////
			
			if (selectionListener != null) {
				/// can do a table. 
				selectionListener.loanAnalysisOccured();
			}
		}
	}
	
	
	/**
	 * refresh the loan panel when user picks a loan.
	 * @param currentLoan
	 */
	public void repaintWithCurrentLoan(Loan currentLoan) {

		JPanel loanInfo = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		loanInfo.setLayout(gbl);

		GridBagConstraints gc = new GridBagConstraints();

		//////////// next row /////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("Loan ID :     "), gc);

		// coordinate (1,0)
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JTextField(Integer.toString(currentLoan.getLoanId()), 20), gc);
		
		///////////// next row /////////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("Principal :     "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JTextField(Double.toString(currentLoan.getPrincipal()), 20), gc);
		
		///////////// next row /////////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("Loan Period :     "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JTextField(Integer.toString(currentLoan.getLoanPeriod()), 20), gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("Interest rates:      "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JTextField(Double.toString(currentLoan.getRate()), 20), gc);
		
		
		///////////// next row /////////////////
		gc.gridy++;
	
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("Credit Grading :      "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JTextField(currentLoan.getCreditGrade(), 20), gc);
		
		///////////// next row /////////////////
		gc.gridy++;
	
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel("----------------------------"), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JLabel("------------PAYMENT ANALIZER------------------"), gc);
		
		///////////// next row /////////////////
		gc.gridy++;
	
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.fill = GridBagConstraints.NONE;
		loanInfo.add(new JLabel("Year of Payment:      "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		
		String[] yearChoice = {"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
		year = new JComboBox<String>(yearChoice);
		loanInfo.add(year, gc);
		
		///////////// next row /////////////////
		gc.gridy++;
	
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.fill = GridBagConstraints.NONE;
		loanInfo.add(new JLabel("Month of Payment:      "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		String[] monthChoice = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		month = new JComboBox<String>(monthChoice);
		loanInfo.add(month, gc);
		
		///////////// next row /////////////////
		gc.gridy++;
	
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.fill = GridBagConstraints.NONE;
		loanInfo.add(new JLabel("Payment:      "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		amount = new JTextField(10);
		loanInfo.add(amount, gc);
		
		///////////// next row /////////////////
		gc.gridy++;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		analyseBtn = new JButton("Analyse");
		
		analyseBtn.addActionListener(this);
		analyseBtn.setActionCommand("Analyse");
		loanInfo.add(analyseBtn, gc);
		
		///////////// next row /////////////////
		
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.anchor = GridBagConstraints.LINE_END;
		loanInfo.add(new JLabel(""), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		loanInfo.add(new JLabel(""), gc);
		
		add(loanInfo, BorderLayout.CENTER);
		
		
	}
	
	/**
	 * sets the form listener for loan panel.
	 * @param selectionListener
	 */
	public void setFormListener(LoanAnalysisListener selectionListener) {
		this.selectionListener = selectionListener;
	}
}
