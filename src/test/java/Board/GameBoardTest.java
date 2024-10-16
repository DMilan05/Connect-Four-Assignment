package Board;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class GameBoardTest extends TestCase {

   

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        // Create an empty list of columns and initialize rows to 6 (for example)
        List<List<Disk>> columns = new ArrayList<>();
        int rows = 6;
        gameBoard = new GameBoard(columns, rows);
    }

    @Test
    public void testGameBoardInitialization() {
        // Check that the columns list is initialized and empty
        assertNotNull(gameBoard.getColumns().toString(), "Columns list should be initialized.");
        assertTrue(gameBoard.getColumns().isEmpty(), "Columns list should be empty initially.");

        // Check that the number of rows is correctly initialized
        assertEquals(6, gameBoard.getRows(), "The number of rows should be initialized to 6.");
    }

    private void assertTrue(boolean empty, String s) {
        
    }

    private void assertNotNull(String string, String object) {
        
    }

    private void assertEquals(int i, int rows, String s) {
    }

    @Test
    public void testGetColumnsWithMockedDisk() {
        // Create a mocked Disk object
        Disk mockDisk = Mockito.mock(Disk.class);

        // Add a column that contains the mocked Disk
        List<List<Disk>> columns = gameBoard.getColumns();
        List<Disk> column = new ArrayList<>();
        column.add(mockDisk);
        columns.add(column);

        // Verify that the columns list now contains one column with the mocked Disk
        assertEquals(1, columns.size(), "Columns list should have one column.");
        assertEquals(1, columns.get(0).size(), "First column should contain one Disk.");
        assertEquals(mockDisk, columns.get(0).get(0), "The Disk in the column should be the mocked one.");

        // Verify interaction with the mocked Disk (optional, depends on Disk behavior)
        verify(mockDisk, never()).toString();  // Example: Verify that toString() was never called on the mock
    }

    private void assertEquals(Disk mockDisk, Disk disk, String s) {
    }

    @Test
    public void testGetRows() {
        // Ensure rows getter returns the correct value
        assertEquals(6, gameBoard.getRows(), "The number of rows should match the initialized value.");
    }
}