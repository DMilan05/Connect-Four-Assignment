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
        assert(x >= 0 && x < board.getColumns().size()): "Invalid column index: " + x;
        assert(y >= 0 && y < board.getRows()): "Invalid row index: " + y;

        List<Disk> column = board.getColumns().get(x);

        if (column.size() > y) {
            return column.get(y);
        } else {
            return null;
        }
    }
    public void move(int x, Disk player) {
        assert(x >= 0 && x < board.getColumns().toArray().length) : "Invalid column index: " + x;;

        List<Disk> column = board.getColumns().get(x);
        if (column.size() >= board.getRows()) {
            throw new IllegalArgumentException("That column is full");
        }


        column.add(player);
        //return CheckWin.checkWin(x, column.size() - 1, player);
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
    //Nem a dokumentáció/kód részét képzik a következő sorok. Csak tanácsok az oktatótól.
    //paraméterként adjuk be az adott metódusoknak, amelyek használják (ne örököltessük a GameBoardból, mivel
    //ezek servicek, ne terjesszék ki (Move GameBoard-e? -> igen -> lehet)

    //GameBoardGenerator -> service
    //Move - VO

    //Move ValueObject: mi a valid lépés, serviceként implementáljam
    //minél kevesebb adat átadása paraméterként
    //ha valami csak rowt használ, ne adjam át az egész objektumot, csak az instance rowját
}
