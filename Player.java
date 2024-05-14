import java.util.ArrayList;
import java.util.HashMap;
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

    public boolean wantsCard(List<Integer> getDiscardsCopy, HashMap<Integer, ArrayList<Integer>> hands) {
        // combine all the cards in the discards list and the hands hashmap
        List<Integer> allCardsShowing = new ArrayList<>(getDiscardsCopy);
        for (ArrayList<Integer> cards : hands.values()) {
            allCardsShowing.addAll(cards);
        }

        // create a temporary full deck of cards
        DeckOfCards tempDeck = new DeckOfCards();

        // remove all the cards in the allCardsShowing list from the temporary deck
        for (int card : allCardsShowing) {
            tempDeck.getDeck().remove(Integer.valueOf(card));
        }
        
        double prob = calculateProbability(tempDeck.getDeck());

        return prob < 0.25;
    }

    private double calculateProbability(List<Integer> tempDeck) {
        int successfulOutcomes = 0;
    
        for (int num : hand) {
            if (tempDeck.contains(num)) {
                successfulOutcomes++;
            }
        }
    
        double totalOutcomes = hand.size();
        return successfulOutcomes / totalOutcomes;
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
        sb.append(" (score: " + score + ")");
        return sb.toString();
    }
}
