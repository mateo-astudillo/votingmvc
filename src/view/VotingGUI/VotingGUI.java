package view.VotingGUI;

import controller.Controller;
import model.candidate.Candidate;
import model.contest.Contest;

import javax.swing.*;
import java.awt.*;

public class VotingGUI extends JPanel {
    private final Controller controller;
    private final ButtonGroup contestGroup;

    public VotingGUI(Controller controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        contestGroup = new ButtonGroup();
        Button vote = new Button("Votar");
        add(new JLabel("Candidatos"));
        addContests();
        add(vote);

        vote.addActionListener(e -> {
            String actionCommand = contestGroup.getSelection().getActionCommand();
            if (actionCommand.isEmpty()) {
                return;
            }
            int listNumber = Integer.parseInt(actionCommand);
            controller.vote(listNumber);
        });
    }

    private void addContests() {
        for (Contest contest : controller.getContests()) {
            JPanel contestPanel = new JPanel();
            JRadioButton contestButton = new JRadioButton();
            contestButton.setActionCommand(String.valueOf(contest.getId()));
            contestGroup.add(contestButton);
            for (Candidate candidate : contest.getCandidates()) {
                JLabel candidateLabel = new JLabel(candidate.getLastName() + " " + candidate.getFirstName());
                contestPanel.add(candidateLabel);
            }
            contestPanel.add(contestButton);
            add(contestPanel);
            contestPanel.setVisible(true);
        }
    }
}
