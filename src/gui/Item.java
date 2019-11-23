package gui;

public class Item {
	private String label;
	private int value;
	
	public Item(String label, int value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getLabel();
	}
	
	
}
