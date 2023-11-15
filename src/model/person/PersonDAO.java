package model.person;

import model.DBConnection.DBConnection;

import java.sql.*;

public class PersonDAO {
    public static boolean isValid(int document) {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT COUNT(*) FROM `Person` WHERE `Person`.`document` = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, document);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static Person getPerson(int document) {
        Connection connection = DBConnection.getConnection();
        Person person = new Person();
        person.setDocument(document);
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL getPerson(?, ?, ?, ?, ?, ?, ?);");
            callableStatement.setInt("document", document);
            callableStatement.execute();
            person.setId(callableStatement.getInt("id"));
            person.setFirstName(callableStatement.getString("firstName"));
            person.setLastName(callableStatement.getString("lastName"));
            person.setBirthdate(callableStatement.getDate("birthdate"));
            person.setAddress(callableStatement.getString("address"));
            person.setGender(callableStatement.getString("gender"));
            callableStatement.close();
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
