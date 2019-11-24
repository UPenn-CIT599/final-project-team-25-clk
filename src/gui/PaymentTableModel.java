package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Payment;



public class PaymentTableModel extends AbstractTableModel {
	private List<Payment> db;
	private String[] colNames = {"Monthly Principal", "Monthly Interest", "Monthly Total", "Pay or Default", "Payment", "Loan ID"};
	
	public PaymentTableModel() {
	}
	
	public void setData(List<Payment> db) {
		this.db = db;
	}
		

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Payment payment = db.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return payment.getMonthlyPaymentForPrincipal();
		case 1:
			return payment.getMonthlyPaymentForInterest();
		case 2:
			return payment.getMonthlyPaymentTotal();
		case 3:
			return payment.getPayOrDefault();
		case 4:
			return payment.getPaymentMadeForEachMonth();
		case 5:
			return payment.getLoanId();

		}
		
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

}
