package eventobject;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

class IntegerVerifierTest {

	@Test
	void testVerifyNumbers() {
		IntegerVerifier test = new IntegerVerifier();
		JTextField text = new JTextField();
		text.setText("123");
		
		assertEquals(true, test.verify(text));
	}
	
	@Test
	void testVerifyLettersJComponent() {
		IntegerVerifier test = new IntegerVerifier();
		JTextField text = new JTextField();
		text.setText("abc");
		assertEquals(false, test.verify(text));
	}

	
}
