package hu.nye.winning.conditions;

import hu.nye.model.Board;
import hu.nye.model.Disk;

import java.util.List;

public class CheckWin {
    private final Board board;
    private final List<List<Disk>> columns;
    private final int rows;

    public CheckWin(Board board) {
        this.board = board;
        this.columns = board.getColumns();
        this.rows = board.getRows();
    }

    // Access a specific cell in the board and handle out-of-bounds
    private Disk getCell(int x, int y) {
        if (x >= 0 && x < columns.size() && y >= 0 && y < rows) {
            return columns.get(x).get(y); // Retrieve disk at column x, row y
        }
        return Disk.EMPTY; // Return EMPTY if out of bounds
    }

    private boolean checkLine(int xStart, int yStart, int xDir, int yDir, Disk player) {
        for (int i = 0; i < 4; ++i) {
            int x = xStart + xDir * i;
            int y = yStart + yDir * i;

            if (x < 0 || x >= columns.size() || y < 0 || y >= rows) {
                return false; // Out of bounds
            }

            Disk cell = getCell(x, y);
            if (cell == null || cell != player) { // Use direct comparison
                return false; // Stop if we encounter a different disk
            }
        }
        return true;
    }

    // Checks all directions from a given point for a win
    public boolean checkWin(int x, int y, Disk player) {
        // Vertical (downwards from current position)
        if (checkLine(x, y, 0, -1, player)) {
            return true;
        }

        // Check horizontal and both diagonal lines
        for (int offset = 0; offset < 4; ++offset) {
            // Horizontal
            if (checkLine(x - 3 + offset, y, 1, 0, player)) {
                return true;
            }
            // Leading diagonal (top-left to bottom-right)
            if (checkLine(x - 3 + offset, y + 3 - offset, 1, -1, player)) {
                return true;
            }
            // Trailing diagonal (bottom-left to top-right)
            if (checkLine(x - 3 + offset, y - 3 + offset, 1, 1, player)) {
                return true;
            }
        }
        return false;
    }

    // Check for a draw (no empty slots left and no winner)
    public boolean checkDraw() {
        for (List<Disk> column : columns) {
            if (column.contains(Disk.EMPTY)) {
                return false; // If any cell is empty, it's not a draw
            }
        }
        return true; // No empty cells left
    }
}
