import controller.Controller;
import model.DBConnection.DBConnection;
import model.candidate.Candidate;
import model.candidate.CandidateDAO;
import model.contest.Contest;
import model.contest.ContestDAO;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start");

        new Controller();

    }
}