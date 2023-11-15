package view;

import controller.Controller;
import model.person.Person;

import javax.swing.*;

public class ValidateGUI {
    private JPanel validateGUIPanel;
    private JLabel validateVoterLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel birthdateLabel;
    private JLabel firstNameValueLabel;
    private JLabel lastNameValueLabel;
    private JLabel birthdateValueLabel;
    private JTextField documentTextField;
    private JButton getPersonButton;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel documentLabel;
    private JButton closeTableButton;
    private JLabel genderValueLabel;
    private JLabel genderLabel;
    private JLabel addressLabel;
    private JLabel addressValueLabel;
    private Person person;

    public ValidateGUI(Controller controller) {

        getPersonButton.addActionListener(actionEvent -> {
            int document = Integer.parseInt( this.documentTextField.getText() );
            if (!controller.isValid(document)) {
                return;
            }
            person = controller.getPerson(document);
            this.firstNameValueLabel.setText(person.getFirstName());
            this.lastNameValueLabel.setText(person.getLastName());
            this.birthdateValueLabel.setText(person.getBirthdate().toString());
            this.addressValueLabel.setText(person.getAddress());
            this.genderValueLabel.setText(person.getGender());
        });

        confirmButton.addActionListener(actionEvent -> {
            this.clearFields();
            controller.vote(this.person);
        });
    }

    public JPanel getValidateGUIPanel() {
        return validateGUIPanel;
    }

    private void clearFields() {
        this.documentTextField.setText("");
        this.firstNameValueLabel.setText("");
        this.lastNameValueLabel.setText("");
        this.birthdateValueLabel.setText("");
        this.genderValueLabel.setText("");
        this.addressValueLabel.setText("");
    }
}
