package Board;

import java.util.List;

public class BoardWriter extends GameBoardGenerator{

    public BoardWriter(List<List<Disk>> columns, int rows) {
        super(columns, rows);
    }

    public void writeOut() {
        System.out.println(this.toString());
    }


    //This Override was created by ChatGPT.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Disk> column : this.getColumns()) {
            for (Disk disk : column) {
                sb.append(disk.toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
