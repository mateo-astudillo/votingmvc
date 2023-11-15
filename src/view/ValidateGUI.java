package view;

import model.person.Person;

import javax.swing.*;

public class ValidateGUI {
    private JPanel validateGUIPanel;
    private JLabel validateVoterLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel genderLabel;
    private JLabel firstNameValueLabel;
    private JLabel lastNameValueLabel;
    private JLabel genderValueLabel;
    private JTextField documentTextField;
    private JButton getPersonButton;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel documentLabel;

    public ValidateGUI() {
        confirmButton.setVisible(false);
        cancelButton.setVisible(false);

        getPersonButton.addActionListener(actionEvent -> {
            Person person = new Person();
            person.setDocument( Integer.parseInt( this.documentTextField.getText() ) );
            this.firstNameValueLabel.setText(person.getFirstName());
            this.lastNameValueLabel.setText(person.getLastName());
            this.genderValueLabel.setText(person.getGender());
            confirmButton.setVisible(true);
            cancelButton.setVisible(true);
        });
    }

    public JPanel getValidateGUIPanel() {
        return validateGUIPanel;
    }
}
