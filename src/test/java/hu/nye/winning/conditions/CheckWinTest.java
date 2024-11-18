package hu.nye.winning.conditions;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckWinTest {

    private CheckWin checkWin;
    private Board board;

    @BeforeEach
    void setUp() {
        // Given: Initialize columns as a 6x7 grid (6 rows, 7 columns)
        List<List<Disk>> columns = new ArrayList<>();
        for (int i = 0; i < 7; i++) {  // 7 columns
            List<Disk> column = new ArrayList<>();
            for (int j = 0; j < 6; j++) {  // 6 rows
                column.add(Disk.EMPTY);  // Fill with empty disks initially
            }
            columns.add(column);
        }

        board = new Board(columns, 6);  // Initialize the Board with the populated columns and 6 rows
        checkWin = new CheckWin(board);  // Create CheckWin instance
    }

    @Test
    void testVerticalWin() {
        // Given: A column with 4 yellow disks
        board.getColumns().get(0).set(0, Disk.YELLOW);
        board.getColumns().get(0).set(1, Disk.YELLOW);
        board.getColumns().get(0).set(2, Disk.YELLOW);
        board.getColumns().get(0).set(3, Disk.YELLOW);

        // When: Checking for a vertical win at position (0, 3) with yellow player
        boolean result = checkWin.checkWin(0, 3, Disk.YELLOW);

        // Then: The result should be true because there's a vertical win
        assertTrue(result);
    }

    @Test
    void testHorizontalWin() {
        // Given: A row with 4 red disks
        board.getColumns().get(0).set(0, Disk.RED);
        board.getColumns().get(1).set(0, Disk.RED);
        board.getColumns().get(2).set(0, Disk.RED);
        board.getColumns().get(3).set(0, Disk.RED);

        // When: Checking for a horizontal win at position (0, 0) with red player
        boolean result = checkWin.checkWin(0, 0, Disk.RED);

        // Then: The result should be true because there's a horizontal win
        assertTrue(result);
    }

    @Test
    void testDiagonalWin() {
        // Given: A leading diagonal with 4 yellow disks
        board.getColumns().get(0).set(0, Disk.YELLOW);
        board.getColumns().get(1).set(1, Disk.YELLOW);
        board.getColumns().get(2).set(2, Disk.YELLOW);
        board.getColumns().get(3).set(3, Disk.YELLOW);

        // When: Checking for a diagonal win at position (0, 0) with yellow player
        boolean result = checkWin.checkWin(0, 0, Disk.YELLOW);

        // Then: The result should be true because there's a leading diagonal win
        assertTrue(result);
    }

    @Test
    void testDraw() {
        // Given: The board is full with no winner
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                // Alternating disk placement for draw (no vertical, horizontal, or diagonal win)
                board.getColumns().get(x).set(y, (x + y) % 2 == 0 ? Disk.RED : Disk.YELLOW);
            }
        }

        // When: Checking for a draw
        boolean result = checkWin.checkDraw();

        // Then: The result should be true because the board is full with no winner
        assertTrue(result);
    }

    @Test
    void testNoWinner() {
        // Given: A board with no winner yet
        board.getColumns().get(0).set(0, Disk.RED);
        board.getColumns().get(1).set(0, Disk.YELLOW);

        // When: Checking for a win at position (0, 0) with red player
        boolean result = checkWin.checkWin(0, 0, Disk.RED);

        // Then: The result should be false because there is no winning line
        assertFalse(result);
    }
}
