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
        /*Disk result = move.getCell(0, 2); // Column 2 doesn't exist
        assertNull(result, "Expected null for out-of-bounds cell");*/
        Disk result = move.getCell(0, 2); // Column 2 doesn't exist
        assertNull(result, "Expected null for out-of-bounds cell");
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

        assertEquals("That column is full.", exception.getMessage()); // Updated to match the actual exception message
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

        assertEquals("Invalid column index.", exception.getMessage()); // Updated to match the actual exception message
    }

    @Test
    void testToString_shouldReturnFormattedBoardString() {
        String expected = "EMPTY EMPTY \nRED EMPTY \n";
        String boardString = move.toString();
        assertEquals(expected, boardString, "Expected formatted board string to match");
    }
}
