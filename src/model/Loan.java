package model;
/**
 * David
 * 
 */

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loan implements Serializable {
	
	private static final long serialVersionUID = -3405617888750938390L;
	private int loanId;
	private double principal;
	private double rate;
	private int loanPeriod;
	private double monthlyPayment;
	private String creditGrade;
	private double monthlyPaymentForTheLoanRounded;
	private HashMap<Integer, Double> loanSchedule;
	private Date loanStartingDate;
	
	public String getCreditGrade() {
		return creditGrade;
	}
	
	public Loan(int loanId, double principal, double rate, 
			int loanPeriod, String creditGrade, Date today) {
		this.loanId = loanId;
		this.principal = principal;
		this.rate = rate;
		this.loanPeriod = loanPeriod;
		this.creditGrade = creditGrade;
		this.loanStartingDate = today;
	}

	//////// DAVID ////////////////
	/**
	 * takes in double principal, double rate, int loanPeriod to
	 * @return monthly payment for each loan
	 */
	public double monthlyPayment () {  
		
		double monthlyRate = rate / 12;
		double numerator = Math.pow(1 + monthlyRate, loanPeriod) * monthlyRate * principal;
		double denominator = Math.pow(1 + monthlyRate, loanPeriod) - 1;
		double answer = numerator / denominator;
		double monthlyPaymentForTheLoan = answer;		
		DecimalFormat d = new DecimalFormat("#.##");
		String monthlyPaymentForTheLoanStr = d.format(monthlyPaymentForTheLoan);
		double monthlyPaymentForTheLoanRounded = Double.parseDouble(monthlyPaymentForTheLoanStr);
		
		this.monthlyPaymentForTheLoanRounded = monthlyPaymentForTheLoanRounded;
		return monthlyPaymentForTheLoanRounded;
	}
	
	/**
	 * Installment number : incremental number given to each payment in the loan schedule 
	 * 					ex) first payment is given installment number 1, second payment given installment number of 2
	 * 						installment numbers are keys for the hashmap
	 * Monthly principal : for the amount of money paid for each installment number, certain portion of it
	 * 					   (whose ratio changes for each installment number) contributes in paying back the principal borrowed.
	 * 					ex) principal 20000
	 * 						installment number 1 : total payment due : 150
	 * 											   monthly principal : 70
	 * 											   remaining principal : 20000 - 70 = 19930
	 * 					ex) installment number 2 : total payment due : 150
	 * 											   monthly principal : 74
	 * 											   remaining principal : 19930 - 74 = 19856 ...
	 * HashMap ex) key, value
	 * 			     1,    70
	 * 				 2,    74
	 * 				..,   ...,
	 * 				
	 * takes in double principal, double rate, int loanPeriod to calculate monthly payment
	 * @return hashmap that matches each installment number 
	 */
	public HashMap<Integer, Double> mapMonthToPaymentDue () {
		double monthlyRate = rate / 12;
		double numerator = Math.pow(1 + monthlyRate, loanPeriod) * monthlyRate * principal;
		double denominator = Math.pow(1 + monthlyRate, loanPeriod) - 1;
		double answer = numerator / denominator;
		DecimalFormat d = new DecimalFormat("#.00");
		double monthlyPaymentForTheLoanRounded = Double.parseDouble(d.format(answer));	
				
		this.loanSchedule = new HashMap<Integer, Double>();
		int x = 1;
		while (x <= loanPeriod) {						
			double monthlyInterest = principal * (double) rate / 12;
			DecimalFormat df = new DecimalFormat("#.00");
			double monthlyInterestRounded = Double.parseDouble(df.format(monthlyInterest));			
			double monthlyPrincipal = monthlyPaymentForTheLoanRounded - monthlyInterestRounded;			
			loanSchedule.put(x, monthlyPrincipal);
			principal = principal - monthlyPrincipal;			
			x++;					
		}
		return loanSchedule;
	}

	/**
	 * 
	 * @param chosenDate
	 * @param 
	 * @return installment number that corresponds to the date that user has chosen(chosenDate)
	 */
	public int calcInstallNumFrDateSelected(String chosenDate) {
		
				
		String pattern = "yyyyMM";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String currentDate = simpleDateFormat.format(loanStartingDate);
		
		Pattern year = Pattern.compile("^\\d{4}");
		Integer.parseInt(chosenDate);
		Matcher m = year.matcher(currentDate); 
		Matcher n = year.matcher(chosenDate);  

		int installNum = 0;
		int monthDifference = 0;
		int yearToMonths = 0;
		
		int mMonth = 0;
		int mYear = 0;
		if (m.find()) {
			//extract month from currentDate
			String tempNumber = m.group(); //year extracted(String)
			mYear = Integer.parseInt(tempNumber);
			mMonth = Integer.parseInt(currentDate) - mYear * 100;	
		}
		System.out.println("year of current date is " + mYear);

		int nMonth = 0;
		int nYear = 0;
		if (n.find()) {
			//extract month from chosenDate
			String tempNumber = n.group();
			nYear = Integer.parseInt(tempNumber);
			nMonth = Integer.parseInt(chosenDate) - nYear * 100;								
		}

		int yearDifference = nYear - mYear;
		monthDifference = nMonth - mMonth;
		yearToMonths = yearDifference * 12;	
		installNum = monthDifference + yearToMonths;
		System.out.println("diff in year converted to months is " + yearToMonths);
		System.out.println("diff in months is " + monthDifference);
		
		return installNum;
	}

	
	
	/**
	 * 
	 * @param installmentNumber 
	 * @param amountToPay: total amount assumed to have been paid back for the past (installmentNumber-1) installments
	 * 					   (input by the user)
	 * 		  monthlyPaymentForTheLoanRounded: amount to be paid for each month
	 * calculates principal amount left to pay after the installmentNumber that the user has chosen
	 * returns messages for various cases
	 */
	double maximum = 0;
	double futurePrincipalDue = 0;
	double futureInterestDue = 0;
	
	public String amountDue (int installmentNumber, double amountWillingToPay) { 
		int nextinstallmentNumber = installmentNumber + 1;
		double totalAmtDueForPayback = installmentNumber * monthlyPaymentForTheLoanRounded;

		/**
		 * Case 1: when user inputs amount(amountWillingToPay) that is greater than 
		 * 		   the total amount due for the whole loan period
		 * return message 1
		 */
		String message1 = null;
		
		/**
		 * Case 2: when user inputs amount(amountWillingToPay) that is greater than 
		 * 		   the total amount due for the selected installment number but is
		 * 		   less than the total amount due for the whole loan period
		 * return message 2
		 */
		String message2 = null;
		
		/**
		 * Case 3: when user inputs amount(amountWillingToPay) that is less than 
		 * 		   the total amount due for the selected installment number
		 * return message 3
		 */
		String message3 = null;
		
		if (totalAmtDueForPayback <= amountWillingToPay) { 
			
			/* Detailed explanation for the term : <amtPaidBackforFutureInst>
			 * Assume that it's possible for the user to pay back early.
			 * amtPaidBackforFutureInst = if user was willing to pay more than what was necessary for that
			 * installment date, we assume that the difference between those two numbers, amtPaidBackforFutureInst,
			 * is used to pay back for future PRINCIPAL amount, but not the interest
			 * (interest have not been accrued yet, so there is no interest that the user can pay back currently
			 * for FUTURE installments. Hence, amtPaidBackforFutureInst can only contribute in 
			 * paying back for the principal. Note that for every installment, the principal has to be paid back in full.
			 * IOW, partial pay back of principal for any of the installment is not possible.
			 * 
			 */
			double amtToBePaidBackEarly = amountWillingToPay - totalAmtDueForPayback; 
			
			double tempSum = 0;
			
			for (int i = 1; i <= loanPeriod; i++) {
				if (installmentNumber < i) { 
					tempSum += loanSchedule.get(i);
				}
			}
			if (tempSum < amtToBePaidBackEarly) { 
				message1 = "You will have paid back all the principal that you are entitled to pay during " +
						   " 0 to " + loanPeriod + " installments.";
				return message1;
			}
			/*
			 * if the amt of money user wishes to pay back are less than all the principal due for 
			 * installments that are subsequent to the one that user has chosen
			 */
			else { 
				double sumOfPrinPaidBackEarly = 0;;
				int lastInstallmentNum = 0;
				int count = 0;	
				for (int i = 1; i <= loanPeriod; i++) {
					if (installmentNumber < i) {					
						while (amtToBePaidBackEarly > loanSchedule.get(i)) {
							amtToBePaidBackEarly = amtToBePaidBackEarly - loanSchedule.get(i);	
							sumOfPrinPaidBackEarly += loanSchedule.get(i);
							i++;
							count++;
						}
						lastInstallmentNum = installmentNumber + count;
					}
				}
				message2 = "You have paid back all the payment due for " + installmentNumber +
						   " installments. You will also have paid back $" + sumOfPrinPaidBackEarly + " during " + 
							nextinstallmentNumber + " to " + lastInstallmentNum + " installments.";
				return message2;
			}	
		}
		else {
			message3 = "You have paid " + amountWillingToPay + " out of " + new DecimalFormat("#.00").format(totalAmtDueForPayback) + " that is due.";
			return message3;
		}
	}	
		
	//////// DAVID////////
	
	public double monthlyPayment (double p, double r, int l) {
		double monthlyRate = r / 12;
		double numerator = Math.pow(1 + monthlyRate, l) * monthlyRate * p;
		double denominator = Math.pow(1 + monthlyRate, l) - 1;
		double answer = numerator / denominator;
		monthlyPayment = answer;
		
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
	

	public Date getLoanStartingDate() {
		return loanStartingDate;
	}

	
	public void setLoanStartingDate(Date loanStartingDate) {
		this.loanStartingDate = loanStartingDate;
	}

}	
//	public static void main(String[] args) {
//		int loanId = 1234;
//		double principal = 20000;
//		double rate = 0.075;
//		int loanPeriod = 60;
//		String creditGrade = "A";
//		Date today = new Date();
//		Loan k = new Loan(loanId, principal, rate, loanPeriod, creditGrade, today);
//		
//		System.out.println("starting date is " + k.getLoanStartingDate());
//		double actual = k.monthlyPayment(20000, 0.075, 60);		
//		System.out.println(actual);
//		
//		HashMap<Integer, Double > testMap = k.mapMonthToPaymentDue();
//		
////		int number = k.calcInstallNumFrDateSelected("202001");
//		int number = 3;
//		System.out.println("installment number is " + number);
//		
//		k.amountDue(3, 2000);
//	}
	

