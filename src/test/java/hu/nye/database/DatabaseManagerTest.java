package hu.nye.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManagerTest {

    private DatabaseManager databaseManager;

    // Setup method to initialize the DatabaseManager instance
    @BeforeEach
    public void setUp() {
        databaseManager = new DatabaseManager();
    }

    @Test
    public void givenValidConnectionDetails_whenGetConnection_thenConnectionIsEstablished() {
        // Given: DatabaseManager instance is available

        // When: We try to establish a connection
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
        } catch (SQLException e) {
            fail("Connection should be established successfully");
        }

        // Then: The connection should not be null
        assertNotNull(connection, "The connection should not be null.");

        // Optionally: Close the connection if it was successfully established
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            fail("Failed to close the connection.");
        }
    }

    @Test
    public void givenInvalidConnectionDetails_whenGetConnection_thenSQLExceptionIsThrown() {
        // Given: A DatabaseManager instance with incorrect connection details
        DatabaseManager invalidDatabaseManager = new DatabaseManager() {
            @Override
            public Connection getConnection() throws SQLException {
                // Overriding the connection method to simulate an invalid connection
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/invalid_db", "wrong_user", "wrong_password");
            }
        };

        // When: We try to get a connection
        SQLException exception = assertThrows(SQLException.class, () -> invalidDatabaseManager.getConnection());

        // Then: A SQLException should be thrown
        assertNotNull(exception, "SQLException should be thrown for invalid connection details.");
    }
}
