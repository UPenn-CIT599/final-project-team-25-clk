package model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class LoanTest {

	@Test
	void monthlyPaymentTest() {
		int loanId = 1234;
		double principal = 20000;
		double rate = 0.075;
		int loanPeriod = 60;
		String creditGrade = "A";
		Date today = new Date(201912);
		Loan testLoan = new Loan(loanId, principal, rate, loanPeriod, creditGrade, today);
		double actual = testLoan.monthlyPayment(20000, 0.075, 60);
		DecimalFormat d = new DecimalFormat("0.00");
		String monthlyPaymentForTheLoanStr = d.format(actual);
		double monthlyPaymentForTheLoanRounded = Double.parseDouble(monthlyPaymentForTheLoanStr);
		assertEquals(400.76, monthlyPaymentForTheLoanRounded);
	}
	
	@Test
	void mapMonthToPaymentDue() {
		int loanId = 1234;
		double principal = 20000;
		double rate = 0.075;
		int loanPeriod = 60;
		String creditGrade = "A";
		Date today = new Date(201912);
		Loan testLoan = new Loan(loanId, principal, rate, loanPeriod, creditGrade, today);
		HashMap<Integer, Double > actual = testLoan.mapMonthToPaymentDue();
		double firstPayment = actual.get(1);
		DecimalFormat d = new DecimalFormat("0.00");
		String monthlyPaymentForTheLoanStr = d.format(firstPayment);
		double monthlyPaymentForTheLoanRounded = Double.parseDouble(monthlyPaymentForTheLoanStr);
		assertEquals(275.76, monthlyPaymentForTheLoanRounded);
	}
	
	@Test
	void calcInstallNumFrDateSelected() {
		String chosenDateString = "202001";
		int loanId = 1234;
		double principal = 20000;
		double rate = 0.075;
		int loanPeriod = 60;
		String creditGrade = "A";
		Date today = new Date();
		Loan testLoan = new Loan(loanId, principal, rate, loanPeriod, creditGrade, today);
		testLoan.setLoanStartingDate(today);
		int number = testLoan.calcInstallNumFrDateSelected(chosenDateString);
		assertEquals(1, number);
	}
	
	@Test
	void amountDue () { //i need loanPeriod to be 60;
		int loanId = 1234;
		double principal = 20000;
		double rate = 0.075;
		int loanPeriod = 60;
		String creditGrade = "A";
		Date today = new Date(201912);
		Loan testLoan = new Loan(loanId, principal, rate, loanPeriod, creditGrade, today);
		
		double actual = testLoan.monthlyPayment(20000, 0.075, 60);
		DecimalFormat d = new DecimalFormat("0.00");
		String monthlyPaymentForTheLoanStr = d.format(actual);
		double monthlyPaymentForTheLoanRounded = Double.parseDouble(monthlyPaymentForTheLoanStr);
		
		HashMap<Integer, Double > testMap = testLoan.mapMonthToPaymentDue();
		
		int installmentNumber = 3;
		String testMessage = testLoan.amountDue(installmentNumber, 30000);
		assertEquals("You will have paid back all the principal that you are entitled to pay during " + 
					 " 0 to " + loanPeriod + " installments.", testMessage);
	}
	
}
