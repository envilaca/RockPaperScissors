package rockpaperscissors;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class Operation {
    public void run(Scanner scanner, Session session) {
        players = session.getPlayers();
    }

    protected boolean isInvalidQuery(String query, List<String> validQueries) {
        if (validQueries.contains(query)) {
            return false;
        } else {
            System.out.printf("Sorry, %s is not recognised.%n", query);
            return true;
        }
    }

    protected boolean isExtantName(String name) {
        if (players.containsKey(name)) {
            System.out.println("A player with that name already exists!");
            return true;
        }
        return false;
    }

    protected boolean isExtintName(String name) {
        if (!players.containsKey(name)) {
            System.out.println("No such player could be found.");
            return true;
        }
        return false;
    }

    protected boolean isRobotName(String name) {
        if (!players.get(name).isHuman()) {
            System.out.println("You're not a robot!");
            return true;
        }
        return false;
    }

    protected Map<String, Player> players;
}
