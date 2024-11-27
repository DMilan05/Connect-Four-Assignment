package hu.nye.winning.conditions;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import hu.nye.board.GameBoardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("CheckWin Test")
class CheckWinTest {

    private final CheckWin checkWin = new CheckWin();
    private final GameBoardGenerator boardGenerator = new GameBoardGenerator();

    @Test
    @DisplayName("Should detect a horizontal win")
    void givenBoardWithHorizontalWin_whenCheckWin_thenReturnsTrue() {
        // Given
        Board board = boardGenerator.generateBoard(7, 6);
        List<List<Disk>> columns = board.getColumns();
        for (int col = 0; col < 4; col++) {
            columns.get(col).set(0, Disk.YELLOW); // Place four yellow disks in a row
        }

        // When
        boolean result = checkWin.checkWin(board, Disk.YELLOW);

        // Then
        assertTrue(result, "Horizontal win should be detected");
    }

    @Test
    @DisplayName("Should detect a vertical win")
    void givenBoardWithVerticalWin_whenCheckWin_thenReturnsTrue() {
        // Given
        Board board = boardGenerator.generateBoard(7, 6);
        List<List<Disk>> columns = board.getColumns();
        for (int row = 0; row < 4; row++) {
            columns.get(0).set(row, Disk.RED); // Place four red disks in a column
        }

        // When
        boolean result = checkWin.checkWin(board, Disk.RED);

        // Then
        assertTrue(result, "Vertical win should be detected");
    }

    @Test
    @DisplayName("Should detect a diagonal (top-left to bottom-right) win")
    void givenBoardWithDiagonalTopLeftToBottomRightWin_whenCheckWin_thenReturnsTrue() {
        // Given
        Board board = boardGenerator.generateBoard(7, 6);
        List<List<Disk>> columns = board.getColumns();
        for (int i = 0; i < 4; i++) {
            columns.get(i).set(i, Disk.YELLOW); // Place yellow disks diagonally
        }

        // When
        boolean result = checkWin.checkWin(board, Disk.YELLOW);

        // Then
        assertTrue(result, "Diagonal (top-left to bottom-right) win should be detected");
    }

    @Test
    @DisplayName("Should detect a diagonal (bottom-left to top-right) win")
    void givenBoardWithDiagonalBottomLeftToTopRightWin_whenCheckWin_thenReturnsTrue() {
        // Given
        Board board = boardGenerator.generateBoard(7, 6);
        List<List<Disk>> columns = board.getColumns();
        for (int i = 0; i < 4; i++) {
            columns.get(i).set(3 - i, Disk.RED); // Place red disks diagonally
        }

        // When
        boolean result = checkWin.checkWin(board, Disk.RED);

        // Then
        assertTrue(result, "Diagonal (bottom-left to top-right) win should be detected");
    }

    @Test
    @DisplayName("Should not detect a win when no win condition is met")
    void givenBoardWithoutWin_whenCheckWin_thenReturnsFalse() {
        // Given
        Board board = boardGenerator.generateBoard(7, 6);

        // When
        boolean result = checkWin.checkWin(board, Disk.YELLOW);

        // Then
        assertFalse(result, "No win should be detected on an empty board");
    }
}
