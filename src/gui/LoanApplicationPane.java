package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Customer;
import model.Database;

public class LoanApplicationPane extends JPanel {
	private JLabel loanAmountLabel;
	private JLabel loanDurationLabel;
	private JLabel reasonLabel;

	private JTextField loanAmountField;
	private JTextField loanDurationField;
	private JTextField reasonField;
	private JButton submit;

	public LoanApplicationPane(Database database) {
		loanAmountLabel = new JLabel("Loan Application Amount: ");
		loanDurationLabel = new JLabel("Loan Duration: ");
		reasonLabel = new JLabel("Reason for applying: ");

		loanAmountField = new JTextField(20);
		loanDurationField = new JTextField(20);
		reasonField = new JTextField(20);

		submit = new JButton("Apply");

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String loanAmount = loanAmountField.getText();
				String loanDuration = loanDurationField.getText();
				String reason = reasonField.getText();
				
				Customer currentCustomer = database.getCurrentCustomer();
				currentCustomer.addLoanApplication(loanAmount, loanDuration, reason);

				database.updateCustomer(currentCustomer);
			}

		});
		
		layoutComponent();
	}

	public void layoutComponent() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		//////////// next row /////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(loanAmountLabel, gc);

		// coordinate (1,0)
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(loanAmountField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(loanDurationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(loanDurationField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(reasonLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(reasonField, gc);
		
		/////////// next row //////////////////
		
		gc.weightx = 1;
		gc.weighty = 2.0; 
		
		gc.gridy++;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(submit, gc);		
		
		
	}

}
