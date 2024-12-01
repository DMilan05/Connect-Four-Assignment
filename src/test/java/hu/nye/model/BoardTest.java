package hu.nye.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    // Given-When-Then test for Board constructor and methods
    @Test
    void testBoardConstructorAndGetters() {
        // Given
        List<List<Disk>> columns = new ArrayList<>();
        columns.add(new ArrayList<>());  // First column (empty)
        columns.add(new ArrayList<>());  // Second column (empty)
        int rows = 6;  // Example number of rows for the board

        Board board = new Board(columns, rows);

        // When
        List<List<Disk>> resultColumns = board.getColumns();
        int resultRows = board.getRows();

        // Then
        assertEquals(columns.size(), resultColumns.size(), "The number of columns should be the same as the input list");
        assertEquals(rows, resultRows, "The number of rows should match the input value");

        // Ensure that the original list of columns is not modified by the getter
        resultColumns.get(0).add(Disk.YELLOW);  // Modify the returned list (adding a disk)
        assertTrue(columns.get(0).isEmpty(), "The original columns list should remain unchanged after modification");
    }

    // Test for isFull method
    @Test
    void testIsFullWhenBoardIsNotFull() {
        // Given
        List<List<Disk>> columns = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            List<Disk> column = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                column.add(Disk.EMPTY); // All cells are empty
            }
            columns.add(column);
        }
        Board board = new Board(columns, 6);

        // When
        boolean result = board.isFull();

        // Then
        assertFalse(result, "The board should not be full when all cells are empty");
    }

    @Test
    void testIsFullWhenBoardIsFull() {
        // Given
        List<List<Disk>> columns = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            List<Disk> column = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                column.add(Disk.YELLOW); // All cells are occupied
            }
            columns.add(column);
        }
        Board board = new Board(columns, 6);

        // When
        boolean result = board.isFull();

        // Then
        assertTrue(result, "The board should be full when all cells are occupied");
    }

    @Test
    void testIsFullWhenBoardIsPartiallyFull() {
        // Given
        List<List<Disk>> columns = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            List<Disk> column = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                if (j % 2 == 0) {
                    column.add(Disk.YELLOW); // Some cells are occupied
                } else {
                    column.add(Disk.EMPTY); // Some cells are empty
                }
            }
            columns.add(column);
        }
        Board board = new Board(columns, 6);

        // When
        boolean result = board.isFull();

        // Then
        assertFalse(result, "The board should not be full when some cells are empty");
    }
}
