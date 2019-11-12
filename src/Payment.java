
public class Payment {
	
	private double monthlyPaymentForPrincipal;
	private double monthlyPaymentForInterest;
	private double monthlyPaymentTotal;
	private boolean payOrDefault;
	private double paymentMadeForEachMonth;
	
	
	//method to get totalmonthlyPayment
	double total;
	public double totalPayment(double mp, double mi) {
		total = mp + mi;
		return total;
	}
		
	//construct Payment
	public Payment(double mp, double mi, double t, boolean pd, double pem) {
		this.monthlyPaymentForPrincipal = mp;
		this.monthlyPaymentForInterest = mi;
		this.monthlyPaymentTotal = t;
		this.payOrDefault = pd;
		this.paymentMadeForEachMonth = pem;
	}

	/**
	 * get monthly payment for principal
	 * @return
	 */
	public double getMonthlyPaymentForPrincipal() {
		return monthlyPaymentForPrincipal;
	}

	/**
	 * get monthly payment for interest.
	 * @return
	 */
	public double getMonthlyPaymentForInterest() {
		return monthlyPaymentForInterest;
	}

	/**
	 * get monthly payment total.
	 * @return
	 */
	public double getMonthlyPaymentTotal() {
		return monthlyPaymentTotal;
	}

	/**
	 * return get pay or default.
	 * @return
	 */
	public boolean getPayOrDefault() {
		return payOrDefault;
	}

	/**
	 * get payment made for each month
	 * @return
	 */
	public double getPaymentMadeForEachMonth() {
		return paymentMadeForEachMonth;
	}
	
	
}
