package rockpaperscissors;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Robot extends Player {
    public Robot(int difficulty) {
        // I was at first considering having the difficulty be real, as in a higher chance of
        // loss against certain opponents. But that didn't seem to be in the spirit of the game.
        this.memory = new HashMap<>();
        this.difficulty = difficulty;
        setThresholds();

        this.verbose = false;
    }

    public Robot(int difficulty, boolean verbose) {
        this.memory = new HashMap<>();
        this.difficulty = difficulty;
        setThresholds();

        this.verbose = verbose;
    }

    private void setThresholds() {
        double rock;
        double paper;
        double scissors;

        if (difficulty == 2) {
            // Normally random.
            Random random = new Random();

            rock = Math.abs(random.nextGaussian());
            paper = Math.abs(random.nextGaussian());
            scissors = Math.abs(random.nextGaussian());
        } else {
            // Very biased.
            rock = Math.random();
            paper = Math.random();
            scissors = Math.random();
        }
        double sum = rock + paper + scissors;

        // True randomness is the best rock paper scissors strategy
        // The more random these parameters, though, the less random the bots! These are used for
        // calculating their responses based on a standard random float from zero to one.
        if (difficulty == 4) {
            // As close to truly random as possible.
            this.upper = (double) 2 / 3;
            this.lower = (double) 1 / 3;
        } else {
            this.upper = ((paper + scissors) / sum + (double) 2 / 3) / 2;
            this.lower = (scissors / sum + (double) 1 / 3) / 2;
        }
    }

    // Assumes if someone's lost, they're more likely to switch hands
    // And if they've won, they're more likely to keep playing the same thing.
    // Difficulty 3 Robots should do better against these kinds of players
    void numberphile() {
        String lastOppHand = "rock";
        String lastBotHand = "scissors";
        if (memory.containsKey(opponent)) {
            lastOppHand = memory.get(opponent)[0];
            lastBotHand = memory.get(opponent)[1];
        }
        if (verbose) System.out.printf("Last time I played %s and you played %s.%n",
                lastBotHand, lastOppHand);

        // Play randomly on a draw.
        if (lastOppHand.equals(lastBotHand)) {
            upper = (double) 2 / 3;
            lower = (double) 1 / 3;
            return;
        }

        boolean opponentWon = switch (lastBotHand) {
            case "rock" -> lastOppHand.equals("paper");
            case "paper" -> lastOppHand.equals("scissors");
            case "scissors" -> lastOppHand.equals("rock");
            default -> false;
        };
        // Come to think of it, perhaps something similar could have been achieved by
        // modifying uW and lW (still based on the last hand).
        // And now I'm doing that.
        double opponentCompetence = Math.pow(2, (opponent.getWins() - opponent.getLosses()));
        double favoured = 1 + 1 / opponentCompetence;
        double abhorred = 1 / (1 + 1 / opponentCompetence);
        double sum = favoured + 1 + abhorred;

        if (verbose) System.out.printf("I'm %.1f%% likely to go with my gut.%n", favoured * 100 / sum);
        if (verbose) System.out.printf("Last time you played %s and you %s, so this time I think you'll play ",
                lastOppHand, opponentWon ? "won" : "lost");

        if (lastOppHand.equals("rock") && opponentWon
         || lastOppHand.equals("paper") && !opponentWon) {
            if (verbose) System.out.printf("rock.%n");

            upper = (favoured + abhorred) / sum;
            lower = abhorred / sum;
        } else if (lastOppHand.equals("paper")
         || lastOppHand.equals("scissors") && !opponentWon) {
            if (verbose) System.out.printf("paper.%n");

            upper = (1 + favoured) / sum;
            lower = favoured / sum;
        } else {
            if (verbose) System.out.printf("scissors.%n");

            upper = (abhorred + 1) / sum;
            lower = 1 / sum;
        }
    }

    @Override
    public void chooseHand() {
        if (difficulty == 3) numberphile();

        double seed = Math.random();

        if (verbose) System.out.printf("%.2f, %.2f%n", upper * 100, lower * 100);
        if (verbose) System.out.printf("%.2f%n", seed);

        hand = seed > upper ? "rock" : seed > lower ? "paper" : "scissors";
    }

    @Override
    public void setOpponent(Player player) {
        opponent = player;
    }

    public boolean isHuman() {
        return false;
    }

    @Override
    public void remember(Player player) {
        memory.put(player, new String[]{player.getHand(), hand});
    }

    Player opponent;
    // Last game the bot played against a player
    Map<Player, String[]> memory;
    int difficulty;
    // upper threshold
    double upper;
    // lower threshold
    double lower;
}
