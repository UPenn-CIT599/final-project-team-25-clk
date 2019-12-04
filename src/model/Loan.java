package model;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Loan implements Serializable {
	
	private static final long serialVersionUID = -3405617888750938390L;
	private int loanId;
	private double principal;
	private double rate;
	private int loanPeriod;
	private double monthlyPayment;
	private String creditGrade;
	public String getCreditGrade() {
		return creditGrade;
	}


	private ArrayList<Payment> payments;
	
	/**
	 * constructor for loan.
	 * @param loanId
	 * @param principal
	 * @param rate
	 * @param loanPeriod
	 * @param monthlyPayment
	 */
	public Loan(int loanId, double principal, double rate, 
			int loanPeriod, String creditGrade) {
		this.loanId = loanId;
		this.principal = principal;
		this.rate = rate;
		this.loanPeriod = loanPeriod;
		this.creditGrade = creditGrade;
		this.payments = new ArrayList<Payment>();
	}
	
	
	// storage for payments.
	public void addPayment(HashMap<String, String> paymentInfo) {
		double monthlyPaymentForPrincipal = Double.parseDouble(paymentInfo.get("monthlyPaymentForPrincipal"));
		double monthlyPaymentForInterest = Double.parseDouble(paymentInfo.get("monthlyPaymentForInterest"));
		double monthlyPaymentTotal = Double.parseDouble(paymentInfo.get("monthlyPaymentTotal"));
		boolean payOrDefault = Boolean.parseBoolean(paymentInfo.get("payOrDefault"));
		double paymentMadeForEachMonth = Double.parseDouble(paymentInfo.get("paymentMadeForEachMonth"));
		int paymentId = payments.size();
		
		Payment payment = new Payment(monthlyPaymentForPrincipal, monthlyPaymentForInterest,
				monthlyPaymentTotal, payOrDefault, paymentMadeForEachMonth, paymentId);
		this.payments.add(payment);
	}
	
	public void updatePaymentForTheMonth(double amount, int index) {
		payments.get(index).setPaymentMadeForEachMonth(amount);
	}
	
	// without index, set update payment for last month.
	public void updatePaymentForTheMonth(double amount) {
		payments.get(payments.size() - 1).setPaymentMadeForEachMonth(amount);
	}
	
	public ArrayList<Payment> getPayments() {
		return payments;
	}
	
	public Payment getPayment(int paymentId) {
		Iterator i = payments.iterator();
		while (i.hasNext()) {
			Payment payment = (Payment) i.next();
			if (paymentId == payment.getPaymentId()) {
				return payment;
			}
		}
		return null;
	}
	
	
	//method to calculate monthly payment
	double mp;
	double mi;	
	
	public double monthlyPayment (double p, double r, int l) {
		double monthlyRate = r / 12;
		double numerator = Math.pow(1 + monthlyRate, l) * monthlyRate * p;
		double denominator = Math.pow(1 + monthlyRate, l) - 1;
		double answer = numerator / denominator;
		monthlyPayment = answer;
		
		//mp = monthlyPaymentForPrincipal;
		//mi = monthlyPaymentForInterest;
		return monthlyPayment;
		
	}
	
	public int getLoanId() {
		return loanId;
	}


	public double getPrincipal() {
		return principal;
	}


	public double getRate() {
		return rate;
	}


	public int getLoanPeriod() {
		return loanPeriod;
	}


	public double getMonthlyPayment() {
		return monthlyPayment;
	}



	public double getMp() {
		return mp;
	}


	public double getMi() {
		return mi;
	}
	
	
	//hashmap for the loan schedule
	/*
	public HashMap<Integer, Payment> loanSchedule(int l, Customer c) {
		boolean payOrDefault = c.decidePayOrNot();
		double payUp = c.howMuchToPay();
		Payment p = new Payment(mp, mi, monthlyPayment, payOrDefault, payUp);
		monthToPayment = new HashMap<Integer, Payment>();
		for (int i = 1; i <= l; i++) {
			monthToPayment.put(i, p);
		}
		return monthToPayment;	
	}
	*/
}
