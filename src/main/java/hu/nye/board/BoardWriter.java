package hu.nye.board;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.util.List;

public class BoardWriter {
    private final Board board;

    public BoardWriter(Board board) {
        this.board = board;
    }

    public void writeOut() {
        // Print the board state after every move
        System.out.println(this.toString());
    }


    //This Override was created by ChatGPT.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int numRows = board.getColumns().get(0).size(); // Number of rows
        int numCols = board.getColumns().size();       // Number of columns

        // Build a larger divider line
        StringBuilder rowDivider = new StringBuilder();
        for (int i = 0; i < numCols; i++) {
            rowDivider.append("+----------");
        }
        rowDivider.append("+\n"); // Add the final '+'

        for (int row = 0; row < numRows; row++) {
            sb.append(rowDivider); // Add the divider line before each row
            for (int col = 0; col < numCols; col++) {
                sb.append("|  ").append(String.format("%-8s", board.getColumns().get(col).get(row)));
            }
            sb.append("|\n"); // Close the row with a vertical bar
        }
        sb.append(rowDivider); // Add the final divider line at the bottom

        return sb.toString();
    }





}
