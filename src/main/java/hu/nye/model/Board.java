package hu.nye.model;

import java.util.ArrayList;
import java.util.List;


public class Board {
    private final List<List<Disk>> columns;
    private final int rows;

    public Board(List<List<Disk>> columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }



    public List<List<Disk>> getColumns() {
        return new ArrayList<>(columns);
    }

    public int getRows() {
        return rows;
    }
}
