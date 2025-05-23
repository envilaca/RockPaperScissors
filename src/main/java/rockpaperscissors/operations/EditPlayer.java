package rockpaperscissors.operations;

import rockpaperscissors.Operation;
import rockpaperscissors.Player;
import rockpaperscissors.Session;

import java.util.Scanner;

public class EditPlayer extends Operation {
    @Override
    public void run(Scanner scanner, Session session) {
        super.run(scanner, session);

        System.out.println("Which player would you like to edit?");
        String playerName = scanner.nextLine();
        if (isExtintName(playerName)) return;

        System.out.print("Please enter a new name for the player: ");
        String name = scanner.nextLine();
        if (isExtantName(name)) return;

        Player player = players.get(playerName);
        players.put(name, player);
        players.remove(playerName);
        if (playerName.equals(session.getCurrentPlayerName())) {
            session.setCurrentPlayerName(name);
        }
    }
}
