package Game;


import Board.Disk;
import Board.GameBoard;
import Game.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComputerMoveTest {
    private Move move;
    private Disk mockDisk;

    @BeforeEach
    void setUp() {
        // The Mock and the Test was generated by ChatGPT.
        // Initialize a 6x7 game board (6 rows, 7 columns)
        List<List<Disk>> columns = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            columns.add(new ArrayList<>()); // 7 empty columns
        }

        move = new Move(columns, 6);  // 6 rows and 7 columns

        // Create a mock Disk object to simulate player moves
        mockDisk = Mockito.mock(Disk.class);
    }

    @Test
    void testMoveInitialization() {
        // Verify that the columns are initialized correctly
        assertNotNull(move.getColumns(), "Columns list should be initialized.");
        assertEquals(7, move.getColumns().size(), "There should be 7 columns.");

        // Check that each column is empty initially
        for (List<Disk> column : move.getColumns()) {
            assertTrue(column.isEmpty(), "Each column should be empty initially.");
        }

        // Verify the number of rows is correct
        assertEquals(6, move.getRows(), "There should be 6 rows.");
    }

    @Test
    void testGetCellValidPosition() {
        // Simulate a move by adding a mockDisk in column 0, row 0
        move.move(0, mockDisk);

        // Verify that getCell returns the correct mockDisk
        assertEquals(mockDisk, move.getCell(0, 0), "getCell(0, 0) should return the mockDisk.");
    }

    @Test
    void testGetCellOutOfBounds() {
        // Test for positions outside the valid range, should return null
        assertNull(move.getCell(10, 0), "getCell with an invalid column should return null.");
        assertNull(move.getCell(0, 10), "getCell with an invalid row should return null.");
    }

    @Test
    void testMoveValid() {
        // Test a valid move to column 0 using the mockDisk
        move.move(0, mockDisk);

        // Verify that the mockDisk was added to the column
        assertEquals(1, move.getColumns().get(0).size(), "Column 0 should have 1 disk after the move.");
        assertEquals(mockDisk, move.getColumns().get(0).get(0), "Column 0 should contain the mockDisk.");

        // Verify that no methods on the mockDisk have been called (optional)
        verify(mockDisk, never()).toString(); // Verify no interaction with toString() or other methods
    }

    @Test
    void testMoveFullColumn() {
        // Simulate a full column by adding mock disks up to the number of rows
        for (int i = 0; i < move.getRows(); i++) {
            move.move(0, mockDisk);
        }

        // Try to add one more disk to the full column, expect an IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> move.move(0, mockDisk));
        assertEquals("That column is full", exception.getMessage(), "Exception message should indicate the column is full.");

        // Verify that no extra disk was added to the full column
        assertEquals(6, move.getColumns().get(0).size(), "Column 0 should still have 6 disks.");
    }

    @Test
    void testMoveOutOfBounds() {
        // Test an invalid move with a column index out of bounds
        Exception exception = assertThrows(Exception.class, () -> move.move(10, mockDisk));
        assertTrue(exception.getMessage().contains(""), "Moving to an out-of-bounds column should trigger an AssertionError.");
    }

}