package hu.nye.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlayerNameTest {

    private InputProvider inputProviderMock;
    private PlayerName playerName;

    @BeforeEach
    void setUp() {
        // Given a player name and a mocked InputProvider
        inputProviderMock = Mockito.mock(InputProvider.class);
        playerName = new PlayerName("John", inputProviderMock);
    }

    @Test
    void testGetPlayerName_shouldReturnCorrectName() {
        // Given a PlayerName with an initial name

        // When getPlayerName is called
        String result = playerName.getPlayerName();

        // Then it should return the initialized player name
        assertEquals("John", result);
    }

    @Test
    void testAskForPlayerName_shouldReturnNewPlayerName() {
        // Given a new name provided by InputProvider
        when(inputProviderMock.getInput()).thenReturn("Alice");

        // When askForPlayerName is called
        PlayerName newPlayer = playerName.askForPlayerName();

        // Then the new PlayerName should contain the new name
        assertEquals("Alice", newPlayer.getPlayerName());
    }

    @Test
    void testToString_shouldReturnFormattedString() {
        // Given a PlayerName with a specific name

        // When toString is called
        String result = playerName.toString();

        // Then the result should match the expected format
        assertEquals("Player Name: John", result);
    }

    @Test
    void testEquals_shouldReturnTrueForSameName() {
        // Given two PlayerName objects with the same name
        PlayerName anotherPlayer = new PlayerName("John", inputProviderMock);

        // When equals is called
        boolean isEqual = playerName.equals(anotherPlayer);

        // Then it should return true
        assertEquals(true, isEqual);
    }

    @Test
    void testEquals_shouldReturnFalseForDifferentName() {
        // Given two PlayerName objects with different names
        PlayerName differentPlayer = new PlayerName("Alice", inputProviderMock);

        // When equals is called
        boolean isEqual = playerName.equals(differentPlayer);

        // Then it should return false
        assertEquals(false, isEqual);
    }

    @Test
    void testHashCode_shouldBeEqualForSameName() {
        // Given two PlayerName objects with the same name
        PlayerName anotherPlayer = new PlayerName("John", inputProviderMock);

        // When hashCode is called
        int hashCode1 = playerName.hashCode();
        int hashCode2 = anotherPlayer.hashCode();

        // Then their hashCodes should be equal
        assertEquals(hashCode1, hashCode2, "Expected hashCodes to be equal for objects with the same name");
    }

    @Test
    void testHashCode_shouldBeDifferentForDifferentNames() {
        // Given two PlayerName objects with different names
        PlayerName differentPlayer = new PlayerName("Alice", inputProviderMock);

        // When hashCode is called
        int hashCode1 = playerName.hashCode();
        int hashCode2 = differentPlayer.hashCode();

        // Then their hashCodes should be different
        assertEquals(false, hashCode1 == hashCode2, "Expected hashCodes to be different for objects with different names");
    }

    @Test
    void testHashCode_shouldBeConsistent() {
        // Given a single PlayerName object

        // When hashCode is called multiple times
        int hashCode1 = playerName.hashCode();
        int hashCode2 = playerName.hashCode();

        // Then the hashCode should remain consistent
        assertEquals(hashCode1, hashCode2, "Expected hashCode to remain consistent for the same object");
    }

}
