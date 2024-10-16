package Board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameBoardGeneratorTest {
    // The Mock and the Test was generated by ChatGPT.
    private GameBoardGenerator gameBoardGenerator;
    private List<List<Disk>> mockedColumns;

    @BeforeEach
    void setUp() {
        // Mock the list of columns using Mockito
        mockedColumns = mock(List.class);

        // Initialize GameBoardGenerator with mocked columns and 6 rows
        gameBoardGenerator = new GameBoardGenerator(mockedColumns, 6);
    }

    @Test
    void testGameBoardGeneratorInitialization() {
        // Verify that the mocked columns are passed correctly to GameBoardGenerator
        assertEquals(mockedColumns, gameBoardGenerator.getColumns(), "Columns list should be the mocked list.");

        // Check that the number of rows is correctly initialized
        assertEquals(6, gameBoardGenerator.getRows(), "The number of rows should be initialized to 6.");
    }

    @Test
    void testGenerateBoardAddsColumn() {
        // Before calling generateBoard, columns should be empty (as mocked)
        when(mockedColumns.size()).thenReturn(0); // Mock initial size of columns

        // Call generateBoard to add a new empty column
        gameBoardGenerator.generateBoard();

        // Verify that a new empty column is added
        verify(mockedColumns, times(1)).add(anyList());  // Verify that add() was called on mockedColumns

        // Optionally, you can check for the size after the call
        when(mockedColumns.size()).thenReturn(1);  // Mock size after adding a column
        assertEquals(1, gameBoardGenerator.getColumns().size(), "Columns list should have one column after calling generateBoard.");
    }

    @Test
    void testGenerateBoardMultipleTimes() {
        // Mock the behavior of adding multiple columns
        when(mockedColumns.size()).thenReturn(0, 1, 2); // Mock sequential size updates

        // Call generateBoard multiple times
        gameBoardGenerator.generateBoard();
        gameBoardGenerator.generateBoard();
        gameBoardGenerator.generateBoard();

        // Verify that add() was called 3 times to add a new column
        verify(mockedColumns, times(3)).add(anyList());

        // Mock the final size to verify the number of added columns
        when(mockedColumns.size()).thenReturn(3); // Mock final size of columns
        assertEquals(3, gameBoardGenerator.getColumns().size(), "Columns list should have three columns after calling generateBoard three times.");
    }
}
