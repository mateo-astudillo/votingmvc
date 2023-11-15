package controller;

import model.electionClerk.ElectionClerk;
import model.electionClerk.ElectionClerkDAO;
import model.incidence.Incidence;
import model.incidence.IncidenceDAO;
import model.incidence.IncidenceType;
import model.person.Person;
import model.person.PersonDAO;
import view.MainWindow;

public class Controller {
    private ElectionClerk electionClerk;
    private Person currentPerson;
    private final MainWindow window;
    public Controller() {
        electionClerk = new ElectionClerk();
        window = new MainWindow(this);
        window.setVisible(true);
    }

    public void login(String username, String password) {
        int id = ElectionClerkDAO.validate(username, password);
        if (id == 0) {
            Incidence incidence = new Incidence();
            incidence.setType(IncidenceType.FAILED_LOGIN);
            incidence.setDescription("Usuario: " + username + " contraseña: " + password);
            IncidenceDAO.save(incidence);
            return;
        }
        electionClerk.setId(id);
        window.go("main");
    }

    public boolean isValid(int document) {
        return PersonDAO.isValid(document);
    }

    public Person getPerson(int document) {
        return PersonDAO.getPerson(document);
    }

    public void vote(Person person) {
        this.setCurrentPerson(person);
        window.go("voting");
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }
}
