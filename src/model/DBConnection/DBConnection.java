package model.DBConnection;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() {
        Properties properties = new Properties();
        String database, username, password, host, url, driver, path;
        path = "src/model/DBConnection/connection.properties";
        int port;
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);
            properties.load(reader);
            database = properties.getProperty("database");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port"));
            url = driver + "://" + host + ":" + port + "/" + database;
            return DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
