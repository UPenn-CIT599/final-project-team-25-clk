package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Payment;

public class ManageLoanPane extends JPanel {
	private JTable paymentTable;
	private PaymentTableModel tableModel;
	
	public ManageLoanPane() {
		tableModel = new PaymentTableModel();
		paymentTable = new JTable(tableModel);
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(paymentTable), BorderLayout.CENTER);
		
	}
	
	public void setData(List<Payment> payments) {
		for (Payment p : payments) {
			System.out.println(p.getMonthlyPaymentForInterest());
		}
		tableModel.setData(payments);
	}
}
