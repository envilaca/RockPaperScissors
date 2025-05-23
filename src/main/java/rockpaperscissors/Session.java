package rockpaperscissors;

import java.util.HashMap;
import java.util.Map;

public class Session {
    Session(String playerName, boolean verbose) {
        currentPlayerName = playerName;
        players = new HashMap<>() {{
            if (verbose) {
                put("Amver-bose", new Robot(3, true));
                put("Robotomy", new Robot(1, true));
                put("Beepert", new Robot(2, true));
            }
            put("Colin", new Robot(3, false));
            put("Calculester", new Robot(4, false));
            put(playerName, new Player());
        }};
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    public void setCurrentPlayerName(String playerName) {
        currentPlayerName = playerName;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    String currentPlayerName;
    Map<String, Player> players;
}
