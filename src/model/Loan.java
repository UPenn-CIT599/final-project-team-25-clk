package model;
import java.text.DecimalFormat;
import java.util.HashMap;

import Payment;

public class Loan {
	
	private int loanId;
	private double principal;
	private double rate;
	private int loanPeriod;
	private double monthlyPayment;
	
	private int customerId;
	
	public Loan(int loanId, double principal, double rate, 
			int loanPeriod, double monthlyPayment, int customerId) {
		this.loanId = loanId;
		this.principal = principal;
		this.rate = rate;
		this.loanPeriod = loanPeriod;
		this.monthlyPayment = monthlyPayment;
		this.customerId = customerId;
	}
	
	//private Customer c;
	//private Payment p;
	//private HashMap<Integer, Payment> monthToPayment;
	
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


	public int getCustomerId() {
		return customerId;
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
