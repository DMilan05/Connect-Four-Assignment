package hu.nye.player;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleInputProviderTest {

    @Test
    void shouldReturnUserInput_whenInputIsProvided() {
        // Given
        String simulatedInput = "test input";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            ConsoleInputProvider consoleInputProvider = new ConsoleInputProvider();

            // When
            String result = consoleInputProvider.getInput();

            // Then
            assertEquals(simulatedInput, result, "The input should match the simulated value.");
        } finally {
            // Restore System.in to avoid affecting other tests
            System.setIn(originalIn);
        }
    }
}
