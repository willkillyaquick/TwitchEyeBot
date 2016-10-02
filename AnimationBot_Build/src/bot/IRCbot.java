package bot;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONArray;

import fileUtil.FileJSONPraser;
import urlUtil.TwitchImageJSON;

public class IRCbot extends Thread {
	VarKeeper vars = new VarKeeper();
 
	public void run() {
		System.out.println("Loggin into IRC Channel");
		try {
			irc();
		} catch (Exception e) {
			System.out.println("Failed to login to Twitch IRC");
			e.printStackTrace();
		}
	}

	public static void irc() throws Exception {
		//Logging In
		String HOST = VarKeeper.getConfigs().getJsonFile().getJSONArray("irc").getJSONObject(0).getString("url");
		//String HOST = "irc.twitch.tv";
		String NICK = VarKeeper.getConfigs().getJsonFile().getJSONArray("irc").getJSONObject(0).getString("NICK");
		String PASS = VarKeeper.getConfigs().getJsonFile().getJSONArray("irc").getJSONObject(0).getString("PASS");
		String CHANNEL = VarKeeper.getConfigs().getJsonFile().getJSONArray("irc").getJSONObject(0).getString("CHANNEL");
		Socket socket = new Socket(HOST, VarKeeper.getConfigs().getJsonFile().getJSONArray("irc").getJSONObject(0).getInt("SOCKET"));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer.write(String.format("PASS %s\r\n", PASS));
		System.out.println("Sending Password");
		writer.write(String.format("NICK %s\r\n", NICK));
		System.out.println(String.format("Logging in %s\n", NICK));
		writer.flush();
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			if (line.indexOf("004") >= 0) {
				// We are now logged in.
				System.out.println("Logged in");
				break;
			} else if (line.indexOf("433") >= 0) {
				System.out.println("Nickname is already in use.");
				return;
			} else if (line.indexOf("Error logging in") >= 0) {
				System.out.println("Problem with login.");
				return;
			}
		}
		// Join the channel.
		writer.write(String.format("JOIN %s\r\n", CHANNEL));
		System.out.println(String.format("Joining Channel %s\n", CHANNEL));
		writer.flush();
		
		VarKeeper.getfJSON().getJsonFile().get("emotions");

		while ((line = reader.readLine()) != null) {
			if (line.toLowerCase().startsWith("ping ")) {
				writer.write("PONG " + line.substring(5) + "\r\n");
				System.out.println("I Was Pinged\n");
				writer.flush();
			} else {
				//System.out.println(fJSON.getJsonFile().getJSONArray("emotions").toString());
				JSONArray z = VarKeeper.getfJSON().getJsonFile().getJSONArray("emotions");
				for (int x = 0; x < z.length(); x++){
					if (Pattern.compile("\\W" + z.getJSONObject(x).getString("code") + "(\\s|$)").matcher(line).find()){
						System.out.println("Yes");
						VarKeeper.setId(x);
						VarKeeper.setProcessThis(z.getJSONObject(x).getString("code").toString());
						VarKeeper.setWaitingProcess(true);
						break;
					}
				}
				if (!VarKeeper.isWaitingProcess()) {
					z = VarKeeper.gettJSON().getJson().getJSONArray("emoticons");
					for (int x = 0; x < z.length(); x++) {
						if (Pattern.compile("\\W" + z.getJSONObject(x).getString("code").toString() + "(\\s|$)")
								.matcher(line).find()) {
							VarKeeper.setProcessKey(z.getJSONObject(x).get("id").toString());
							VarKeeper.setWaitingProcess(true);
							VarKeeper.setProcessRoot(z.getJSONObject(x).get("code").toString());
							break;
						}
					}
				}

				if (Pattern.compile(">commands(\\s|$)").matcher(line).find()) {
					writer.write("PRIVMSG " + CHANNEL
							+ " :Commands that can be used with > .. haliDance, haliDanceLarge, pow, heart, shotsFired\r\n");
					System.out.println("Message incomming \r\n");
					writer.flush();
				} else {
					System.out.println(line);
					writer.flush();
				}
			}
		}
	}
}
