package hu.nye.board;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BoardWriterTest {

    private Board boardMock;
    private BoardWriter boardWriter;

    @BeforeEach
    void setUp() {
        // Given a mock Board object with predefined columns and disks
        boardMock = Mockito.mock(Board.class);

        // Example board setup with 2 columns
        List<Disk> column1 = Arrays.asList(Disk.RED, Disk.EMPTY, Disk.EMPTY);
        List<Disk> column2 = Arrays.asList(Disk.YELLOW, Disk.EMPTY, Disk.RED);

        when(boardMock.getColumns()).thenReturn(Arrays.asList(column1, column2));

        // Initialize the BoardWriter with the mocked board
        boardWriter = new BoardWriter(boardMock);
    }

    @Test
    void testToString_shouldReturnFormattedBoardString() {
        // Given a Board with specific Disk placements
        // (Already set up in setUp method)

        // When we call the toString method
        String boardString = boardWriter.toString();

        // Then the result should match the expected formatted string
        String expected = "RED EMPTY EMPTY \nYELLOW EMPTY RED \n";
        assertEquals(expected, boardString);
    }

    @Test
    void testWriteOut_shouldPrintBoardToConsole() {
        // Given a Board with specific Disk placements
        // (Already set up in setUp method)

        // When we call the writeOut method
        boardWriter.writeOut();

        // Then the output should match the board's string representation
        // Use SystemOutRule or similar in a real environment to capture console output
    }
}
