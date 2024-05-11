import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private DeckOfCards deck;
    private List<Integer> discards;

    public Dealer(DeckOfCards deck) {
        this.deck = deck;
        deck.shuffle();
        discards = new ArrayList<Integer>();
    }

    // method to remove the single lowest card from the players' hands
    public int removeLowestCard(List<Player> players) {
        int lowestCard = 10;
        int lowestCardIndex = 0;
        int lowestPlayer = 0;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            for (int j = 0; j < player.getHand().size(); j++) {
                if (player.getHand().get(j) <= lowestCard) {
                    lowestCard = player.getHand().get(j);
                    lowestCardIndex = j;
                    lowestPlayer = i;
                }
            }
        }
        players.get(lowestPlayer).getHand().remove(lowestCardIndex);
        return lowestCard;
    }

    public void clearHand(Player player) {
        discards.addAll(player.getHand());
        player.getHand().clear();
    }

    public int deal() {
        return deck.draw();
    }
}
