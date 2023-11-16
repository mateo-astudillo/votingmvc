package model.vote;

import model.DBConnection.DBConnection;
import model.contest.Contest;

import java.sql.*;

public class VoteDAO {
    public static boolean vote(Vote vote) {
        Connection connection = DBConnection.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL vote(?, ?, ?);");
            callableStatement.setInt("contestId", vote.getContestId());
            callableStatement.setInt("personId", vote.getPersonId());
            callableStatement.execute();
            return callableStatement.getInt("result") == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
