import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LoanApplication {
	private int loanApplicationId;
	private int customerId;
	private double loanAmount;
	private int loanDuration;
	private String reasonForApplying;
	private Date applicationDate;
	private double creditCardSpendingThisMonth;
	private Date lastDefaultDate;
	private double allocatedCreditLimit;
	private Date firstCreditCardDate;
	private int inquireBorrowingEligibilityTimes;

	public LoanApplication() {

	}

	public LoanApplication(HashMap loanApplicationDetail, int userId) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		// required info
		if (loanApplicationDetail.containsKey("loanAmount")) {
			this.loanAmount = Double.parseDouble((String) loanApplicationDetail.get("loanAmount"));
		}

		// required info
		if (loanApplicationDetail.containsKey("loanDuration")) {
			this.loanDuration = Integer.parseInt((String) loanApplicationDetail.get("loanDuration"));
		}

		if (loanApplicationDetail.containsKey("reasonForApplying")) {
			this.reasonForApplying = (String) loanApplicationDetail.get("reasonForApplying");
		}

		if (loanApplicationDetail.containsKey("creditCardSpendingThisMonth")) {
			this.creditCardSpendingThisMonth = Double
					.parseDouble((String) loanApplicationDetail.get("creditCardSpendingThisMonth"));
		}

		if (loanApplicationDetail.containsKey("lastDefaultDate")) {
			try {
				this.lastDefaultDate = sdf.parse((String) loanApplicationDetail.get("lastDefaultDate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (loanApplicationDetail.containsKey("allocatedCreditLimit")) {
			this.allocatedCreditLimit = Double.parseDouble((String) loanApplicationDetail.get("allocatedCreditLimit"));
		}

		if (loanApplicationDetail.containsKey("firstCreditCardDate")) {
			try {
				this.firstCreditCardDate = sdf.parse((String) loanApplicationDetail.get("firstCreditCardDate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (loanApplicationDetail.containsKey("inquireBorrowingEligibilityTimes")) {
			this.inquireBorrowingEligibilityTimes = Integer
					.parseInt((String) loanApplicationDetail.get("inquireBorrowingEligibilityTimes"));
		}

		this.applicationDate = new Date();
		this.customerId = userId;
		this.loanApplicationId = 1;
	}

	/**
	 * get loan application id
	 * 
	 * @return
	 */
	public int getLoanApplicationId() {
		return loanApplicationId;
	}

	/**
	 * get customer id associated with this loan aplication.
	 * 
	 * @return
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * get loan amount
	 * 
	 * @return
	 */
	public double getLoanAmount() {
		return loanAmount;
	}

	/**
	 * get loan duration
	 * 
	 * @return
	 */
	public int getLoanDuration() {
		return loanDuration;
	}

	/**
	 * get reason for applying.
	 * 
	 * @return
	 */
	public String getReasonForApplying() {
		return reasonForApplying;
	}

	/**
	 * get application date.
	 * 
	 * @return
	 */
	public Date getApplicationDate() {
		return applicationDate;
	}

	/**
	 * get credit card spending of this month. Used in credit scoring.
	 * 
	 * @return
	 */
	public double getCreditCardSpendingThisMonth() {
		return creditCardSpendingThisMonth;
	}

	/**
	 * get last default date of customer.
	 * 
	 * @return
	 */
	public Date getLastDefaultDate() {
		return lastDefaultDate;
	}

	/**
	 * get allocated credit limit of the credit card.
	 * 
	 * @return
	 */
	public double getAllocatedCreditLimit() {
		return allocatedCreditLimit;
	}

	/**
	 * get first date of getting credit card.
	 * 
	 * @return
	 */
	public Date getFirstCreditCardDate() {
		return firstCreditCardDate;
	}

	/**
	 * get how many times has customer enquire about eligibility to obtain a loan.
	 * 
	 * @return
	 */
	public int getInquireBorrowingEligibilityTimes() {
		return inquireBorrowingEligibilityTimes;
	}

}
