package model.electionClerk;

import model.DBConnection.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ElectionClerkDAO {

    public static int validate(String username, String password) {
        Connection connection = DBConnection.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL validateElectionClerk(?, ?, ?);");
            callableStatement.setString("username", username);
            callableStatement.setString("password", password);
            callableStatement.execute();
            int id = callableStatement.getInt("id");
            callableStatement.close();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
