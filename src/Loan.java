import java.text.DecimalFormat;
import java.util.HashMap;

public class Loan {

	private double principal;
	private double rate;
	private int loanPeriod;
	private double monthlyPayment;
	
	private int loanId;
	private int customerId;
	
	private Customer c;
	
	private Payment p;
	private HashMap<Integer, Payment> monthToPayment;
	
	//method to calculate monthly payment
	double mp;
	double mi;	
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
	
	//hashmap for the loan schedule
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
	
	public static void main(String[] args) {
		Loan a = new Loan();
		DecimalFormat df = new DecimalFormat("##.00");
		System.out.println(df.format(a.monthlyPayment(20000, 0.075, 60)));
	}
}
