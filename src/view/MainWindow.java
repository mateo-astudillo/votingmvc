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

    public void dialog(String message) {
        JDialog dialog = new JDialog(this, "", true);
        dialog.setMinimumSize(new Dimension(300, 100));
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("ARIAL", Font.PLAIN, 20));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialog.add(messageLabel);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
