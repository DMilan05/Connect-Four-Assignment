package hu.nye.winning.conditions;

import hu.nye.model.Board;
import hu.nye.model.Disk;

import java.util.List;
import hu.nye.game.Move;

public class CheckWin {
    private final List<List<Disk>> columns;
    private final int rows;
    private final Board board;

    public CheckWin(List<List<Disk>> columns, int rows, Board board) {
        this.columns = columns;
        this.rows = rows;
        this.board = board;
    }
    /*private boolean checkLine(int x1, int y1, int xDiff, int yDiff, Disk player) {
        for (int i = 0; i < 4; ++i) {
            int x = x1 + (xDiff * i);
            int y = y1 + (yDiff * i);

            if (x < 0 || x > columns.size() - 1) {
                return false;
            }

            if (y < 0 || y > rows - 1) {
                return false;
            }

        }

        if (player != getCell(x, y)) {
            return false;
        }

        return true;
    }*/
    // Add this method to access cells on the board
    private Disk getCell(int x, int y) {
        if (x >= 0 && x < columns.size() && y >= 0 && y < rows) {
            return columns.get(x).get(y); // Retrieve disk at column x, row y
        }
        return null; // Return null if out of bounds
    }

    private boolean checkLine(int x1, int y1, int xDiff, int yDiff, Disk player) {
        for (int i = 0; i < 4; ++i) {
            int x = x1 + (xDiff * i);
            int y = y1 + (yDiff * i);

            if (x < 0 || x >= columns.size() || y < 0 || y >= rows) {
                return false;
            }

            Disk cell = getCell(x, y);
            if (cell == null || !cell.equals(player)) { // Use equals() for comparison
                return false;
            }
        }
        return true;
    }

    public boolean checkWin(int x, int y, Disk player) {
        // Vertical line
        if (checkLine(x, y, 0, -1, player)) {
            return true;
        }

        for (int offset = 0; offset < 4; ++offset) {
            // Horizontal line
            if (checkLine(x - 3 + offset, y, 1, 0, player)) {
                return true;
            }

            // Leading diagonal
            if (checkLine(x - 3 + offset, y + 3 - offset, 1, -1, player)) {
                return true;
            }

            // Trailing diagonal
            if (checkLine(x - 3 + offset, y - 3 + offset, 1, 1, player)) {
                return true;
            }
        }

        return false;
    }
}
