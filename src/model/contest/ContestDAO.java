package model.contest;

import model.DBConnection.DBConnection;
import model.candidate.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContestDAO {
    public static ArrayList<Contest> getContest() {
        ArrayList<Contest> contests = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String query = "SELECT `id`, `name`, `number` FROM `Contest`;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Contest c = new Contest();
                c.setId(resultSet.getInt("id"));
                c.setName(resultSet.getString("name"));
                c.setListNumber(resultSet.getInt("number"));
                contests.add(c);
            }
            resultSet.close();
            preparedStatement.close();
            return contests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
