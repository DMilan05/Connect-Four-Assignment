package hu.nye.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputProviderTest {

    /*private ConsoleInputProvider consoleInputProvider;

    @BeforeEach
    void setUp() {
        consoleInputProvider = new ConsoleInputProvider();
    }

    @Test
    void testGetInput() {
        // Given: Simulate user input
        String input = "Hello, World!";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // Redirect System.in to our simulated input stream

        // When: Calling getInput()
        String result = consoleInputProvider.getInput();

        // Then: The result should match the simulated input
        assertEquals(input, result);
    }

    @Test
    void testGetInputEmptyString() {
        // Given: Simulate empty user input
        String input = "";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // When: Calling getInput()
        String result = consoleInputProvider.getInput();

        // Then: The result should be an empty string
        assertEquals(input, result);
    }

    @Test
    void testGetInputWithWhitespace() {
        // Given: Simulate user input with leading/trailing whitespace
        String input = "  Hello, World!  ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // When: Calling getInput()
        String result = consoleInputProvider.getInput();

        // Then: The result should match the input, including the whitespace
        assertEquals(input, result);
    }*/
}
