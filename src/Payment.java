
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

	public double getMonthlyPaymentForPrincipal() {
		return monthlyPaymentForPrincipal;
	}

	public double getMonthlyPaymentForInterest() {
		return monthlyPaymentForInterest;
	}

	public double getMonthlyPaymentTotal() {
		return monthlyPaymentTotal;
	}

	public boolean getPayOrDefault() {
		return payOrDefault;
	}

	public double getPaymentMadeForEachMonth() {
		return paymentMadeForEachMonth;
	}
	
	
}
