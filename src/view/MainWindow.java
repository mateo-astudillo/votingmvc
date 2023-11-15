package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JPanel panel;
    private CardLayout cardLayout;
    public MainWindow() {
        setTitle("Voting System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        Login login = new Login();
        ValidateGUI validateGUI = new ValidateGUI();

        panel.add(login.getLoginPanel(), "login");
        panel.add(validateGUI.getValidateGUIPanel(), "validatePerson");

        add(panel);
        pack();
    }

    public void go(String page) {
        cardLayout.show(panel, page);
    }
}
