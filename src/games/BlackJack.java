package games;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class BlackJack {
	/*
	 * TODO add split add double, more flexible variables add checks to everything, add betting
	 * add options for all blackjack dealer rules (stand on all 17s, hit all 17s, hit soft 17s etc.
	 */

	/**
	 * @param args
	 */
	static Deck deck = new Deck(1);
	static ArrayList<Player> players = new ArrayList<Player>();
	static Timer timer = new Timer();
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// deck.printDeck(50);
		players.add(new Viewer("Ryan"));
//		players.add(new Viewer("Yadira"));
		players.add(new Dealer());
		// deal cards
		for (int i = 0; i < 2; i++) {
			for (int x = 0; x < players.size(); x++) {
				players.get(x).drawCard(deck);
			}
		}
		deck.printDeck(48);
		// loop through players
		for (int x = 0; x < players.size(); x++) {
			// display hand of players
			players.get(x).printHand(false);
			// if the player is the dealer
			if (players.get(x).equals(players.get(players.size() - 1))) {
				// if the player has a 10+
				if (players.get(x).getHand().get(1).getNumber() > 9) {
					System.out.println("Checking for blackjack");
					// if the players hidden card is an ace
					if (players.get(x).getHand().get(0).getNumber() == 1) {
						System.out.println("Dealer has blackjack!");
						// determine if players loose or have a blackjack and
						// push
						for (int y = 0; y < players.size() - 1; y++) {
							if (players.get(y).getTotal() != 21) {
								System.out.println(players.get(y).getName()
										+ " loses");
							} else {
								System.out.println(players.get(y).getName()
										+ " pushes");
							}
							players.get(y).emptyHand();
							players.remove(y);
							y--;
						}
						System.exit(1);
					}
				}
				if (players.get(x).getHand().get(1).getNumber() == 1) {
					// TODO CHECK SCANNER. this loop is checking if top is an
					// ace and
					// if players want insurance. Add check for
					// viewer blackjack, ask if viewer wants even
					// money or insurance if yes, 2:1 else 3:2 else push.
					// https://www.youtube.com/watch?v=zBYUHx4eNwQ
					for (int o = 0; o < players.size(); o++) {
						if (players.get(o).getTotal() == 21) {
							boolean wantsEvenMoney = players.get(o)
									.wantsEvenMoney();
							if (wantsEvenMoney) {
								System.out.println("Taking even money");
								// TODO: pay out blackjack even money 2:1
								players.get(o).emptyHand();
								players.remove(o);
								x--;
							}
						}
						boolean wantsInsurance = players.get(o)
								.wantsInsurance();
						if (wantsInsurance) {
							System.out.println("Buying insurance");
							// TODO: place half bet on hold
						}
					}
					// TODO offer even money or insurance to viewers with
					// blackjack; pay and remove player
					// if they want even money
					if (players.get(x).getHand().get(0).getNumber() > 9) {
						for (int o = 0; o < players.size() - 1; o++) {
							if (players.get(o).getTotal() < 21
									&& players.get(o).isHasInsurance()) {
								// TODO pay out insurance 2:1, loose bet; push
								// pushes(10 bet/5 insurances pays 5. Loss 10,
								// Total is
								// 10.
							}
							if (players.get(o).getTotal() == 21
									&& !players.get(o).isHasInsurance()) {
								// TODO push
							}
							if (players.get(o).getTotal() == 21
									&& players.get(o).isHasInsurance()) {
								// TODOPay out value of insurance 2:1 and hand
								// pushes(10 bet/5 insurances pays 5. Total is
								// 20.
							}
							players.get(o).emptyHand();
							players.remove(o);
							o--;
						}
					} else {
						for (int o = 0; o < players.size() - 1; o++) {
							if (players.get(o).isHasInsurance()) {
								System.out
										.println("Dealer does not have blakjack");
								// TODO Lose insurance bet
							}
						}
					}
				}
			}
		}
		for (int o = 0; o < players.size() - 1; o++) {
			if (players.get(o).getTotal() == 21) {
				System.out.println(players.get(o).getName() + " has blackjack");
				players.get(o).emptyHand();
				players.remove(o);
				o--;
			}
		}
		for (int x = 0; x < players.size(); x++) {
			boolean endTurn = false;
			while (!endTurn) {
				endTurn = players.get(x).wantToStay();
				if (endTurn
						&& players.get(x).equals(
								players.get(players.size() - 1))) {
					players.get(x).printHand(true);
				}
				if (!endTurn) {
					players.get(x).drawCard(deck);
					players.get(x).printHand(true);
					if (players.get(x).getTotal() > 21) {
						if (players.get(x) == players.get(players.size() - 1)) {
							System.out.println("Dealer bust!");
							players.get(x).emptyHand();
							players.remove(x);
							for (int o = 0; o < players.size(); o++) {
								System.out.println(players.get(o).getName()
										+ " wins");
								// TODO pay out winning hand
							}
							System.exit(1);
						} else {
							System.out.println(players.get(x).getName()
									+ " bust!");
							players.get(x).emptyHand();
							players.remove(x);
							x--;
							break;
						}
					}
					if (players.get(x).getTotal() == 21) {
						System.out.println("Player hit 21 and stays");
						break;
					}
				}
			}
		}
		for (int x = 0; x < players.size() - 1; x++) {
			if (players.get(x).getTotal() > players.get(players.size() - 1)
					.getTotal()) {
				System.out.println(players.get(x).getName() + " wins");
				// TODO insert bet winning
			}
			if (players.get(x).getTotal() == players.get(players.size() - 1)
					.getTotal()) {
				System.out.println(players.get(x).getName() + " pushes");
				// TODO wins nothing and chips are returned
			}
			if (players.get(x).getTotal() < players.get(players.size() - 1)
					.getTotal()) {
				System.out.println(players.get(x).getName() + " loses");
				// TODO loses bet
			}
		}
		System.exit(1);
	}
}
