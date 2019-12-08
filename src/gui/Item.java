package gui;

/**
 * item class is for inserting as item in combo boxes in swing.
 *
 */
public class Item {
	private String label;
	private int value;
	
	/**
	 * item class consisting of label and value.
	 * @param label
	 * @param value
	 */
	public Item(String label, int value) {
		this.label = label;
		this.value = value;
	}

	/**
	 * getting label for item.
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * getting value for item.
	 * @return
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getLabel();
	}
	
	
}
