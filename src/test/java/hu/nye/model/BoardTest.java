package hu.nye.model;

import hu.nye.model.Board;
import hu.nye.model.Disk;
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
}
