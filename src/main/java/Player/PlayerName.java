package Player;

import com.sun.tools.jdeprscan.scan.Scan;

import java.util.Scanner;

public class PlayerName {
    public String playerName;

    public PlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void askForPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("PLease write your name here: ");
        setPlayerName(scanner.nextLine());
    }
}
