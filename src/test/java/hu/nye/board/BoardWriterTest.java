package hu.nye.board;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardWriterTest {

    @Test
    public void testToString() {
        // Given: A mock board with some test data
        List<Disk> column1 = Arrays.asList(Disk.RED, Disk.YELLOW, Disk.EMPTY);
        List<Disk> column2 = Arrays.asList(Disk.YELLOW, Disk.RED, Disk.EMPTY);
        List<Disk> column3 = Arrays.asList(Disk.EMPTY, Disk.EMPTY, Disk.RED);

        Board mockBoard = Mockito.mock(Board.class);
        Mockito.when(mockBoard.getColumns()).thenReturn(Arrays.asList(column1, column2, column3));

        // When: Creating a BoardWriter and calling toString
        BoardWriter boardWriter = new BoardWriter(mockBoard);
        String result = boardWriter.toString();

        // Then: The output should match the expected formatted string
        String expected = "+----------+----------+----------+\n" +
                "|  RED     |  YELLOW  |  EMPTY   |\n" +
                "+----------+----------+----------+\n" +
                "|  YELLOW  |  RED     |  EMPTY   |\n" +
                "+----------+----------+----------+\n" +
                "|  EMPTY   |  EMPTY   |  RED     |\n" +
                "+----------+----------+----------+\n";

        assertEquals(expected, result);
    }
}
