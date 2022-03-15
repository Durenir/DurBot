package games;

import java.util.ArrayList;
import java.util.Scanner;

public class Viewer implements Player{

	public boolean hasInsurance = false;
	ArrayList<Card> hand = new ArrayList<Card>();
	private String name;

	public Viewer(String name) {
		this.name = name;
	}

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
			System.out.println("["+hand.get(i).toString()+"]");
		}
	}

	//TODO Might need to get rid/change dealer logic to player logic
	public boolean wantToStay() {
        Boolean stay = null;
        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.print(this.getName()+", would you like to \"hit\" or \"stay\"? ");
            String input = sc.nextLine();
            System.out.println(input);
            if(input.equals("hit")){
            	stay=false;
            	break;
            }
            if(input.equals("stay")){
                    stay = true;
                    break;
            }
            else{
            	System.out.println("Not a valid option");
            	break;
            }
        }
        return stay;
	}
	
	public boolean wantsEvenMoney(){
		Boolean wants = null;
        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.print(this.getName()+", would you like even money \"yes\" or \"no\"? ");
            String input = sc.nextLine();
            System.out.println(input);
            if(input.equals("no")){
            	wants=false;
            	break;
            }
            if(input.equals("yes")){
            	wants = true;
                    break;
            }
            else{
            	System.out.println("Not a valid option");
            	break;
            }
        }
        return wants;
	}

	public boolean wantsInsurance(){
		Boolean wants = null;
        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.print(this.getName()+", would you like insurance \"yes\" or \"no\"? ");
            String input = sc.nextLine();
            System.out.println(input);
            if(input.equals("no")){
            	this.hasInsurance = false;
            	wants=false;
            	break;
            }
            if(input.equals("yes")){
            	this.hasInsurance = true;
            	wants = true;
                    break;
            }
            else{
            	System.out.println("Not a valid option");
            	break;
            }
        }
        return wants;
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
