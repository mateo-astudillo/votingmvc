package view;

import controller.Controller;
import view.VotingGUI.VotingGUI;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JPanel panel;
    private final CardLayout cardLayout;
    public MainWindow(Controller controller) {
        setTitle("Voting System");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        Login login = new Login(controller);
        ValidateGUI validateGUI = new ValidateGUI(controller);
        VotingGUI votingGUI = new VotingGUI(controller);

        panel.add(login.getLoginPanel(), "login");
        panel.add(validateGUI.getValidateGUIPanel(), "main");
        panel.add(votingGUI, "voting");

        add(panel);
        pack();
    }

    public void go(String page) {
        cardLayout.show(panel, page);
    }
}
