package rockpaperscissors;

public class Player {
    public int getWins() {
        return wins;
    }

    public void incWins() {
        wins++;
    }

    public int getDraws() {
        return draws;
    }

    public void incDraws() {
        draws++;
    }

    public int getLosses() {
        return losses;
    }

    public void incLosses() {
        losses++;
    }

    public String getHand() {
        return hand;
    }

    public boolean setHand(String hand) {
        if (hand.equals("rock") || hand.equals("paper") || hand.equals("scissors")) {
            this.hand = hand;
            return false;
        } else if (hand.equals("auto")) {
            double seed = Math.random();
            this.hand = seed > (double) 2 / 3 ? "rock" : seed > (double) 1 / 3 ? "paper" : "scissors";
            return false;
        }
        System.out.printf("You can't play %s!%n", hand);
        return true;
    }

    public void chooseHand() {
    }

    public void setOpponent(Player opponent) {

    }

    public boolean isHuman() {
        return true;
    }

    public void remember(Player player) {

    }

    int wins;
    int draws;
    int losses;
    String hand;

    boolean verbose;
}
