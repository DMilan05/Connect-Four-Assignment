package ComputerPlayer;


import Board.Disk;
import Board.GameBoard;
import Board.GameBoardGenerator;

import java.util.List;
import java.util.Random;

public class ComputerMove extends GameBoard {
    public ComputerMove(List<List<Disk>> columns, int rows) {
        super(columns, rows);
    }

   public void putADisk() {
       Random random = new Random();

       // Generate a random number between 1 and 7
       int randomNumber = random.nextInt(7) + 1;
   }
}
