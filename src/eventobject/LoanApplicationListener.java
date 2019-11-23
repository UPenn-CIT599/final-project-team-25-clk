package eventobject;
import java.util.EventListener;

public interface LoanApplicationListener extends EventListener {
	public void formEventOccurred(LoanApplicationForm e);
}