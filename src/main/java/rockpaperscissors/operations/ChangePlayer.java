package rockpaperscissors.operations;

import rockpaperscissors.Operation;
import rockpaperscissors.Session;

import java.util.Scanner;

public class ChangePlayer extends Operation {
    @Override
    public void run(Scanner scanner, Session session) {
        super.run(scanner, session);

        currentPlayerName = session.getCurrentPlayerName();

        System.out.println("Who would you like to play as?");
        String playerName = scanner.nextLine();
        if (isInvalidName(playerName)) return;

        session.setCurrentPlayerName(playerName);
        System.out.printf("Hello, %s!%n", playerName);
    }

    public boolean isInvalidName(String playerName) {
        if (isExtintName(playerName)) return true;
        if (isRobotName(playerName)) return true;
        if (playerName.equals(currentPlayerName)) {
            System.out.printf("You're already playing as %s!%n", playerName);
            return true;
        }
        return false;
    }

    String currentPlayerName;
}
