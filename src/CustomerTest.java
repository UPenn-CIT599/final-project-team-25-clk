

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Customer;
import model.Loan;
import model.LoanApplication;

class CustomerTest {

	@Test
	void testAddLoanApplication() {
		Customer c = new Customer(0, "Jack", "Software Engineer");
		c.addLoanApplication(1000, 12, 1, 12000, 1000, 50, 600, 10, 20, 5);
		LoanApplication la = c.getLoanApplicationById(0);
		assertEquals(1000, la.getLoanAmount());
		assertEquals(12, la.getLoanDuration());
		assertEquals(1, la.getPubRec());
		assertEquals(12000, la.getRevol_bal());
		assertEquals(1000, la.getTotal_rev_hi_lim());
		assertEquals(50, la.getMo_sin_old_rev_tl_op());
		assertEquals(600, la.getInq_last_6mths());
		assertEquals(10, la.getNum_actv_bc_tl());
		assertEquals(20, la.getNum_actv_rev_tl());
	}

	@Test
	void testGetLastLoanApplication() {
		Customer c = new Customer(0, "Jack", "Software Engineer");
		LoanApplication la = c.addLoanApplication(1000, 12, 1, 12000, 1000, 50, 600, 10, 20, 5);
		
		assertEquals(la, c.getLastLoanApplication());
	}

	@Test
	void testAddLoan() {
		Customer c = new Customer(0, "Jack", "Software Engineer");
		c.addLoan(1000, 12, 24, "A");
		Loan l = c.getLoan(0);
		assertEquals(1000, l.getPrincipal());
		assertEquals(12, l.getRate());
		assertEquals(24, l.getLoanPeriod());
		assertEquals("A", l.getCreditGrade());
	}

}
