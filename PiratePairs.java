import java.util.ArrayList;
import java.util.List;

// TODO: don't reset players, reset decks, deal with discards, scores, etc.
public class PiratePairs {
    public void playOneRound(List<Integer> scores) {
        // game variables
        boolean gameOver = false;
        int currentPlayer = 0;
        final int losingScore = 30;
        final int numPlayers = 3;

        // create a Dealer and a list of Players
        Dealer dealer = new Dealer(new DeckOfCards());
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            double riskFactor = (double) i / numPlayers;
            players.add(new Player(riskFactor));
        }

        // play the game
        while (!gameOver) {
            printHands(players);

            Player player = players.get(currentPlayer);

            if (player.getHand().size() == 0 || player.wantsCard(dealer.getDiscardsCopy(), dealer.getHands(players))) {
                int card = dealer.deal();
                player.addCard(card);
                System.out.println("Player " + player.getPlayerNumber() + " has drawn a " + card);
                if (player.hasPair(card)) {
                    System.out.println("Player " + player.getPlayerNumber() + " has a pair!");
                    player.addScore(card);
                    if (player.getScore() >= losingScore) {
                        gameOver = true;
                    }
                    dealer.clearHand(player);
                }
            } else {
                int lowestCard = dealer.removeLowestCard(players);
                player.addScore(lowestCard);
                System.out.println("Player " + player.getPlayerNumber() + " passes and scores a " + lowestCard
                        + " for a total of " + player.getScore());
                dealer.clearHand(player);
                if (player.getScore() >= losingScore) {
                    gameOver = true;
                }
            }

            currentPlayer = (currentPlayer + 1) % numPlayers;
        }

        // game over report
        System.out.println("Game over!");
        for (Player player : players) {
            System.out.println("Player " + player.getPlayerNumber() + " scored " + player.getScore());
        }
        printHands(players);
        scores.set(currentPlayer, scores.get(currentPlayer) + 1);
    }

    public static void main(String[] args) {
        PiratePairs game = new PiratePairs();

        // make a list of scores to keep track of the winners
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            scores.add(0);
        }

        // play rounds
        for (int i = 0; i < 100; i++) {
            game.playOneRound(scores);
        }

        // print the scores
        for (int i = 0; i < scores.size(); i++) {
            System.out.println("Player " + i + " has " + scores.get(i) + " losses");
        }
    }

    private static void printHands(List<Player> players) {
        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println("--------------------");
    }
}
