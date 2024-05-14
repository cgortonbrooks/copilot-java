import java.util.ArrayList;
import java.util.List;

public class PiratePairs {
    final static String logLevel = "BRIEF";
    //final static String logLevel = "VERBOSE";
    public static void main(String[] args) {
        final int numPlayers = 12;
        final int losingScore = 60 / numPlayers + 1;
        final int numRounds = 1000;

        welcomeMessage(numPlayers, losingScore, numRounds);
        
        // create players
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            double riskFactor = (double) i / (numPlayers - 1);
            players.add(new Player(riskFactor));
        }

        // make a list of scores to keep track of the winners
        List<Integer> losses = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            losses.add(0);
        }

        // play rounds
        for (int i = 0; i < numRounds; i++) {
            playOneRound(players, losingScore, losses);

            // reset player hands and scores
            for (Player player : players) {
                player.reset();
            }
        }

        printLosses(losses, players);
    }

    public static void playOneRound(List<Player> players, int losingScore, List<Integer> scores) {
        // game variables
        boolean gameOver = false;

        // pick a random player to start
        int currentPlayer = (int) (Math.random() * players.size());

        // create a Dealer
        Dealer dealer = new Dealer(new DeckOfCards());

        // play the game
        while (!gameOver) {
            printPlayers(players);

            Player player = players.get(currentPlayer);

            if (player.getHand().size() == 0 || player.wantsCard(dealer.getDiscardsCopy(), dealer.getHands(players))) {
                int card = dealer.deal();
                player.addCard(card);
                log("Player " + player.getPlayerNumber() + " has drawn a " + card, "VERBOSE");
                //System.out.println("Player " + player.getPlayerNumber() + " has drawn a " + card);
                if (player.hasPair(card)) {
                    log("Player " + player.getPlayerNumber() + " has a pair!", "VERBOSE");
                    player.addScore(card);
                    if (player.getScore() >= losingScore) {
                        gameOver = true;
                    }
                    dealer.clearHand(player);
                }
            } else {
                int lowestCard = dealer.removeLowestCard(players);
                player.addScore(lowestCard);
                log("Player " + player.getPlayerNumber() + " passes and scores a " + lowestCard
                        + " for a total of " + player.getScore(), "VERBOSE");
                dealer.clearHand(player);
                if (player.getScore() >= losingScore) {
                    gameOver = true;
                }
            }

            currentPlayer = (currentPlayer + 1) % players.size();
        }

        // game over report
        log("Game over!", "VERBOSE");
        for (Player player : players) {
            log("Player " + player.getPlayerNumber() + " scored " + player.getScore(), "VERBOSE");
        }
        printPlayers(players);
        scores.set(currentPlayer, scores.get(currentPlayer) + 1);
    }

    private static void printLosses(List<Integer> losses, List<Player> players) {
        for (int i = 0; i < losses.size(); i++) {
            // print the number of losses for each player and their risk factor
            log("Player " + i + " lost " + losses.get(i) + " times with risk level " + String.format("%.1f%%", players.get(i).getRiskFactor() * 100), "BRIEF");
        }
    }

    private static void printPlayers(List<Player> players) {
        for (Player player : players) {
            log(player.toString(), "VERBOSE");
        }
        log("--------------------", "VERBOSE");
    }

    private static void welcomeMessage(int numPlayers, int losingScore, int numRounds) {
        System.out.println("Welcome to Pirate Pairs!");
        System.out.println("There are " + numPlayers + " players.");
        System.out.println("The first player to score " + losingScore + " points loses.");
        System.out.println("We will play " + numRounds + " rounds.");
    }

    private static void log(String message, String level) {
        if (level.equals("VERBOSE") && logLevel.equals("VERBOSE")) {
            System.out.println(message);
        }
        if (level.equals("BRIEF") && logLevel.equals("BRIEF")) {
            System.out.println(message);
        }
    }
}
