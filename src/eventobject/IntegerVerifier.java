package eventobject;

import java.awt.Container;
import java.math.BigDecimal;
import java.util.regex.Pattern;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class IntegerVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
        	double d = Double.parseDouble(text); 
        	return true;
        } catch (NumberFormatException e) {
        	Container c = (Container) input.getParent();
        	JOptionPane.showMessageDialog(c, "Must input a number." );
            return false;
        }
    }
}