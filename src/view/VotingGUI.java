package view;

import model.candidate.Candidate;

import javax.swing.*;
import java.util.ArrayList;

public class VotingGUI {
    private JPanel votingPanel;
    private JPanel candidatesPanel;
    private JLabel candidateLabel;
    private JLabel officeValueLabel;
    public void addCandidates(ArrayList<Candidate> candidates) {
        for (Candidate c : candidates) {
            JPanel candidatePanel = new JPanel();
            candidatePanel.setAlignmentX(SwingConstants.RIGHT);
            JLabel firstNameLabel = new JLabel(c.getFirstName());
            JLabel lastNameLabel = new JLabel(c.getLastName());
            JLabel officeLabel = new JLabel(c.getOffice());
            JLabel contestLabel= new JLabel(c.getFirstName());
            candidatePanel.add(firstNameLabel);
            candidatePanel.add(lastNameLabel);
            candidatePanel.add(officeLabel);
            candidatePanel.add(contestLabel);
        }
    }
}
