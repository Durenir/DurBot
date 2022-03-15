package components;

import java.util.ArrayList;
import java.util.HashMap;


//This class will update the user file when currency's are created or deleted
//Maybe use this for managing tickets and all currencys aswell as initializing them within
//TwitchViewer.java

//Other option is to make a list of currency names by iterating keysets of currency hashmap and add the names with
//the amount of 0 to each new user. Sounds sloppy
public class CurrencyManager {
	ArrayList<String> currencyList = new ArrayList<String>();

	public CurrencyManager() {

	}
	public ArrayList<String> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(ArrayList<String> currencyList) {
		this.currencyList = currencyList;
	}
	public void addCurrency(String currencyName) {
		this.currencyList.add(currencyName);
		System.out.println("Adding "+currencyName+" to the currency list");
	}
	public void removeCurrency(String currencyName) {
		for (int i = 0; i < currencyList.size(); i++) {
			if(this.currencyList.get(i).equals(currencyName)) {
				this.currencyList.remove(i);
			}
		}
	}
	//Iterate through viewers, update hashmap, save new data to file.
	public void updateUsers(HashMap<String, TwitchViewer> viewers) {
		
	}
}
