package Board;

import java.util.List;

public class BoardWriter extends GameBoardGenerator{


    public BoardWriter(List<List<Disk>> columns, int rows) {
        super(columns, rows);
    }

    public void writeOut() {
        System.out.println(this.getColumns());
    }

}
