package bot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import components.Currency;
import components.CurrencyManager;
import components.TwitchViewer;

public class Utils {

	public static ArrayList<Command> Commands = new ArrayList<Command>();
	public static HashMap<String ,TwitchViewer> twitchViewers = new HashMap<String, TwitchViewer>();
	public static CurrencyManager currencyManager;

	public static void readUsersFile(String fileName) {
		System.out.println("Loading users");
		// This will reference one line at a time
		String line = null;
		String name = null;
		HashMap<String, Currency> currency = new HashMap<String, Currency>();
		HashMap<String, Currency> onHold = new HashMap<String, Currency>();
		int tickets = 0;
		String followDate = null;
		String viewerDate = null;
		int hours = 0;
		boolean isFollowing = false;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("src\\BotData\\" + fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				if(line.startsWith("n")) {
					String[] split = line.split("\\s+");
					name = split[1].toString();
				}
				if(line.startsWith("c")) {
					String[] split = line.split("\\s+");
					currency.put(split[1], new Currency(split[1], Integer.parseInt(split[2])));
				}
				if(line.startsWith("oh")) {
					String[] split = line.split("\\s+");
					onHold.put(split[1], new Currency(split[1], Integer.parseInt(split[2])));
				}
				if(line.startsWith("t")) {
					String[] split = line.split("\\s+");
					tickets = Integer.parseInt(split[1]);
				}
				if(line.startsWith("f")) {
					if(line.contains("[")){
					followDate = line.substring(line.indexOf("["), line.indexOf("]") + 1);
					} else {
						followDate = "Not following";
					}
				}
				if(line.startsWith("v")) {
					viewerDate = line.substring(line.indexOf("["), line.indexOf("]") + 1);
				}
				if(line.startsWith("h")) {
					String[] split = line.split("\\s+");
					hours = Integer.parseInt(split[1]);
				}
				if(line.startsWith("b")) {
					String[] split = line.split("\\s+");
					if(split[1].equals("Not following")) {
							isFollowing = false;
					} else {
						isFollowing = true;
					}
				}
				if(line.trim().equals("") || line.trim().equals("\n")) {
					twitchViewers.put(name, new TwitchViewer(name, currency, onHold, tickets, followDate, viewerDate, hours, isFollowing));
					System.out.println(name + " added to list");
					currency.clear();
					onHold.clear();
				}
//				String[] split = line.split("\\s+");
//				Command command = new Command(split[0], split[1]);
//				Commands.add(command);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + "src\\BotData\\"
					+ fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + "src\\BotData\\"
					+ fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	public static void writeUserFile(String fileName) {
		//TODO
		File inputFile = new File("src\\BotData\\" + fileName);
		File tempFile = new File("src\\BotData\\tempFile");
		BufferedWriter writer = null;
		try {

			writer = new BufferedWriter(new FileWriter(tempFile, true));
//			writer.write(command);
			for (Map.Entry<String, TwitchViewer> entry : twitchViewers.entrySet()) {
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			    writer.write("n " + getTwitchViewers().get(entry.getKey()).getViewerName());
			    writer.write(System.getProperty("line.separator"));
			    for (Map.Entry<String, Currency> currencies : getTwitchViewers().get(entry.getKey()).getViewerPoints().entrySet()) {
			    	writer.write("c " + getTwitchViewers().get(entry.getKey()).getViewerPoints().get(currencies.getKey()).getName() + " " + getTwitchViewers().get(entry.getKey()).getViewerPoints().get(currencies.getKey()).getAmount());
				    writer.write(System.getProperty("line.separator"));
			    }
			    for (Map.Entry<String, Currency> currencies : getTwitchViewers().get(entry.getKey()).getPointsOnHold().entrySet()) {
			    	writer.write("oh " + getTwitchViewers().get(entry.getKey()).getPointsOnHold().get(currencies.getKey()).getName() + " " + getTwitchViewers().get(entry.getKey()).getPointsOnHold().get(currencies.getKey()).getAmount());
				    writer.write(System.getProperty("line.separator"));
			    }
			    writer.write("t " + getTwitchViewers().get(entry.getKey()).getViewerTickets());
			    writer.write(System.getProperty("line.separator"));
			    writer.write("f " + getTwitchViewers().get(entry.getKey()).getFollowDate());
			    writer.write(System.getProperty("line.separator"));
			    writer.write("v " + getTwitchViewers().get(entry.getKey()).getViewerDate());
			    writer.write(System.getProperty("line.separator"));
			    writer.write("h " + getTwitchViewers().get(entry.getKey()).getHoursInStream());
			    writer.write(System.getProperty("line.separator"));

			    if(getTwitchViewers().get(entry.getKey()).isFollowing()) {
			    	writer.write("b Following");
				    writer.write(System.getProperty("line.separator"));
				    writer.write(System.getProperty("line.separator"));
			    }
			    if(!getTwitchViewers().get(entry.getKey()).isFollowing()) {
			    	writer.write("b Not following");
				    writer.write(System.getProperty("line.separator"));
				    writer.write(System.getProperty("line.separator"));
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}

		inputFile.delete();
		boolean successful = tempFile.renameTo(inputFile);
		System.out.println(successful);
	}

	public static void readCommandFile(String fileName) {

		// This will reference one line at a time
		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("src\\BotData\\" + fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				String[] split = line.split("\\s+");
				Command command = new Command(split[0], split[1]);
				Commands.add(command);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + "src\\BotData\\"
					+ fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + "src\\BotData\\"
					+ fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	public static void writeCommandFile(String fileName, String command) {
		BufferedWriter writer = null;
		try {
			// create a temporary file
			File logFile = new File("src\\BotData\\" + fileName);

			// This will output the full path where the file will be written
			// to...
			System.out.println(logFile.getCanonicalPath());

			writer = new BufferedWriter(new FileWriter(logFile, true));
			writer.write(command);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	public static void deleteCommandFile(String fileName, String command)
			throws IOException {
		File inputFile = new File("src\\BotData\\" + fileName);
		File tempFile = new File("src\\BotData\\tempFile");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String currentLine;

		while ((currentLine = reader.readLine()) != null) {
			if (null != currentLine && !currentLine.startsWith(command)) {
				writer
						.write(currentLine
								+ System.getProperty("line.separator"));
			}
		}
		writer.close();
		reader.close();
		inputFile.delete();
		boolean successful = tempFile.renameTo(inputFile);
		System.out.println(successful);
	}

	public static void editCommandFile(String fileName, String command,
			String newResponse) throws IOException {
		deleteCommandFile(fileName, command);
		writeCommandFile(fileName, command + " " + newResponse);
	}

	public static void readSettingsFile(String fileName){
		// This will reference one line at a time
		String line = null;
		currencyManager = new CurrencyManager();
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("src\\BotData\\" + fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				String[] split = line.split("\\s+");
//				Command command = new Command(split[0], split[1]);
//				Commands.add(command);
				if(split[0].equals("c")){
					currencyManager.addCurrency(split[1]);
				}
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + "src\\BotData\\"
					+ fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + "src\\BotData\\"
					+ fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	public static ArrayList<Command> getCommands() {
		return Commands;
	}

	public static HashMap<String, TwitchViewer> getTwitchViewers() {
		return twitchViewers;
	}

	public static CurrencyManager getCurrencyManager() {
		return currencyManager;
	}

	public static void setCurrencyManager(CurrencyManager currencyManager) {
		Utils.currencyManager = currencyManager;
	}
}
