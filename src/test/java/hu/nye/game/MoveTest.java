package hu.nye.game;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MoveTest {

    private Board boardMock;
    private Move move;
    private List<List<Disk>> columns;

    @BeforeEach
    void setUp() {
        // Given: A mock Board with predefined columns and rows
        columns = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(Disk.EMPTY, Disk.EMPTY)), // Column 0
                new ArrayList<>(Arrays.asList(Disk.RED, Disk.EMPTY))     // Column 1
        ));

        boardMock = Mockito.mock(Board.class);

        // Mock the getColumns and getRows methods to return predefined columns and rows
        when(boardMock.getColumns()).thenReturn(columns);
        when(boardMock.getRows()).thenReturn(2);

        move = new Move(boardMock);
    }

    @Test
    void testGetCell_shouldReturnCorrectDisk() {
        // Given: A board with a RED disk in position (1, 0)

        // When: getCell is called for position (1, 0)
        Disk result = move.getCell(1, 0);

        // Then: The result should be RED
        assertEquals(Disk.RED, result, "Expected to find RED disk at position (1, 0)");
    }

    @Test
    void testGetCell_shouldReturnNullForOutOfBounds() {
        // Given: A board with a defined number of rows and columns

        // When: getCell is called for an out-of-bounds position (0, 2)
        Disk result = move.getCell(0, 2);

        // Then: The result should be null
        assertNull(result, "Expected null for out-of-bounds cell");
    }

    @Test
    void testMove_shouldAddDiskToSpecifiedColumn() {
        // Given: An empty position in column 0 and a YELLOW disk to be added
        int column = 0;
        Disk playerDisk = Disk.YELLOW;

        // When: move is called with column 0
        move.move(column, playerDisk);

        // Then: The last position in column 0 should be YELLOW
        List<List<Disk>> updatedColumns = boardMock.getColumns();
        assertEquals(playerDisk, updatedColumns.get(column).get(updatedColumns.get(column).size() - 1),
                "Expected last position in column 0 to be YELLOW");
    }

    @Test
    void testMove_shouldThrowExceptionIfColumnIsFull() {
        // Given: A column that is full
        int column = 1;
        Disk playerDisk = Disk.RED;

        // Mock a full column scenario
        columns.set(1, new ArrayList<>(Arrays.asList(Disk.RED, Disk.RED)));  // Full column

        // When: move is called on the full column
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> move.move(column, playerDisk),
                "Expected move to throw exception for full column"
        );

        // Then: An IllegalArgumentException should be thrown with a specific message
        assertEquals("That column is full", exception.getMessage());
    }

    @Test
    void testMove_shouldThrowExceptionForInvalidColumnIndex() {
        // Given: An invalid column index (-1)
        int column = -1;
        Disk playerDisk = Disk.RED;

        // When: move is called with an invalid column index
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> move.move(column, playerDisk),
                "Expected move to throw exception for invalid column"
        );

        // Then: An IllegalArgumentException should be thrown
        assertEquals("Invalid column index.", exception.getMessage());
    }

    @Test
    void testToString_shouldReturnFormattedBoardString() {
        // Given: A board setup with specific disk placements
        String expected = "EMPTY EMPTY \nRED EMPTY \n";

        // When: toString is called
        String boardString = move.toString();

        // Then: The result should match the expected formatted string
        assertEquals(expected, boardString, "Expected formatted board string to match");
    }
}
