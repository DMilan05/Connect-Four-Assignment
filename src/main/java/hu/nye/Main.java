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

    import java.util.List;
    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            // Initialize the game board and components
            GameBoardGenerator generator = new GameBoardGenerator();
            Board board = generator.generateBoard(7, 6);
            BoardWriter boardWriter = new BoardWriter(board);
            CheckWin checkWin = new CheckWin(board);
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

                        // Check for win or draw
                        if (checkWin.checkWin(column, board.getColumns().get(column).size() - 1, Disk.YELLOW)) {
                            System.out.println(humanPlayer.getPlayerName() + " wins!");
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

                        // Check for win or draw
                        if (checkWin.checkWin(column, board.getColumns().get(column).size() - 1, Disk.RED)) {
                            System.out.println("Computer wins!");

                            gameOn = false;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid move: " + e.getMessage());
                        continue;
                    }
                }

                // Check for draw
                if (checkWin.checkDraw()) {
                    System.out.println("It's a draw!");
                    highScoreService.savePlayerWin(humanPlayer.getPlayerName());
                    gameOn = false;
                }

                // Switch player
                currentPlayerDisk = (currentPlayerDisk == Disk.YELLOW) ? Disk.RED : Disk.YELLOW;


            }
            highScoreService.printHighScores();

        }
    }



