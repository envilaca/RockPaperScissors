package rockpaperscissors.operations;

import rockpaperscissors.Operation;
import rockpaperscissors.Session;

import java.util.Scanner;

public class RemovePlayer extends Operation {
    public void run(Scanner scanner, Session session) {
        super.run(scanner, session);

        currentPlayerName = session.getCurrentPlayerName();

        System.out.println("Which player would you like to remove?");
        String playerName = scanner.nextLine();
        if (isInvalidName(playerName)) return;

        players.remove(playerName);
        System.out.printf("%s has been removed.%n", playerName);
    }

    private boolean isInvalidName(String playerName) {
        if (isExtintName(playerName)) return true;
        if (playerName.equals(currentPlayerName)) {
            System.out.println("Don't remove yourself!");
            return true;
        }
        return false;
    }

    String currentPlayerName;
}
