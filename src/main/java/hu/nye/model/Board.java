package hu.nye.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Disk>> columns;
    private final int rows;

    public Board(List<List<Disk>> columns, int rows) {
        // Deep copy the columns list to prevent external modifications
        this.columns = new ArrayList<>();
        for (List<Disk> column : columns) {
            this.columns.add(new ArrayList<>(column));  // Create a new list for each column
        }
        this.rows = rows;
    }


    public List<List<Disk>> getColumns() {
        return columns; // Return the original list
    }

    public int getRows() {
        return rows;
    }
    public boolean isFull() {
        for (List<Disk> column : columns) {
            for (Disk disk : column) {
                if (disk == Disk.EMPTY) {
                    return false; // If there is some empty place, then the board is not full.
                }
            }
        }
        return true; // If there is not any empty place, then the board is full.
    }

}

