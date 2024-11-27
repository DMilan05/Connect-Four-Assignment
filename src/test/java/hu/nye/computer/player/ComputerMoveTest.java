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






}
