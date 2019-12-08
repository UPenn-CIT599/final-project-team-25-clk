package model;
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
	
	/**
	 * constructor for loan.
	 * @param loanId
	 * @param principal
	 * @param rate
	 * @param loanPeriod
	 * @param monthlyPayment
	 */
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
	
	public double monthlyPayment () {  //double principal, double rate, int loanPeriod
		
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
	 * @param currentDate
	 * @param 
	 * @return installment number (number in the sequence between 1st payment and last payment)
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
		
		return installNum;
	}

	
	
	/**
	 * 
	 * @param installmentNumber 
	 * @param amountToPay: total amount assumed to have been paid back for the past (installmentNumber-1) installments
	 * @param monthlyPaymentForTheLoanRounded: amount to be paid for each month
	 * @return principal amount left to pay
	 */
	double maximum = 0;
	double futurePrincipalDue = 0;
	double futureInterestDue = 0;
	
	public String amountDue (int installmentNumber, double amountWillingToPay) { 
		int nextinstallmentNumber = installmentNumber + 1;
		double totalAmtDueForPayback = installmentNumber * monthlyPaymentForTheLoanRounded;

		String message1 = null;
		String message2 = null;
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
