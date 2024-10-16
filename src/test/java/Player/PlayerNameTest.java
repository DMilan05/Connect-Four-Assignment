package Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerNameTest {

    private PlayerName playerName;

    @BeforeEach
    void setUp() {
        // Initialize with a default player name
        playerName = new PlayerName("DefaultName");
    }

    @Test
    void testPlayerNameInitialization() {
        // Test that the player name is initialized correctly
        assertEquals("DefaultName", playerName.getPlayerName(), "Player name should be initialized to 'DefaultName'.");
    }

    @Test
    void testGetPlayerName() {
        // Test that the getter returns the correct player name
        assertEquals("DefaultName", playerName.getPlayerName(), "getPlayerName() should return 'DefaultName'.");
    }

    @Test
    void testSetPlayerName() {
        // Test that the setter updates the player name correctly
        playerName.setPlayerName("NewPlayer");
        assertEquals("NewPlayer", playerName.getPlayerName(), "setPlayerName() should update the name to 'NewPlayer'.");
    }

    @Test
    void testAskForPlayerName() {
        // Simulate user input for player name using ByteArrayInputStream
        String simulatedInput = "JohnDoe";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Call askForPlayerName(), which will use the simulated input
        playerName.askForPlayerName();

        // Check that the player name was updated correctly based on the simulated input
        assertEquals("JohnDoe", playerName.getPlayerName(), "askForPlayerName() should update the name to 'JohnDoe'.");

        // Reset System.in after the test
        System.setIn(System.in);
    }
}
