package hu.nye.board;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameBoardGeneratorTest {

    private GameBoardGenerator gameBoardGenerator;

    @BeforeEach
    void setUp() {
        // Given a new instance of GameBoardGenerator
        gameBoardGenerator = new GameBoardGenerator();
    }

    @Test
    void testGenerateBoard_shouldCreateBoardWithCorrectDimensions() {
        // Given column and row values
        int columns = 3;
        int rows = 4;

        // When generateBoard is called with the given dimensions
        Board board = gameBoardGenerator.generateBoard(columns, rows);

        // Then the board should have the specified number of columns and rows
        assertEquals(columns, board.getColumns().size(), "Number of columns should match");
        board.getColumns().forEach(column ->
                assertEquals(rows, column.size(), "Each column should have the specified number of rows"));
    }

    @Test
    void testGenerateBoard_shouldInitializeAllCellsAsEmpty() {
        // Given specific dimensions for the board
        int columns = 2;
        int rows = 3;

        // When generateBoard is called
        Board board = gameBoardGenerator.generateBoard(columns, rows);

        // Then each cell in the board should be initialized as EMPTY
        for (List<Disk> column : board.getColumns()) {
            for (Disk disk : column) {
                assertEquals(Disk.EMPTY, disk, "Each cell should be initialized as EMPTY");
            }
        }
    }

    @Test
    void testGenerateBoard_shouldHandleZeroDimensions() {
        // Given zero for both columns and rows
        int columns = 0;
        int rows = 0;

        // When generateBoard is called
        Board board = gameBoardGenerator.generateBoard(columns, rows);

        // Then the board should contain no columns
        assertTrue(board.getColumns().isEmpty(), "Board should have no columns when zero dimensions are provided");
    }
}
