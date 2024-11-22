package hu.nye.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/game";
    private static final String USER = "root"; // username
    private static final String PASSWORD = "ProgtechBeadando2024//"; // password

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    //mysql -u root -p
}
