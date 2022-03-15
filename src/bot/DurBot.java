//TODO: Add in checking for everything. Especially for making sure that the command exist, and that duplicates aren't added.

package bot;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

import components.TwitchViewer;

public class DurBot extends PircBot {

	public DurBot() {
		// TODO:Get bot name
		this.setName("durenbot");
		this.isConnected();
		Utils.readSettingsFile("settings.txt");
		Utils.readCommandFile("commands.txt");
		Utils.readUsersFile("users.txt");
//		this.updateUsers();
	}

	/*
	 * get first word of message = message
	 *
	 * for(int i = 0; i =< commands.length; i++ if
	 * message.equalsignorecase(commands[i].getCommand)
	 */

	//TODO possibly switch the if's to case switches
	public void onMessage(String channel, String sender, String login,
			String hostname, String message) {
		// Add for loop to iterate through commands list
		for (int i = 0; i < Utils.Commands.size(); i++) {
			if (message.startsWith(Utils.getCommands().get(i).getCommand())) {
				sendMessage(channel, Utils.getCommands().get(i).getResponse());
			}
		}
		if (message.startsWith("!addcmd ")) {
			String[] split = message.toString().split("\\s+", 3);
			// System.out.println(split[1].toString());
			// sendMessage(channel, split[1].toString());
			// TODO: Find a way to add ignores case
			// TODO: Add check to make sure that command doesn't already exist
			Utils.getCommands().add(new Command(split[1], split[2]));
			Utils.writeCommandFile("commands.txt", split[1] + " " + split[2]);
		}
		if (message.startsWith("!delcmd ")) {
			String[] split = message.toString().split("\\s+", 2);
			// System.out.println(split[1].toString());
			// sendMessage(channel, split[1].toString());
			for (int i = 0; i < Utils.Commands.size(); i++) {
				// System.out.println(Utils.getCommands().get(i).getCommand().toString());
				if (Utils.getCommands().get(i).getCommand().toString().equals(
						split[1].toString())) {
					// System.out.println("Command " + split[1].toString()+"
					// removed");
					Utils.getCommands().remove(i);
					try {
						Utils.deleteCommandFile("commands.txt", split[1]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// System.out.println(Utils.getCommands().get(i).getCommand().toString());
				}
			}
		}
		if (message.startsWith("!editcmd ")) {
			String[] split = message.toString().split("\\s+", 3);
			// System.out.println(split[1].toString());
			// sendMessage(channel, split[1].toString());
			for (int i = 0; i < Utils.Commands.size(); i++) {
				System.out.println(Utils.getCommands().get(i).getCommand()
						.toString());
				if (Utils.getCommands().get(i).getCommand().toString().equals(
						split[1].toString())) {
					Utils.getCommands().get(i).setResponse(split[2]);
					System.out.println("Command " + split[1].toString()
							+ " edited");
					try {
						Utils.editCommandFile("commands.txt", split[1], split[2]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Utils.getCommands().get(i).getCommand()
							.toString());
				}
			}

		}
		if (message.equalsIgnoreCase("!test")) {
			sendMessage(channel, sender.toString() + " Test complete");
//			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			DateFormat df = new SimpleDateFormat("MMM dd yyyy");
			Date dateobj = new Date();
			System.out.println(df.format(dateobj));
		}
		// TODO Ignore case
		String[] split = message.toString().split("\\s+");
		if (message.startsWith("!"
				+ Utils.getTwitchViewers().get(sender.toString())
						.getViewerPoints().get(
								message.substring(1, split[0].length()))
						.getName())) {
			if (split.length > 1) {
				if (split[1].equalsIgnoreCase("add")) {
					Utils.getTwitchViewers().get(split[3]).getViewerPoints()
							.get(split[0].substring(1, split[0].length()))
							.setAmount(
									Utils.getTwitchViewers().get(split[3])
											.getViewerPoints().get(
													split[0].substring(1,
															split[0].length()))
											.getAmount()
											+ Integer.parseInt(split[2]));
					System.out.println(sender + " added " + split[2] + " " + split[0].substring(1, split[0].length()) + " to " + split[3]);
				}
				if (split[1].equalsIgnoreCase("give")) {
					Utils.getTwitchViewers().get(split[3]).getViewerPoints()
					.get(split[0].substring(1, split[0].length()))
					.setAmount(
							Utils.getTwitchViewers().get(split[3])
									.getViewerPoints().get(
											split[0].substring(1,
													split[0].length()))
									.getAmount()
									+ Integer.parseInt(split[2]));
					Utils.getTwitchViewers().get(sender).getViewerPoints()
					.get(split[0].substring(1, split[0].length()))
					.setAmount(
							Utils.getTwitchViewers().get(sender)
									.getViewerPoints().get(
											split[0].substring(1,
													split[0].length()))
									.getAmount()
									- Integer.parseInt(split[2]));
				}
				if (split[1].equalsIgnoreCase("check")) {
					sendMessage(channel, sender.toString() + ", " + split[2].toString() + " has " + Utils.getTwitchViewers().get(split[2])
							.getViewerPoints().get(
									message.substring(1, split[0].length()))
							.getAmount() + " " + Utils.getTwitchViewers().get(split[2])
							.getViewerPoints().get(
									message.substring(1, split[0].length()))
							.getName());
				}
				if (split[1].equalsIgnoreCase("addall")) {
					//TODO
				}
				if (split[1].equalsIgnoreCase("giveall")) {
					//TODO
				}
			}
			if (split.length == 1) {
				sendMessage(channel, sender.toString()
						+ " you have "
						+ Utils.getTwitchViewers().get(sender.toString())
								.getViewerPoints().get(
										message.substring(1, message.length()))
								.getAmount()
						+ " "
						+ Utils.getTwitchViewers().get(sender.toString())
								.getViewerPoints().get(
										message.substring(1, message.length()))
								.getName());
			}
		}
//		System.out.println(System.getProperty("user.dir").toString());
	}

	//TODO Remove this after proper user tracking
//	public void updateUsers(){
//		User[] users = this.getUsers("#durenir");
//		for(int i=0; i < users.length; i++) {
//			System.out.println(users[i].getNick());
//		}
//	}

//	public void onUserList(String channel, User[] users) {
//		System.out.println("Getting users list");
//		System.out.println(channel);
//		for(int i=0; i < users.length; i++) {
//			System.out.println(users[i].getNick());
//			if(Utils.getTwitchViewers().containsKey(users[i].getNick())) {
//				System.out.print("User " + users[i].getNick() + " ignored");
//			} else {
//				Utils.getTwitchViewers().put(users[i].getNick(), new TwitchViewer(users[i].getNick()));
//				System.out.println("User " + users[i].getNick() + " added");
//				//TODO might be better to put this somewhere else
//				Utils.writeUserFile("users.txt");
//			}
//		}
//	}
	public void onJoin(String channel, String sender, String login, String hostname) {
		System.out.println("Getting users list");
		System.out.println(channel);
			if(Utils.getTwitchViewers().containsKey(sender)) {
				System.out.print("User " + sender + " ignored");
			} else {
				Utils.getTwitchViewers().put(sender, new TwitchViewer(sender));
				System.out.println("User " + sender + " added");
				//TODO might be better to put this somewhere else
				Utils.writeUserFile("users.txt");
			}
	}
}
