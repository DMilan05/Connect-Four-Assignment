package hu.nye.board;

import hu.nye.model.Board;
import hu.nye.model.Disk;

import java.util.ArrayList;
import java.util.List;

public class GameBoardGenerator {


    public GameBoardGenerator() {

    }

    public Board generateBoard(int column,int row) {
        List<List<Disk>> boardColumns = new ArrayList<>();
        List<Disk> disks = new ArrayList<>();
        for(int j = 0; j < row; j++) {
            disks.add(Disk.EMPTY);
        }
        for(int i = 0; i<column;i++) {
            boardColumns.add(disks);
        }
        Board board = new Board(boardColumns,row);
        //generate rows, generate columns
        return board;
    }
}
