package model.candidate;

import model.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CandidateDAO {
    public static ArrayList<Candidate> getCandidates() {
        ArrayList<Candidate> candidates = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT first_name, last_name, office, contest, number FROM candidate;");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Candidate c = new Candidate();
                c.setFirstName(resultSet.getString("first_name"));
                c.setLastName(resultSet.getString("last_name"));
                c.setOffice(resultSet.getString("office"));
                c.setContest(resultSet.getString("contest"));
                c.setListNumber(resultSet.getString("number"));
                candidates.add(c);
            }
            resultSet.close();
            preparedStatement.close();
            return candidates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
