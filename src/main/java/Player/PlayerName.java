package Player;

import java.util.Objects;
import java.util.Scanner;

public class PlayerName {
    //For this class, I used ChatGPT, because it suggested me the use of InputProvider.
    private final String playerName;
    private InputProvider inputProvider;

    public PlayerName(String playerName, InputProvider inputProvider) {
        this.playerName = playerName;
        this.inputProvider = inputProvider;
    }

    public String getPlayerName() {
        return playerName;
    }


    //For immutability, I used ChatGPT, because it wasn't clear for me what it is and what it is good for.
    //Now I know that it is used when we want something to be final and can not be changed.
    public PlayerName askForPlayerName() {
        System.out.print("Please write your name here: ");
        String newName = inputProvider.getInput();
        return new PlayerName(newName, inputProvider);
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