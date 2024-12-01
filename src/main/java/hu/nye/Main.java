package hu.nye;

import hu.nye.database.DatabaseManager;
import hu.nye.database.HighScoreService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Game initialization started.");

        // Initialize the game board and components
        GameBoardGenerator generator = new GameBoardGenerator();
        Board board = generator.generateBoard(7, 6);
        BoardWriter boardWriter = new BoardWriter(board);
        CheckWin checkWin = new CheckWin();
        InputProvider inputProvider = new ConsoleInputProvider();
        PlayerName humanPlayer = new PlayerName("", inputProvider);
        humanPlayer = humanPlayer.askForPlayerName();

        LOGGER.info("Human player '{}' has joined the game.", humanPlayer.getPlayerName());

        ComputerMove computerPlayer = new ComputerMove(board);
        Move moveService = new Move(board);
        DatabaseManager databaseManager = new DatabaseManager();
        HighScoreService highScoreService = new HighScoreService(databaseManager);

        boolean gameOn = true;
        Disk currentPlayerDisk = Disk.YELLOW;  // Start with the human player

        boardWriter.writeOut();

        while (gameOn) {
            LOGGER.info("Current player: {} (Disk: {}).",
                    currentPlayerDisk == Disk.YELLOW ? humanPlayer.getPlayerName() : "Computer",
                    currentPlayerDisk);

            if (currentPlayerDisk == Disk.YELLOW) {
                // Human's turn
                System.out.print("Enter a column (0 to 6): ");
                int column = Integer.parseInt(inputProvider.getInput());

                try {
                    moveService.move(column, Disk.YELLOW);  // Human move
                    LOGGER.info("Human player '{}' chose column {}", humanPlayer.getPlayerName(), column);
                    boardWriter.writeOut();

                    if (checkWin.checkWin(board, currentPlayerDisk)) {
                        LOGGER.info("{} wins the game!", humanPlayer.getPlayerName());
                        System.out.println(humanPlayer.getPlayerName() + " wins!");
                        highScoreService.savePlayerWin(humanPlayer.getPlayerName());
                        gameOn = false;
                    }
                } catch (IllegalArgumentException e) {
                    LOGGER.warn("Invalid move by human player '{}': {}", humanPlayer.getPlayerName(), e.getMessage());
                    System.out.println("Invalid move: " + e.getMessage());
                    continue;
                }
            } else {
                // Computer's turn
                int column = computerPlayer.getRandomAvailableColumn();
                LOGGER.info("Computer chose column {}", column);

                try {
                    moveService.move(column, Disk.RED);  // Computer move
                    boardWriter.writeOut();

                    if (checkWin.checkWin(board, currentPlayerDisk)) {
                        LOGGER.info("Computer wins the game!");
                        System.out.println("Computer wins!");
                        highScoreService.saveComputerPlayerWin("Computer");
                        gameOn = false;
                    }
                } catch (IllegalArgumentException e) {
                    LOGGER.warn("Invalid move by computer: {}", e.getMessage());
                    System.out.println("Invalid move: " + e.getMessage());
                    continue;
                }
            }

            // Check for a draw
            if (board.isFull()) {
                LOGGER.info("The game ended in a draw.");
                System.out.println("It's a draw!");
                gameOn = false;
                break;
            }

            // Switch player
            currentPlayerDisk = (currentPlayerDisk == Disk.YELLOW) ? Disk.RED : Disk.YELLOW;
        }

        highScoreService.printHighScores();
        LOGGER.info("Game over. High scores printed.");
    }
}


//I just did this because in the IntelliJ Jacoco report
// it says 80% percent coverage. With the new one (the one with the loggers) it says 76%.
//But if I check index.html, it still says 96 percent.
//Main is closed out in the pom.xml, and also clean install runs well.



/*package hu.nye;


    import hu.nye.database.DatabaseManager;
    import hu.nye.database.HighScoreService;
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

    import java.util.List;
    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            // Initialize the game board and components
            GameBoardGenerator generator = new GameBoardGenerator();
            Board board = generator.generateBoard(7, 6);
            BoardWriter boardWriter = new BoardWriter(board);
            CheckWin checkWin = new CheckWin();
            InputProvider inputProvider = new ConsoleInputProvider();
            PlayerName humanPlayer = new PlayerName("", inputProvider);
            humanPlayer = humanPlayer.askForPlayerName();
            System.out.println(humanPlayer);
            ComputerMove computerPlayer = new ComputerMove(board);
            Move moveService = new Move(board);
            DatabaseManager databaseManager = new DatabaseManager();
            HighScoreService highScoreService = new HighScoreService(databaseManager);

            boolean gameOn = true;
            Disk currentPlayerDisk = Disk.YELLOW;  // Start with the human player

            boardWriter.writeOut();

            while (gameOn) {
                // Print the current player
                System.out.println("Current player: " + currentPlayerDisk);

                if (currentPlayerDisk == Disk.YELLOW) {
                    // Human's turn
                    System.out.print("Enter a column (0 to 6): ");
                    int column = Integer.parseInt(inputProvider.getInput());

                    try {
                        moveService.move(column, Disk.YELLOW);  // Human move
                        boardWriter.writeOut();
                        if (checkWin.checkWin(board, currentPlayerDisk)) {
                            System.out.println(humanPlayer.getPlayerName() + " wins!");
                            highScoreService.savePlayerWin(humanPlayer.getPlayerName());
                            gameOn = false;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid move: " + e.getMessage());
                        continue;
                    }
                } else {
                    // Computer's turn
                    int column = computerPlayer.getRandomAvailableColumn();

                    try {
                        moveService.move(column, Disk.RED);  // Computer move
                        boardWriter.writeOut();
                        if (checkWin.checkWin(board, currentPlayerDisk)) {
                            System.out.println("Computer wins!");
                            highScoreService.saveComputerPlayerWin("Computer");
                            gameOn = false;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid move: " + e.getMessage());
                        continue;
                    }
                }
                // Check for a draw
                if (board.isFull()) { // Az új isFull metódus hívása
                    System.out.println("It's a draw!");
                    gameOn = false;
                    break;
                }





                // Switch player
                currentPlayerDisk = (currentPlayerDisk == Disk.YELLOW) ? Disk.RED : Disk.YELLOW;


            }
            highScoreService.printHighScores();

        }
    }*/



