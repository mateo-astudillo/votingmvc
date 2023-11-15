package view;

import controller.Controller;

import javax.swing.*;

public class Login {
    private JLabel openTableLabel;
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public Login(Controller controller) {
        loginButton.addActionListener(actionEvent -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            this.clearFields();
            if ( username.length() != 6 || password.length() != 6 ) return;
            controller.login(username, password);
        });
    }

    public JPanel getLoginPanel() {
        return this.loginPanel;
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
}
