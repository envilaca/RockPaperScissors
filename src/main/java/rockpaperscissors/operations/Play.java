package rockpaperscissors.operations;

import rockpaperscissors.Operation;
import rockpaperscissors.Player;
import rockpaperscissors.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Play extends Operation {
    @Override
    public void run(Scanner scanner, Session session) {
        super.run(scanner, session);

        String currentPlayerName = session.getCurrentPlayerName();
        opponentNames = new ArrayList<>(players.keySet()) {{
            remove(currentPlayerName);
        }};

        if (players.size() < 2) {
            System.out.println("You need at least two players to play!");
            return;
        }

        System.out.println("Who would you like to play against?");
        System.out.println("Here are your options:\n");
        // Show the player the other players.
        for (int i = 0; i < opponentNames.size(); i++) {
            System.out.println((i + 1) + ". " + opponentNames.get(i));
        }

        // Choosing opponent. Prompt player to enter a number.
        System.out.print("\nPlease enter the index of your chosen opponent: ");
        String nameTwo = findOpponentName(scanner.nextLine());
        if (nameTwo == null) return;

        Player playerOne = players.get(currentPlayerName);
        Player playerTwo = players.get(nameTwo);

        // Play the game until it's not a draw.
        boolean draw = true;
        while (draw) {
            System.out.print("You play: ");
            if (playerOne.setHand(scanner.nextLine())) return;

            // Ideally player two, human, would not be entering their answer after
            // seeing player one's on the console.
            if (playerTwo.isHuman()) {
                System.out.printf("%s plays: ", nameTwo);
                if (playerTwo.setHand(scanner.nextLine())) return;
            } else {
                playerTwo.setOpponent(playerOne);
                playerTwo.chooseHand();
            }

            final String handOne = playerOne.getHand();
            final String handTwo = playerTwo.getHand();

            draw = handOne.equals(handTwo);
            System.out.printf("You played %s and %s played %s.%n", handOne, nameTwo, handTwo);
            // Robots have a little function for remembering the last thing the person
            // they're playing against played. It's only used in difficulty 3.
            playerTwo.remember(playerOne);

            // I considered always incrementing draws and simply subtracting from them
            // at the start, but it doesn't seem like it would be that much better.
            if (draw) {
                System.out.println("Draw! Play again.");
                playerOne.incDraws();
                playerTwo.incDraws();
            }
        }
        // IntelliJ simplified this switch case for me
        // Thanks IntelliJ
        boolean result = switch (playerOne.getHand()) {
            case "rock" -> playerTwo.getHand().equals("scissors");
            case "paper" -> playerTwo.getHand().equals("rock");
            case "scissors" -> playerTwo.getHand().equals("paper");
            default -> false;
        };
        (result ? playerOne : playerTwo).incWins();
        (result ? playerTwo : playerOne).incLosses();

        System.out.printf("%s wins!%n", result ? currentPlayerName : nameTwo);
    }

    public String findOpponentName(String opponentInput) {
        if (opponentNames.contains(opponentInput)) return opponentInput;
        try {
            return opponentNames.get(Integer.parseInt(opponentInput) - 1);
        } catch (NumberFormatException ignored) {
            System.out.println("Not a valid input, I'm afraid. Looking for a number.");
        } catch (IndexOutOfBoundsException ignored) {
            System.out.println("That's more than there are players!");
        }
        return null;
    }

    private List<String> opponentNames;
}
