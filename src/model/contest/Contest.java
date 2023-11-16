package model.contest;

import model.candidate.Candidate;

import java.util.ArrayList;

public class Contest {
    private int id;
    private String name;
    private int listNumber;
    private final ArrayList<Candidate> candidates;

    public Contest() {
        candidates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListNumber() {
        return listNumber;
    }

    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void addCandidate(Candidate candidate) {
        this.candidates.add(candidate);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
