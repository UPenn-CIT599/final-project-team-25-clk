import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.LoanApplication;

class CustomerTest {

	@Test
	void testApplyLoan() {
		// testing of customer applying for a loan.
		Customer customer1 = new Customer();
		String loanAmount = "100000";
		String loanDuration = "36";
		String reasonForApplying = "buying a house";
		String creditCardSpendingThisMonth = "1500";
		String lastDefaultDate = "12/04/2014";
		String allocatedCreditLimit = "10000";
		String firstCreditCardDate = "01/03/2006";
		String inquireBorrowingEligibilityTimes = "0";
		customer1.applyLoan(loanAmount, loanDuration, reasonForApplying, creditCardSpendingThisMonth, lastDefaultDate,
				allocatedCreditLimit, firstCreditCardDate, inquireBorrowingEligibilityTimes);

		LoanApplication loanApplication = customer1.getLoanApplication();

		Assertions.assertEquals(1, loanApplication.getCustomerId());
		Assertions.assertEquals(100000, loanApplication.getLoanAmount());
		Assertions.assertEquals(36, loanApplication.getLoanDuration());
		Assertions.assertEquals("buying a house", loanApplication.getReasonForApplying());
		Assertions.assertEquals(1500, loanApplication.getCreditCardSpendingThisMonth());
		try {
			Date lastDefaultDate2 = new SimpleDateFormat("dd/MM/yyyy").parse("12/04/2014");
			Assertions.assertEquals(lastDefaultDate2, loanApplication.getLastDefaultDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Assertions.assertEquals(10000, loanApplication.getAllocatedCreditLimit());
		try {
			Date firstCreditCardDate2 = new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2006");
			Assertions.assertEquals(firstCreditCardDate2, loanApplication.getFirstCreditCardDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Assertions.assertEquals(0, loanApplication.getInquireBorrowingEligibilityTimes());
	}

	@Test
	void testApplyLoanWithoutRequiredInfo() {
		Customer customer1 = new Customer();
		String loanAmount = ""; // mising loan amount application.
		String loanDuration = "36";
		String reasonForApplying = "buying a house";
		String creditCardSpendingThisMonth = "1500";
		String lastDefaultDate = "12/04/2014";
		String allocatedCreditLimit = "10000";
		String firstCreditCardDate = "01/03/2006";
		String inquireBorrowingEligibilityTimes = "0";

		customer1.applyLoan(loanAmount, loanDuration, reasonForApplying, creditCardSpendingThisMonth, lastDefaultDate,
				allocatedCreditLimit, firstCreditCardDate, inquireBorrowingEligibilityTimes);

		Assertions.assertEquals(null, customer1.getLoanApplication());

	}

}
