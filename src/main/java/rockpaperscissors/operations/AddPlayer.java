package rockpaperscissors.operations;

import rockpaperscissors.Operation;
import rockpaperscissors.Player;
import rockpaperscissors.Robot;
import rockpaperscissors.Session;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddPlayer extends Operation {
    @Override
    public void run(Scanner scanner, Session session) {
        super.run(scanner, session);

        System.out.println("Would you like to add a 'human' or a 'robot'?");
        playerType = scanner.nextLine().toLowerCase();
        if (isInvalidQuery(playerType, playerTypes)) return;

        System.out.print("Please enter a name for the player: ");
        String name = scanner.nextLine();
        if (isExtantName(name)) return;

        if (playerType.equals("robot")) {
            System.out.print("Please choose a difficulty between 1 and 4: ");
            int difficulty;
            try {
                difficulty = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
                return;
            }
            if (difficulty < 1 || difficulty > 4) {
                System.out.print("That's not between 1 and 4.");
                return;
            }
            players.put(name, new Robot(difficulty));
        } else {
            players.put(name, new Player());
        }
    }

    String playerType;
    List<String> playerTypes = Arrays.asList("human", "robot");
}
