import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
    private List<Integer> deck;

    public DeckOfCards() {
        deck = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < i; j++) {
                deck.add(i);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public int draw() {
        return deck.remove(0);
    }

    public String toString() {
        return deck.toString();
    }
}
