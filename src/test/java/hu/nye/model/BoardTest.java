package hu.nye.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class BoardTest {

    private List<List<Disk>> columns;
    private int rows;
    private Board board;

    @BeforeEach
    void setUp() {
        // Given a 2x2 board setup
        rows = 2;
        columns = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(Disk.EMPTY, Disk.RED)),
                new ArrayList<>(Arrays.asList(Disk.YELLOW, Disk.EMPTY))
        ));

        // When Board is initialized with these columns and rows
        board = new Board(columns, rows);
    }

    @Test
    void testGetColumns_shouldReturnCorrectColumns() {
        // When getColumns is called
        List<List<Disk>> resultColumns = board.getColumns();

        // Then the returned columns should match the initialized columns
        assertEquals(columns, resultColumns, "Columns should match the initialized values");
    }

    @Test
    void testGetColumns_shouldReturnDeepCopyOfColumns() {
        // When getColumns is called
        List<List<Disk>> resultColumns = board.getColumns();

        // Then the returned list should be a deep copy
        assertNotSame(columns, resultColumns, "Returned columns list should be a new instance");
        for (int i = 0; i < columns.size(); i++) {
            assertNotSame(columns.get(i), resultColumns.get(i), "Each column should be a new instance");
        }
    }

    @Test
    void testGetRows_shouldReturnCorrectRowCount() {
        // When getRows is called
        int resultRows = board.getRows();

        // Then it should match the initialized row count
        assertEquals(rows, resultRows, "Row count should match the initialized value");
    }
}
