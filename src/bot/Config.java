package bot;

public class Config {

	// TODO:Get Current Game
	public static String currentGame = "";

	public static void main(String[] args) throws Exception {
		// TODO:Channel name
		String channel = "";
		String auth = "";
		// Define the bot
		DurBot bot = new DurBot();
		bot.setVerbose(true);
		// TODO: Get account auth
		bot.connect("irc.twitch.tv", 6667, auth);
		bot.sendRawLine("CAP REQ :twitch.tv/membership");
		bot.joinChannel(channel);
//		bot.sendRawLine("CAP REQ :twitch.tv/membership");
		bot.getName();
		}
}
