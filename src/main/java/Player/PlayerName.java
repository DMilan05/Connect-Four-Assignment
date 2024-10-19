package Player;

import java.util.Objects;
import java.util.Scanner;

public class PlayerName {
    //For this class, I used ChatGPT, because it suggested me the use of InputProvider.
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

    @Override
    public String toString() {
        return "Player Name: " + playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName that = (PlayerName) o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}