package view.VotingGUI;

import controller.Controller;
import model.candidate.Candidate;
import model.contest.Contest;

import javax.swing.*;
import java.awt.*;

public class VotingGUI extends JPanel {
    private final Controller controller;
    private final ButtonGroup contestGroup;
    private final Button vote;

    public VotingGUI(Controller controller) {
        this.controller = controller;
        contestGroup = new ButtonGroup();
        vote = new Button("Votar");
        add(new JLabel("Candidatos"));
        addContests();
        add(vote);

        vote.addActionListener(e -> {
            int listNumber = Integer.parseInt(contestGroup.getSelection().getActionCommand());
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
