package hu.nye.computer.player;

import hu.nye.model.Board;
import hu.nye.model.Disk;

import java.util.List;
import java.util.Random;

public class ComputerMove {
    private final Board board;
    private final Random random;

    public ComputerMove(Board board) {
        this.board = board;
        this.random = new Random();
    }

    /**
     * Generates a random column index where a disk can be placed.
     * Ensures the column is not already full.
     *
     * @return an available column index for placing a disk.
     */
    public int getRandomAvailableColumn() {
        int column;
        do {
            column = random.nextInt(board.getColumns().size());
        } while (board.getColumns().get(column).size() >= board.getRows());
        return column;
    }

    /**
     * Retrieves the disk at the specified location on the board.
     *
     * @param x the column index
     * @param y the row index
     * @return the disk at the given location or null if the location is empty
     */
    public Disk getCell(int x, int y) {
        assert(x >= 0 && x < board.getColumns().size()) : "Invalid column index: " + x;
        assert(y >= 0 && y < board.getRows()) : "Invalid row index: " + y;

        List<Disk> column = board.getColumns().get(x);
        return (column.size() > y) ? column.get(y) : null;
    }

    /**
     * Places a disk in a random available column.
     *
     * @param player the disk to place in the chosen column
     */
    public void makeMove(Disk player) {
        int columnIndex = getRandomAvailableColumn();
        List<Disk> column = board.getColumns().get(columnIndex);

        if (column.size() >= board.getRows()) {
            throw new IllegalArgumentException("Selected column is full");
        }

        column.add(player);
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
