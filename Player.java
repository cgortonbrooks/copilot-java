import java.util.ArrayList;
import java.util.List;

public class Player {
    private static int playerCount = 0;
    private List<Integer> hand;
    private int playerNumber;
    private int score = 0;

    public Player() {
        playerCount++;
        playerNumber = playerCount;
        hand = new ArrayList<>();
    }

    public boolean wantsCard() {
        // int sum = 0;
        // for (int card : hand) {
        //     sum += card;
        // }
        // return sum < 30;
        if (hand.size() == 0) {
            return true;
        }
        return Math.random() < 0.5;
    }

    public void addCard(int card) {
        hand.add(card);
    }

    public boolean hasPair(int lastCard) {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i) == lastCard) {
                return true;
            }
        }
        return false;
    }

    public void addScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public List<Integer> getHand() {
        return hand;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player " + playerNumber + ": ");
        for (int i = 0; i < hand.size(); i++) {
            sb.append(hand.get(i));
            if (i < hand.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
