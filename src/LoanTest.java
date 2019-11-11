import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoanTest {

	@Test
	void testCalculateTotalInterest() {
		Loan loan = new Loan(1, 1, 0.1, 100000, 60, "1/1/2017");
		Assertions.assertEquals(50000, loan.calculateTotalInterest());
	}

	@Test
	void testMonthsRemainingTo() {
		Loan loan = new Loan(1, 1, 0.1, 100000, 60, "1/1/2017");
		Assertions.assertEquals(26, loan.monthsRemainingToMaturity());
	}

}
