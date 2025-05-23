package rockpaperscissors.operations;

import rockpaperscissors.Operation;
import rockpaperscissors.Player;
import rockpaperscissors.Session;

import java.util.Map;
import java.util.Scanner;

public class ViewScore extends Operation {
    @Override
    public void run(Scanner scanner, Session session) {
        super.run(scanner, session);

        for (Map.Entry<String, Player> playerEntry : players.entrySet()) {
            Player player = playerEntry.getValue();
            System.out.println("\n" + playerEntry.getKey());
            System.out.println("  Wins:   " + player.getWins());
            System.out.println("  Losses: " + player.getLosses());
            System.out.println("  Draws:  " + player.getDraws());
        }
    }
}
