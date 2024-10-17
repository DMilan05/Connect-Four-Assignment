package Board;

import javax.crypto.spec.PSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameBoard {
    private final List<List<Disk>> columns;
    private final int rows;

    public GameBoard(List<List<Disk>> columns, int rows) {
        this.columns = new ArrayList<>();
        this.rows = rows;
    }



    public List<List<Disk>> getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }


}
