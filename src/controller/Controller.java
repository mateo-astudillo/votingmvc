package controller;

import model.candidate.Candidate;
import model.candidate.CandidateDAO;
import model.contest.Contest;
import model.contest.ContestDAO;
import model.electionClerk.ElectionClerk;
import model.electionClerk.ElectionClerkDAO;
import model.incidence.Incidence;
import model.incidence.IncidenceDAO;
import model.incidence.IncidenceType;
import model.person.Person;
import model.person.PersonDAO;
import model.vote.Vote;
import model.vote.VoteDAO;
import view.MainWindow;

import java.time.LocalTime;
import java.util.ArrayList;

public class Controller {
    private final ElectionClerk electionClerk;
    private Person currentPerson;
    private final MainWindow window;
    public Controller() {
        electionClerk = new ElectionClerk();
        window = new MainWindow(this);
        window.setVisible(true);
    }

    public void login(String username, String password) {
        int id = ElectionClerkDAO.validate(username, password);
        this.electionClerk.setId(id);
        LocalTime now = LocalTime.now();
        if (id == 0) {
            Incidence incidence = new Incidence();
            incidence.setType(IncidenceType.FAILED_LOGIN);
            incidence.setDescription("Usuario: " + username + " contraseña: " + password);
            IncidenceDAO.save(incidence);
            return;
        }
        if ( now.isBefore(LocalTime.of(18, 0)) || now.isAfter(LocalTime.of(8, 0))) {
            Incidence incidence = new Incidence();
            incidence.setType(IncidenceType.LATE_OPENING);
            incidence.setDescription("presidente de mesa: " + this.electionClerk.getId());
            IncidenceDAO.save(incidence);
        }
        if ( now.isBefore(LocalTime.of(7, 50)) || now.isAfter(LocalTime.of(18, 0))) {
            Incidence incidence = new Incidence();
            incidence.setType(IncidenceType.TRY_OPEN_AFTER);
            incidence.setDescription("presidente de mesa: " + this.electionClerk.getId());
            IncidenceDAO.save(incidence);
            // return;
        }
        ElectionClerkDAO.openTable(this.electionClerk.getId());
        window.go("main");
    }

    public boolean isValid(int document) {
        return PersonDAO.isValid(document);
    }

    public Person getPerson(int document) {
        return PersonDAO.getPerson(document);
    }

    public void confirm(Person person) {
        if (PersonDAO.alreadyVoted(person.getDocument())) {
            window.dialog("Ya votó");
            return;
        }
        this.setCurrentPerson(person);
        window.go("voting");
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public ArrayList<Contest> getContests() {
        ArrayList<Contest> contests = ContestDAO.getContest();
        for (Candidate candidate : CandidateDAO.getCandidates()) {
            for (Contest contest : contests) {
                if (candidate.getContest().equals(contest.getName())) {
                    contest.addCandidate(candidate);
                }
            }
        }
        return contests;
    }

    public void vote(int contestId) {
        Vote vote = new Vote();
        vote.setContestId(contestId);
        vote.setPersonId(this.currentPerson.getId());
        if (!VoteDAO.vote(vote)) {
            Incidence incidence = new Incidence();
            incidence.setType(IncidenceType.FAILED_VOTE);
            incidence.setDescription("documento: " + this.currentPerson.getDocument());
            IncidenceDAO.save(incidence);
        }
        window.dialog("Voto emitido correctamente");
        this.currentPerson = null;
        this.window.go("main");
    }

    public void closeTable() {
        LocalTime now = LocalTime.now();
        if ( now.isAfter(LocalTime.of(18, 0)) || now.isBefore(LocalTime.of(8, 0))) {
            Incidence incidence = new Incidence();
            incidence.setType(IncidenceType.LATE_CLOSING);
            incidence.setDescription("presidente de mesa: " + this.electionClerk.getId());
            IncidenceDAO.save(incidence);
        }
        if (now.isBefore(LocalTime.of(18, 0)) && now.isAfter(LocalTime.of(8, 0))) {
            Incidence incidence = new Incidence();
            incidence.setType(IncidenceType.TRY_CLOSING_BEFORE);
            incidence.setDescription("presidente de mesa: " + this.electionClerk.getId());
            IncidenceDAO.save(incidence);
            return;
        }
        ElectionClerkDAO.closeTable(this.electionClerk.getId());
        window.dispose();
    }

    public void cancelPerson(Person person) {
        Incidence incidence = new Incidence();
        incidence.setType(IncidenceType.CANCEL_PERSON);
        incidence.setDescription("documento: " + person.getFirstName());
        IncidenceDAO.save(incidence);
    }

    public void dialog(String message) {
        window.dialog(message);
    }
}
