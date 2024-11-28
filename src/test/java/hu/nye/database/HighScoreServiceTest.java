package hu.nye.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class HighScoreServiceTest {

    @Mock
    private DatabaseManager databaseManager;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private HighScoreService highScoreService;


    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
    }

    // GIVEN: A player with a valid name
    // WHEN: Save player win is called
    // THEN: No exception is thrown and the database interaction occurs
    @Test
    void givenValidPlayer_whenSavePlayerWin_thenNoSQLException() throws SQLException {
        // Arrange
        when(databaseManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        // Act
        highScoreService.savePlayerWin("Player1");

        // Assert
        verify(preparedStatement, times(1)).setString(1, "Player1");
        verify(preparedStatement, times(1)).executeUpdate();
    }

    // GIVEN: SQLException occurs while saving a player's win
    // WHEN: Save player win is called
    // THEN: SQLException is caught and handled gracefully
    @Test
    void givenSQLException_whenSavePlayerWin_thenSQLExceptionIsHandled() throws SQLException {
        // Arrange
        when(databaseManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenThrow(new SQLException("Database error"));

        // Act
        highScoreService.savePlayerWin("Player1");

        // Assert
        verify(connection, times(1)).prepareStatement(any(String.class));
        // Verify exception handling or log message, if necessary
    }

    // GIVEN: High scores are available in the database
    // WHEN: printHighScores is called
    // THEN: High scores are printed correctly
    @Test
    void givenHighScores_whenPrintHighScores_thenHighScoresAreFetched() throws SQLException {
        // Arrange
        when(databaseManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("player_name")).thenReturn("Player1");
        when(resultSet.getInt("wins")).thenReturn(5);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); // Redirect System.out

        // Act
        highScoreService.printHighScores();

        // Assert
        String expectedOutput = "Player1: 5 wins";
        assertTrue(outContent.toString().contains(expectedOutput),
                "Expected output to contain: " + expectedOutput);

        // Cleanup
        System.setOut(System.out);
    }
    // GIVEN: No high scores in the database
    // WHEN: printHighScores is called
    // THEN: "No high scores found" is printed
    @Test
    void givenNoHighScores_whenPrintHighScores_thenNoHighScoresFound() throws SQLException {
        // Arrange
        when(databaseManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Act
        highScoreService.printHighScores();

        // Assert
        verify(resultSet, times(1)).next();
        // Verify print statement or logging message
    }

    @Test
    void givenValidComputerName_whenSaveComputerPlayerWin_thenNoSQLException() throws SQLException {
        // GIVEN: A valid computer name and a prepared SQL query
        String computerName = "Computer1";
        String query = "INSERT INTO high_scores (player_name, wins) VALUES (?, 1) " +
                "ON DUPLICATE KEY UPDATE wins = wins + 1";

        when(databaseManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(query)).thenReturn(preparedStatement);

        // WHEN: The method saveComputerPlayerWin is called
        highScoreService.saveComputerPlayerWin(computerName);

        // THEN: The query is prepared and executed successfully
        verify(connection, times(1)).prepareStatement(query);
        verify(preparedStatement, times(1)).setString(1, computerName);
        verify(preparedStatement, times(1)).executeUpdate();
    }
    @Test
    void givenSQLException_whenSaveComputerPlayerWin_thenSQLExceptionIsHandled() throws SQLException {
        // GIVEN: A valid computer name but a database error occurs
        String computerName = "Computer1";
        String query = "INSERT INTO high_scores (player_name, wins) VALUES (?, 1) " +
                "ON DUPLICATE KEY UPDATE wins = wins + 1";

        when(databaseManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(query)).thenThrow(new SQLException("Database error"));

        // WHEN: The method saveComputerPlayerWin is called
        highScoreService.saveComputerPlayerWin(computerName);

        // THEN: The exception is caught and handled gracefully
        verify(connection, times(1)).prepareStatement(query);
        // You can also verify error logging if applicable
    }
    @Test
    void givenSQLException_whenPrintHighScores_thenSQLExceptionIsHandled() throws SQLException {
        // Arrange: Mock the database to throw an exception
        when(databaseManager.getConnection()).thenThrow(new SQLException("Database connection error"));

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent)); // Redirect System.err

        // Act
        highScoreService.printHighScores();

        // Assert
        assertTrue(
                errContent.toString().contains("Database connection error"),
                "Expected System.err to contain the SQLException message"
        );

        // Cleanup
        System.setErr(System.err);
    }
    @Test
    void givenSQLException_whenSavePlayerWin_thenSQLExceptionIsHandledAndStackTracePrinted() throws SQLException {
        // Arrange: Mock the database to throw an exception
        when(databaseManager.getConnection()).thenThrow(new SQLException("Error saving player win"));

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent)); // Redirect System.err

        // Act
        highScoreService.savePlayerWin("Player1");

        // Assert
        assertTrue(
                errContent.toString().contains("Error saving player win"),
                "Expected System.err to contain the SQLException message"
        );

        // Cleanup
        System.setErr(System.err);
    }


}
