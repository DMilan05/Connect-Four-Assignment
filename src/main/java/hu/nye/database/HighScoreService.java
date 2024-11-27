package hu.nye.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HighScoreService {
    private final DatabaseManager databaseManager;

    public HighScoreService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void savePlayerWin(String playerName) {
        String query = "INSERT INTO high_scores (player_name, wins) VALUES (?, 1) " +
                "ON DUPLICATE KEY UPDATE wins = wins + 1";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            System.out.println("Saving win for: " + playerName);
            statement.setString(1, playerName);
            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error saving player win: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void saveComputerPlayerWin(String computerName) {
        String query = "INSERT INTO high_scores (player_name, wins) VALUES (?, 1) " +
                "ON DUPLICATE KEY UPDATE wins = wins + 1";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            System.out.println("Saving win for computer: " + computerName);
            statement.setString(1, computerName);
            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error saving computer win: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /*public void printHighScores() {
        String query = "SELECT player_name, wins FROM high_scores ORDER BY wins DESC";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Fetching high scores...");
            boolean hasResults = false;

            while (resultSet.next()) {
                hasResults = true;
                String name = resultSet.getString("player_name");
                int wins = resultSet.getInt("wins");
                System.out.printf("%s: %d wins%n", name, wins);
            }

            if (!hasResults) {
                System.out.println("No high scores found.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching high scores: " + e.getMessage());
            e.printStackTrace();
        }
    }*/
    public void printHighScores() {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT player_name, wins FROM high_scores");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String playerName = resultSet.getString("player_name");
                int wins = resultSet.getInt("wins");
                System.out.printf("%s: %d wins%n", playerName, wins);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception
        }
    }

}
