package games;

import java.util.ArrayList;

public class Dealer implements Player{

	public boolean hasInsurance = false;
	ArrayList<Card> hand = new ArrayList<Card>();
	private String name = "Dealer";

	public void drawCard(Deck deck) {
		hand.add(deck.getNextCard());
	}

	public void emptyHand() {
		hand.clear();
	}

	public int getTotal() {
		int handSum = 0;
		int cardNum;
		int numAces = 0;

		for (int c=0;c<this.hand.size();c++) {
			cardNum = this.hand.get(c).getNumber();
			if(cardNum == 1) {
				numAces++;
				handSum+=11;
			} else if(cardNum>10){
				handSum+=10;
			} else {
				handSum+=cardNum;
			}
		}

		while(handSum>21 && numAces>0) {
			handSum -= 10;
			numAces--;
		}
		return handSum;
	}

	public void printHand(boolean showFirstCard) {
		System.out.println(this.getName()+" has:");
		for(int i=0; i<hand.size();i++){
			if(i == 0 && !showFirstCard){
				System.out.println("[HIDDEN]");
			} else {
			System.out.println("["+hand.get(i).toString()+"]");
			}
		}
	}
	
	public boolean wantsEvenMoney(){
		return false;
	}

	//TODO return soft total for "hit on soft 17" rule
	public boolean wantToStay() {
		boolean stay = false;
        int total = getTotal();

        if(total >= 17){
            stay = true;
            System.out.println("Dealer stays.\n");
        }
        else{
            System.out.println("Dealer hits.");
        }
        return stay;
	}
	public boolean wantsInsurance(){
		this.hasInsurance = false;
		return false;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHasInsurance() {
		return hasInsurance;
	}

	public void setHasInsurance(boolean hasInsurance) {
		this.hasInsurance = hasInsurance;
	}


}
