package Game;

import Board.Disk;
import Board.GameBoard;

import java.util.List;

public class Move extends GameBoard{

    public Move(List<List<Disk>> columns, int rows) {
        super(columns, rows);
    }

    public Disk getCell(int x, int y) {
        assert(x >= 0 && x < this.getColumns().size()): "Invalid column index: " + x;
        assert(y >= 0 && y < getRows()): "Invalid row index: " + y;

        List<Disk> column = this.getColumns().get(x);

        if (column.size() > y) {
            return column.get(y);
        } else {
            return null;
        }
    }
    public void move(int x, Disk player) {
        assert(x >= 0 && x < getColumns().toArray().length) : "Invalid column index: " + x;;

        List<Disk> column = getColumns().get(x);
        if (column.size() >= this.getRows()) {
            throw new IllegalArgumentException("That column is full");
        }


        column.add(player);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Disk> column : this.getColumns()) {
            for (Disk disk : column) {
                sb.append(disk.toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
