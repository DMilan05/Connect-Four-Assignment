package Board;

import java.util.ArrayList;
import java.util.List;

public class GameBoardGenerator extends GameBoard{

    public GameBoardGenerator(List<List<Disk>> columns, int rows) {
        super(columns, rows);
    }



    public void generateBoard() {
        this.getColumns().add(new ArrayList<>());
    }
}
