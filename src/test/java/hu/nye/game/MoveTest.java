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

class MoveTest {

    private Board boardMock;
    private Move move;

    @BeforeEach
    void setUp() {
        // Given a mock Board with columns and rows
        boardMock = Mockito.mock(Board.class);
        List<List<Disk>> columns = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(Disk.EMPTY, Disk.EMPTY)), // Column 0
                new ArrayList<>(Arrays.asList(Disk.RED, Disk.EMPTY))     // Column 1
        ));

        Mockito.when(boardMock.getColumns()).thenReturn(columns);
        Mockito.when(boardMock.getRows()).thenReturn(2);

        move = new Move(boardMock);
    }

    @Test
    void testGetCell_shouldReturnCorrectDisk() {
        // Given a mock board with a RED disk in position (1, 0)

        // When getCell is called for position (1, 0)
        Disk result = move.getCell(1, 0);

        // Then the result should be RED
        assertEquals(Disk.RED, result);
    }

    @Test
    void testGetCell_shouldReturnNullForOutOfBounds() {
        // Given a board with defined rows and columns

        // When getCell is called for a position outside of valid bounds
        Disk result = move.getCell(0, 2);

        // Then the result should be null
        assertNull(result, "Should return null for out-of-bounds cell");
    }

    @Test
    void testMove_shouldAddDiskToSpecifiedColumn() {
        // Given an empty position in column 0
        int column = 0;
        Disk playerDisk = Disk.YELLOW;

        // When move is called with column 0
        move.move(column, playerDisk);

        // Then the last position in column 0 should be YELLOW
        List<Disk> columnDisks = boardMock.getColumns().get(column);
        assertEquals(playerDisk, columnDisks.get(columnDisks.size() - 1));
    }

    @Test
    void testMove_shouldThrowExceptionIfColumnIsFull() {
        // Given a column that is full
        int column = 1;
        Disk playerDisk = Disk.RED;

        // When move is called on a full column
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> move.move(column, playerDisk));

        // Then an IllegalArgumentException should be thrown with a specific message
        assertEquals("That column is full", exception.getMessage());
    }

    @Test
    void testToString_shouldReturnFormattedBoardString() {
        // Given a board setup with specific disk placements

        // When toString is called
        String boardString = move.toString();

        // Then the result should match the expected formatted string
        String expected = "EMPTY EMPTY \nRED EMPTY \n";
        assertEquals(expected, boardString);
    }
}
