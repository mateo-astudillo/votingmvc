package view;

import javax.swing.*;

public class Login {
    private JLabel openTableLabel;
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public Login() {
        loginButton.addActionListener(actionEvent -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
        });
    }

    public JPanel getLoginPanel() {
        return this.loginPanel;
    }
}
