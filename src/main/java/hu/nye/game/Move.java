package hu.nye.game;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import hu.nye.winning.conditions.CheckWin;

import java.util.List;

public class Move{
    private final Board board;
    public Move(Board board) {
        this.board = board;
    }

    public Disk getCell(int x, int y) {
        if (x < 0 || x >= board.getColumns().size()) {
            return null;
        }
        // Validate row index
        if (y < 0 || y >= board.getRows()) {
            return null;
        }

        List<Disk> column = board.getColumns().get(x);

        if (column.size() > y) {
            return column.get(y);
        } else {
            return null;
        }
    }

    public void move(int x, Disk player) {
        // Validate the column index
        if (x < 0 || x >= board.getColumns().size()) {
            throw new IllegalArgumentException("Invalid column index.");
        }

        List<Disk> column = board.getColumns().get(x);

        // Check if the column is full
        if (!column.contains(Disk.EMPTY)) {
            throw new IllegalArgumentException("That column is full.");
        }

        // Place the disk in the lowest available slot
        for (int i = column.size() - 1; i >= 0; i--) {
            if (column.get(i) == Disk.EMPTY) {
                column.set(i, player); // Replace EMPTY with the player's disk
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Disk> column : board.getColumns()) {
            for (Disk disk : column) {
                sb.append(disk.toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
