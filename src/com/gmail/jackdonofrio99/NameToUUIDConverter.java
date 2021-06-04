package com.gmail.jackdonofrio99;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class NameToUUIDConverter {

	private String name;

	public NameToUUIDConverter(String name) {
		this.name = name;
	}

	public String getUUID() throws IOException, IOException {
		String mojangURL = "https://api.mojang.com/users/profiles/minecraft/" + name;
		URLConnection connection = new URL(mojangURL).openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String rawLine = in.readLine();
		in.close();
		String uuid = rawLine.substring(rawLine.indexOf("id") + 5, rawLine.length() - 2);
		return insertDashUUID(uuid);
	}

	/*
	 * Adds dashes to make Mojang raw player UUIDs compliant with standard format
	 * UUID as used in spigot API.
	 * 
	 * credit to user Quackster on bukkit.org 
	 * src: https://bukkit.org/threads/java-adding-dashes-back-to-minecrafts-uuids.272746/
	 */
	public static String insertDashUUID(String uuid) {
		StringBuffer sb = new StringBuffer(uuid);
		sb.insert(8, "-");

		sb = new StringBuffer(sb.toString());
		sb.insert(13, "-");

		sb = new StringBuffer(sb.toString());
		sb.insert(18, "-");

		sb = new StringBuffer(sb.toString());
		sb.insert(23, "-");

		return sb.toString();
	}
}
