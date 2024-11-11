package hu.nye;

import hu.nye.model.Board;
import hu.nye.model.Disk;
import hu.nye.board.BoardWriter;
import hu.nye.board.GameBoardGenerator;
import hu.nye.computer.player.ComputerMove;
import hu.nye.game.Move;
import hu.nye.player.ConsoleInputProvider;
import hu.nye.player.InputProvider;
import hu.nye.player.PlayerName;
import hu.nye.winning.conditions.CheckWin;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int columns = 7; // Standard Connect 4 grid width
        int rows = 6;    // Standard Connect 4 grid height

        // Initialize board and components
        GameBoardGenerator generator = new GameBoardGenerator();
        Board board = generator.generateBoard(columns, rows);
        BoardWriter boardWriter = new BoardWriter(board);
        CheckWin checkWin = new CheckWin(board.getColumns(), rows, board);

        // Initialize players
        InputProvider inputProvider = new ConsoleInputProvider();
        PlayerName humanPlayer = new PlayerName("Human", inputProvider);
        ComputerMove computerPlayer = new ComputerMove(board);
        Move moveService = new Move(board);

        boolean gameOn = true;
        Disk currentPlayerDisk = Disk.YELLOW;  // Human player starts with YELLOW

        System.out.println("Welcome to Connect 4!");
        boardWriter.writeOut();

        while (gameOn) {
            if (currentPlayerDisk == Disk.YELLOW) {
                // Human move
                System.out.print("Enter a column (0 to " + (columns - 1) + "): ");
                int column = Integer.parseInt(inputProvider.getInput());

                try {
                    moveService.move(column, Disk.YELLOW);
                    if (checkWin.checkWin(column, board.getColumns().get(column).size() - 1, Disk.YELLOW)) {
                        System.out.println("Congratulations! " + humanPlayer.getPlayerName() + " wins!");
                        gameOn = false;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue; // Retry the move
                }
            } else {
                // Computer move
                computerPlayer.makeMove(Disk.RED);
                int column = computerPlayer.getRandomAvailableColumn();
                System.out.println("Computer places a disk in column " + column);

                if (checkWin.checkWin(column, board.getColumns().get(column).size() - 1, Disk.RED)) {
                    System.out.println("Computer wins!");
                    gameOn = false;
                }
            }

            boardWriter.writeOut(); // Display the board after each move

            // Check for a draw
            if (board.getColumns().stream().allMatch(col -> col.size() >= rows)) {
                System.out.println("It's a draw!");
                gameOn = false;
            }

            // Switch player turn
            currentPlayerDisk = (currentPlayerDisk == Disk.YELLOW) ? Disk.RED : Disk.YELLOW;
        }
    }
}
