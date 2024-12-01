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
        // Mock Board with predefined columns and rows
        columns = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(Disk.EMPTY, Disk.EMPTY)), // Column 0
                new ArrayList<>(Arrays.asList(Disk.RED, Disk.EMPTY))     // Column 1
        ));

        boardMock = Mockito.mock(Board.class);
        when(boardMock.getColumns()).thenReturn(columns);
        when(boardMock.getRows()).thenReturn(2);

        move = new Move(boardMock);
    }

    @Test
    void testGetCell_shouldReturnCorrectDisk() {
        Disk result = move.getCell(1, 0);
        assertEquals(Disk.RED, result, "Expected to find RED disk at position (1, 0)");
    }

    @Test
    void testGetCell_shouldReturnNullForOutOfBounds() {
        // Test for invalid column index
        Disk result1 = move.getCell(-1, 0);
        assertNull(result1, "Expected null for out-of-bounds column index (-1, 0)");

        Disk result2 = move.getCell(2, 0);
        assertNull(result2, "Expected null for out-of-bounds column index (2, 0)");

        // Test for invalid row index
        Disk result3 = move.getCell(0, -1);
        assertNull(result3, "Expected null for out-of-bounds row index (0, -1)");

        Disk result4 = move.getCell(0, 2);
        assertNull(result4, "Expected null for out-of-bounds row index (0, 2)");
    }

    @Test
    void testMove_shouldAddDiskToSpecifiedColumn() {
        int column = 0;
        Disk playerDisk = Disk.YELLOW;

        move.move(column, playerDisk);

        List<List<Disk>> updatedColumns = boardMock.getColumns();
        assertEquals(playerDisk, updatedColumns.get(column).get(updatedColumns.get(column).size() - 1),
                "Expected last position in column 0 to be YELLOW");
    }

    @Test
    void testMove_shouldThrowExceptionIfColumnIsFull() {
        int column = 1;
        Disk playerDisk = Disk.RED;

        // Simulate a full column
        columns.set(1, new ArrayList<>(Arrays.asList(Disk.RED, Disk.RED)));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> move.move(column, playerDisk),
                "Expected move to throw exception for full column"
        );

        assertEquals("That column is full.", exception.getMessage());
    }

    @Test
    void testMove_shouldThrowExceptionForInvalidColumnIndex() {
        int column = -1;
        Disk playerDisk = Disk.RED;

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> move.move(column, playerDisk),
                "Expected move to throw exception for invalid column"
        );

        assertEquals("Invalid column index.", exception.getMessage());
    }

    @Test
    void testToString_shouldReturnFormattedBoardString() {
        String expected = "EMPTY EMPTY \nRED EMPTY \n";
        String boardString = move.toString();
        assertEquals(expected, boardString, "Expected formatted board string to match");
    }
    @Test
    void testGetCell_shouldReturnNullWhenColumnSizeIsSmallerThanRowIndex() {
        // Simulate a column that is shorter than the requested row index
        columns.set(0, new ArrayList<>(Arrays.asList(Disk.EMPTY))); // Column 0 has only 1 row

        Disk result = move.getCell(0, 1); // Requesting row 1 in column 0, which doesn't exist
        assertNull(result, "Expected null when the column size is smaller than the requested row index");
    }

}
