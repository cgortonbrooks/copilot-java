import java.util.ArrayList;
import java.util.List;

public class PiratePairs {
    public static void main(String[] args) {
        boolean gameOver = false;
        int currentPlayer = 0;
        final int losingScore = 30;

        // create a Dealer
        Dealer dealer = new Dealer(new DeckOfCards());

        // create a list of Players
        final int numPlayers = 4;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
        }

        // deal 1 card to each Player
        while (!gameOver) {
            printHands(players);

            Player player = players.get(currentPlayer);

            if (player.wantsCard()) {
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

        System.out.println("Game over!");
        // print the final scores
        for (Player player : players) {
            System.out.println("Player " + player.getPlayerNumber() + " scored " + player.getScore());
        }
        printHands(players);
    }

    private static void printHands(List<Player> players) {
        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println("--------------------");
    }
}
