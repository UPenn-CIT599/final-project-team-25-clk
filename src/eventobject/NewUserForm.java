package eventobject;

import java.util.EventObject;

public class NewUserForm extends EventObject {
	private String name;
	private String occupation;
	private String annualIncome;
	
	public NewUserForm(Object source, String name, String occupation, String annualIncome) {
		super(source);
		this.name = name;
		this.occupation = occupation;
		this.annualIncome = annualIncome;
	}

	public String getName() {
		return name;
	}

	public String getOccupation() {
		return occupation;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

}
