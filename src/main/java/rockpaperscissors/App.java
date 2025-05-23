package rockpaperscissors;

import rockpaperscissors.operations.*;

import java.util.*;

public class App {
    public static void main(String[] args) {
        // Making some lists to facilitate some future user input checks
        Map<String, Operation> interfaceOp = new HashMap<>() {{
            put("play", new Play());
            put("view score", new ViewScore());
            put("change player", new ChangePlayer());
            put("add player", new AddPlayer());
            put("edit player", new EditPlayer());
            put("remove player", new RemovePlayer());
        }};

        System.out.println("Welcome to rock paper scissors!");
        System.out.println("Since you're just opening it up, who are you?");
        System.out.print("Please enter your name: ");

        // Immediately we create a new human player.
        Scanner scanner = new Scanner(System.in);
        Session session = new Session(scanner.nextLine(), args[0].equals("v"));

        String query;
        System.out.println("\nYou can 'play' or 'exit'.");
        while (!(query = scanner.nextLine().toLowerCase()).equals("exit")) {
            // We see an invalid command and send them right on to the next loop(/out of the switch)
            // You'll be seeing a lot of this.
            if (interfaceOp.containsKey(query)) interfaceOp.get(query).run(scanner, session);
            else System.out.println("Sorry, that command is not recognised.");

            // Maybe this could at some point be a for loop through my list of valid commands
            // Not today, though
            // Yesterday, however
            System.out.print("\nYou can ");
            for (String op : interfaceOp.keySet()) {
                System.out.printf("'%s', ", op);
            }
            System.out.println("or 'exit'.");
        }
        scanner.close();
    }
}
