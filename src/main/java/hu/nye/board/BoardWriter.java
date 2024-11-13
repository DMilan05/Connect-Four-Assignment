package hu.nye.board;

import hu.nye.model.Board;
import hu.nye.model.Disk;

import java.util.List;

public class BoardWriter {
    private final Board board;

    public BoardWriter(Board board) {
        this.board = board;
    }

    public void writeOut() {
        System.out.println(this.toString());
    }


    //This Override was created by ChatGPT.
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
