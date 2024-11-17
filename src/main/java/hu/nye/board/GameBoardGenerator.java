package hu.nye.board;

import hu.nye.model.Board;
import hu.nye.model.Disk;

import java.util.ArrayList;
import java.util.List;

public class GameBoardGenerator {


    public GameBoardGenerator() {

    }

    public Board generateBoard(int column, int row) {
        List<List<Disk>> boardColumns = new ArrayList<>();

        // Create a new list for each column
        for (int i = 0; i < column; i++) {
            List<Disk> columnDisks = new ArrayList<>();
            for (int j = 0; j < row; j++) {
                columnDisks.add(Disk.EMPTY);
            }
            boardColumns.add(columnDisks);
        }

        return new Board(boardColumns, row);
    }


}
