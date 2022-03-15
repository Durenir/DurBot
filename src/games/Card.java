package games;

public class Card {
	
	private Suit suit;
	private int number;
	
	public Card(Suit suit, int number) {
		this.suit = suit;
		this.number = number;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String toString() {
		String numStr=null;
		switch(this.number){
		case 1:
			numStr = "Ace";
			break;
			
		case 2:
			numStr = "2";
			break;
			
		case 3:
			numStr = "3";
			break;
		
		case 4:
			numStr = "4";
			break;
			
		case 5:
			numStr = "5";
			break;
			
		case 6:
			numStr = "6";
			break;
			
		case 7:
			numStr = "7";
			break;
			
		case 8:
			numStr = "8";
			break;
			
		case 9:
			numStr = "9";
			break;
			
		case 10:
			numStr = "10";
			break;
			
		case 11:
			numStr = "Jack";
			break;
			
		case 12:
			numStr = "Queen";
			break;
			
		case 13:
			numStr = "King";
			break;
		}
		return numStr + " of "+ suit.toString();
	}
	
}