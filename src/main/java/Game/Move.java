package Game;

import Board.Disk;
import Board.GameBoard;

import java.util.List;

public class Move extends GameBoard{
    GameBoard gameBoard = new GameBoard(this.getColumns(),this.getRows());
    public Move(List<List<Disk>> columns, int rows) {
        super(columns, rows);
    }

    public Disk getCell(int x, int y) {
        assert(x >= 0 && x < this.getColumns().size());
        assert(y >= 0 && y < getRows());

        List<Disk> column = this.getColumns().get(x);

        if (column.size() > y) {
            return column.get(y);
        } else {
            return null;
        }
    }
    public void move(int x, Disk player) {
        assert(x >= 0 && x < getColumns().toArray().length);

        List<Disk> column = getColumns().get(x);
        if (column.size() >= this.getRows()) {
            throw new IllegalArgumentException("That column is full");
        }


        column.add(player);
    }


}
