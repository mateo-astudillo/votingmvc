package model.incidence;

import model.DBConnection.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class IncidenceDAO {
    public static void save(Incidence incidence) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        try {
            CallableStatement callableStatement =  connection.prepareCall("CALL saveIncidence(?, ?, ?);");
            callableStatement.setString("type", String.valueOf(incidence.getType()));
            callableStatement.setString("description", incidence.getDescription());
            callableStatement.execute();
            incidence.setId(callableStatement.getInt("id"));
            callableStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
