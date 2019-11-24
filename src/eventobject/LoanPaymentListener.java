package eventobject;
import java.util.EventListener;

public interface LoanPaymentListener extends EventListener {
	public void loanPaymentOccured(double amount);
}

