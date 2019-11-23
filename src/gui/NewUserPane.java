package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eventobject.NewUserForm;
import eventobject.NewUserFormListener;

public class NewUserPane extends JPanel {
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JLabel annualIncomeLabel;

	private JTextField nameField;
	private JTextField occupationField;
	private JTextField annualIncomeField;
	private JButton submit;
	private NewUserFormListener formListener;

	public NewUserPane() {
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		annualIncomeLabel = new JLabel("Annual Income: ");

		nameField = new JTextField(20);
		occupationField = new JTextField(20);
		annualIncomeField = new JTextField(20);

		submit = new JButton("submit");

		submit.addActionListener(new ActionListener() {	

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				String annualIncome = annualIncomeField.getText();

				NewUserForm ev = new NewUserForm(this, name, occupation, annualIncome);

				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});
		
		layoutComponent();
	}

	public void layoutComponent() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		//////////// next row /////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(nameLabel, gc);

		// coordinate (1,0)
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);
		
		///////////// next row /////////////////
		gc.gridy++;

		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(annualIncomeLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(annualIncomeField, gc);
		
		/////////// next row //////////////////
		
		gc.weightx = 1;
		gc.weighty = 2.0; 
		
		gc.gridy++;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(submit, gc);		
		
		
	}

	public void setFormListener(NewUserFormListener formListener) {
		this.formListener = formListener;
	}
}
