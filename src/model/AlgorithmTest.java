package model;
import static org.junit.Assert.*;

import org.junit.Test;

public class AlgorithmTest {

	Algorithm algo = new Algorithm();
	int pubRec = 2;
	double revol_bal = 2000.0; //user's current maximum revolving credit
	double total_rev_hi_lim = 10000.0; // user's allocated revolving credit limit
	int mo_sin_old_rev_tl_op = 48; //number of months since the user's creation of revolving credit 
	int inq_last_6mths = 2; //how many inquiries the user has made for borrowing in the last 6 months
	int num_actv_bc_tl = 1; //number of active check cards
	int num_actv_rev_tl = 1; //number of active credit cards
	int open_act_il = 1; //number of active borrowing (loan) accounts


	@Test
	public void testPaymentHistoryScore() {
		assertEquals(220, algo.paymentHistoryScore(pubRec),0);
	}

	@Test
	public void testAmountsOwedScore() {
		assertEquals(770, algo.amountsOwedScore(revol_bal, total_rev_hi_lim), 0);
	}

	@Test
	public void testCreditHistoryScore() {
		assertEquals(760, algo.creditHistoryScore(mo_sin_old_rev_tl_op),0);
	}

	@Test
	public void testPursuitofNewCreditScore() {
		assertEquals(450, algo.pursuitofNewCreditScore(inq_last_6mths),0);
	}

	@Test
	public void testCreditMixScore() {
		assertEquals(250, algo.creditMixScore(num_actv_bc_tl, num_actv_rev_tl, open_act_il),0);
	}


}


