import java.util.ArrayList;
import java.util.List;

public class Player {
    private static int playerCount = 0;
    private List<Integer> hand;
    private int playerNumber;

    public Player() {
        playerCount++;
        playerNumber = playerCount;
        hand = new ArrayList<>();
    }

    public void addCard(int card) {
        hand.add(card);
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
