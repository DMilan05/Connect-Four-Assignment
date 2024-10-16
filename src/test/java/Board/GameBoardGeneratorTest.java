package Board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameBoardGeneratorTest {
    private GameBoardGenerator gameBoardGenerator;

    @BeforeEach
    void setUp() {
        // Initialize with an empty list of columns and 6 rows
        List<List<Disk>> columns = new ArrayList<>();
        int rows = 6;
        gameBoardGenerator = new GameBoardGenerator(columns, rows);
    }

    @Test
    void testGameBoardGeneratorInitialization() {
        // Check that the columns list is initialized correctly
        assertNotNull(gameBoardGenerator.getColumns(), "Columns list should be initialized.");
        assertTrue(gameBoardGenerator.getColumns().isEmpty(), "Columns list should be empty initially.");

        // Check that the number of rows is correctly inherited and initialized
        assertEquals(6, gameBoardGenerator.getRows(), "The number of rows should be initialized to 6.");
    }

    @Test
    void testGenerateBoardAddsColumn() {
        // Before calling generateBoard, columns should be empty
        List<List<Disk>> columns = gameBoardGenerator.getColumns();
        assertEquals(0, columns.size(), "Columns list should be empty before calling generateBoard.");

        // Call generateBoard to add a new empty column
        gameBoardGenerator.generateBoard();

        // After calling generateBoard, the columns list should have one column
        assertEquals(1, columns.size(), "Columns list should have one column after calling generateBoard.");
        assertTrue(columns.get(0).isEmpty(), "The first column should be an empty list.");
    }

    @Test
    void testGenerateBoardMultipleTimes() {
        // Call generateBoard multiple times
        gameBoardGenerator.generateBoard();
        gameBoardGenerator.generateBoard();
        gameBoardGenerator.generateBoard();

        // Verify that three empty columns are added
        List<List<Disk>> columns = gameBoardGenerator.getColumns();
        assertEquals(3, columns.size(), "Columns list should have three columns after calling generateBoard three times.");

        // Ensure all columns are empty
        for (List<Disk> column : columns) {
            assertTrue(column.isEmpty(), "Each column should be an empty list.");
        }
    }
}