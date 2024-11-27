package hu.nye.computer.player;

import hu.nye.model.Board;
import hu.nye.model.Disk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerMove {
    private final Board board;
    private final Random random;

    public ComputerMove(Board board) {
        this.board = board;
        this.random = new Random();
    }

    //Generates a random column and checks if there are any empty slots.
    public int getRandomAvailableColumn() {
        List<List<Disk>> columns = board.getColumns();
        List<Integer> availableColumns = new ArrayList<>();

        for (int i = 0; i < columns.size(); i++) {
            List<Disk> column = columns.get(i);
            if (column.contains(Disk.EMPTY)) {
                availableColumns.add(i);
            }
        }

        if (availableColumns.isEmpty()) {
            throw new IllegalStateException("No available columns to make a move");
        }

        Random random = new Random();
        return availableColumns.get(random.nextInt(availableColumns.size()));
    }

    /*column = random.nextInt(board.getColumns().size());
            */


    //Retrieves the disk at the specified location on the board.
    // x - column index, y - row index. null - empty
    public Disk getCell(int x, int y) {
        assert(x >= 0 && x < board.getColumns().size()) : "Invalid column index: " + x;
        assert(y >= 0 && y < board.getRows()) : "Invalid row index: " + y;

        List<Disk> column = board.getColumns().get(x);
        return (column.size() > y) ? column.get(y) : null;
    }



    //The computer makes a move with the random generated column.
    /*public void makeMove(Disk disk) {
        int columnIndex = getRandomAvailableColumn();
        List<Disk> column = board.getColumns().get(columnIndex);

        for (int i = column.size() - 1; i >= 0; i--) {
            if (column.get(i) == Disk.EMPTY) {
                column.set(i, disk);
                return;
            }
        }

        throw new IllegalStateException("No empty space found in the selected column");
    }*/
    public void makeMove(Disk disk) {
        int column = getRandomAvailableColumn(); // Ensure this picks a valid column
        for (int row = board.getRows() - 1; row >= 0; row--) {
            if (board.getColumns().get(column).get(row) == Disk.EMPTY) {
                board.getColumns().get(column).set(row, disk);
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
