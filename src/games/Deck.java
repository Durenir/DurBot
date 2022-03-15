package games;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private ArrayList<Card> shoe;
	
	//TODO Make enum for card numbers and do .values to add them just like we did with the suit
	public Deck(int numDecks) {
		this.shoe = new ArrayList<Card>();
		for(int d=0; d<numDecks; d++) {
			for(int s=0; s<4;s++){
				for(int n=1; n<=13; n++){
					this.shoe.add(new Card(Suit.values()[s], n));
				}
			}
		}
		shuffle();
	}
	
	public void shuffle() {
		Collections.shuffle(shoe);
	}
	
	public Card getNextCard() {
		Card card = shoe.get(0);
		shoe.remove(0);
		return card;
	}
	
	public void resetDeck() {
		shoe.clear();
	}
	
	public void printDeck(int numToPrint) {
		for(int c=0; c<numToPrint;c++) {
			System.out.println("["+(c+1)+"/"+shoe.size()+"]"+"["+shoe.get(c).toString()+"]");
		}
	}
}
