package Player;

import java.util.Scanner;

public class PlayerName {
    private String playerName;
    private InputProvider inputProvider;

    public PlayerName(String playerName, InputProvider inputProvider) {
        this.playerName = playerName;
        this.inputProvider = inputProvider;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void askForPlayerName() {
        System.out.print("Please write your name here: ");
        setPlayerName(inputProvider.getInput());
    }
}