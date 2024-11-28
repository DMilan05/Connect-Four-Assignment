package hu.nye.computer.player;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ComputerMoveTest {


    @Test
    public void testGetRandomAvailableColumn() {
        // Given: A mock board with a few columns
        List<Disk> column1 = Arrays.asList(Disk.RED, Disk.EMPTY, Disk.EMPTY);
        List<Disk> column2 = Arrays.asList(Disk.RED, Disk.RED, Disk.RED); // Full column
        List<Disk> column3 = Arrays.asList(Disk.EMPTY, Disk.EMPTY, Disk.EMPTY); // Empty column

        Board mockBoard = Mockito.mock(Board.class);
        when(mockBoard.getColumns()).thenReturn(Arrays.asList(column1, column2, column3));

        // When: Creating a ComputerMove instance and calling getRandomAvailableColumn
        ComputerMove computerMove = new ComputerMove(mockBoard);
        int column = computerMove.getRandomAvailableColumn();

        // Then: The chosen column should have available space
        assertTrue(column == 0 || column == 2, "Expected column to be 0 or 2, as column 1 is full");
    }
    @Test
    void testGetRandomAvailableColumn_shouldThrowExceptionWhenNoColumnsAvailable() {
        // Given: A mock board where all columns are full
        List<Disk> column1 = Arrays.asList(Disk.RED, Disk.YELLOW, Disk.RED);
        List<Disk> column2 = Arrays.asList(Disk.YELLOW, Disk.RED, Disk.YELLOW);
        List<Disk> column3 = Arrays.asList(Disk.RED, Disk.YELLOW, Disk.RED);

        Board mockBoard = Mockito.mock(Board.class);
        when(mockBoard.getColumns()).thenReturn(Arrays.asList(column1, column2, column3));

        // When: Creating a ComputerMove instance
        ComputerMove computerMove = new ComputerMove(mockBoard);

        // Then: Expect an IllegalStateException to be thrown
        Exception exception = assertThrows(
                IllegalStateException.class,
                computerMove::getRandomAvailableColumn,
                "Expected getRandomAvailableColumn to throw IllegalStateException when no columns are available"
        );
        assertEquals("No available columns to make a move", exception.getMessage());
    }


    @Test
    public void testGetCell() {
        // Given: A mock board with some disks placed
        List<Disk> column1 = Arrays.asList(Disk.RED, Disk.YELLOW, Disk.EMPTY);
        List<Disk> column2 = Arrays.asList(Disk.YELLOW, Disk.RED, Disk.EMPTY);

        Board mockBoard = Mockito.mock(Board.class);
        when(mockBoard.getColumns()).thenReturn(Arrays.asList(column1, column2));
        when(mockBoard.getRows()).thenReturn(3);

        // When: Calling getCell to retrieve specific positions
        ComputerMove computerMove = new ComputerMove(mockBoard);

        // Valid indices within the mocked board
        Disk diskAtCell00 = computerMove.getCell(0, 0); // RED in column 0, row 0
        Disk diskAtCell11 = computerMove.getCell(1, 1); // RED in column 1, row 1

        // Then: The correct disks should be returned for each cell
        assertEquals(Disk.RED, diskAtCell00, "Expected RED at (0,0)");
        assertEquals(Disk.RED, diskAtCell11, "Expected RED at (1,1)");
    }
    @Test
    public void testMakeMove() {
        // Given: A real board with specific columns
        List<List<Disk>> columns = new ArrayList<>();
        columns.add(new ArrayList<>(Arrays.asList(Disk.EMPTY, Disk.EMPTY, Disk.EMPTY))); // Column 0
        columns.add(new ArrayList<>(Arrays.asList(Disk.RED, Disk.RED, Disk.RED)));      // Column 1 (Full)
        columns.add(new ArrayList<>(Arrays.asList(Disk.EMPTY, Disk.YELLOW, Disk.RED))); // Column 2

        Board realBoard = new Board(columns, 3);

        // When: Calling makeMove with a YELLOW disk
        ComputerMove computerMove = new ComputerMove(realBoard);
        computerMove.makeMove(Disk.YELLOW);

        // Then: A YELLOW disk should be placed in a valid position in column 0 or 2
        List<List<Disk>> updatedColumns = realBoard.getColumns();

        // Verify that the disk is placed in the lowest available row of column 0 or 2
        boolean validPlacement =
                updatedColumns.get(0).get(2) == Disk.YELLOW || // Lowest row of column 0
                        updatedColumns.get(2).get(0) == Disk.YELLOW;   // Lowest row of column 2
        assertTrue(validPlacement, "Expected a YELLOW disk in the lowest row of column 0 or 2");
    }

    @Test
    public void testToString() {
        // Given: A mock board with specific columns
        List<Disk> column1 = Arrays.asList(Disk.RED, Disk.EMPTY, Disk.EMPTY);
        List<Disk> column2 = Arrays.asList(Disk.YELLOW, Disk.RED, Disk.EMPTY);
        List<Disk> column3 = Arrays.asList(Disk.EMPTY, Disk.EMPTY, Disk.EMPTY);

        Board mockBoard = Mockito.mock(Board.class);
        when(mockBoard.getColumns()).thenReturn(Arrays.asList(column1, column2, column3));

        // When: Calling toString on the ComputerMove instance
        ComputerMove computerMove = new ComputerMove(mockBoard);
        String boardString = computerMove.toString();

        // Then: The string representation should match the board
        String expectedOutput =
                "RED EMPTY EMPTY \n" +
                        "YELLOW RED EMPTY \n" +
                        "EMPTY EMPTY EMPTY \n";
        assertEquals(expectedOutput.trim(), boardString.trim(), "Expected board representation does not match");
    }








}
