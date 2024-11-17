package hu.nye.computer.player;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComputerMoveTest {
    private Board board;
    private ComputerMove computerMove;

    @BeforeEach
    void setUp() {
        List<List<Disk>> columns = new ArrayList<>();
        int rowCount = 6;
        int columnCount = 7;

        // Initialize empty columns for the board
        for (int i = 0; i < columnCount; i++) {
            List<Disk> column = new ArrayList<>();
            columns.add(column);
        }
        board = new Board(columns, rowCount);
        computerMove = new ComputerMove(board);
    }

    @Test
    void testGetRandomAvailableColumn_givenNonFullColumns_whenGetRandomAvailableColumn_thenReturnsValidColumnIndex() {
        // Given: A board with all columns having at least one empty row
        int columnCount = board.getColumns().size();

        // When: The getRandomAvailableColumn is called
        int column = computerMove.getRandomAvailableColumn();

        // Then: The returned column index should be within the bounds and not be full
        assertTrue(column >= 0 && column < columnCount);
        assertTrue(board.getColumns().get(column).size() < board.getRows());
    }

    @Test
    void testGetCell_givenValidIndices_whenGetCell_thenReturnsExpectedDisk() {
        // Given: A board with an inserted disk at (2, 0)
        int columnIndex = 2;
        int rowIndex = 0;
        board.getColumns().get(columnIndex).add(Disk.RED);

        // When: The getCell method is called on that location
        Disk result = computerMove.getCell(columnIndex, rowIndex);

        // Then: The returned disk should be RED
        assertEquals(Disk.RED, result);
    }

    @Test
    void testGetCell_givenEmptyLocation_whenGetCell_thenReturnsNull() {
        // Given: A board with an empty cell at (0, 0)
        int columnIndex = 0;
        int rowIndex = 0;

        // When: The getCell method is called on that location
        Disk result = computerMove.getCell(columnIndex, rowIndex);

        // Then: The returned disk should be null
        assertNull(result);
    }

    @Test
    void testMakeMove_givenAvailableColumn_whenMakeMove_thenDiskIsPlaced() {
        // Given: A board with an available column
        Disk playerDisk = Disk.YELLOW;

        // When: makeMove is called with the player's disk
        computerMove.makeMove(playerDisk);

        // Then: The disk should be added to a valid column that is not full
        boolean diskAdded = board.getColumns().stream()
                .anyMatch(column -> column.size() > 0 && column.contains(playerDisk));
        assertTrue(diskAdded);
    }

    @Test
    void testMakeMove_givenFullColumn_whenMakeMove_thenThrowsException() {
        // Given: A board with the first column completely filled
        int columnToFill = 0;
        for (int i = 0; i < board.getRows(); i++) {
            board.getColumns().get(columnToFill).add(Disk.RED);
        }
        Disk playerDisk = Disk.YELLOW;

        // When & Then: An IllegalArgumentException should be thrown when trying to place a disk in a full column
        assertThrows(IllegalArgumentException.class, () -> computerMove.makeMove(playerDisk));
    }

    @Test
    void testToString_givenBoardWithDisks_whenToString_thenReturnsCorrectStringRepresentation() {
        // Given: A board with specific disks
        board.getColumns().get(0).add(Disk.YELLOW);
        board.getColumns().get(1).add(Disk.RED);

        // When: The toString method is called
        String boardString = computerMove.toString();

        // Then: The output should contain the correct representation of disks in each column
        assertTrue(boardString.contains("YELLOW"));
        assertTrue(boardString.contains("RED"));
    }
}
