package games;

import java.util.ArrayList;

public interface Player {
	boolean hasInsurance = false;
	public void emptyHand();
	public void printHand(boolean showFirstCard);
	 // Player hand as an array.

    // Player name as a String.


    /**
     * Generate a card and add it to the player's hand.
     */
    void drawCard(Deck deck);

    /**
     * Ask player if (s)he wants to hit or stay, returns a boolean on the choice of staying.
     * @return
     */
    boolean wantToStay();

    /**
     * Retrieves the total of the player's hand.
     * @return
     */
    int getTotal();

    String getName();
    public ArrayList<Card> getHand();
    boolean wantsEvenMoney();
    boolean wantsInsurance();
    boolean isHasInsurance();
    void setHasInsurance(boolean hasInsurance);
}
