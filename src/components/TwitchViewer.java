package components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


//TODO add ranks and usergroups
//TODO change viewer tickets to hashmap for multiple raffles at one time
public class TwitchViewer {
	private String viewerName;
	public HashMap<String, Currency> viewerPoints;
	public HashMap<String, Currency> pointsOnHold;
	private int viewerTickets;
	private String followDate;
	private String viewerDate;
	private int hoursInStream;
	private boolean isFollowing;

	// Init a new viewer to the stream
	public TwitchViewer(String viewerName) {
		this.viewerName = viewerName;
		this.viewerPoints = new HashMap<String, Currency>();
		// For loop of currency list in currency manager. Add each index (String
		// name of currency) as a key

		//TODO MAKE SURE THIS WORKS. HAVE SETTINGS LOADED ADD CURRENCIES TO CURRENCY MANAGER.
		//HAVA CURRENCY MANANAGER DEAL WITH POINTS ON HOLD
		System.out.println("HEREREREREREREHRERERERERERHERE");
		for (int i = 0; i < bot.Utils.getCurrencyManager().getCurrencyList().size(); i++) {
			viewerPoints.put(bot.Utils.getCurrencyManager().getCurrencyList().get(i).toString(), new Currency(bot.Utils.getCurrencyManager()
					.getCurrencyList().get(i), 0));
			System.out.println("Added "+bot.Utils.getCurrencyManager().getCurrencyList().get(i).toString()+" to new twitch viewer");
		}
		this.pointsOnHold = new HashMap<String, Currency>();
		for (int i = 0; i < bot.Utils.getCurrencyManager().getCurrencyList().size(); i++) {
			pointsOnHold.put(bot.Utils.getCurrencyManager().getCurrencyList().get(i).toString(), new Currency(bot.Utils.getCurrencyManager()
					.getCurrencyList().get(i), 0));
		}
		this.viewerTickets = 0;
		this.followDate = "Not following";
		// TODO get current date			
		DateFormat df = new SimpleDateFormat("MMM dd yyyy");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		this.viewerDate = "[" + df.format(dateobj) + "]";
		this.hoursInStream = 0;
		this.isFollowing = false;
	}

	public TwitchViewer(String viewerName,
			HashMap<String, Currency> viewerPoints,
			HashMap<String, Currency> pointsOnHold, int viewerTickets,
			String followDate, String viewerDate, int hoursInStream,
			boolean isFollowing) {
		this.viewerName = viewerName;
		this.viewerPoints = new HashMap<String, Currency>(viewerPoints);
		this.pointsOnHold = new HashMap<String, Currency>(pointsOnHold);
		this.viewerTickets = viewerTickets;
		this.followDate = followDate;
		this.viewerDate = viewerDate;
		this.hoursInStream = hoursInStream;
		this.isFollowing = isFollowing;
	}

	public String getViewerName() {
		return viewerName;
	}

	public void setViewerName(String viewerName) {
		this.viewerName = viewerName;
	}

	public HashMap<String, Currency> getViewerPoints() {
		return viewerPoints;
	}

	public void setViewerPoints(HashMap<String, Currency> viewerPoints) {
		this.viewerPoints = viewerPoints;
	}

	public HashMap<String, Currency> getPointsOnHold() {
		return pointsOnHold;
	}

	public void setPointsOnHold(HashMap<String, Currency> pointsOnHold) {
		this.pointsOnHold = pointsOnHold;
	}

	public int getViewerTickets() {
		return viewerTickets;
	}

	public void setViewerTickets(int viewerTickets) {
		this.viewerTickets = viewerTickets;
	}

	public String getFollowDate() {
		return followDate;
	}

	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}

	public String getViewerDate() {
		return viewerDate;
	}

	public void setViewerDate(String viewerDate) {
		this.viewerDate = viewerDate;
	}

	public int getHoursInStream() {
		return hoursInStream;
	}

	public void setHoursInStream(int hoursInStream) {
		this.hoursInStream = hoursInStream;
	}

	public boolean isFollowing() {
		return isFollowing;
	}

	public void setFollowing(boolean isFollowing) {
		this.isFollowing = isFollowing;
	}
}
