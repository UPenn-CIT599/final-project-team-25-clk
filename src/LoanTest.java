import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;

import model.Loan;

class LoanTest {

	@Test
	void test() {
		Loan s = new Loan();
		double f = s.monthlyPayment(20000, 0.075, 60);
		DecimalFormat df = new DecimalFormat("##.00");
		assertEquals(df.format(f), "400.76");
	}

}
