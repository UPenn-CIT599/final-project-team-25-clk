package eventobject;

import java.util.EventObject;

public class LoanApplicationForm extends EventObject {
	private String loanApplicationAmount;
	private String loanApplicationDuration;
	private String reason;
	
	public LoanApplicationForm(Object source, String loanApplicationAmount, String loanApplicationDuration, String reason) {
		super(source);
		this.loanApplicationAmount = loanApplicationAmount;
		this.loanApplicationDuration = loanApplicationDuration;
		this.reason = reason;
	}

	public String getLoanApplicationAmount() {
		return loanApplicationAmount;
	}

	public String getLoanApplicationDuration() {
		return loanApplicationDuration;
	}

	public String getReason() {
		return reason;
	}
}
