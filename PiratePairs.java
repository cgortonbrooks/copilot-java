public class PiratePairs {
    public static void main(String[] args) {
        // create a new deck of cards and shuffle it
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        // create four players and deal them each a card
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        player1.addCard(deck.draw());
        player2.addCard(deck.draw());
        player3.addCard(deck.draw());
        player4.addCard(deck.draw());
  
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);
        System.out.println(player4);
    }
}
